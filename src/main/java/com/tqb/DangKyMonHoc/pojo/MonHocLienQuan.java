/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "mon_hoc_lien_quan")
@NamedQueries({
    @NamedQuery(name = "MonHocLienQuan.findAll", query = "SELECT m FROM MonHocLienQuan m"),
    @NamedQuery(name = "MonHocLienQuan.findByMonHocId", query = "SELECT m FROM MonHocLienQuan m WHERE m.monHocLienQuanPK.monHocId = :monHocId"),
    @NamedQuery(name = "MonHocLienQuan.findByLienQuanId", query = "SELECT m FROM MonHocLienQuan m WHERE m.monHocLienQuanPK.lienQuanId = :lienQuanId"),
    @NamedQuery(name = "MonHocLienQuan.findByNganhId", query = "SELECT m FROM MonHocLienQuan m WHERE m.monHocLienQuanPK.nganhId = :nganhId"),
    @NamedQuery(name = "MonHocLienQuan.findByLoai", query = "SELECT m FROM MonHocLienQuan m WHERE m.monHocLienQuanPK.loai = :loai")})
public class MonHocLienQuan implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonHocLienQuanPK monHocLienQuanPK;
    @JoinColumn(name = "mon_hoc_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonHoc monHoc;
    @JoinColumn(name = "lien_quan_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonHoc monHoc1;
    @JoinColumn(name = "nganh_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Nganh nganh;

    public MonHocLienQuan() {
    }

    public MonHocLienQuan(MonHocLienQuanPK monHocLienQuanPK) {
        this.monHocLienQuanPK = monHocLienQuanPK;
    }

    public MonHocLienQuan(int monHocId, int lienQuanId, int nganhId, String loai) {
        this.monHocLienQuanPK = new MonHocLienQuanPK(monHocId, lienQuanId, nganhId, loai);
    }

    public MonHocLienQuanPK getMonHocLienQuanPK() {
        return monHocLienQuanPK;
    }

    public void setMonHocLienQuanPK(MonHocLienQuanPK monHocLienQuanPK) {
        this.monHocLienQuanPK = monHocLienQuanPK;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public MonHoc getMonHoc1() {
        return monHoc1;
    }

    public void setMonHoc1(MonHoc monHoc1) {
        this.monHoc1 = monHoc1;
    }

    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monHocLienQuanPK != null ? monHocLienQuanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonHocLienQuan)) {
            return false;
        }
        MonHocLienQuan other = (MonHocLienQuan) object;
        if ((this.monHocLienQuanPK == null && other.monHocLienQuanPK != null) || (this.monHocLienQuanPK != null && !this.monHocLienQuanPK.equals(other.monHocLienQuanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.MonHocLienQuan[ monHocLienQuanPK=" + monHocLienQuanPK + " ]";
    }
    
}
