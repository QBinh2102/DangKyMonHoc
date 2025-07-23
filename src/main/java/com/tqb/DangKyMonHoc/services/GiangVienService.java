/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.GiangVien;
import java.util.List;

/**
 *
 * @author toquocbinh2102
 */
public interface GiangVienService {
    
    GiangVien findById(int id);
    List<GiangVien> findAll();
    List<GiangVien> findByHoTenContaining(String hoTen);
    GiangVien addOrUpdate(GiangVien giangVien);
    
}
