/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.TietHoc;
import com.tqb.DangKyMonHoc.repositories.TietHocRepository;
import com.tqb.DangKyMonHoc.services.TietHocService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class TietHocServiceImpl implements TietHocService{
    
    @Autowired
    private TietHocRepository tietHocRepo;

    @Override
    public TietHoc findById(int id) {
        return this.tietHocRepo.findById(id);
    }

    @Override
    public List<TietHoc> findTietHoc() {
        return this.tietHocRepo.findAllByOrderByIdAsc();
    }

    @Override
    public TietHoc addOrUpdate(TietHoc tietHoc) {
        return this.tietHocRepo.save(tietHoc);
    }
    
}
