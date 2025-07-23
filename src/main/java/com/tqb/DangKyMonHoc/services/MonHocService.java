/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import java.util.List;

/**
 *
 * @author toquocbinh2102
 */
public interface MonHocService {
    
    MonHoc findById(int id);
    List<MonHoc> findAll();
    List<MonHoc> findByTenMonContaining(String tenMon);
    MonHoc addOrUpdate(MonHoc monHoc);
    
}
