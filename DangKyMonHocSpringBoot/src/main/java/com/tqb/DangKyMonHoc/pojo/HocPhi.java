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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "hoc_phi")
@NamedQueries({
    @NamedQuery(name = "HocPhi.findAll", query = "SELECT h FROM HocPhi h"),
    @NamedQuery(name = "HocPhi.findById", query = "SELECT h FROM HocPhi h WHERE h.id = :id"),
    @NamedQuery(name = "HocPhi.findByTongTien", query = "SELECT h FROM HocPhi h WHERE h.tongTien = :tongTien"),
    @NamedQuery(name = "HocPhi.findByTrangThai", query = "SELECT h FROM HocPhi h WHERE h.trangThai = :trangThai")})
public class HocPhi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tong_tien")
    private BigDecimal tongTien = BigDecimal.ZERO;
    @Column(name = "trang_thai")
    private String trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hocPhiId")
    @JsonIgnore
    private Set<ChiTietHocPhi> chiTietHocPhiSet;
    @JoinColumn(name = "hoc_ky_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HocKy hocKyId;
    @JoinColumn(name = "sinh_vien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SinhVien sinhVienId;

    public HocPhi() {
    }

    public HocPhi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Set<ChiTietHocPhi> getChiTietHocPhiSet() {
        return chiTietHocPhiSet;
    }

    public void setChiTietHocPhiSet(Set<ChiTietHocPhi> chiTietHocPhiSet) {
        this.chiTietHocPhiSet = chiTietHocPhiSet;
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
        if (!(object instanceof HocPhi)) {
            return false;
        }
        HocPhi other = (HocPhi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.HocPhi[ id=" + id + " ]";
    }
    
}
