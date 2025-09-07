/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.HocPhi;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author toquocbinh2102
 */
public interface HocPhiService {

    HocPhi findById(int id);

    HocPhi findHocPhiMoiNhatTheoSinhVien(int sinhVienId);

    List<HocPhi> findHocPhi(Map<String, String> params);

    Page<HocPhi> findHocPhiPage(Map<String, String> params);

    HocPhi addOrUpdate(HocPhi hocPhi);

}
