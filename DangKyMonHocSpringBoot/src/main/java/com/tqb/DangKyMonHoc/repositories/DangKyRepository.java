/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.dto.ThongKeDTO;
import com.tqb.DangKyMonHoc.pojo.DangKy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface DangKyRepository extends JpaRepository<DangKy, Integer>{
    
    DangKy findById(int id);
    List<DangKy> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);
    List<DangKy> findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(int sinhVienId, int hocKyId);
    List<DangKy> findAllByOrderByIdAsc();
    
    @Query("""
        SELECT new com.tqb.DangKyMonHoc.dto.ThongKeDTO(
                CAST(COALESCE(SUM(CASE WHEN dk.trangThai = 'HOAN_THANH' THEN 1 ELSE 0 END), 0) AS long),
                CAST(COALESCE(SUM(CASE WHEN dk.trangThai = 'TRUOT' THEN 1 ELSE 0 END), 0) AS long)
            )
        FROM DangKy dk
        JOIN dk.buoiHocId bh
        JOIN bh.monHocId mh
        WHERE bh.hocKyId.id = :hocKyId
          AND mh.khoaId.id = :khoaId
          AND mh.id = :monHocId
    """)
    ThongKeDTO thongKe(
            @Param("hocKyId") int hocKyId,
            @Param("khoaId") int khoaId,
            @Param("monHocId") int monHocId
    );
}
