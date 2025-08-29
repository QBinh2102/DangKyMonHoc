/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface HocKyRepository extends JpaRepository<HocKy, Integer>{
    
    HocKy findById(int id);
    HocKy findTopByOrderByIdDesc();
    List<HocKy> findAllByOrderByIdDesc();
    
    @Query("""
           SELECT DISTINCT hk
           FROM DangKy dk
           JOIN dk.hocKyId hk
           WHERE dk.sinhVienId.id = :sinhVienId
           ORDER BY hk.namHoc, hk.ky
           """)
    List<HocKy> findHocKyBySinhVienId(@Param("sinhVienId") int sinhVienId);
    
}
