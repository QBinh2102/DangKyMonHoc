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
@Table(name = "phong_hoc")
@NamedQueries({
    @NamedQuery(name = "PhongHoc.findAll", query = "SELECT p FROM PhongHoc p"),
    @NamedQuery(name = "PhongHoc.findById", query = "SELECT p FROM PhongHoc p WHERE p.id = :id"),
    @NamedQuery(name = "PhongHoc.findByTenPhong", query = "SELECT p FROM PhongHoc p WHERE p.tenPhong = :tenPhong"),
    @NamedQuery(name = "PhongHoc.findByLoai", query = "SELECT p FROM PhongHoc p WHERE p.loai = :loai")})
public class PhongHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ten_phong")
    private String tenPhong;
    @Basic(optional = false)
    @Column(name = "loai")
    private String loai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phongHocId")
    @JsonIgnore
    private Set<LichHoc> lichHocSet;

    public PhongHoc() {
    }

    public PhongHoc(Integer id) {
        this.id = id;
    }

    public PhongHoc(Integer id, String tenPhong, String loai) {
        this.id = id;
        this.tenPhong = tenPhong;
        this.loai = loai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Set<LichHoc> getLichHocSet() {
        return lichHocSet;
    }

    public void setLichHocSet(Set<LichHoc> lichHocSet) {
        this.lichHocSet = lichHocSet;
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
        if (!(object instanceof PhongHoc)) {
            return false;
        }
        PhongHoc other = (PhongHoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.PhongHoc[ id=" + id + " ]";
    }
    
}
