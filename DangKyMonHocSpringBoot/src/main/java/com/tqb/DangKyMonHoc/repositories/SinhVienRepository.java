/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.SinhVien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer>{
    
    SinhVien findById(int id);
    List<SinhVien> findByNguoiDung_HoTenContainingIgnoreCaseOrderByIdAsc(String hoTen);
    List<SinhVien> findAllByOrderByIdAsc();
    
}
