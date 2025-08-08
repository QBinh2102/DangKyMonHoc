/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface QuyDinhRepository extends JpaRepository<QuyDinh, Integer>{
    
    QuyDinh findById(int id);
    List<QuyDinh> findAllByOrderByIdAsc();
    List<QuyDinh> findByTenContainingIgnoreCaseOrderByIdAsc(String quyDinh);
    QuyDinh findByTen (String ten);
    
}
