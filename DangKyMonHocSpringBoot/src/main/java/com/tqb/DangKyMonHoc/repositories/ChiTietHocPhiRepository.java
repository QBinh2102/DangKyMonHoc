/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.ChiTietHocPhi;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author toquocbinh2102
 */
public interface ChiTietHocPhiRepository extends JpaRepository<ChiTietHocPhi, Integer> {

    List<ChiTietHocPhi> findByHocPhiId_SinhVienId_IdAndHocPhiId_HocKyId_IdOrderByIdAsc(int sinhVienId, int hocKyId);

    List<ChiTietHocPhi> findByHocPhiId_Id(int hocPhiId);

    @Transactional
    void deleteByHocPhiId_IdAndMonHocId_Id(int hocPhiId, int monHocId);

    boolean existsByHocPhiId_Id(int hocPhiId);

    @Query("SELECT COALESCE(SUM(c.chiPhi), 0) FROM ChiTietHocPhi c WHERE c.hocPhiId.id = :hocPhiId")
    BigDecimal sumSoTienByHocPhiId(int hocPhiId);
}
