/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author toquocbinh2102
 */
@Embeddable
public class MonHocLienQuanPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "mon_hoc_id")
    private int monHocId;
    @Basic(optional = false)
    @Column(name = "lien_quan_id")
    private int lienQuanId;
    @Basic(optional = false)
    @Column(name = "nganh_id")
    private int nganhId;
    @Basic(optional = false)
    @Column(name = "loai")
    private String loai;

    public MonHocLienQuanPK() {
    }

    public MonHocLienQuanPK(int monHocId, int lienQuanId, int nganhId, String loai) {
        this.monHocId = monHocId;
        this.lienQuanId = lienQuanId;
        this.nganhId = nganhId;
        this.loai = loai;
    }

    public int getMonHocId() {
        return monHocId;
    }

    public void setMonHocId(int monHocId) {
        this.monHocId = monHocId;
    }

    public int getLienQuanId() {
        return lienQuanId;
    }

    public void setLienQuanId(int lienQuanId) {
        this.lienQuanId = lienQuanId;
    }

    public int getNganhId() {
        return nganhId;
    }

    public void setNganhId(int nganhId) {
        this.nganhId = nganhId;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) monHocId;
        hash += (int) lienQuanId;
        hash += (int) nganhId;
        hash += (loai != null ? loai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonHocLienQuanPK)) {
            return false;
        }
        MonHocLienQuanPK other = (MonHocLienQuanPK) object;
        if (this.monHocId != other.monHocId) {
            return false;
        }
        if (this.lienQuanId != other.lienQuanId) {
            return false;
        }
        if (this.nganhId != other.nganhId) {
            return false;
        }
        if ((this.loai == null && other.loai != null) || (this.loai != null && !this.loai.equals(other.loai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.MonHocLienQuanPK[ monHocId=" + monHocId + ", lienQuanId=" + lienQuanId + ", nganhId=" + nganhId + ", loai=" + loai + " ]";
    }
    
}
