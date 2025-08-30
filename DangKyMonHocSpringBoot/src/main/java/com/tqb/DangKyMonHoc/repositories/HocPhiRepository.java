/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.HocPhi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface HocPhiRepository extends JpaRepository<HocPhi, Integer>{
    
    HocPhi findById(int id);
    HocPhi findBySinhVienId_IdAndHocKyId_Id(int sinhVienId, int hocKyId);
    List<HocPhi> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);
    List<HocPhi> findAllByOrderByIdDesc();
    Page<HocPhi> findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseOrderByIdDesc(String hoTenSV, Pageable pageable);
    Page<HocPhi> findByTrangThaiOrderByIdDesc(String trangThai, Pageable pageable);
    Page<HocPhi> findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseAndTrangThaiOrderByIdDesc(String hoTenSV, String trangThai, Pageable pageable);
    Page<HocPhi> findAllByOrderByIdDesc(Pageable pageable);
    
}
