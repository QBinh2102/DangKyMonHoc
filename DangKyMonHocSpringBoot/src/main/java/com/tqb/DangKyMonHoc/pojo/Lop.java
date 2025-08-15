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
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "lop")
@NamedQueries({
    @NamedQuery(name = "Lop.findAll", query = "SELECT l FROM Lop l"),
    @NamedQuery(name = "Lop.findById", query = "SELECT l FROM Lop l WHERE l.id = :id"),
    @NamedQuery(name = "Lop.findByMaLop", query = "SELECT l FROM Lop l WHERE l.maLop = :maLop"),
    @NamedQuery(name = "Lop.findBySiSo", query = "SELECT l FROM Lop l WHERE l.siSo = :siSo"),
    @NamedQuery(name = "Lop.findByKhoaHoc", query = "SELECT l FROM Lop l WHERE l.khoaHoc = :khoaHoc")})
public class Lop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ma_lop")
    private String maLop;
    @Basic(optional = false)
    @Column(name = "si_so")
    private int siSo;
    @Column(name = "khoa_hoc")
    private Integer khoaHoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lopId")
    @JsonIgnore
    private Set<BuoiHoc> buoiHocSet;
    @JoinColumn(name = "nganh_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Nganh nganhId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lopId")
    @JsonIgnore
    private Set<SinhVien> sinhVienSet;

    public Lop() {
    }

    public Lop(Integer id) {
        this.id = id;
    }

    public Lop(Integer id, String maLop, int siSo) {
        this.id = id;
        this.maLop = maLop;
        this.siSo = siSo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public Integer getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(Integer khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public Set<BuoiHoc> getBuoiHocSet() {
        return buoiHocSet;
    }

    public void setBuoiHocSet(Set<BuoiHoc> buoiHocSet) {
        this.buoiHocSet = buoiHocSet;
    }

    public Nganh getNganhId() {
        return nganhId;
    }

    public void setNganhId(Nganh nganhId) {
        this.nganhId = nganhId;
    }

    public Set<SinhVien> getSinhVienSet() {
        return sinhVienSet;
    }

    public void setSinhVienSet(Set<SinhVien> sinhVienSet) {
        this.sinhVienSet = sinhVienSet;
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
        if (!(object instanceof Lop)) {
            return false;
        }
        Lop other = (Lop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.Lop[ id=" + id + " ]";
    }
    
}
