/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.dto.BuoiHocDTO;
import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
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
public interface BuoiHocRepository extends JpaRepository<BuoiHoc, Integer> {

    BuoiHoc findById(int id);

    List<BuoiHoc> findByMonHocId_IdOrderByIdAsc(int monHocId);

    List<BuoiHoc> findByLopId_IdOrderByIdAsc(int lopId);

    List<BuoiHoc> findAllByOrderByIdDesc();

    Page<BuoiHoc> findByLopId_MaLopContainingIgnoreCaseOrderByIdAsc(String maLop, Pageable pageable);

    Page<BuoiHoc> findAllByOrderByIdDesc(Pageable pageable);

    @Query("""
            SELECT new com.tqb.DangKyMonHoc.dto.BuoiHocDTO(
                 bh.id,
                 mh.tenMon,
                 (mh.tinChiLyThuyet + mh.tinChiThucHanh),
                 l.maLop,
                 bh.siSo,
                 (bh.siSo - COUNT(dk.id))
            )
            FROM BuoiHoc bh
            JOIN bh.lopId l
            JOIN bh.monHocId mh
            LEFT JOIN DangKy dk ON dk.buoiHocId = bh
            WHERE bh.hocKyId.id = :hocKyId
                AND l.id = :lopId
            GROUP BY bh.id, mh.tenMon, mh.tinChiLyThuyet, mh.tinChiThucHanh, l.maLop, bh.siSo
           """)
    List<BuoiHocDTO> findBuoiHocTheoHocKyVaLop(@Param("hocKyId") int hocKyId,
            @Param("lopId") int lopId);

    @Query("""
            SELECT new com.tqb.DangKyMonHoc.dto.BuoiHocDTO(
                 bh.id,
                 mh.tenMon,
                 (mh.tinChiLyThuyet + mh.tinChiThucHanh),
                 l.maLop,
                 bh.siSo,
                 (bh.siSo - COUNT(dk.id))
            )
            FROM BuoiHoc bh
            JOIN bh.lopId l
            JOIN bh.monHocId mh
            LEFT JOIN DangKy dk ON dk.buoiHocId = bh
            WHERE bh.hocKyId.id = :hocKyId
                AND mh.id = :monHocId
            GROUP BY bh.id, mh.tenMon, mh.tinChiLyThuyet, mh.tinChiThucHanh, l.maLop, bh.siSo
           """)
    List<BuoiHocDTO> findBuoiHocTheoHocKyVaMonHoc(@Param("hocKyId") int hocKyId,
            @Param("monHocId") int monHocId);

    @Query("""
            SELECT new com.tqb.DangKyMonHoc.dto.BuoiHocDTO(
                 bh.id,
                 mh.tenMon,
                 (mh.tinChiLyThuyet + mh.tinChiThucHanh),
                 l.maLop,
                 bh.siSo,
                 (bh.siSo - COUNT(dk.id))
            )
            FROM BuoiHoc bh
            JOIN bh.lopId l
            JOIN bh.monHocId mh
            LEFT JOIN DangKy dk ON dk.buoiHocId = bh
            WHERE bh.id = :buoiHocId
            GROUP BY bh.id, mh.tenMon, mh.tinChiLyThuyet, mh.tinChiThucHanh, l.maLop, bh.siSo
           """)
    BuoiHocDTO findBuoiHocTheoId(@Param("buoiHocId") int buoiHocId);
}
