/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.Khoa;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface KhoaService {
    
    Khoa findById(int id);
    List<Khoa> findKhoa(Map<String,String> params);
    Khoa addOrUpdate(Khoa khoa);
    
}
