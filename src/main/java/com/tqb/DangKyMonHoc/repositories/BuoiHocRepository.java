/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface BuoiHocRepository extends JpaRepository<BuoiHoc, Integer>{
    
    BuoiHoc findById(int id);
    List<BuoiHoc> findByMonHocId_IdOrderByIdAsc(int monHocId);
    List<BuoiHoc> findByHocKyId_IdOrderByIdAsc(int hocKyId);
    List<BuoiHoc> findByMonHocId_IdAndHocKyId_IdOrderByIdAsc(int monHocId, int hocKyId);
    List<BuoiHoc> findAllByOrderByIdAsc();
    
}
