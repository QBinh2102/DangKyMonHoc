/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.PhongHoc;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface PhongHocRepository extends JpaRepository<PhongHoc, Integer>{
    
    PhongHoc findById (int id);
    List<PhongHoc> findByLoaiOrderByIdAsc(String loai);
    List<PhongHoc> findAllByOrderByIdAsc();
    Page<PhongHoc> findByTenPhongContainingIgnoreCaseOrderByIdAsc(String tenPhong, Pageable pageable);
    Page<PhongHoc> findByLoaiOrderByIdAsc(String loai, Pageable pageable);
    Page<PhongHoc> findByTenPhongContainingIgnoreCaseAndLoaiOrderByIdAsc(String tenPhong, String loai, Pageable pageable);
    Page<PhongHoc> findAllByOrderByIdAsc(Pageable pageable);
}
