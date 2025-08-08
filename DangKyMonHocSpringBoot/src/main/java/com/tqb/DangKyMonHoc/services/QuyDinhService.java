/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface QuyDinhService {
    
    QuyDinh findById(int id);
    List<QuyDinh> findQuyDinh(Map<String,String> params);
    QuyDinh findByTen(String ten);
    QuyDinh addOrUpdate(QuyDinh quyDinh);
    QuyDinh delete(QuyDinh quyDinh);
    
}
