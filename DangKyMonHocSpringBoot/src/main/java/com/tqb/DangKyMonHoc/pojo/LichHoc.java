/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "lich_hoc")
@NamedQueries({
    @NamedQuery(name = "LichHoc.findAll", query = "SELECT l FROM LichHoc l"),
    @NamedQuery(name = "LichHoc.findById", query = "SELECT l FROM LichHoc l WHERE l.id = :id"),
    @NamedQuery(name = "LichHoc.findByThu", query = "SELECT l FROM LichHoc l WHERE l.thu = :thu"),
    @NamedQuery(name = "LichHoc.findByNgayBatDau", query = "SELECT l FROM LichHoc l WHERE l.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "LichHoc.findByNgayKetThuc", query = "SELECT l FROM LichHoc l WHERE l.ngayKetThuc = :ngayKetThuc"),
    @NamedQuery(name = "LichHoc.findByLoai", query = "SELECT l FROM LichHoc l WHERE l.loai = :loai")})
public class LichHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "thu")
    private String thu;
    @Basic(optional = false)
    @Column(name = "ngay_bat_dau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;
    @Basic(optional = false)
    @Column(name = "ngay_ket_thuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    @Column(name = "loai")
    private String loai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lichHocId")
    @JsonIgnore
    private Set<ThoiKhoaBieu> thoiKhoaBieuSet;
    @JoinColumn(name = "buoi_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BuoiHoc buoiHocId;
    @JoinColumn(name = "phong_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PhongHoc phongHocId;
    @JoinColumn(name = "tiet_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TietHoc tietHocId;

    public LichHoc() {
    }

    public LichHoc(Integer id) {
        this.id = id;
    }

    public LichHoc(Integer id, Date ngayBatDau, Date ngayKetThuc) {
        this.id = id;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Set<ThoiKhoaBieu> getThoiKhoaBieuSet() {
        return thoiKhoaBieuSet;
    }

    public void setThoiKhoaBieuSet(Set<ThoiKhoaBieu> thoiKhoaBieuSet) {
        this.thoiKhoaBieuSet = thoiKhoaBieuSet;
    }

    public BuoiHoc getBuoiHocId() {
        return buoiHocId;
    }

    public void setBuoiHocId(BuoiHoc buoiHocId) {
        this.buoiHocId = buoiHocId;
    }

    public PhongHoc getPhongHocId() {
        return phongHocId;
    }

    public void setPhongHocId(PhongHoc phongHocId) {
        this.phongHocId = phongHocId;
    }

    public TietHoc getTietHocId() {
        return tietHocId;
    }

    public void setTietHocId(TietHoc tietHocId) {
        this.tietHocId = tietHocId;
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
        if (!(object instanceof LichHoc)) {
            return false;
        }
        LichHoc other = (LichHoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.LichHoc[ id=" + id + " ]";
    }
    
}
