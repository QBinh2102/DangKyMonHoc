/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.Lop;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface LopRepository extends JpaRepository<Lop, Integer> {

    Lop findById(int id);

    List<Lop> findAllByOrderByIdDesc();

    Page<Lop> findByMaLopContainingIgnoreCaseOrderByIdAsc(String maLop, Pageable pageable);
    Page<Lop> findAllByOrderByIdDesc(Pageable pageable);

    @Query("""
        SELECT l
        FROM Lop l
        LEFT JOIN l.sinhVienSet sv
        WHERE l.nganhId.id = :nganhId AND l.khoaHoc = :khoaHoc
        GROUP BY l
        HAVING COUNT(sv) < l.siSo
    """)
    List<Lop> findLopChuaDay(@Param("nganhId") int nganhId, @Param("khoaHoc") int khoaHoc);



}
