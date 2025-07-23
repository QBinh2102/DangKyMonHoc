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
@Table(name = "sinh_vien")
@NamedQueries({
    @NamedQuery(name = "SinhVien.findAll", query = "SELECT s FROM SinhVien s"),
    @NamedQuery(name = "SinhVien.findById", query = "SELECT s FROM SinhVien s WHERE s.id = :id"),
    @NamedQuery(name = "SinhVien.findByHoTen", query = "SELECT s FROM SinhVien s WHERE s.hoTen = :hoTen"),
    @NamedQuery(name = "SinhVien.findByEmail", query = "SELECT s FROM SinhVien s WHERE s.email = :email"),
    @NamedQuery(name = "SinhVien.findByMatKhau", query = "SELECT s FROM SinhVien s WHERE s.matKhau = :matKhau"),
    @NamedQuery(name = "SinhVien.findByNgaySinh", query = "SELECT s FROM SinhVien s WHERE s.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "SinhVien.findByKhoaHoc", query = "SELECT s FROM SinhVien s WHERE s.khoaHoc = :khoaHoc"),
    @NamedQuery(name = "SinhVien.findBySoTinChi", query = "SELECT s FROM SinhVien s WHERE s.soTinChi = :soTinChi")})
public class SinhVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ho_ten")
    private String hoTen;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "mat_khau")
    private String matKhau;
    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @Basic(optional = false)
    @Column(name = "khoa_hoc")
    private int khoaHoc;
    @Basic(optional = false)
    @Column(name = "so_tin_chi")
    private int soTinChi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienId")
    @JsonIgnore
    private Set<Diem> diemSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienId")
    @JsonIgnore
    private Set<DangKy> dangKySet;
    @JoinColumn(name = "khoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Khoa khoaId;
    @JoinColumn(name = "nganh_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Nganh nganhId;

    public SinhVien() {
    }

    public SinhVien(Integer id) {
        this.id = id;
    }

    public SinhVien(Integer id, String hoTen, String email, String matKhau, int khoaHoc, int soTinChi) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.khoaHoc = khoaHoc;
        this.soTinChi = soTinChi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(int khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
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

    public Khoa getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Khoa khoaId) {
        this.khoaId = khoaId;
    }

    public Nganh getNganhId() {
        return nganhId;
    }

    public void setNganhId(Nganh nganhId) {
        this.nganhId = nganhId;
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
