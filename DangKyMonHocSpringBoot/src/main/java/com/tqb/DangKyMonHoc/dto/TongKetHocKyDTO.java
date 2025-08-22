/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.dto;

/**
 *
 * @author toquocbinh2102
 */
public class TongKetHocKyDTO {
    private int sinhVienId;
    private String ky;
    private String namHoc;
    private Double diemTbHk;
    private int tinChiDatHk;
    private Double diemTbTichLuy;
    private int tinChiTichLuy;

    public TongKetHocKyDTO(int sinhVienId, String ky, String namHoc, Double diemTbHk, int tinChiDatHk, Double diemTbTichLuy, int tinChiTichLuy) {
        this.sinhVienId = sinhVienId;
        this.ky = ky;
        this.namHoc = namHoc;
        this.diemTbHk = diemTbHk;
        this.tinChiDatHk = tinChiDatHk;
        this.diemTbTichLuy = diemTbTichLuy;
        this.tinChiTichLuy = tinChiTichLuy;
    }

    /**
     * @return the sinhVienId
     */
    public int getSinhVienId() {
        return sinhVienId;
    }

    /**
     * @param sinhVienId the sinhVienId to set
     */
    public void setSinhVienId(int sinhVienId) {
        this.sinhVienId = sinhVienId;
    }

    /**
     * @return the ky
     */
    public String getKy() {
        return ky;
    }

    /**
     * @param ky the ky to set
     */
    public void setKy(String ky) {
        this.ky = ky;
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
     * @return the diemTbHk
     */
    public Double getDiemTbHk() {
        return diemTbHk;
    }

    /**
     * @param diemTbHk the diemTbHk to set
     */
    public void setDiemTbHk(Double diemTbHk) {
        this.diemTbHk = diemTbHk;
    }

    /**
     * @return the tinChiDatHk
     */
    public int getTinChiDatHk() {
        return tinChiDatHk;
    }

    /**
     * @param tinChiDatHk the tinChiDatHk to set
     */
    public void setTinChiDatHk(int tinChiDatHk) {
        this.tinChiDatHk = tinChiDatHk;
    }

    /**
     * @return the diemTbTichLuy
     */
    public Double getDiemTbTichLuy() {
        return diemTbTichLuy;
    }

    /**
     * @param diemTbTichLuy the diemTbTichLuy to set
     */
    public void setDiemTbTichLuy(Double diemTbTichLuy) {
        this.diemTbTichLuy = diemTbTichLuy;
    }

    /**
     * @return the tinChiTichLuy
     */
    public int getTinChiTichLuy() {
        return tinChiTichLuy;
    }

    /**
     * @param tinChiTichLuy the tinChiTichLuy to set
     */
    public void setTinChiTichLuy(int tinChiTichLuy) {
        this.tinChiTichLuy = tinChiTichLuy;
    }

    
}
