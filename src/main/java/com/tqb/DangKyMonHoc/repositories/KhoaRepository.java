/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.Khoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface KhoaRepository extends JpaRepository<Khoa, Integer>{
    
    List<Khoa> findAllByOrderByIdAsc();
    Khoa findById(int id);
    List<Khoa> findByTenKhoaContainingIgnoreCaseOrderByIdAsc(String tenKhoa);
    
}
