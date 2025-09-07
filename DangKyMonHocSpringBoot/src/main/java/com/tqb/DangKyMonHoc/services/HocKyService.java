/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author toquocbinh2102
 */
public interface HocKyService {

    HocKy findById(int id);

    HocKy findTopByOrderByIdDesc();

    Page<HocKy> findHocKyPage(Map<String, String> params);

    List<HocKy> findAllByOrderByIdDesc();

    List<HocKy> findHocKyBySinhVienId(int sinhVienId);

    HocKy add(HocKy hocKy);

}
