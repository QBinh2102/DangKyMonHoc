/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "ca")
@NamedQueries({
    @NamedQuery(name = "Ca.findAll", query = "SELECT c FROM Ca c"),
    @NamedQuery(name = "Ca.findById", query = "SELECT c FROM Ca c WHERE c.id = :id"),
    @NamedQuery(name = "Ca.findByTenCa", query = "SELECT c FROM Ca c WHERE c.tenCa = :tenCa"),
    @NamedQuery(name = "Ca.findBySiSo", query = "SELECT c FROM Ca c WHERE c.siSo = :siSo")})
public class Ca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ten_ca")
    private String tenCa;
    @Column(name = "si_so")
    private Integer siSo;
    @JoinColumn(name = "buoi_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BuoiHoc buoiHocId;

    public Ca() {
    }

    public Ca(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public Integer getSiSo() {
        return siSo;
    }

    public void setSiSo(Integer siSo) {
        this.siSo = siSo;
    }

    public BuoiHoc getBuoiHocId() {
        return buoiHocId;
    }

    public void setBuoiHocId(BuoiHoc buoiHocId) {
        this.buoiHocId = buoiHocId;
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
        if (!(object instanceof Ca)) {
            return false;
        }
        Ca other = (Ca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.Ca[ id=" + id + " ]";
    }
    
}
