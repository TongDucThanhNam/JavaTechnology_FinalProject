package com.tdtu.finalproject.model;

import javax.persistence.*;

@Entity
public class San {
    @Column(name = "masanbong")
    private int maSanBong;

    @Column(name = "tensanbong")
    private String tenSanBong;

    @Column(name = "loaisan")
    private String loaiSan;

    @Column(name = "giathue")
    private int giaThue;

    public San(int maSanBong, String tenSanBong, String loaiSan, int giaThue) {
        this.maSanBong = maSanBong;
        this.tenSanBong = tenSanBong;
        this.loaiSan = loaiSan;
        this.giaThue = giaThue;
    }

    public San() {

    }

    public void setMaSanBong(Integer maSanBong) {
        this.maSanBong = maSanBong;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getMaSanBong() {
        return maSanBong;
    }

    public String getTenSanBong() {
        return tenSanBong;
    }

    public void setTenSanBong(String tenSanBong) {
        this.tenSanBong = tenSanBong;
    }

    public String getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(String loaiSan) {
        this.loaiSan = loaiSan;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }
}
