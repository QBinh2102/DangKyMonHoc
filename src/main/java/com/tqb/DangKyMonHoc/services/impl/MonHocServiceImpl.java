/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import com.tqb.DangKyMonHoc.repositories.MonHocRepository;
import com.tqb.DangKyMonHoc.services.MonHocService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class MonHocServiceImpl implements MonHocService{
    
    @Autowired
    private MonHocRepository monHocRepo;

    @Override
    public MonHoc findById(int id) {
        return this.monHocRepo.findById(id);
    }

    @Override
    public List<MonHoc> findAll() {
        return this.monHocRepo.findAllByOrderByIdAsc();
    }

    @Override
    public List<MonHoc> findByTenMonContaining(String tenMon) {
        return this.monHocRepo.findByTenMonContainingIgnoreCaseOrderByIdAsc(tenMon);
    }

    @Override
    public MonHoc addOrUpdate(MonHoc monHoc) {
        return this.monHocRepo.save(monHoc);
    }
    
}
