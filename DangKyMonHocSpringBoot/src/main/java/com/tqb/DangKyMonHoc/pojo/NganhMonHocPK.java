/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author toquocbinh2102
 */
@Embeddable
public class NganhMonHocPK implements Serializable{
    @Column(name = "nganh_id")
    private int nganhId;

    @Column(name = "mon_hoc_id")
    private int monHocId;

    // Constructors
    public NganhMonHocPK() {}

    public NganhMonHocPK(int nganhId, int monHocId) {
        this.nganhId = nganhId;
        this.monHocId = monHocId;
    }

    // Getters and Setters
    public int getNganhId() { return nganhId; }
    public void setNganhId(int nganhId) { this.nganhId = nganhId; }

    public int getMonHocId() { return monHocId; }
    public void setMonHocId(int monHocId) { this.monHocId = monHocId; }

    // equals & hashCode: bắt buộc phải override cho composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NganhMonHocPK)) return false;
        NganhMonHocPK that = (NganhMonHocPK) o;
        return nganhId == that.nganhId && monHocId == that.monHocId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nganhId, monHocId);
    }
}
