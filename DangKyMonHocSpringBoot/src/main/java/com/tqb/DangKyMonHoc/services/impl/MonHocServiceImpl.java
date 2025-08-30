/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import com.tqb.DangKyMonHoc.repositories.MonHocRepository;
import com.tqb.DangKyMonHoc.services.MonHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class MonHocServiceImpl implements MonHocService {

    @Autowired
    private MonHocRepository monHocRepo;

    @Override
    public MonHoc findById(int id) {
        return this.monHocRepo.findById(id);
    }

    @Override
    public List<MonHoc> findMonHoc(Map<String, String> params) {
        String hocKyId = params.get("hocKyId");
        String khoaId = params.get("khoaId");
        boolean hasHocKyId = hocKyId != null && !hocKyId.isEmpty();
        boolean hasKhoaId = khoaId != null && !khoaId.isEmpty();

        if (hasHocKyId && hasKhoaId) {
            return this.monHocRepo.findMonHocByHocKyAndKhoaFromDangKy(Integer.parseInt(hocKyId), Integer.parseInt(khoaId));
        } else {
            return this.monHocRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public MonHoc addOrUpdate(MonHoc monHoc) {
        return this.monHocRepo.save(monHoc);
    }

    @Override
    public Page<MonHoc> findMonHocPage(Map<String, String> params) {
        String page = params.get("page");
        String tenMon = params.get("tenMon");
        String khoaId = params.get("khoaId");
        boolean hasTenMon = tenMon != null && !tenMon.isEmpty();
        boolean hasKhoaId = khoaId != null && !khoaId.isEmpty();

        int size = 10;
        Pageable pageable = PageRequest.of(Integer.parseInt(page), size);
        
        if (hasTenMon && hasKhoaId) {
            return this.monHocRepo.findByTenMonContainingIgnoreCaseAndKhoaId_IdOrderByIdAsc(tenMon, Integer.parseInt(khoaId), pageable);
        } else if (hasTenMon) {
            return this.monHocRepo.findByTenMonContainingIgnoreCaseOrderByIdAsc(tenMon, pageable);
        } else if (hasKhoaId) {
            return this.monHocRepo.findByKhoaId_IdOrderByIdAsc(Integer.parseInt(khoaId), pageable);
        } else {
            return this.monHocRepo.findAllByOrderByIdAsc(pageable);
        }
    }

}
