/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.dto.BuoiHocDTO;
import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import com.tqb.DangKyMonHoc.pojo.LichHoc;
import com.tqb.DangKyMonHoc.repositories.BuoiHocRepository;
import com.tqb.DangKyMonHoc.repositories.HocKyRepository;
import com.tqb.DangKyMonHoc.repositories.LichHocRepository;
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

    @Autowired
    private HocKyRepository hocKyRepo;
    
    @Autowired
    private LichHocRepository lichHocRepo;

    @Override
    public BuoiHoc findById(int id) {
        return this.buoiHocRepo.findById(id);
    }

    @Override
    public List<BuoiHoc> findBuoiHoc(Map<String, String> params) {
        String monHocId = params.get("monHocId");
        String hocKyId = params.get("hocKyId");
        String maLop = params.get("maLop");
        boolean hasMonHocId = monHocId != null && !monHocId.isEmpty();
        boolean hasHocKyId = hocKyId != null && !hocKyId.isEmpty();
        boolean hasMaLop = maLop != null && !maLop.isEmpty();

        if (hasMaLop && hasHocKyId){
            return this.buoiHocRepo.findByHocKyId_IdAndLopId_MaLopContainingIgnoreCaseOrderByIdAsc(Integer.parseInt(hocKyId), maLop);
        }else if (hasMaLop) {
            return this.buoiHocRepo.findByLopId_MaLopContainingIgnoreCaseOrderByIdAsc(maLop);
        } else if (hasMonHocId) {
            return this.buoiHocRepo.findByMonHocId_IdOrderByIdAsc(Integer.parseInt(monHocId));
        } else if (hasHocKyId) {
            return this.buoiHocRepo.findByHocKyId_IdOrderByIdAsc(Integer.parseInt(hocKyId));
        } else {
            return this.buoiHocRepo.findAllByOrderByIdDesc();
        }
    }

    @Override
    public BuoiHoc addOrUpdate(BuoiHoc buoiHoc) {
        if (buoiHoc.getId() == null) {
            buoiHoc.setHocKyId(this.hocKyRepo.findTopByOrderByIdDesc());
        }
        return this.buoiHocRepo.save(buoiHoc);
    }

    @Override
    public List<BuoiHocDTO> findDanhSachBuoiHocDangKy(Map<String, String> params) {
        String lopId = params.get("lopId");
        String monHocId = params.get("monHocId");

        boolean hasMonHocId = monHocId != null && !monHocId.trim().isEmpty();
        boolean hasLopId = lopId != null && !lopId.trim().isEmpty();

        int hocKyLatest = this.hocKyRepo.findTopByOrderByIdDesc().getId();

        if (hasMonHocId) {
            List<BuoiHocDTO> list = this.buoiHocRepo.findBuoiHocTheoHocKyVaMonHoc(hocKyLatest, Integer.parseInt(monHocId));
            for (BuoiHocDTO dto : list) {
                List<LichHoc> lichHoc = this.lichHocRepo.findByBuoiHocId_IdOrderByIdAsc(dto.getBuoiHocId());
                dto.setListLichHoc(lichHoc);
            }
            return list;
        } else if (hasLopId) {
            List<BuoiHocDTO> list = this.buoiHocRepo.findBuoiHocTheoHocKyVaNganh(hocKyLatest, Integer.parseInt(lopId));
            for (BuoiHocDTO dto : list) {
                List<LichHoc> lichHoc = this.lichHocRepo.findByBuoiHocId_IdOrderByIdAsc(dto.getBuoiHocId());
                dto.setListLichHoc(lichHoc);
            }
            return list;
        } else {
            throw new IllegalArgumentException("Thiếu tham số 'lopId' hoặc 'monHocId'");
        }
    }
}
