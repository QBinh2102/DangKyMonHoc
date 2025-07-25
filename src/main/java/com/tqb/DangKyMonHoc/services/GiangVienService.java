/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.GiangVien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface GiangVienService {
    
    GiangVien findById(int id);
    List<GiangVien> findGiangVien(Map<String,String> params);
    GiangVien addOrUpdate(GiangVien giangVien);
    
}
