/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.LichHoc;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface LichHocRepository extends JpaRepository<LichHoc, Integer> {

    LichHoc findById(int id);

    List<LichHoc> findByBuoiHocId_IdOrderByIdAsc(int buoiHocId);

    List<LichHoc> findAllByOrderByIdAsc();

    @Query("""
        SELECT COUNT(l) > 0
        FROM LichHoc l
        WHERE l.phongHocId.id = :phongId
          AND l.tietHocId.gioBatDau = :gioBatDau
          AND FUNCTION('dayofweek', l.ngayBatDau) = FUNCTION('dayofweek', :ngayBatDau)
          AND (
                (:ngayBatDau BETWEEN l.ngayBatDau AND l.ngayKetThuc)
             OR (:ngayKetThuc BETWEEN l.ngayBatDau AND l.ngayKetThuc)
             OR (l.ngayBatDau BETWEEN :ngayBatDau AND :ngayKetThuc)
          )
    """)
    boolean existsLichHocTrung(
            @Param("phongId") int phongId,
            @Param("gioBatDau") LocalTime gioBatDau,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc
    );

}
