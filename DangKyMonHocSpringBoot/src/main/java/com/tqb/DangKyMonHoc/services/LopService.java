/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.Lop;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface LopService {
    
    Lop findById(int id);
    List<Lop> findLop(Map<String,String> params);
    Lop addOrUpdate(Lop lop);
    
}
