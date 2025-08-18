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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "mon_hoc")
@NamedQueries({
    @NamedQuery(name = "MonHoc.findAll", query = "SELECT m FROM MonHoc m"),
    @NamedQuery(name = "MonHoc.findById", query = "SELECT m FROM MonHoc m WHERE m.id = :id"),
    @NamedQuery(name = "MonHoc.findByTenMon", query = "SELECT m FROM MonHoc m WHERE m.tenMon = :tenMon"),
    @NamedQuery(name = "MonHoc.findByTinChiLyThuyet", query = "SELECT m FROM MonHoc m WHERE m.tinChiLyThuyet = :tinChiLyThuyet"),
    @NamedQuery(name = "MonHoc.findByTinChiThucHanh", query = "SELECT m FROM MonHoc m WHERE m.tinChiThucHanh = :tinChiThucHanh"),
    @NamedQuery(name = "MonHoc.findByPhanTramGiuaKy", query = "SELECT m FROM MonHoc m WHERE m.phanTramGiuaKy = :phanTramGiuaKy"),
    @NamedQuery(name = "MonHoc.findByPhanTramCuoiKy", query = "SELECT m FROM MonHoc m WHERE m.phanTramCuoiKy = :phanTramCuoiKy"),
    @NamedQuery(name = "MonHoc.findByDiemQuaMon", query = "SELECT m FROM MonHoc m WHERE m.diemQuaMon = :diemQuaMon"),
    @NamedQuery(name = "MonHoc.findByDeCuong", query = "SELECT m FROM MonHoc m WHERE m.deCuong = :deCuong")})
public class MonHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ten_mon")
    private String tenMon;
    @Basic(optional = false)
    @Column(name = "tin_chi_ly_thuyet")
    private int tinChiLyThuyet;
    @Basic(optional = false)
    @Column(name = "tin_chi_thuc_hanh")
    private int tinChiThucHanh;
    @Basic(optional = false)
    @Column(name = "phan_tram_giua_ky")
    private int phanTramGiuaKy;
    @Basic(optional = false)
    @Column(name = "phan_tram_cuoi_ky")
    private int phanTramCuoiKy;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "diem_qua_mon")
    private BigDecimal diemQuaMon;
    @Column(name = "de_cuong")
    private String deCuong;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHocId")
    @JsonIgnore
    private Set<BuoiHoc> buoiHocSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHocId")
    @JsonIgnore
    private Set<Diem> diemSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHoc")
    @JsonIgnore
    private Set<MonHocLienQuan> monHocLienQuanSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHoc1")
    @JsonIgnore
    private Set<MonHocLienQuan> monHocLienQuanSet1;
    @JoinColumn(name = "khoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Khoa khoaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHoc")
    @JsonIgnore
    private Set<NganhMonHoc> nganhMonHocSet;

    public MonHoc() {
    }

    public MonHoc(Integer id) {
        this.id = id;
    }

    public MonHoc(Integer id, String tenMon, int tinChiLyThuyet, int tinChiThucHanh, int phanTramGiuaKy, int phanTramCuoiKy, BigDecimal diemQuaMon) {
        this.id = id;
        this.tenMon = tenMon;
        this.tinChiLyThuyet = tinChiLyThuyet;
        this.tinChiThucHanh = tinChiThucHanh;
        this.phanTramGiuaKy = phanTramGiuaKy;
        this.phanTramCuoiKy = phanTramCuoiKy;
        this.diemQuaMon = diemQuaMon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getTinChiLyThuyet() {
        return tinChiLyThuyet;
    }

    public void setTinChiLyThuyet(int tinChiLyThuyet) {
        this.tinChiLyThuyet = tinChiLyThuyet;
    }

    public int getTinChiThucHanh() {
        return tinChiThucHanh;
    }

    public void setTinChiThucHanh(int tinChiThucHanh) {
        this.tinChiThucHanh = tinChiThucHanh;
    }

    public int getPhanTramGiuaKy() {
        return phanTramGiuaKy;
    }

    public void setPhanTramGiuaKy(int phanTramGiuaKy) {
        this.phanTramGiuaKy = phanTramGiuaKy;
    }

    public int getPhanTramCuoiKy() {
        return phanTramCuoiKy;
    }

    public void setPhanTramCuoiKy(int phanTramCuoiKy) {
        this.phanTramCuoiKy = phanTramCuoiKy;
    }

    public BigDecimal getDiemQuaMon() {
        return diemQuaMon;
    }

    public void setDiemQuaMon(BigDecimal diemQuaMon) {
        this.diemQuaMon = diemQuaMon;
    }

    public String getDeCuong() {
        return deCuong;
    }

    public void setDeCuong(String deCuong) {
        this.deCuong = deCuong;
    }

    public Set<BuoiHoc> getBuoiHocSet() {
        return buoiHocSet;
    }

    public void setBuoiHocSet(Set<BuoiHoc> buoiHocSet) {
        this.buoiHocSet = buoiHocSet;
    }

    public Set<Diem> getDiemSet() {
        return diemSet;
    }

    public void setDiemSet(Set<Diem> diemSet) {
        this.diemSet = diemSet;
    }

    public Set<MonHocLienQuan> getMonHocLienQuanSet() {
        return monHocLienQuanSet;
    }

    public void setMonHocLienQuanSet(Set<MonHocLienQuan> monHocLienQuanSet) {
        this.monHocLienQuanSet = monHocLienQuanSet;
    }

    public Set<MonHocLienQuan> getMonHocLienQuanSet1() {
        return monHocLienQuanSet1;
    }

    public void setMonHocLienQuanSet1(Set<MonHocLienQuan> monHocLienQuanSet1) {
        this.monHocLienQuanSet1 = monHocLienQuanSet1;
    }

    public Khoa getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Khoa khoaId) {
        this.khoaId = khoaId;
    }

    public Set<NganhMonHoc> getNganhMonHocSet() {
        return nganhMonHocSet;
    }

    public void setNganhMonHocSet(Set<NganhMonHoc> nganhMonHocSet) {
        this.nganhMonHocSet = nganhMonHocSet;
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
        if (!(object instanceof MonHoc)) {
            return false;
        }
        MonHoc other = (MonHoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tqb.DangKyMonHoc.pojo.MonHoc[ id=" + id + " ]";
    }
    
}
