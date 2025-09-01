/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.dto;

/**
 *
 * @author toquocbinh2102
 */
public class ThongKeTheoLopDTO {

    private int buoiHocId;
    private String maLop;
    private Long tongSinhVien;
    private Long soHoanThanh;
    private Long soTruot;

    public ThongKeTheoLopDTO(int buoiHocId, String maLop, Number tongSinhVien, Number soHoanThanh, Number soTruot) {
        this.buoiHocId = buoiHocId;
        this.maLop = maLop;
        this.tongSinhVien = tongSinhVien.longValue();
        this.soHoanThanh = soHoanThanh.longValue();
        this.soTruot = soTruot.longValue();
    }

    /**
     * @return the buoiHocId
     */
    public int getBuoiHocId() {
        return buoiHocId;
    }

    /**
     * @param buoiHocId the buoiHocId to set
     */
    public void setBuoiHocId(int buoiHocId) {
        this.buoiHocId = buoiHocId;
    }

    /**
     * @return the maLop
     */
    public String getMaLop() {
        return maLop;
    }

    /**
     * @param maLop the maLop to set
     */
    public void setLopId(String maLop) {
        this.maLop = maLop;
    }

    /**
     * @return the tongSinhVien
     */
    public Long getTongSinhVien() {
        return tongSinhVien;
    }

    /**
     * @param tongSinhVien the tongSinhVien to set
     */
    public void setTongSinhVien(Long tongSinhVien) {
        this.tongSinhVien = tongSinhVien;
    }

    /**
     * @return the soHoanThanh
     */
    public Long getSoHoanThanh() {
        return soHoanThanh;
    }

    /**
     * @param soHoanThanh the soHoanThanh to set
     */
    public void setSoHoanThanh(Long soHoanThanh) {
        this.soHoanThanh = soHoanThanh;
    }

    /**
     * @return the soTruot
     */
    public Long getSoTruot() {
        return soTruot;
    }

    /**
     * @param soTruot the soTruot to set
     */
    public void setSoTruot(Long soTruot) {
        this.soTruot = soTruot;
    }

}
