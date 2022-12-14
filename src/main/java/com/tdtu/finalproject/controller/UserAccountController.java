package com.tdtu.finalproject.controller;

import com.tdtu.finalproject.repository.RoleRepository;
import com.tdtu.finalproject.repository.UserRepository;
import com.tdtu.finalproject.model.Role;
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
public class UserAccountController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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

    //Route "quanlytaikhoan": Trang quản lý tài khoản, method GET
    @RequestMapping("/quanlytaikhoan")
    public String quanLyTaiKhoan(Model model) {
        //Select tất cả User trong bảng user -> lưu vào List
        List<User> userList = (List<User>) userRepository.findAll();
        //Truyền List vào model gửi tới View
        model.addAttribute("userList", userList);
        return "quanlytaikhoan";
    }

    //Route "/quanlytaikhoan/delete/{id}": xoá tai khoản
    @RequestMapping(value = "/quanlytaikhoan/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        System.out.println("Xóa tài khoản");
        //Xoá khỏi bảng user với dòng có id = id
        userRepository.deleteById(id);
        return "redirect:/quanlytaikhoan";
    }

    //Route "/quanlytaikhoan/edit/{id}": cập nhật tài khoản, method GET
    @RequestMapping(value = "/quanlytaikhoan/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        System.out.println("thay doi thong tin tai khoan");

        //Lấy dòng trong bảng user có id = id
        User user = userRepository.findById(id).get();
        //gán vào model -> gửi đến View
        model.addAttribute("user", user);

        //List Role
        List<Role> roleList = (List<Role>) roleRepository.findAll();
        model.addAttribute("roleList", roleList);

        return "update_tai_khoan";
    }

    //Route "/quanlytaikhoan/edit/{id}": cập nhật tài khoản, method POST
    @RequestMapping(value = "/quanlytaikhoan/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @RequestParam String username, @RequestParam String email, @RequestParam String phone, @RequestParam int roleId, Model model) {
        //Cập nhật thông tin tài khoản
        User user1 = userRepository.findById(id).get();
        user1.setUsername(username);
        user1.setEmail(email);
        user1.setPhone(phone);
        user1.setRoleId(roleId);
        userRepository.save(user1);
        return "redirect:/quanlytaikhoan";
    }

    //Route "/quanlytaikhoan/themtaikhoan": trang thêm mới tài khoản, method GET
    @RequestMapping(value = "/quanlytaikhoan/themtaikhoan", method = RequestMethod.GET)
    public String themTaiKhoan(Model model) {
        //Gán vào model đối tượng User -> gửi đến View
        model.addAttribute("user", new User());
        return "themtaikhoan";
    }

    //Route "/quanlytaikhoan/themtaikhoan": trang thêm mới tài khoản, method POST
    @RequestMapping(value = "/quanlytaikhoan/themtaikhoan", method = RequestMethod.POST)
    public String themTaiKhoan(User user) {
        System.out.println("Thêm tài khoản");
        //Thay đổi mật khẩu dạng text sang Mã hoá Bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Lưu user đã tạo và đã mã hoá mật khẩu vào bảng user
        userRepository.save(user);
        return "redirect:/quanlytaikhoan";
    }
}
