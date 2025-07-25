/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.MonHocLienQuan;
import com.tqb.DangKyMonHoc.pojo.MonHocLienQuanPK;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface MonHocLienQuanService {
    
    List<MonHocLienQuan> findMonHocLienQuan(Map<String,String> params);
    MonHocLienQuan add(MonHocLienQuan monHocLienQuan);
    boolean delete(MonHocLienQuanPK id);
    
}
