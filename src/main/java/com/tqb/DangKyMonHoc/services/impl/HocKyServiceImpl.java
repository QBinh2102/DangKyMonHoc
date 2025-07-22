/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import com.tqb.DangKyMonHoc.repositories.HocKyRepository;
import com.tqb.DangKyMonHoc.services.HocKyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class HocKyServiceImpl implements HocKyService{
    
    @Autowired
    private HocKyRepository hocKyRepo;
    
    @Override
    public HocKy findById(int id) {
        return this.hocKyRepo.findById(id);
    }

    @Override
    public HocKy findTopByOrderByIdDesc() {
        return this.hocKyRepo.findTopByOrderByIdDesc();
    }

    @Override
    public List<HocKy> findAllByOrderByIdAsc() {
        return this.hocKyRepo.findAllByOrderByIdAsc();
    }

    @Override
    public HocKy add(HocKy hocKy) {
        return this.hocKyRepo.save(hocKy);
    }
    
}
