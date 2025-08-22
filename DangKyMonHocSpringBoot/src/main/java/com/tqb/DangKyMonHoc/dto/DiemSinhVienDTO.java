/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.dto;

import java.math.BigDecimal;

/**
 *
 * @author toquocbinh2102
 */
public class DiemSinhVienDTO {
    
    private String hocKy;
    private String namHoc;
    private String tenMonHoc;
    private String maLop;
    private int tinChiLyThuyet;
    private int tinChiThucHanh;
    private BigDecimal diemGiuaKy;
    private BigDecimal diemCuoiKy;
    private BigDecimal diemTongKet;
    private String trangThai;

    public DiemSinhVienDTO(String hocKy, String namHoc, String tenMonHoc, String maLop, 
            int tinChiLyThuyet, int tinChiThucHanh, BigDecimal diemGiuaKy, 
            BigDecimal diemCuoiKy, BigDecimal diemTongKet, String trangThai) {
        this.hocKy = hocKy;
        this.namHoc = namHoc;
        this.tenMonHoc = tenMonHoc;
        this.maLop = maLop;
        this.tinChiLyThuyet = tinChiLyThuyet;
        this.tinChiThucHanh = tinChiThucHanh;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiKy;
        this.diemTongKet = diemTongKet;
        this.trangThai = trangThai;
    }

    /**
     * @return the hocKy
     */
    public String getHocKy() {
        return hocKy;
    }

    /**
     * @param hocKy the hocKy to set
     */
    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    /**
     * @return the namHoc
     */
    public String getNamHoc() {
        return namHoc;
    }

    /**
     * @param namHoc the namHoc to set
     */
    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    /**
     * @return the tenMonHoc
     */
    public String getTenMonHoc() {
        return tenMonHoc;
    }

    /**
     * @param tenMonHoc the tenMonHoc to set
     */
    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
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
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    /**
     * @return the tinChiLyThuyet
     */
    public int getTinChiLyThuyet() {
        return tinChiLyThuyet;
    }

    /**
     * @param tinChiLyThuyet the tinChiLyThuyet to set
     */
    public void setTinChiLyThuyet(int tinChiLyThuyet) {
        this.tinChiLyThuyet = tinChiLyThuyet;
    }

    /**
     * @return the tinChiThucHanh
     */
    public int getTinChiThucHanh() {
        return tinChiThucHanh;
    }

    /**
     * @param tinChiThucHanh the tinChiThucHanh to set
     */
    public void setTinChiThucHanh(int tinChiThucHanh) {
        this.tinChiThucHanh = tinChiThucHanh;
    }

    /**
     * @return the diemGiuaKy
     */
    public BigDecimal getDiemGiuaKy() {
        return diemGiuaKy;
    }

    /**
     * @param diemGiuaKy the diemGiuaKy to set
     */
    public void setDiemGiuaKy(BigDecimal diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    /**
     * @return the diemCuoiKy
     */
    public BigDecimal getDiemCuoiKy() {
        return diemCuoiKy;
    }

    /**
     * @param diemCuoiKy the diemCuoiKy to set
     */
    public void setDiemCuoiKy(BigDecimal diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    /**
     * @return the diemTongKet
     */
    public BigDecimal getDiemTongKet() {
        return diemTongKet;
    }

    /**
     * @param diemTongKet the diemTongKet to set
     */
    public void setDiemTongKet(BigDecimal diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
