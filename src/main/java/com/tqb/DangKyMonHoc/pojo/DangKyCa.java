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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "dang_ky_ca")
@NamedQueries({
    @NamedQuery(name = "DangKyCa.findAll", query = "SELECT d FROM DangKyCa d"),
    @NamedQuery(name = "DangKyCa.findById", query = "SELECT d FROM DangKyCa d WHERE d.id = :id"),
    @NamedQuery(name = "DangKyCa.findByTenCa", query = "SELECT d FROM DangKyCa d WHERE d.tenCa = :tenCa")})
public class DangKyCa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ten_ca")
    private String tenCa;
    @JoinColumn(name = "dang_ky_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private DangKy dangKyId;

    public DangKyCa() {
    }

    public DangKyCa(Integer id) {
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

    public DangKy getDangKyId() {
        return dangKyId;
    }

    public void setDangKyId(DangKy dangKyId) {
        this.dangKyId = dangKyId;
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
        if (!(object instanceof DangKyCa)) {
            return false;
        }
        DangKyCa other = (DangKyCa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.DangKyCa[ id=" + id + " ]";
    }
    
}
