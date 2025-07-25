/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.Diem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface DiemRepository extends JpaRepository<Diem, Integer>{
    
    Diem findById(int id);
    List<Diem> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);
    List<Diem> findBySinhVienId_IdAndMonHocId_IdOrderByIdAsc(int sinhVienId, int monHocId);
    List<Diem> findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(int sinhVienId, int hocKyId);
    List<Diem> findByMonHocId_IdAndHocKyId_IdAndLoaiOrderByIdAsc(int monHocId, int hocKyId, String loai);
    List<Diem> findAllByOrderByIdAsc();
    
}
