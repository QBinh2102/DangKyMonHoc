/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface ThoiKhoaBieuRepository extends JpaRepository<ThoiKhoaBieu, Integer>{
    
    ThoiKhoaBieu findById(int id);
    List<ThoiKhoaBieu> findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(int sinhVienId, int hocKyId);
    List<ThoiKhoaBieu> findAllByOrderByIdAsc();
    
    @Query("""
           SELECT tkb
           FROM ThoiKhoaBieu tkb
           JOIN tkb.lichHocId lh
           WHERE tkb.sinhVienId.id = :sinhVienId
           AND tkb.hocKyId.id = :hocKyId
           AND NOT (lh.ngayKetThuc < :ngayBatDau OR lh.ngayBatDau > :ngayKetThuc)
           """)
    List<ThoiKhoaBieu> findBySinhVienAndHocKy(
            @Param("sinhVienId") int sinhVienId,
            @Param("hocKyId") int hocKyId,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc
    );
    
}
