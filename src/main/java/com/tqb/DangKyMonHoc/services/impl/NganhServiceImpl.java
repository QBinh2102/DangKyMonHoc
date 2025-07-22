/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.Nganh;
import com.tqb.DangKyMonHoc.repositories.NganhRepository;
import com.tqb.DangKyMonHoc.services.NganhService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class NganhServiceImpl implements NganhService {

    @Autowired
    private NganhRepository nganhRepo;

    @Override
    public Nganh findById(int id) {
        return this.nganhRepo.findById(id);
    }

    @Override
    public List<Nganh> findAll() {
        return this.nganhRepo.findAllByOrderByIdAsc();
    }

    @Override
    public List<Nganh> findByTenNganhContaining(String tenNganh) {
        return this.nganhRepo.findByTenNganhContainingIgnoreCaseOrderByIdAsc(tenNganh);
    }

    @Override
    public List<Nganh> findByKhoaId(int khoaId) {
        return this.nganhRepo.findByKhoaId_IdOrderByIdAsc(khoaId);
    }

    @Override
    public Nganh addOrUpdate(Nganh nganh) {
        return this.nganhRepo.save(nganh);
    }

}
