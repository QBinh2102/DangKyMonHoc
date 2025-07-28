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
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "dang_ky")
@NamedQueries({
    @NamedQuery(name = "DangKy.findAll", query = "SELECT d FROM DangKy d"),
    @NamedQuery(name = "DangKy.findById", query = "SELECT d FROM DangKy d WHERE d.id = :id"),
    @NamedQuery(name = "DangKy.findByTrangThai", query = "SELECT d FROM DangKy d WHERE d.trangThai = :trangThai"),
    @NamedQuery(name = "DangKy.findByNgayDangKy", query = "SELECT d FROM DangKy d WHERE d.ngayDangKy = :ngayDangKy")})
public class DangKy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_dang_ky")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngayDangKy;
    @JoinColumn(name = "buoi_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BuoiHoc buoiHocId;
    @JoinColumn(name = "hoc_ky_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HocKy hocKyId;
    @JoinColumn(name = "sinh_vien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SinhVien sinhVienId;

    public DangKy() {
    }

    public DangKy(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(LocalDateTime ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public BuoiHoc getBuoiHocId() {
        return buoiHocId;
    }

    public void setBuoiHocId(BuoiHoc buoiHocId) {
        this.buoiHocId = buoiHocId;
    }

    public HocKy getHocKyId() {
        return hocKyId;
    }

    public void setHocKyId(HocKy hocKyId) {
        this.hocKyId = hocKyId;
    }

    public SinhVien getSinhVienId() {
        return sinhVienId;
    }

    public void setSinhVienId(SinhVien sinhVienId) {
        this.sinhVienId = sinhVienId;
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
        if (!(object instanceof DangKy)) {
            return false;
        }
        DangKy other = (DangKy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.DangKy[ id=" + id + " ]";
    }
    
}
