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
@Table(name = "khoa")
@NamedQueries({
    @NamedQuery(name = "Khoa.findAll", query = "SELECT k FROM Khoa k"),
    @NamedQuery(name = "Khoa.findById", query = "SELECT k FROM Khoa k WHERE k.id = :id"),
    @NamedQuery(name = "Khoa.findByTenKhoa", query = "SELECT k FROM Khoa k WHERE k.tenKhoa = :tenKhoa")})
public class Khoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ten_khoa")
    private String tenKhoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaId")
    @JsonIgnore
    private Set<MonHoc> monHocSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaId")
    @JsonIgnore
    private Set<GiangVien> giangVienSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaId")
    @JsonIgnore
    private Set<SinhVien> sinhVienSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaId")
    @JsonIgnore
    private Set<Nganh> nganhSet;

    public Khoa() {
    }

    public Khoa(Integer id) {
        this.id = id;
    }

    public Khoa(Integer id, String tenKhoa) {
        this.id = id;
        this.tenKhoa = tenKhoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public Set<MonHoc> getMonHocSet() {
        return monHocSet;
    }

    public void setMonHocSet(Set<MonHoc> monHocSet) {
        this.monHocSet = monHocSet;
    }

    public Set<GiangVien> getGiangVienSet() {
        return giangVienSet;
    }

    public void setGiangVienSet(Set<GiangVien> giangVienSet) {
        this.giangVienSet = giangVienSet;
    }

    public Set<SinhVien> getSinhVienSet() {
        return sinhVienSet;
    }

    public void setSinhVienSet(Set<SinhVien> sinhVienSet) {
        this.sinhVienSet = sinhVienSet;
    }

    public Set<Nganh> getNganhSet() {
        return nganhSet;
    }

    public void setNganhSet(Set<Nganh> nganhSet) {
        this.nganhSet = nganhSet;
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
        if (!(object instanceof Khoa)) {
            return false;
        }
        Khoa other = (Khoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.Khoa[ id=" + id + " ]";
    }
    
}
