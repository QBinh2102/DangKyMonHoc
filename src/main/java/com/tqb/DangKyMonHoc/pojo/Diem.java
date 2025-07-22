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
@Table(name = "diem")
@NamedQueries({
    @NamedQuery(name = "Diem.findAll", query = "SELECT d FROM Diem d"),
    @NamedQuery(name = "Diem.findById", query = "SELECT d FROM Diem d WHERE d.id = :id"),
    @NamedQuery(name = "Diem.findByLanHoc", query = "SELECT d FROM Diem d WHERE d.lanHoc = :lanHoc"),
    @NamedQuery(name = "Diem.findByLoai", query = "SELECT d FROM Diem d WHERE d.loai = :loai"),
    @NamedQuery(name = "Diem.findByDiem", query = "SELECT d FROM Diem d WHERE d.diem = :diem")})
public class Diem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "lan_hoc")
    private int lanHoc;
    @Basic(optional = false)
    @Column(name = "loai")
    private String loai;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "diem")
    private BigDecimal diem;
    @JoinColumn(name = "mon_hoc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MonHoc monHocId;
    @JoinColumn(name = "sinh_vien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SinhVien sinhVienId;

    public Diem() {
    }

    public Diem(Integer id) {
        this.id = id;
    }

    public Diem(Integer id, int lanHoc, String loai) {
        this.id = id;
        this.lanHoc = lanHoc;
        this.loai = loai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLanHoc() {
        return lanHoc;
    }

    public void setLanHoc(int lanHoc) {
        this.lanHoc = lanHoc;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public BigDecimal getDiem() {
        return diem;
    }

    public void setDiem(BigDecimal diem) {
        this.diem = diem;
    }

    public MonHoc getMonHocId() {
        return monHocId;
    }

    public void setMonHocId(MonHoc monHocId) {
        this.monHocId = monHocId;
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
        if (!(object instanceof Diem)) {
            return false;
        }
        Diem other = (Diem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.Diem[ id=" + id + " ]";
    }
    
}
