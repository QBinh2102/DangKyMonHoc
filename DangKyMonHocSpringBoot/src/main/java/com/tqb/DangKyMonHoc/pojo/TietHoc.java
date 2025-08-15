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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "tiet_hoc")
@NamedQueries({
    @NamedQuery(name = "TietHoc.findAll", query = "SELECT t FROM TietHoc t"),
    @NamedQuery(name = "TietHoc.findById", query = "SELECT t FROM TietHoc t WHERE t.id = :id"),
    @NamedQuery(name = "TietHoc.findByTiet", query = "SELECT t FROM TietHoc t WHERE t.tiet = :tiet"),
    @NamedQuery(name = "TietHoc.findByGioBatDau", query = "SELECT t FROM TietHoc t WHERE t.gioBatDau = :gioBatDau"),
    @NamedQuery(name = "TietHoc.findByGioKetThuc", query = "SELECT t FROM TietHoc t WHERE t.gioKetThuc = :gioKetThuc")})
public class TietHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tiet")
    private String tiet;
    @Basic(optional = false)
    @Column(name = "gio_bat_dau")
    @Temporal(TemporalType.TIME)
    private LocalTime gioBatDau;
    @Basic(optional = false)
    @Column(name = "gio_ket_thuc")
    @Temporal(TemporalType.TIME)
    private LocalTime gioKetThuc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tietHocId")
    @JsonIgnore
    private Set<LichHoc> lichHocSet;

    public TietHoc() {
    }

    public TietHoc(Integer id) {
        this.id = id;
    }

    public TietHoc(Integer id, String tiet, LocalTime gioBatDau, LocalTime gioKetThuc) {
        this.id = id;
        this.tiet = tiet;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTiet() {
        return tiet;
    }

    public void setTiet(String tiet) {
        this.tiet = tiet;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
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
        if (!(object instanceof TietHoc)) {
            return false;
        }
        TietHoc other = (TietHoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.TietHoc[ id=" + id + " ]";
    }
    
}
