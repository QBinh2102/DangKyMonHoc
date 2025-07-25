/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.LichHoc;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface LichHocService {
    
    LichHoc findById(int id);
    List<LichHoc> findLichHoc(Map<String,String> params);
    LichHoc addOrUpdate(LichHoc lichHoc);
    
}
