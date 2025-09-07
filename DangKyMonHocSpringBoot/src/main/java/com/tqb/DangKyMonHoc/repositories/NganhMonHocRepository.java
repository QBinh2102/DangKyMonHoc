/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.NganhMonHoc;
import com.tqb.DangKyMonHoc.pojo.NganhMonHocPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author toquocbinh2102
 */
public interface NganhMonHocRepository extends JpaRepository<NganhMonHoc, NganhMonHocPK> {

    List<NganhMonHoc> findById_NganhId(int nganhId);

    List<NganhMonHoc> findById_MonHocId(int monHocId);

    List<NganhMonHoc> findById_NganhIdAndId_MonHocId(int nganhId, int monHocId);

    List<NganhMonHoc> findAllByOrderById_NganhIdAscId_MonHocIdAsc();

    //Danh sách môn học theo ngành và trước kỳ của môn học
    @Query("""
            SELECT n
            FROM NganhMonHoc n
            WHERE n.nganh.id = :nganhId AND n.ky < :ky
           """)
    List<NganhMonHoc> findById_NganhIdAndKy(@Param("nganhId") int nganhId, @Param("ky") int ky);

}
