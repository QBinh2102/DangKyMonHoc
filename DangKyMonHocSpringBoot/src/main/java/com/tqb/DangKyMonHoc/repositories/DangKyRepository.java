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
public interface DangKyRepository extends JpaRepository<DangKy, Integer> {

    DangKy findById(int id);

    DangKy findBySinhVienId_IdAndBuoiHocId_MonHocId_IdAndHocKyId_Id(int sinhVienId, int monHocId, int hocKyId);

    List<DangKy> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);

    List<DangKy> findBySinhVienId_IdAndBuoiHocId_MonHocId_Id(int sinhVienId, int monHocId);

    List<DangKy> findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(int sinhVienId, int hocKyId);

    List<DangKy> findAllByOrderByIdAsc();
    
    @Query("""
           SELECT COUNT(dk) > 0
           FROM DangKy dk
           JOIN dk.buoiHocId bh1
           JOIN bh1.lichHocSet lh1
           JOIN BuoiHoc bh2 ON bh2.id = :buoiHocId
           JOIN bh2.lichHocSet lh2
           WHERE dk.sinhVienId.id = :sinhVienId
            AND dk.hocKyId.id = :hocKyId
            AND lh1.thu = lh2.thu
            AND lh1.tietHocId = lh2.tietHocId
            AND lh1.ngayBatDau <= lh2.ngayKetThuc
            AND lh1.ngayKetThuc >= lh2.ngayBatDau
           """)
    boolean checkTrungLich(@Param("sinhVienId") int sinhVienId, @Param("hocKyId") int hocKyId, @Param("buoiHocId") int buoiHocId);
    
    @Query("""
           SELECT COUNT(dk) > 0
           FROM DangKy dk
           WHERE dk.sinhVienId.id = :sinhVienId
            AND dk.hocKyId.id < :hocKyId
            AND dk.buoiHocId.monHocId.id = :monHocId
           """)
    boolean checkHocTruoc(@Param("sinhVienId") int sinhVienId, @Param("hocKyId") int hocKyId, @Param("monHocId") int monHocLienQuanId);
    
    @Query("""
           SELECT COUNT(dk) > 0
           FROM DangKy dk
           WHERE dk.sinhVienId.id = :sinhVienId
            AND dk.hocKyId.id < :hocKyId
            AND dk.buoiHocId.monHocId.id = :monHocId
            AND dk.trangThai = "HOAN_THANH"
           """)
    boolean checkTienQuyet(@Param("sinhVienId") int sinhVienId, @Param("hocKyId") int hocKyId, @Param("monHocId") int monHocLienQuanId);

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
