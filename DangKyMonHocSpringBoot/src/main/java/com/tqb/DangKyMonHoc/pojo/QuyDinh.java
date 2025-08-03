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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "quy_dinh")
@NamedQueries({
    @NamedQuery(name = "QuyDinh.findAll", query = "SELECT q FROM QuyDinh q"),
    @NamedQuery(name = "QuyDinh.findById", query = "SELECT q FROM QuyDinh q WHERE q.id = :id"),
    @NamedQuery(name = "QuyDinh.findByTen", query = "SELECT q FROM QuyDinh q WHERE q.ten = :ten"),
    @NamedQuery(name = "QuyDinh.findByGiaTri", query = "SELECT q FROM QuyDinh q WHERE q.giaTri = :giaTri")})
public class QuyDinh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ten")
    private String ten;
    @Basic(optional = false)
    @Column(name = "gia_tri")
    private int giaTri;

    public QuyDinh() {
    }

    public QuyDinh(Integer id) {
        this.id = id;
    }

    public QuyDinh(Integer id, String ten, int giaTri) {
        this.id = id;
        this.ten = ten;
        this.giaTri = giaTri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
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
        if (!(object instanceof QuyDinh)) {
            return false;
        }
        QuyDinh other = (QuyDinh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.QuyDinh[ id=" + id + " ]";
    }
    
}
