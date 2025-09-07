/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.dto.BuoiHocDTO;
import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author toquocbinh2102
 */
public interface BuoiHocService {

    BuoiHoc findById(int id);

    List<BuoiHoc> findBuoiHoc(Map<String, String> params);

    Page<BuoiHoc> findBuoiHocPage(Map<String, String> params);

    BuoiHoc addOrUpdate(BuoiHoc buoiHoc);

    List<BuoiHocDTO> findDanhSachBuoiHocDangKy(Map<String, String> params);
}
