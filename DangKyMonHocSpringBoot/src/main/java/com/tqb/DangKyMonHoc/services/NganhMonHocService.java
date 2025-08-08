/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.NganhMonHoc;
import com.tqb.DangKyMonHoc.pojo.NganhMonHocPK;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface NganhMonHocService {
    
    List<NganhMonHoc> findNganhMonHoc(Map<String,String> params);
    NganhMonHoc add(NganhMonHoc nganhMonHoc);
    void deleteByIdMonHocId(int monHocId);
}
