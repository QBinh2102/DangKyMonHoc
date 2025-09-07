/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.MonHocLienQuan;
import com.tqb.DangKyMonHoc.pojo.MonHocLienQuanPK;
import com.tqb.DangKyMonHoc.repositories.MonHocLienQuanRepository;
import com.tqb.DangKyMonHoc.services.MonHocLienQuanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class MonHocLienQuanServiceImpl implements MonHocLienQuanService {

    @Autowired
    private MonHocLienQuanRepository monHocLienQuanRepo;

    @Override
    public List<MonHocLienQuan> findMonHocLienQuan(Map<String, String> params) {
        String monHocId = params.get("monHocId");
        String loai = params.get("loai");
        boolean hasMonHocId = monHocId != null && !monHocId.isEmpty();
        boolean hasLoai = loai != null && !loai.isEmpty();
        if (hasMonHocId && hasLoai) {
            return this.monHocLienQuanRepo.findByMonHocLienQuanPK_MonHocIdAndMonHocLienQuanPK_Loai(Integer.parseInt(monHocId), loai);
        } else if (hasMonHocId) {
            return this.monHocLienQuanRepo.findByMonHocLienQuanPK_MonHocId(Integer.parseInt(monHocId));
        } else if (hasLoai) {
            return this.monHocLienQuanRepo.findByMonHocLienQuanPK_Loai(loai);
        } else {
            return this.monHocLienQuanRepo.findAllByOrderByMonHocLienQuanPK_MonHocIdAsc();
        }
    }

    @Override
    public MonHocLienQuan add(MonHocLienQuan monHocLienQuan) {
        if (monHocLienQuanRepo.existsById(monHocLienQuan.getMonHocLienQuanPK())) {
            throw new IllegalArgumentException("Bản ghi đã tồn tại!");
        }
        return monHocLienQuanRepo.save(monHocLienQuan);
    }

    @Override
    public boolean delete(MonHocLienQuanPK id) {
        if (!monHocLienQuanRepo.existsById(id)) {
            return false;
        }
        monHocLienQuanRepo.deleteById(id);
        return true;
    }

}
