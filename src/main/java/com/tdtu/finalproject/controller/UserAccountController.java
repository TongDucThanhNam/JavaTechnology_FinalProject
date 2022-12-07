package com.tdtu.finalproject.controller;

import com.tdtu.finalproject.UserRepository;
import com.tdtu.finalproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserAccountController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/quanlytaikhoan")
    public String quanLyTaiKhoan(Model model) {
        List<User> userList = (List<User>) userRepository.findAll();
        model.addAttribute("userList", userList);

        return "quanlytaikhoan";
    }

    @RequestMapping(value = "/quanlytaikhoan/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        System.out.println("Xóa tài khoản");
        userRepository.deleteById(id);
        return "redirect:/quanlytaikhoan";
    }

    @RequestMapping(value = "/quanlytaikhoan/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        System.out.println("thay doi thong tin tai khoan");

        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "update_tai_khoan";
    }

    @RequestMapping(value = "/quanlytaikhoan/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @RequestParam String username, @RequestParam String email, @RequestParam String phone, Model model) {
        User user1 = userRepository.findById(id).get();
        user1.setUsername(username);
        user1.setEmail(email);
        user1.setPhone(phone);
        userRepository.save(user1);
        return "redirect:/quanlytaikhoan";
    }

    @RequestMapping(value = "/quanlytaikhoan/themtaikhoan", method = RequestMethod.GET)
    public String themTaiKhoan(Model model) {
        model.addAttribute("user", new User());
        return "themtaikhoan";
    }

    @RequestMapping(value = "/quanlytaikhoan/themtaikhoan", method = RequestMethod.POST)
    public String themTaiKhoan(User user) {
        System.out.println("Thêm tài khoản");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "redirect:/quanlytaikhoan";
    }
}
