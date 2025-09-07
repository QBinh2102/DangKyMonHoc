/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface QuyDinhRepository extends JpaRepository<QuyDinh, Integer> {

    QuyDinh findById(int id);

    Page<QuyDinh> findAllByOrderByIdAsc(Pageable pageable);

    Page<QuyDinh> findByTenContainingIgnoreCaseOrderByIdAsc(String quyDinh, Pageable pageable);

    QuyDinh findByTen(String ten);

}
