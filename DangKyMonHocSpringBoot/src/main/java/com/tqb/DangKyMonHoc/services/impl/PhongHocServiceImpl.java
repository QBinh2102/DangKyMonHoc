/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.PhongHoc;
import com.tqb.DangKyMonHoc.repositories.PhongHocRepository;
import com.tqb.DangKyMonHoc.services.PhongHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class PhongHocServiceImpl implements PhongHocService{
    
    @Autowired
    private PhongHocRepository phongHocRepo;

    @Override
    public PhongHoc findById(int id) {
        return this.phongHocRepo.findById(id);
    }

    @Override
    public List<PhongHoc> findPhongHoc(Map<String, String> params) {
        String loai = params.get("loai");
        boolean hasLoai = loai!=null && !loai.isEmpty();
        if(hasLoai){
            return this.phongHocRepo.findByLoaiOrderByIdAsc(loai);
        }else{
            return this.phongHocRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public PhongHoc addOrUpdate(PhongHoc phongHoc) {
        return this.phongHocRepo.save(phongHoc);
    }
    
}
