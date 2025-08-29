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
        String tenMon = params.get("tenMon");
        String hocKyId = params.get("hocKyId");
        String khoaId = params.get("khoaId");
        boolean hasTenMon = tenMon != null && !tenMon.isEmpty();
        boolean hasHocKyId = hocKyId != null && !hocKyId.isEmpty();
        boolean hasKhoaId = khoaId != null && !khoaId.isEmpty();

        if (hasTenMon && hasKhoaId) {
            return this.monHocRepo.findByTenMonContainingIgnoreCaseAndKhoaId_IdOrderByIdAsc(tenMon, Integer.parseInt(khoaId));
        } else if (hasTenMon) {
            return this.monHocRepo.findByTenMonContainingIgnoreCaseOrderByIdAsc(tenMon);
        } else if (hasHocKyId && hasKhoaId) {
            return this.monHocRepo.findMonHocByHocKyAndKhoaFromDangKy(Integer.parseInt(hocKyId), Integer.parseInt(khoaId));
        } else {
            return this.monHocRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public MonHoc addOrUpdate(MonHoc monHoc) {
        return this.monHocRepo.save(monHoc);
    }

}
