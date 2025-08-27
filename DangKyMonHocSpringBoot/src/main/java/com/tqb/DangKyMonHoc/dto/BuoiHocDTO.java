/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.dto;

import com.tqb.DangKyMonHoc.pojo.LichHoc;
import java.util.List;

/**
 *
 * @author toquocbinh2102
 */
public class BuoiHocDTO {
    private int buoiHocId;
    private String tenMon;
    private int soTinChi;
    private String lop;
    private int soLuong;
    private int conLai;
    private List<LichHoc> listLichHoc;

    public BuoiHocDTO(int buoiHocId, String tenMon, int soTinChi, String lop, Integer soLuong, Long conLai) {
        this.buoiHocId = buoiHocId;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.lop = lop;
        this.soLuong = (soLuong == null ? 0 : soLuong);
        this.conLai = (conLai == null ? 0 : conLai.intValue());
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
     * @return the tenMon
     */
    public String getTenMon() {
        return tenMon;
    }

    /**
     * @param tenMon the tenMon to set
     */
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    /**
     * @return the soTinChi
     */
    public int getSoTinChi() {
        return soTinChi;
    }

    /**
     * @param soTinChi the soTinChi to set
     */
    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    /**
     * @return the lop
     */
    public String getLop() {
        return lop;
    }

    /**
     * @param lop the lop to set
     */
    public void setLop(String lop) {
        this.lop = lop;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the conLai
     */
    public int getConLai() {
        return conLai;
    }

    /**
     * @param conLai the conLai to set
     */
    public void setConLai(int conLai) {
        this.conLai = conLai;
    }

    /**
     * @return the listLichHoc
     */
    public List<LichHoc> getListLichHoc() {
        return listLichHoc;
    }

    /**
     * @param listLichHoc the listLichHoc to set
     */
    public void setListLichHoc(List<LichHoc> listLichHoc) {
        this.listLichHoc = listLichHoc;
    }

    
    
}
