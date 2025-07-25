/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.LichHoc;
import com.tqb.DangKyMonHoc.repositories.LichHocRepository;
import com.tqb.DangKyMonHoc.services.LichHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class LichHocServiceImpl implements LichHocService {

    @Autowired
    private LichHocRepository lichHocRepo;

    @Override
    public LichHoc findById(int id) {
        return this.lichHocRepo.findById(id);
    }

    @Override
    public List<LichHoc> findLichHoc(Map<String, String> params) {
        String buoiHocId = params.get("buoiHocId");
        boolean hasBuoiHocId = buoiHocId != null && !buoiHocId.isEmpty();
        if (hasBuoiHocId) {
            return this.lichHocRepo.findByBuoiHocId_IdOrderByIdAsc(Integer.parseInt(buoiHocId));
        } else {
            return this.lichHocRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public LichHoc addOrUpdate(LichHoc lichHoc) {
        return this.lichHocRepo.save(lichHoc);
    }

}
