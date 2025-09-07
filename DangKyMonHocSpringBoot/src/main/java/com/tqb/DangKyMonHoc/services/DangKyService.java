/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.DangKy;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface DangKyService {

    DangKy findById(int id);

    List<DangKy> findDangKy(Map<String, String> params);

    DangKy add(DangKy dangKy);

    DangKy delete(DangKy dangKy);

}
