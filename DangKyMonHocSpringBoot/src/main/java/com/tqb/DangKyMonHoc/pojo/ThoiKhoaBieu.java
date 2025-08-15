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
@Table(name = "thoi_khoa_bieu")
@NamedQueries({
    @NamedQuery(name = "ThoiKhoaBieu.findAll", query = "SELECT t FROM ThoiKhoaBieu t"),
    @NamedQuery(name = "ThoiKhoaBieu.findById", query = "SELECT t FROM ThoiKhoaBieu t WHERE t.id = :id")})
public class ThoiKhoaBieu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "hoc_ky_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HocKy hocKyId;
    @JoinColumn(name = "lich_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LichHoc lichHocId;
    @JoinColumn(name = "sinh_vien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SinhVien sinhVienId;

    public ThoiKhoaBieu() {
    }

    public ThoiKhoaBieu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HocKy getHocKyId() {
        return hocKyId;
    }

    public void setHocKyId(HocKy hocKyId) {
        this.hocKyId = hocKyId;
    }

    public LichHoc getLichHocId() {
        return lichHocId;
    }

    public void setLichHocId(LichHoc lichHocId) {
        this.lichHocId = lichHocId;
    }

    public SinhVien getSinhVienId() {
        return sinhVienId;
    }

    public void setSinhVienId(SinhVien sinhVienId) {
        this.sinhVienId = sinhVienId;
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
        if (!(object instanceof ThoiKhoaBieu)) {
            return false;
        }
        ThoiKhoaBieu other = (ThoiKhoaBieu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu[ id=" + id + " ]";
    }
    
}
