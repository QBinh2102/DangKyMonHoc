/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface MonHocRepository extends JpaRepository<MonHoc, Integer>{
    
    MonHoc findById(int id);
    MonHoc findByTenMon(String tenMon);
    List<MonHoc> findAllByOrderByIdAsc();
    List<MonHoc> findByTenMonContainingIgnoreCaseOrderByIdAsc(String tenMon);
    @Query("""
           SELECT DISTINCT mh
           FROM DangKy dk
           JOIN dk.buoiHocId bh
           JOIN bh.monHocId mh
           WHERE dk.hocKyId.id = :hocKyId
            AND mh.khoaId.id = :khoaId
           """)
    List<MonHoc> findMonHocByHocKyAndKhoaFromDangKy(@Param("hocKyId") int hocKyId, @Param("khoaId") int khoaId);
    
}
