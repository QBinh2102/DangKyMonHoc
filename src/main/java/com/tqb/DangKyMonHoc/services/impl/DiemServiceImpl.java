/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.Diem;
import com.tqb.DangKyMonHoc.repositories.DiemRepository;
import com.tqb.DangKyMonHoc.services.DiemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class DiemServiceImpl implements DiemService {

    @Autowired
    private DiemRepository diemRepo;

    @Override
    public Diem findById(int id) {
        return this.diemRepo.findById(id);
    }

    @Override
    public List<Diem> findDiem(Map<String, String> params) {
        String sinhVienId = params.get("sinhVienId");
        String monHocId = params.get("monHocId");
        String hocKyId = params.get("hocKyId");
        String loai = params.get("loai");
        boolean hasSinhVienId = sinhVienId != null && !sinhVienId.isEmpty();
        boolean hasMonHocId = monHocId != null && !monHocId.isEmpty();
        boolean hasHocKyId = hocKyId != null && !hocKyId.isEmpty();
        boolean hasLoai = loai != null && !loai.isEmpty();
        if (hasSinhVienId && hasMonHocId) {
            return this.diemRepo.findBySinhVienId_IdAndMonHocId_IdOrderByIdAsc(Integer.parseInt(sinhVienId), Integer.parseInt(monHocId));
        } else if (hasSinhVienId && hasHocKyId) {
            return this.diemRepo.findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(Integer.parseInt(sinhVienId), Integer.parseInt(hocKyId));
        } else if (hasMonHocId && hasHocKyId && hasLoai) {
            return this.diemRepo.findByMonHocId_IdAndHocKyId_IdAndLoaiOrderByIdAsc(Integer.parseInt(monHocId), Integer.parseInt(hocKyId), loai);
        } else if (hasSinhVienId) {
            return this.diemRepo.findBySinhVienId_IdOrderByIdAsc(Integer.parseInt(sinhVienId));
        } else {
            return this.diemRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public Diem addOrUpdate(Diem diem) {
        return this.diemRepo.save(diem);
    }

}
