/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import com.tqb.DangKyMonHoc.repositories.BuoiHocRepository;
import com.tqb.DangKyMonHoc.services.BuoiHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class BuoiHocServiceImpl implements BuoiHocService {

    @Autowired
    private BuoiHocRepository buoiHocRepo;

    @Override
    public BuoiHoc findById(int id) {
        return this.buoiHocRepo.findById(id);
    }

    @Override
    public List<BuoiHoc> findBuoiHoc(Map<String, String> params) {
        String monHocId = params.get("monHocId");
        String hocKyId = params.get("hocKyId");
        boolean hasMonHocId = monHocId != null && !monHocId.isEmpty();
        boolean hasHocKyId = hocKyId != null && !hocKyId.isEmpty();
        if (hasMonHocId && hasHocKyId) {
            return this.buoiHocRepo.findByMonHocId_IdAndHocKyId_IdOrderByIdAsc(Integer.parseInt(monHocId), Integer.parseInt(hocKyId));
        } else if (hasMonHocId) {
            return this.buoiHocRepo.findByMonHocId_IdOrderByIdAsc(Integer.parseInt(monHocId));
        } else if (hasHocKyId) {
            return this.buoiHocRepo.findByHocKyId_IdOrderByIdAsc(Integer.parseInt(hocKyId));
        } else {
            return this.buoiHocRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public BuoiHoc addOrUpdate(BuoiHoc buoiHoc) {
        return this.buoiHocRepo.save(buoiHoc);
    }

}
