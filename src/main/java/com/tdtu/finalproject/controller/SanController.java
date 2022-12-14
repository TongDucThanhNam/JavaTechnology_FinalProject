package com.tdtu.finalproject.controller;

import com.tdtu.finalproject.model.San;
import com.tdtu.finalproject.repository.SanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SanController {

    @Autowired
    private SanRepository sanRepository;

    //	Trang quản lý sân bóng
    @RequestMapping("/quanlysanbong")
    public String quanLySanBong(Model model) {

        List<San> sanList = (List<San>) sanRepository.findAll();
        model.addAttribute("sanList", sanList);
        return "quanlysanbong";
    }

    //	Link tới Trang thêm mới sân bóng
    @RequestMapping(value = "/quanlysanbong/themsan", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("san", new San());
        return "themsan";
    }

    //	Lưu thông tin sân đã thêm mới
    @RequestMapping(value = "/quanlysanbong/themsan", method = RequestMethod.POST)
    public String add(San san) {
        System.out.println("Thêm sân mới");
        sanRepository.save(san);
        return "redirect:/quanlysanbong";
    }

    //	Link tới Trang update sân bóng
    @RequestMapping(value = "/quanlysanbong/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        System.out.println("thay doi thong tin san bong");

        //Lấy dòng trong bảng san có id = id
        San san = sanRepository.findById(id).get();
        //gán vào model -> gửi đến View
        model.addAttribute("san", san);
        return "update_san";
    }

    //	Lưu thông tin sân đã update
    @RequestMapping(value = "/quanlysanbong/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @RequestParam String tenSanBong, @RequestParam String loaiSan, @RequestParam int giaThue, Model model) {
        //Cập nhật thông tin sân bóng
        San san1 = sanRepository.findById(id).get();
        san1.setTenSanBong(tenSanBong);
        san1.setLoaiSan(loaiSan);
        san1.setGiaThue(giaThue);
        sanRepository.save(san1);
        return "redirect:/quanlysanbong";
    }

    //	Xóa Sân bóng
    @RequestMapping(value = "/quanlysanbong/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        System.out.println("Xóa sân bóng");

        sanRepository.deleteById(id);
        return "redirect:/quanlysanbong";
    }
}
