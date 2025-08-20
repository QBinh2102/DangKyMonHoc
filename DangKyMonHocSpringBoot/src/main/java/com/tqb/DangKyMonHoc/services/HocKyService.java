/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface HocKyService {
    
    HocKy findById(int id);
    HocKy findTopByOrderByIdDesc();
    List<HocKy> findAllByOrderByIdAsc();
    List<HocKy> findHocKyBySinhVienId(@Param("sinhVienId") int sinhVienId);
    HocKy add(HocKy hocKy);
    
}
