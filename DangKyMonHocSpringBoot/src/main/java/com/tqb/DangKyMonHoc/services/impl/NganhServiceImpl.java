/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.Nganh;
import com.tqb.DangKyMonHoc.repositories.NganhRepository;
import com.tqb.DangKyMonHoc.services.NganhService;
import java.util.List;
import java.util.Map;
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
    public List<Nganh> findNganh(Map<String, String> params) {
        String tenNganh = params.get("tenNganh");
        String khoaId = params.get("khoaId");
        boolean hasTenNganh = tenNganh != null && !tenNganh.isEmpty();
        boolean hasKhoaId = khoaId != null && !khoaId.isEmpty();
        if (hasTenNganh) {
            return this.nganhRepo.findByTenNganhContainingIgnoreCaseOrderByIdAsc(tenNganh);
        } else if (hasKhoaId) {
            return this.nganhRepo.findByKhoaId_IdOrderByIdAsc(Integer.parseInt(khoaId));
        } else {
            return this.nganhRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public Nganh addOrUpdate(Nganh nganh) {
        return this.nganhRepo.save(nganh);
    }

}
