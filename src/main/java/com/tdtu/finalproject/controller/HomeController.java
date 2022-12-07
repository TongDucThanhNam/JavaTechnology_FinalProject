package com.tdtu.finalproject.controller;

//import com.tdtu.finalproject.DatSanRespository;

import com.tdtu.finalproject.UserRepository;
import com.tdtu.finalproject.model.DatSan;
import com.tdtu.finalproject.model.EmailSenderService;
import com.tdtu.finalproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

//    @Autowired
//    DatSanRespository datSanRespository;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        System.out.println("Trang chủ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername(auth.getName());
        if (user != null) {
            model.addAttribute("user", user);
            System.out.println("Đã đăng nhập");
        }
        return "index";
    }

    @RequestMapping("/login")
    public String viewLoginPage(Model model) {
        return "login";
    }

    @RequestMapping("/logout")
    public String viewLogoutPage(Model model) {
        return "logout";
    }


    @RequestMapping("/register")
    public String viewRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "redirect:/login";
    }

    @RequestMapping("/thongtincanhan")
    public String thongTinCaNhan(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername(auth.getName());
        if (user != null) {
            model.addAttribute("user", user);
            System.out.println("Đã đăng nhập");
        }
        return "thongtincanhan";
    }

    @PostMapping("/thongtincanhan")
    public String thongTinCaNhan(@RequestParam String username, @RequestParam String email, @RequestParam String phone, Model model) {
        //Update user info
        User current_user = userRepository.getUserByUsername(username);
        current_user.setUsername(username);
        current_user.setEmail(email);
        current_user.setPhone(phone);
        userRepository.save(current_user);
        return "redirect:/";
    }

    @RequestMapping("/lichsudatsan")
    public String viewLichSuDatSanPage(Model model) {
        return "lichsudatsan";
    }

    @RequestMapping("/trangdatsan")
    public String viewTrangDatSanPage(Model model) {
        return "trangdatsan";
    }

    @PostMapping("/trangdatsan")
    public String trangDatSan(@RequestParam String NguoiDatSan, @RequestParam int SoDienThoai, @RequestParam String ChonNgay, @RequestParam String ChonGio, Model model) {
        //Update user info
        DatSan datSan = new DatSan();
        datSan.setNguoiDatSan(NguoiDatSan);
        datSan.setSoDienThoai(SoDienThoai);
        datSan.setNgayDatSan(ChonNgay);
        datSan.setGioDatSan(ChonGio);

//        datSanRespository.save(datSan);
        return "redirect:/";
    }

    //send email
    @PostMapping("/sendEmail")
    public String sendEmailDangKyNhanThongTin(@RequestParam String emailDangKy, Model model) {
        System.out.println("Sending email");
        String subject = "Cảm ơn bạn đã đăng ký nhận thông tin về sân bóng";
        String message = "Ngay khi có thông tin mới về sân bóng, chúng tôi sẽ thông báo cho bạn trong thời gian sớm nhất";
        emailSenderService.sendEmail(emailDangKy, subject, message);
        return "redirect:/";
    }

//    @RequestMapping("/quanlytaikhoan")
//    public String quanLyTaiKhoan(Model model) {
//        List<User> userList = (List<User>) userRepository.findAll();
//        model.addAttribute("userList", userList);
//
//        return "quanlytaikhoan";
//    }

}
