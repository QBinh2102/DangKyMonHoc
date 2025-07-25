/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.GiangVien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface GiangVienRepository extends JpaRepository<GiangVien, Integer>{
    
    GiangVien findById(int id);
    List<GiangVien> findAllByOrderByIdAsc();
    List<GiangVien> findByNguoiDung_HoTenContainingIgnoreCaseOrderByIdAsc(String hoTen);
    
}
