/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author toquocbinh2102
 */
public interface MonHocService {

    MonHoc findById(int id);

    List<MonHoc> findMonHoc(Map<String, String> params);

    Page<MonHoc> findMonHocPage(Map<String, String> params);

    MonHoc addOrUpdate(MonHoc monHoc);

}
