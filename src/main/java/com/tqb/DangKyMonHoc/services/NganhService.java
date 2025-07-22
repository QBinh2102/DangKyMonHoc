/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.Nganh;
import java.util.List;

/**
 *
 * @author toquocbinh2102
 */
public interface NganhService {
    
    Nganh findById(int id);
    List<Nganh> findAll();
    List<Nganh> findByTenNganhContaining(String tenNganh);
    List<Nganh> findByKhoaId(int khoaId);
    Nganh addOrUpdate(Nganh nganh);
    
}
