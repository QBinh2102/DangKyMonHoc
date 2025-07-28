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
@Table(name = "giang_vien")
@NamedQueries({
    @NamedQuery(name = "GiangVien.findAll", query = "SELECT g FROM GiangVien g"),
    @NamedQuery(name = "GiangVien.findById", query = "SELECT g FROM GiangVien g WHERE g.id = :id"),
    @NamedQuery(name = "GiangVien.findByHocVi", query = "SELECT g FROM GiangVien g WHERE g.hocVi = :hocVi")})
public class GiangVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "hoc_vi")
    private String hocVi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "giangVienId")
    @JsonIgnore
    private Set<BuoiHoc> buoiHocSet;
    @JoinColumn(name = "khoa_id", referencedColumnName = "id")
    @ManyToOne
    private Khoa khoaId;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @MapsId
    private NguoiDung nguoiDung;

    public GiangVien() {
    }

    public GiangVien(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public Set<BuoiHoc> getBuoiHocSet() {
        return buoiHocSet;
    }

    public void setBuoiHocSet(Set<BuoiHoc> buoiHocSet) {
        this.buoiHocSet = buoiHocSet;
    }

    public Khoa getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Khoa khoaId) {
        this.khoaId = khoaId;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
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
