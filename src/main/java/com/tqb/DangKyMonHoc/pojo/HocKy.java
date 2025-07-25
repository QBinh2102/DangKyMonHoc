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
@Table(name = "hoc_ky")
@NamedQueries({
    @NamedQuery(name = "HocKy.findAll", query = "SELECT h FROM HocKy h"),
    @NamedQuery(name = "HocKy.findById", query = "SELECT h FROM HocKy h WHERE h.id = :id"),
    @NamedQuery(name = "HocKy.findByKy", query = "SELECT h FROM HocKy h WHERE h.ky = :ky"),
    @NamedQuery(name = "HocKy.findByNamHoc", query = "SELECT h FROM HocKy h WHERE h.namHoc = :namHoc")})
public class HocKy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ky")
    private String ky;
    @Basic(optional = false)
    @Column(name = "nam_hoc")
    private String namHoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hocKyId")
    @JsonIgnore
    private Set<BuoiHoc> buoiHocSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hocKyId")
    @JsonIgnore
    private Set<Diem> diemSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hocKyId")
    @JsonIgnore
    private Set<DangKy> dangKySet;

    public HocKy() {
    }

    public HocKy(Integer id) {
        this.id = id;
    }

    public HocKy(Integer id, String ky, String namHoc) {
        this.id = id;
        this.ky = ky;
        this.namHoc = namHoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKy() {
        return ky;
    }

    public void setKy(String ky) {
        this.ky = ky;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Set<BuoiHoc> getBuoiHocSet() {
        return buoiHocSet;
    }

    public void setBuoiHocSet(Set<BuoiHoc> buoiHocSet) {
        this.buoiHocSet = buoiHocSet;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HocKy)) {
            return false;
        }
        HocKy other = (HocKy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.HocKy[ id=" + id + " ]";
    }
    
}
