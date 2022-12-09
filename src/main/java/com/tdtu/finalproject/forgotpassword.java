import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Entity
public class PasswordResetToken {
 
    private static final int EXPIRATION = 60 * 24;
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String token;
 
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
 
    private Date expiryDate;

    @PostMapping("/user/resetPassword")
    public GenericResponse resetPassword(HttpServletRequest request, 
    @RequestParam("email") String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request), 
        request.getLocale(), token, user));
        return new GenericResponse(
        messages.getMessage("message.resetPasswordEmail", null, 
        request.getLocale()));
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    private SimpleMailMessage constructResetTokenEmail(
        String contextPath, Locale locale, String token, User user) {
            String url = contextPath + "/user/changePassword?token=" + token;
            String message = messages.getMessage("message.resetPassword", 
            null, locale);
            return constructEmail("Reset Password", message + " \r\n" + url, user);
        }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model, 
        @RequestParam("token") String token) {
            String result = securityService.validatePasswordResetToken(token);
            if(result != null) {
                String message = messages.getMessage("auth.message." + result, null, locale);
                return "redirect:/login.html?lang=" 
                    + locale.getLanguage() + "&message=" + message;
            } else {
                model.addAttribute("token", token);
                return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
            }
        }
    
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
    
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }
    
    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }
    
    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    @PostMapping("/user/savePassword")
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {

        String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            return new GenericResponse(messages.getMessage(
                "auth.message." + result, null, locale));
        }

        Optional user = userService.getUserByPasswordResetToken(passwordDto.getToken());
        if(user.isPresent()) {
            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
            return new GenericResponse(messages.getMessage(
                "message.resetPasswordSuc", null, locale));
        } else {
            return new GenericResponse(messages.getMessage(
                "auth.message.invalid", null, locale));
        }
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }

    public class PasswordDto {

    private String oldPassword;

    private  String token;

    @ValidPassword
    private String newPassword;
}
}