/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.MonHocLienQuan;
import com.tqb.DangKyMonHoc.pojo.MonHocLienQuanPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface MonHocLienQuanRepository extends JpaRepository<MonHocLienQuan, MonHocLienQuanPK>{
    
    List<MonHocLienQuan> findByMonHocLienQuanPK_MonHocId(int monHocId);
    List<MonHocLienQuan> findAllByOrderByMonHocLienQuanPK_MonHocIdAsc();
    List<MonHocLienQuan> findByMonHocLienQuanPK_MonHocIdAndMonHocLienQuanPK_Loai(int monHocId, String loai);
    List<MonHocLienQuan> findByMonHocLienQuanPK_Loai(String loai);
    
}
