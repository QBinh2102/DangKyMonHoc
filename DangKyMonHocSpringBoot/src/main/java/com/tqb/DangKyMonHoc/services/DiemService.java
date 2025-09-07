/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.Diem;
import com.tqb.DangKyMonHoc.dto.DiemSinhVienDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface DiemService {

    Diem findById(int id);

    List<Diem> findDiem(Map<String, String> params);

    List<DiemSinhVienDTO> findDiemBySinhVienId(int sinhVienId);

    Diem addOrUpdate(Diem diem);

}
