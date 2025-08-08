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
@Table(name = "buoi_hoc")
@NamedQueries({
    @NamedQuery(name = "BuoiHoc.findAll", query = "SELECT b FROM BuoiHoc b"),
    @NamedQuery(name = "BuoiHoc.findById", query = "SELECT b FROM BuoiHoc b WHERE b.id = :id"),
    @NamedQuery(name = "BuoiHoc.findBySiSo", query = "SELECT b FROM BuoiHoc b WHERE b.siSo = :siSo"),
    @NamedQuery(name = "BuoiHoc.findByLoai", query = "SELECT b FROM BuoiHoc b WHERE b.loai = :loai")})
public class BuoiHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "si_so")
    private Integer siSo;
    @Column(name = "loai")
    private String loai;
    @JoinColumn(name = "giang_vien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GiangVien giangVienId;
    @JoinColumn(name = "hoc_ky_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HocKy hocKyId;
    @JoinColumn(name = "mon_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MonHoc monHocId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buoiHocId")
    @JsonIgnore
    private Set<DangKy> dangKySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buoiHocId")
    @JsonIgnore
    private Set<LichHoc> lichHocSet;

    public BuoiHoc() {
    }

    public BuoiHoc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiSo() {
        return siSo;
    }

    public void setSiSo(Integer siSo) {
        this.siSo = siSo;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public GiangVien getGiangVienId() {
        return giangVienId;
    }

    public void setGiangVienId(GiangVien giangVienId) {
        this.giangVienId = giangVienId;
    }

    public HocKy getHocKyId() {
        return hocKyId;
    }

    public void setHocKyId(HocKy hocKyId) {
        this.hocKyId = hocKyId;
    }

    public MonHoc getMonHocId() {
        return monHocId;
    }

    public void setMonHocId(MonHoc monHocId) {
        this.monHocId = monHocId;
    }

    public Set<DangKy> getDangKySet() {
        return dangKySet;
    }

    public void setDangKySet(Set<DangKy> dangKySet) {
        this.dangKySet = dangKySet;
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
        if (!(object instanceof BuoiHoc)) {
            return false;
        }
        BuoiHoc other = (BuoiHoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.BuoiHoc[ id=" + id + " ]";
    }
    
}
