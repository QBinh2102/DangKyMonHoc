/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "thoi_khoa_bieu")
@NamedQueries({
    @NamedQuery(name = "ThoiKhoaBieu.findAll", query = "SELECT t FROM ThoiKhoaBieu t"),
    @NamedQuery(name = "ThoiKhoaBieu.findById", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.id = :id"),
    @NamedQuery(name = "ThoiKhoaBieu.findByThu", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.thu = :thu"),
    @NamedQuery(name = "ThoiKhoaBieu.findByGioBatDau", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.gioBatDau = :gioBatDau"),
    @NamedQuery(name = "ThoiKhoaBieu.findByGioKetThuc", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.gioKetThuc = :gioKetThuc"),
    @NamedQuery(name = "ThoiKhoaBieu.findByNgayBatDau", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "ThoiKhoaBieu.findByNgayKetThuc", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.ngayKetThuc = :ngayKetThuc"),
    @NamedQuery(name = "ThoiKhoaBieu.findByPhong", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.phong = :phong")})
public class ThoiKhoaBieu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "thu")
    private String thu;
    @Basic(optional = false)
    @Column(name = "gio_bat_dau")
    @Temporal(TemporalType.TIME)
    private Date gioBatDau;
    @Basic(optional = false)
    @Column(name = "gio_ket_thuc")
    @Temporal(TemporalType.TIME)
    private Date gioKetThuc;
    @Basic(optional = false)
    @Column(name = "ngay_bat_dau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;
    @Basic(optional = false)
    @Column(name = "ngay_ket_thuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    @Column(name = "phong")
    private String phong;
    @JoinColumn(name = "buoi_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BuoiHoc buoiHocId;

    public ThoiKhoaBieu() {
    }

    public ThoiKhoaBieu(Integer id) {
        this.id = id;
    }

    public ThoiKhoaBieu(Integer id, Date gioBatDau, Date gioKetThuc, Date ngayBatDau, Date ngayKetThuc) {
        this.id = id;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public Date getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Date gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Date getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Date gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public BuoiHoc getBuoiHocId() {
        return buoiHocId;
    }

    public void setBuoiHocId(BuoiHoc buoiHocId) {
        this.buoiHocId = buoiHocId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThoiKhoaBieu)) {
            return false;
        }
        ThoiKhoaBieu other = (ThoiKhoaBieu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu[ id=" + id + " ]";
    }
    
}
