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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "giang_vien")
@NamedQueries({
    @NamedQuery(name = "GiangVien.findAll", query = "SELECT g FROM GiangVien g"),
    @NamedQuery(name = "GiangVien.findById", query = "SELECT g FROM GiangVien g WHERE g.id = :id"),
    @NamedQuery(name = "GiangVien.findByHoTen", query = "SELECT g FROM GiangVien g WHERE g.hoTen = :hoTen"),
    @NamedQuery(name = "GiangVien.findByEmail", query = "SELECT g FROM GiangVien g WHERE g.email = :email"),
    @NamedQuery(name = "GiangVien.findByHocVi", query = "SELECT g FROM GiangVien g WHERE g.hocVi = :hocVi"),
    @NamedQuery(name = "GiangVien.findByMatKhau", query = "SELECT g FROM GiangVien g WHERE g.matKhau = :matKhau")})
public class GiangVien implements Serializable {

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
    @Column(name = "hoc_vi")
    private String hocVi;
    @Column(name = "mat_khau")
    private String matKhau;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "giangVienId")
    @JsonIgnore
    private Set<BuoiHoc> buoiHocSet;

    public GiangVien() {
    }

    public GiangVien(Integer id) {
        this.id = id;
    }

    public GiangVien(Integer id, String hoTen, String email) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
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

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Set<BuoiHoc> getBuoiHocSet() {
        return buoiHocSet;
    }

    public void setBuoiHocSet(Set<BuoiHoc> buoiHocSet) {
        this.buoiHocSet = buoiHocSet;
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
        if (!(object instanceof GiangVien)) {
            return false;
        }
        GiangVien other = (GiangVien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.GiangVien[ id=" + id + " ]";
    }
    
}
