package com.tdtu.finalproject.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DatSan {

    @Column(name = "id")
    private int id;

    @Column(name = "nguoiDatSan")
    private String nguoiDatSan;

    @Column(name = "soDienThoai")
    private int soDienThoai;

    @Column(name = "ngayDatSan")
    private String ngayDatSan;

    @Column(name = "gioDatSan")
    private String gioDatSan;

    public DatSan() {
    }

    public DatSan(int id, String nguoiDatSan, int soDienThoai, String ngayDatSan, String gioDatSan) {
        this.id = id;
        this.nguoiDatSan = nguoiDatSan;
        soDienThoai = soDienThoai;
        this.ngayDatSan = ngayDatSan;
        this.gioDatSan = gioDatSan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNguoiDatSan() {
        return nguoiDatSan;
    }

    public void setNguoiDatSan(String nguoiDatSan) {
        this.nguoiDatSan = nguoiDatSan;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        soDienThoai = soDienThoai;
    }

    public String getNgayDatSan() {
        return ngayDatSan;
    }

    public void setNgayDatSan(String ngayDatSan) {
        this.ngayDatSan = ngayDatSan;
    }

    public String getGioDatSan() {
        return gioDatSan;
    }

    public void setGioDatSan(String gioDatSan) {
        this.gioDatSan = gioDatSan;
    }
}
