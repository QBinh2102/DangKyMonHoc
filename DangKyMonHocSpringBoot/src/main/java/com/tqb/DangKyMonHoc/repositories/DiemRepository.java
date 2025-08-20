/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.Diem;
import com.tqb.DangKyMonHoc.pojo.DiemSinhVienDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    @Query("""
           SELECT new com.tqb.DangKyMonHoc.pojo.DiemSinhVienDTO(
                hk.ky,
                hk.namHoc,
                mh.tenMon,
                l.maLop,
                mh.tinChiLyThuyet,
                mh.tinChiThucHanh,
                diem_gk.diem,
                diem_ck.diem,
                diem_tk.diem,
                dk.trangThai
           )
           FROM DangKy dk
           JOIN dk.hocKyId hk
           JOIN dk.buoiHocId bh
           JOIN bh.monHocId mh
           JOIN bh.lopId l
           LEFT JOIN Diem diem_gk
                ON diem_gk.sinhVienId.id = dk.sinhVienId.id
                AND diem_gk.monHocId.id = mh.id
                AND diem_gk.hocKyId.id = dk.hocKyId.id
                AND diem_gk.loai = 'GIUA_KY'
           LEFT JOIN Diem diem_ck
                ON diem_ck.sinhVienId.id = dk.sinhVienId.id
                AND diem_ck.monHocId.id = mh.id
                AND diem_ck.hocKyId.id = dk.hocKyId.id
                AND diem_ck.loai = 'CUOI_KY'
           LEFT JOIN Diem diem_tk
                ON diem_tk.sinhVienId.id = dk.sinhVienId.id
                AND diem_tk.monHocId.id = mh.id
                AND diem_tk.hocKyId.id = dk.hocKyId.id
                ANd diem_tk.loai = 'TONG_KET'
           WHERE dk.sinhVienId.id = :sinhVienId
           ORDER BY hk.namHoc, hk.ky, mh.id
           """)
    List<DiemSinhVienDTO> findDiemBySinhVienId(@Param("sinhVienId") int sinhVienId);
    
}
