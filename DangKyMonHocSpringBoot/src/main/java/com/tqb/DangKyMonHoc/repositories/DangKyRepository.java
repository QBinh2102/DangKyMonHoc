/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.DangKy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface DangKyRepository extends JpaRepository<DangKy, Integer>{
    
    DangKy findById(int id);
    List<DangKy> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);
    List<DangKy> findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(int sinhVienId, int hocKyId);
    List<DangKy> findAllByOrderByIdAsc();
    
}
