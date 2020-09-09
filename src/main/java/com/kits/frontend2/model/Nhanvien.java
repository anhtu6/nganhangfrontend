package com.kits.frontend2.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Nhanvien {

    private String manhanvien;

    private Long chungminhthu;

    private String tennhanvien;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaysinh;
    private Integer bacnghe;
    private Integer thamnien;

    public Nhanvien() {
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public Long getChungminhthu() {
        return chungminhthu;
    }

    public void setChungminhthu(Long chungminhthu) {
        this.chungminhthu = chungminhthu;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public Integer getBacnghe() {
        return bacnghe;
    }

    public void setBacnghe(Integer bacnghe) {
        this.bacnghe = bacnghe;
    }

    public Integer getThamnien() {
        return thamnien;
    }

    public void setThamnien(Integer thamnien) {
        this.thamnien = thamnien;
    }
}

