/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "nganh_mon_hoc")
public class NganhMonHoc {

    @EmbeddedId
    private NganhMonHocPK id;

    @ManyToOne
    @MapsId("nganhId")
    @JoinColumn(name = "nganh_id")
    private Nganh nganh;

    @ManyToOne
    @MapsId("monHocId")
    @JoinColumn(name = "mon_hoc_id")
    private MonHoc monHoc;

    @Column(name = "ky", nullable = false)
    private int ky;
    
    // Constructors
    public NganhMonHoc() {}

    public NganhMonHoc(Nganh nganh, MonHoc monHoc, int ky) {
        this.id = new NganhMonHocPK(nganh.getId(), monHoc.getId());
        this.nganh = nganh;
        this.monHoc = monHoc;
        this.ky = ky;
    }

    // Getters & Setters
    public NganhMonHocPK getId() { return id; }
    public void setId(NganhMonHocPK id) { this.id = id; }

    public Nganh getNganh() { return nganh; }
    public void setNganh(Nganh nganh) { this.nganh = nganh; }

    public MonHoc getMonHoc() { return monHoc; }
    public void setMonHoc(MonHoc monHoc) { this.monHoc = monHoc; }
    
    public int getKy() { return ky; }
    public void setKy(int ky) { this.ky = ky; }
}

