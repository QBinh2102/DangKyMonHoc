/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface MonHocRepository extends JpaRepository<MonHoc, Integer>{
    
    MonHoc findById(int id);
    List<MonHoc> findAllByOrderByIdAsc();
    List<MonHoc> findByTenMonContainingIgnoreCaseOrderByIdAsc(String tenMon);
    
}
