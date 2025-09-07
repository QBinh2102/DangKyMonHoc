/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.Nganh;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface NganhRepository extends JpaRepository<Nganh, Integer> {

    Nganh findById(int id);

    List<Nganh> findAllByOrderByIdAsc();

    List<Nganh> findByKhoaId_IdOrderByIdAsc(int khoaId);

    Page<Nganh> findByTenNganhContainingIgnoreCaseOrderByIdAsc(String tenKhoa, Pageable pageable);

    Page<Nganh> findByKhoaId_IdOrderByIdAsc(int khoaId, Pageable pageable);

    Page<Nganh> findByTenNganhContainingIgnoreCaseAndKhoaId_IdOrderByIdAsc(String tenNganh, int khoaId, Pageable pageable);

    Page<Nganh> findAllByOrderByIdAsc(Pageable pageable);

}
