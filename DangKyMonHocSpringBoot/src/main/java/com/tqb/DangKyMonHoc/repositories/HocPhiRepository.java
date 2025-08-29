/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.HocPhi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface HocPhiRepository extends JpaRepository<HocPhi, Integer>{
    
    HocPhi findById(int id);
    HocPhi findBySinhVienId_IdAndHocKyId_Id(int sinhVienId, int hocKyId);
    List<HocPhi> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);
    List<HocPhi> findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseOrderByIdDesc(String hoTenSV);
    List<HocPhi> findByHocKyId_IdOrderByIdDesc(int hocKyId);
    List<HocPhi> findByTrangThaiOrderByIdDesc(String trangThai);
    List<HocPhi> findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseAndHocKyId_IdOrderByIdDesc(String hoTenSV, int hocKyId);
    List<HocPhi> findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseAndTrangThaiOrderByIdDesc(String hoTenSV, String trangThai);
    List<HocPhi> findByHocKyId_IdAndTrangThaiOrderByIdDesc(int hocKyId, String trangThai);
    List<HocPhi> findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseAndHocKyId_IdAndTrangThaiOrderByIdDesc(String hoTenSV, int hocKyId, String trangThai);
    List<HocPhi> findAllByOrderByIdDesc();
    
}
