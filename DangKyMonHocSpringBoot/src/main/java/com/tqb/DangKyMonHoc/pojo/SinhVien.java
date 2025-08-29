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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "sinh_vien")
@NamedQueries({
    @NamedQuery(name = "SinhVien.findAll", query = "SELECT s FROM SinhVien s"),
    @NamedQuery(name = "SinhVien.findById", query = "SELECT s FROM SinhVien s WHERE s.id = :id"),
    @NamedQuery(name = "SinhVien.findByKhoaHoc", query = "SELECT s FROM SinhVien s WHERE s.khoaHoc = :khoaHoc"),
    @NamedQuery(name = "SinhVien.findBySoTinChi", query = "SELECT s FROM SinhVien s WHERE s.soTinChi = :soTinChi")})
public class SinhVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "khoa_hoc")
    private Integer khoaHoc;
    @Column(name = "so_tin_chi")
    private Integer soTinChi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienId")
    @JsonIgnore
    private Set<Diem> diemSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienId")
    @JsonIgnore
    private Set<DangKy> dangKySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienId")
    @JsonIgnore
    private Set<ThoiKhoaBieu> thoiKhoaBieuSet;
    @JoinColumn(name = "khoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Khoa khoaId;
    @JoinColumn(name = "lop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lop lopId;
    @JoinColumn(name = "nganh_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Nganh nganhId;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @MapsId
    private NguoiDung nguoiDung;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienId")
    @JsonIgnore
    private Set<HocPhi> hocPhiSet;

    public SinhVien() {
    }

    public SinhVien(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(Integer khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public Integer getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public Set<Diem> getDiemSet() {
        return diemSet;
    }

    public void setDiemSet(Set<Diem> diemSet) {
        this.diemSet = diemSet;
    }

    public Set<DangKy> getDangKySet() {
        return dangKySet;
    }

    public void setDangKySet(Set<DangKy> dangKySet) {
        this.dangKySet = dangKySet;
    }

    public Set<ThoiKhoaBieu> getThoiKhoaBieuSet() {
        return thoiKhoaBieuSet;
    }

    public void setThoiKhoaBieuSet(Set<ThoiKhoaBieu> thoiKhoaBieuSet) {
        this.thoiKhoaBieuSet = thoiKhoaBieuSet;
    }

    public Khoa getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Khoa khoaId) {
        this.khoaId = khoaId;
    }

    public Lop getLopId() {
        return lopId;
    }

    public void setLopId(Lop lopId) {
        this.lopId = lopId;
    }

    public Nganh getNganhId() {
        return nganhId;
    }

    public void setNganhId(Nganh nganhId) {
        this.nganhId = nganhId;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public Set<HocPhi> getHocPhiSet() {
        return hocPhiSet;
    }

    public void setHocPhiSet(Set<HocPhi> hocPhiSet) {
        this.hocPhiSet = hocPhiSet;
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
        if (!(object instanceof SinhVien)) {
            return false;
        }
        SinhVien other = (SinhVien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.SinhVien[ id=" + id + " ]";
    }
    
}
