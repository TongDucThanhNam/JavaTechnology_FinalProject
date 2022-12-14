package com.tdtu.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "datsan")
public class DatSan {
    @Column(name = "madatsan")
    private int maDatSan;

    @Column(name = "masanbong")
    private int maSanBong;

    @Column(name = "tenkhachhang")
    private String tenKhachHang;

    @Column(name = "sodienthoaikhachhang")
    private String soDienThoaiKhachHang;

    @Column(name = "ngaydatsan")
    private String ngayDatSan;

    @Column(name = "giobatdau")
    private String gioBatDau;

    @Column(name = "gioketthuc")
    private String gioKetThuc;

    public DatSan() {
    }

    public DatSan(int maDatSan, int maSanBong, String tenKhachHang, String soDienThoaiKhachHang, String ngayDatSan, String gioBatDau, String gioKetThuc) {
        this.maDatSan = maDatSan;
        this.maSanBong = maSanBong;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
        this.ngayDatSan = ngayDatSan;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMaDatSan() {
        return maDatSan;
    }

    public void setMaDatSan(int maDatSan) {
        this.maDatSan = maDatSan;
    }

    public int getMaSanBong() {
        return maSanBong;
    }

    public void setMaSanBong(int maSanBong) {
        this.maSanBong = maSanBong;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoaiKhachHang() {
        return soDienThoaiKhachHang;
    }

    public void setSoDienThoaiKhachHang(String soDienThoaiKhachHang) {
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
    }

    public String getNgayDatSan() {
        return ngayDatSan;
    }

    public void setNgayDatSan(String ngayDatSan) {
        this.ngayDatSan = ngayDatSan;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

//    public void setMaDatSan(Integer maDatSan) {
//        this.maDatSan = maDatSan;
//    }
//
//    public Integer getMaDatSan() {
//        return maDatSan;
//    }
}
