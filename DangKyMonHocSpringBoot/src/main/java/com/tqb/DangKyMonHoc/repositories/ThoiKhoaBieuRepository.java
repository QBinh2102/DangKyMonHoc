/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface ThoiKhoaBieuRepository extends JpaRepository<ThoiKhoaBieu, Integer>{
    
    ThoiKhoaBieu findById(int id);
    List<ThoiKhoaBieu> findBySinhVienId_IdOrderByIdAsc(int sinhVienId);
    List<ThoiKhoaBieu> findAllByOrderByIdAsc();
    
}
