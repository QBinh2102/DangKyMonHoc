/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.Lop;
import com.tqb.DangKyMonHoc.repositories.LopRepository;
import com.tqb.DangKyMonHoc.services.LopService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class LopServiceImpl implements LopService {

    @Autowired
    private LopRepository lopRepo;

    @Override
    public Lop findById(int id) {
        return this.lopRepo.findById(id);
    }

    @Override
    public List<Lop> findLop(Map<String, String> params) {
        String nganhId = params.get("nganhId");
        String maLop = params.get("maLop");
        boolean hasNganhId = nganhId != null && !nganhId.isEmpty();
        boolean hasMaLop = maLop != null && !maLop.isEmpty();
        if (hasMaLop) {
            return this.lopRepo.findByMaLopContainingIgnoreCaseOrderByIdAsc(maLop);
        } else if (hasNganhId) {
            return this.lopRepo.findLopChuaDay(Integer.parseInt(nganhId), LocalDate.now().getYear());
        } else {
            return this.lopRepo.findAllByOrderByIdDesc();
        }
    }

    @Override
    public Lop addOrUpdate(Lop lop) {
        if (lop.getId() == null) {
            if (lop.getKhoaHoc() == null) {
                lop.setKhoaHoc(LocalDate.now().getYear());
            }
        }
        return this.lopRepo.save(lop);
    }

}
