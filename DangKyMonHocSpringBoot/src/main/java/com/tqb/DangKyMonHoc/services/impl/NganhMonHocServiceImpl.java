/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import com.tqb.DangKyMonHoc.pojo.Nganh;
import com.tqb.DangKyMonHoc.pojo.NganhMonHoc;
import com.tqb.DangKyMonHoc.pojo.NganhMonHocPK;
import com.tqb.DangKyMonHoc.repositories.MonHocRepository;
import com.tqb.DangKyMonHoc.repositories.NganhMonHocRepository;
import com.tqb.DangKyMonHoc.repositories.NganhRepository;
import com.tqb.DangKyMonHoc.services.NganhMonHocService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class NganhMonHocServiceImpl implements NganhMonHocService {

    @Autowired
    private NganhMonHocRepository nganhMonHocRepo;

    @Autowired
    private NganhRepository nganhRepo;

    @Autowired
    private MonHocRepository monHocRepo;

    @Override
    public List<NganhMonHoc> findNganhMonHoc(Map<String, String> params) {
        String nganhId = params.get("nganhId");
        String monHocId = params.get("monHocId");
        boolean hasNganhId = nganhId != null && !nganhId.isEmpty();
        boolean hasMonHocId = monHocId != null && !monHocId.isEmpty();
        if (hasNganhId && hasMonHocId) {
            return this.nganhMonHocRepo.findById_NganhIdAndId_MonHocId(Integer.parseInt(nganhId), Integer.parseInt(monHocId));
        } else if (hasNganhId) {
            return this.nganhMonHocRepo.findById_NganhId(Integer.parseInt(nganhId));
        } else if (hasMonHocId) {
            return this.nganhMonHocRepo.findById_MonHocId(Integer.parseInt(monHocId));
        } else {
            return this.nganhMonHocRepo.findAllByOrderById_NganhIdAscId_MonHocIdAsc();
        }
    }

    @Override
    public NganhMonHoc add(NganhMonHoc nganhMonHoc) {
        NganhMonHocPK id = nganhMonHoc.getId();
        if (this.nganhMonHocRepo.existsById(id)) {
            throw new RuntimeException("Bản ghi đã tồn tại!");
        }

        Nganh nganh = this.nganhRepo.findById(id.getNganhId());
        if (nganh == null) {
            throw new RuntimeException("Không tìm thấy ngành!");
        }
        nganhMonHoc.setNganh(nganh);

        MonHoc monHoc = this.monHocRepo.findById(id.getMonHocId());
        if (monHoc == null) {
            throw new RuntimeException("Không tìm thấy ngành!");
        }
        nganhMonHoc.setMonHoc(monHoc);

        return this.nganhMonHocRepo.save(nganhMonHoc);
    }

    @Override
    public void deleteByIdMonHocId(int monHocId) {
        List<NganhMonHoc> danhSach = this.nganhMonHocRepo.findById_MonHocId(monHocId);
        this.nganhMonHocRepo.deleteAll(danhSach);
    }

}
