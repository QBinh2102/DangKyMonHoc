/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.LichHoc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface LichHocRepository extends JpaRepository<LichHoc, Integer>{
    
    LichHoc findById(int id);
    List<LichHoc> findByBuoiHocId_IdOrderByIdAsc(int buoiHocId);
    List<LichHoc> findAllByOrderByIdAsc();
    
}
