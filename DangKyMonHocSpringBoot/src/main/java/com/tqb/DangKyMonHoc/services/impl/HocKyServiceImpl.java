/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import com.tqb.DangKyMonHoc.repositories.HocKyRepository;
import com.tqb.DangKyMonHoc.repositories.QuyDinhRepository;
import com.tqb.DangKyMonHoc.services.HocKyService;
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
public class HocKyServiceImpl implements HocKyService {

    @Autowired
    private HocKyRepository hocKyRepo;

    @Autowired
    private QuyDinhRepository quyDinhRepo;

    @Override
    public HocKy findById(int id) {
        return this.hocKyRepo.findById(id);
    }

    @Override
    public HocKy findTopByOrderByIdDesc() {
        return this.hocKyRepo.findTopByOrderByIdDesc();
    }
    
    @Override
    public Page<HocKy> findHocKyPage(Map<String,String> params) {
        String page = params.get("page");
        int size = 10;
        Pageable pageable = PageRequest.of(Integer.parseInt(page), size);
        return this.hocKyRepo.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public List<HocKy> findAllByOrderByIdDesc() {
        return this.hocKyRepo.findAllByOrderByIdDesc();
    }

    @Override
    public List<HocKy> findHocKyBySinhVienId(int sinhVienId) {
        return this.hocKyRepo.findHocKyBySinhVienId(sinhVienId);
    }

    @Override
    public HocKy add(HocKy hocKy) {
        if (hocKy.getId() == null) {
            QuyDinh quyDinh = this.quyDinhRepo.findByTen("Số tuần 1 học kỳ");
            if (quyDinh != null && hocKy.getNgayBatDau() != null) {
                int soTuan = quyDinh.getGiaTri();
                hocKy.setNgayKetThuc(hocKy.getNgayBatDau().plusWeeks(soTuan).minusDays(1));
            }
        }
        return this.hocKyRepo.save(hocKy);
    }

}
