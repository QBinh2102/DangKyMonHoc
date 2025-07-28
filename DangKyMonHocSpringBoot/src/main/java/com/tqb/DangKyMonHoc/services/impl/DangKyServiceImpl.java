/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.DangKy;
import com.tqb.DangKyMonHoc.repositories.DangKyRepository;
import com.tqb.DangKyMonHoc.services.DangKyService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class DangKyServiceImpl implements DangKyService {

    @Autowired
    private DangKyRepository dangKyRepo;

    @Override
    public DangKy findById(int id) {
        return this.dangKyRepo.findById(id);
    }

    @Override
    public List<DangKy> findDangKy(Map<String, String> params) {
        String sinhVienId = params.get("sinhVienId");
        String hocKyId = params.get("hocKyId");
        boolean hasSinhVienId = sinhVienId != null && !sinhVienId.isEmpty();
        boolean hasHocKyId = hocKyId != null && !hocKyId.isEmpty();
        if (hasSinhVienId && hasHocKyId) {
            return this.dangKyRepo.findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(Integer.parseInt(sinhVienId), Integer.parseInt(hocKyId));
        } else if (hasSinhVienId) {
            return this.dangKyRepo.findBySinhVienId_IdOrderByIdAsc(Integer.parseInt(sinhVienId));
        } else {
            return this.dangKyRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public DangKy addOrUpdate(DangKy dangKy) {
        if (dangKy.getNgayDangKy() == null) {
            dangKy.setNgayDangKy(LocalDateTime.now()); // hoặc LocalDateTime.now() nếu dùng Java 8+
        }
        return this.dangKyRepo.save(dangKy);
    }

    @Override
    public DangKy delete(DangKy dangKy) {
        this.dangKyRepo.delete(dangKy);
        return dangKy;
    }

}
