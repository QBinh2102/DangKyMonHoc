/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.ChiTietHocPhi;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface ChiTietHocPhiService {

    List<ChiTietHocPhi> findChiTietHocPhiBySinhVienAndHocKy(int sinhVienId, int hocKyId);

    List<ChiTietHocPhi> findChiTietHocPhiByHocPhiId(int hocPhiId);

    ChiTietHocPhi add(Map<String, String> params);

    void delete(int sinhVienId, int buoiHocId);

}
