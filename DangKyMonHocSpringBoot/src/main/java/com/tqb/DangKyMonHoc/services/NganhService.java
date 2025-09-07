/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.Nganh;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author toquocbinh2102
 */
public interface NganhService {

    Nganh findById(int id);

    List<Nganh> findNganh(Map<String, String> params);

    Page<Nganh> findNganhPage(Map<String, String> params);

    Nganh addOrUpdate(Nganh nganh);

}
