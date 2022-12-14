package com.tdtu.finalproject.controller;

//import com.tdtu.finalproject.repository.DatSanRespository;

import com.tdtu.finalproject.repository.DatSanRespository;
import com.tdtu.finalproject.repository.SanRepository;
import com.tdtu.finalproject.repository.UserRepository;
import com.tdtu.finalproject.model.DatSan;
import com.tdtu.finalproject.model.EmailSenderService;
import com.tdtu.finalproject.model.San;
import com.tdtu.finalproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("user")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    DatSanRespository datSanRespository;

    @Autowired
    SanRepository sanRepository;

    //Session lưu thông tin đăng nhập
    @ModelAttribute("auth")
    public User layThongTinDangNhap() {
        //Lấy thông tin đăng nhập:
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Tìm thông tin đăng nhập trong bảng User bằng username -> Trả về đối tượng User
        User user = userRepository.getUserByUsername(auth.getName());
        //Trả về user nều không null, nếu null thì tạo mới user
        return user;
    }

    //Route "/" :Trang chủ , method Get
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomePage(Model model, @ModelAttribute("auth") User auth) {
        System.out.println("Trang chủ");
        if (auth != null) {
            model.addAttribute("auth", auth);
        }
        return "index";
    }

    //Route "login": Trang đăng nhập của spring security
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    //route "logout": Trang đăng xuất của spring security
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "logout";
    }


    //Route "register": Trang đăng ký của spring security
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        //Thêm một đối tượng User để truyền vào model -> gửi đến View.
        model.addAttribute("user", new User());
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user) {
        //Mã hoá mật khẩu
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Thêm đối tượng vào bảng user
        userRepository.save(user);
        //Chuyển hướng về trang login
        return "redirect:/login";
    }

    //Route "thongtincanhan": trang chỉnh sửa thông tin cá nhân, method GET
    @RequestMapping(value = "/thongtincanhan", method = RequestMethod.GET)
    public String thongTinCaNhan(Model model, @ModelAttribute("auth") User auth) {

        if (auth != null) {
            model.addAttribute("auth", auth);
        }
        return "thongtincanhan";
    }


    //Route "thongtincanhan": trang chỉnh sửa thông tin cá nhân, method POST
    @RequestMapping(value = "/thongtincanhan", method = RequestMethod.POST)
    public String thongTinCaNhan(@RequestParam String username, @RequestParam String email, @RequestParam String phone, Model model) {
        //Cập nhật thông tin của User hiện tại.
        User current_user = userRepository.getUserByUsername(username);
        current_user.setUsername(username);
        current_user.setEmail(email);
        current_user.setPhone(phone);

        //Update user này trong bảng user.
        userRepository.save(current_user);

        //Trả về trang chủ
        return "redirect:/";
    }

    //Route "lichsudatsan": Trang lịch sử đặt sân, method GET
    @RequestMapping(value = "/lichsudatsan", method = RequestMethod.GET)
    public String trangLichSuDatSan(Model model) {
        List<DatSan> datSanList = (List<DatSan>) datSanRespository.findAll();
//        model.addAttribute("san", new San());

        model.addAttribute("datSanList", datSanList);
        return "lichsudatsan";
    }

    //Route "trangdatsan": Trang đặt sân, method GET
    @RequestMapping(value = "/trangdatsan", method = RequestMethod.GET)
    public String trangDatSan(Model model) {
        //Lấy tất cả thông tin của bảng San
        List<San> sanList = (List<San>) sanRepository.findAll();
        for (San san : sanList) {
            System.out.println(san.getMaSanBong());
        }
        model.addAttribute("datSan", new DatSan());
        model.addAttribute("sanList", sanList);

        return "trangdatsan";
    }

    //Route "trangdatsan": Trang đặt sân, method = POST
    @RequestMapping(value = "/trangdatsan", method = RequestMethod.POST)
    public String trangDatSan(Model model, DatSan datSan) {
        //Tạo một đối tượng đặt sân vao bảng datsan
        model.addAttribute("datSan", new DatSan());

        datSanRespository.save(datSan);

        return "redirect:/lichsudatsan";
    }

    //Route "sendEmail": Là trang sẽ gửi email sau khi người dùng bấm đăng ký nhận thông tin.
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public String sendEmailDangKyNhanThongTin(@RequestParam String emailDangKy, Model model) {
        System.out.println("Sending email");
        String subject = "Cảm ơn bạn đã đăng ký nhận thông tin về sân bóng";
        String message = "Ngay khi có thông tin mới về sân bóng, chúng tôi sẽ thông báo cho bạn trong thời gian sớm nhất";
        emailSenderService.sendEmail(emailDangKy, subject, message);
        return "redirect:/";
    }

    //Rout "banggia": trang xem các sân bóng và giá, method GET
    @RequestMapping(value = "/banggia", method = RequestMethod.GET)
    public String bangGia(Model model) {
        //Select San có trong bảng San
        List<San> sanList = (List<San>) sanRepository.findAll();
        model.addAttribute("sanList", sanList);
        return "banggia";
    }

    //Route "quanmatkhau": quên mật khẩu, method GET

    //route "quanmatkhau": quên mật khẩu, method POST

    //route "doimatkhau": đổi mật khẩu, method GET

    //route "doimatkhau": đổi mật khẩu, method POST

}
