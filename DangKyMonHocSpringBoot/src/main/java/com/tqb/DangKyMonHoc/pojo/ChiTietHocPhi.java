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
import java.math.BigDecimal;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "chi_tiet_hoc_phi")
@NamedQueries({
    @NamedQuery(name = "ChiTietHocPhi.findAll", query = "SELECT c FROM ChiTietHocPhi c"),
    @NamedQuery(name = "ChiTietHocPhi.findById", query = "SELECT c FROM ChiTietHocPhi c WHERE c.id = :id"),
    @NamedQuery(name = "ChiTietHocPhi.findByChiPhi", query = "SELECT c FROM ChiTietHocPhi c WHERE c.chiPhi = :chiPhi")})
public class ChiTietHocPhi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "chi_phi")
    private BigDecimal chiPhi;
    @JoinColumn(name = "hoc_phi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HocPhi hocPhiId;
    @JoinColumn(name = "mon_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MonHoc monHocId;

    public ChiTietHocPhi() {
    }

    public ChiTietHocPhi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(BigDecimal chiPhi) {
        this.chiPhi = chiPhi;
    }

    public HocPhi getHocPhiId() {
        return hocPhiId;
    }

    public void setHocPhiId(HocPhi hocPhiId) {
        this.hocPhiId = hocPhiId;
    }

    public MonHoc getMonHocId() {
        return monHocId;
    }

    public void setMonHocId(MonHoc monHocId) {
        this.monHocId = monHocId;
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
        if (!(object instanceof ChiTietHocPhi)) {
            return false;
        }
        ChiTietHocPhi other = (ChiTietHocPhi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.ChiTietHocPhi[ id=" + id + " ]";
    }
    
}
