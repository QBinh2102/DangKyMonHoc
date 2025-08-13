/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.PhongHoc;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
public interface PhongHocService {
    
    PhongHoc findById (int id);
    List<PhongHoc> findPhongHoc (Map<String, String> params);
    PhongHoc addOrUpdate (PhongHoc phongHoc);
    
}
