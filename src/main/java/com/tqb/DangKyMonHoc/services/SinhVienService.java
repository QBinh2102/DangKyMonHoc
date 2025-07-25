/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.SinhVien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface SinhVienService {
    
    SinhVien findById(int id);
    List<SinhVien> findSinhVien(Map<String,String> params);
    SinhVien addOrUpdate(SinhVien sinhVien);
    
}
