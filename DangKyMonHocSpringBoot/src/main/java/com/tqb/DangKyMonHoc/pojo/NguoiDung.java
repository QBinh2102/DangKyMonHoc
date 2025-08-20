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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
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
@Table(name = "nguoi_dung")
@NamedQueries({
    @NamedQuery(name = "NguoiDung.findAll", query = "SELECT n FROM NguoiDung n"),
    @NamedQuery(name = "NguoiDung.findById", query = "SELECT n FROM NguoiDung n WHERE n.id = :id"),
    @NamedQuery(name = "NguoiDung.findByHoTen", query = "SELECT n FROM NguoiDung n WHERE n.hoTen = :hoTen"),
    @NamedQuery(name = "NguoiDung.findByNgaySinh", query = "SELECT n FROM NguoiDung n WHERE n.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "NguoiDung.findByGioiTinh", query = "SELECT n FROM NguoiDung n WHERE n.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "NguoiDung.findBySoDienThoai", query = "SELECT n FROM NguoiDung n WHERE n.soDienThoai = :soDienThoai"),
    @NamedQuery(name = "NguoiDung.findByEmail", query = "SELECT n FROM NguoiDung n WHERE n.email = :email"),
    @NamedQuery(name = "NguoiDung.findByCccd", query = "SELECT n FROM NguoiDung n WHERE n.cccd = :cccd"),
    @NamedQuery(name = "NguoiDung.findByMatKhau", query = "SELECT n FROM NguoiDung n WHERE n.matKhau = :matKhau"),
    @NamedQuery(name = "NguoiDung.findByAvatar", query = "SELECT n FROM NguoiDung n WHERE n.avatar = :avatar"),
    @NamedQuery(name = "NguoiDung.findByVaiTro", query = "SELECT n FROM NguoiDung n WHERE n.vaiTro = :vaiTro")})
public class NguoiDung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "cccd")
    private String cccd;
    @Basic(optional = false)
    @Column(name = "mat_khau")
    private String matKhau;
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @Column(name = "vai_tro")
    private String vaiTro;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "nguoiDung")
    @JsonIgnore
    private GiangVien giangVien;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "nguoiDung")
    @JsonIgnore
    private SinhVien sinhVien;

    public NguoiDung() {
    }

    public NguoiDung(Integer id) {
        this.id = id;
    }

    public NguoiDung(Integer id, String hoTen, String email, String matKhau, String vaiTro) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
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
        if (!(object instanceof NguoiDung)) {
            return false;
        }
        NguoiDung other = (NguoiDung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.NguoiDung[ id=" + id + " ]";
    }
    
}
