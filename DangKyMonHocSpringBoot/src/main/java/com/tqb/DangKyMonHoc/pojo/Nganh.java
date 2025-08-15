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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "nganh")
@NamedQueries({
    @NamedQuery(name = "Nganh.findAll", query = "SELECT n FROM Nganh n"),
    @NamedQuery(name = "Nganh.findById", query = "SELECT n FROM Nganh n WHERE n.id = :id"),
    @NamedQuery(name = "Nganh.findByTenNganh", query = "SELECT n FROM Nganh n WHERE n.tenNganh = :tenNganh")})
public class Nganh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ten_nganh")
    private String tenNganh;
    @JoinTable(name = "nganh_mon_hoc", joinColumns = {
        @JoinColumn(name = "nganh_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "mon_hoc_id", referencedColumnName = "id")})
    @ManyToMany
    @JsonIgnore
    private Set<MonHoc> monHocSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nganh")
    @JsonIgnore
    private Set<MonHocLienQuan> monHocLienQuanSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nganhId")
    @JsonIgnore
    private Set<Lop> lopSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nganhId")
    @JsonIgnore
    private Set<SinhVien> sinhVienSet;
    @JoinColumn(name = "khoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Khoa khoaId;

    public Nganh() {
    }

    public Nganh(Integer id) {
        this.id = id;
    }

    public Nganh(Integer id, String tenNganh) {
        this.id = id;
        this.tenNganh = tenNganh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public Set<MonHoc> getMonHocSet() {
        return monHocSet;
    }

    public void setMonHocSet(Set<MonHoc> monHocSet) {
        this.monHocSet = monHocSet;
    }

    public Set<MonHocLienQuan> getMonHocLienQuanSet() {
        return monHocLienQuanSet;
    }

    public void setMonHocLienQuanSet(Set<MonHocLienQuan> monHocLienQuanSet) {
        this.monHocLienQuanSet = monHocLienQuanSet;
    }

    public Set<Lop> getLopSet() {
        return lopSet;
    }

    public void setLopSet(Set<Lop> lopSet) {
        this.lopSet = lopSet;
    }

    public Set<SinhVien> getSinhVienSet() {
        return sinhVienSet;
    }

    public void setSinhVienSet(Set<SinhVien> sinhVienSet) {
        this.sinhVienSet = sinhVienSet;
    }

    public Khoa getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Khoa khoaId) {
        this.khoaId = khoaId;
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
        if (!(object instanceof Nganh)) {
            return false;
        }
        Nganh other = (Nganh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.Nganh[ id=" + id + " ]";
    }
    
}
