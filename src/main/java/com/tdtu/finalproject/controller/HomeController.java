package com.tdtu.finalproject.controller;

//import com.tdtu.finalproject.DatSanRespository;

import com.tdtu.finalproject.DatSanRespository;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    DatSanRespository datSanRespository;

    //Route "/" :Trang chủ , method Get
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomePage(Model model) {
        System.out.println("Trang chủ");
        //Lấy thông tin đăng nhập:
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Tìm thông tin đăng nhập trong bảng User bằng username -> Trả về đối tượng User
        User user = userRepository.getUserByUsername(auth.getName());
        if (user != null) {
            //Thêm đối tượng User vào model -> truyền đến View hay thymeleaf template
            model.addAttribute("user", user);
            System.out.println("Đã đăng nhập");
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
    public String thongTinCaNhan(Model model) {
        //Lấy thông tin user sau khi đã đăng nhập
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername(auth.getName());
        if (user != null) {
            //Nếu tìm thấy thì truyền vào View
            model.addAttribute("user", user);
            System.out.println("Đã đăng nhập");
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
        model.addAttribute("datSanList", datSanList);
        return "lichsudatsan";
    }

    //Route "trangdatsan": Trang đặt sân, method GET
    @RequestMapping("/trangdatsan")
    public String trangDatSan(Model model) {
        return "trangdatsan";
    }

    //Route "trangdatsan": Trang đặt sân, method = POST
    @RequestMapping(value = "/trangdatsan", method = RequestMethod.POST)
    public String trangDatSan(@RequestParam String NguoiDatSan, @RequestParam int SoDienThoai, @RequestParam String ChonNgay, @RequestParam String ChonGio, Model model) {
        //Tạo một đối tượng đặt sân vao bảng datsan
        DatSan datSan = new DatSan();
        datSan.setNguoiDatSan(NguoiDatSan);
        datSan.setSoDienThoai(SoDienThoai);
        datSan.setNgayDatSan(ChonNgay);
        datSan.setGioDatSan(ChonGio);
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
}
