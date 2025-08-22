/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface ThoiKhoaBieuService {
    
    ThoiKhoaBieu findById(int id);
    List<ThoiKhoaBieu> findThoiKhoaBieu(Map<String,String> params);
    ThoiKhoaBieu add(ThoiKhoaBieu thoiKhoaBieu);
    ThoiKhoaBieu delete(ThoiKhoaBieu thoiKhoaBieu);
    List<ThoiKhoaBieu> findBySinhVienAndHocKy(
            @Param("sinhVienId") int sinhVienId,
            @Param("hocKyId") int hocKyId,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc
    );
    
}
