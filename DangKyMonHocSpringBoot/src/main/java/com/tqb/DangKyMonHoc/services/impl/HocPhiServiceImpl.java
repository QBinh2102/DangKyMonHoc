/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.HocPhi;
import com.tqb.DangKyMonHoc.repositories.HocKyRepository;
import com.tqb.DangKyMonHoc.repositories.HocPhiRepository;
import com.tqb.DangKyMonHoc.services.HocPhiService;
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
public class HocPhiServiceImpl implements HocPhiService {

    @Autowired
    private HocPhiRepository hocPhiRepo;

    @Autowired
    private HocKyRepository hocKyRepo;

    @Override
    public HocPhi findById(int id) {
        return this.hocPhiRepo.findById(id);
    }

    @Override
    public List<HocPhi> findHocPhi(Map<String, String> params) {
        String sinhVienId = params.get("sinhVienId");
        boolean hasSinhVienId = sinhVienId != null && !sinhVienId.isEmpty();

        if (hasSinhVienId) {
            return this.hocPhiRepo.findBySinhVienId_IdOrderByIdAsc(Integer.parseInt(sinhVienId));
        } else {
            return this.hocPhiRepo.findAllByOrderByIdDesc();
        }
    }

    @Override
    public HocPhi addOrUpdate(HocPhi hocPhi) {
        if (hocPhi.getId() == null) {
            hocPhi.setTrangThai("CHUA_THANH_TOAN");
            hocPhi.setHocKyId(this.hocKyRepo.findTopByOrderByIdDesc());
        }
        return this.hocPhiRepo.save(hocPhi);
    }

    @Override
    public HocPhi findHocPhiMoiNhatTheoSinhVien(int sinhVienId) {
        return this.hocPhiRepo.findBySinhVienId_IdAndHocKyId_Id(sinhVienId, this.hocKyRepo.findTopByOrderByIdDesc().getId());
    }

    @Override
    public Page<HocPhi> findHocPhiPage(Map<String, String> params) {
        String page = params.get("page");
        String hoTenSV = params.get("hoTen");
        String trangThai = params.get("trangThai");
        boolean hasHoTenSV = hoTenSV != null && !hoTenSV.isEmpty();
        boolean hasTrangThai = trangThai != null && !trangThai.isEmpty();

        int size = 10;
        Pageable pageable = PageRequest.of(Integer.parseInt(page), size);

        if (hasHoTenSV && hasTrangThai) {
            return this.hocPhiRepo.findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseAndTrangThaiOrderByIdDesc(hoTenSV, trangThai, pageable);
        } else if (hasHoTenSV) {
            return this.hocPhiRepo.findBySinhVienId_NguoiDung_HoTenContainingIgnoreCaseOrderByIdDesc(hoTenSV, pageable);
        } else if (hasTrangThai) {
            return this.hocPhiRepo.findByTrangThaiOrderByIdDesc(trangThai, pageable);
        } else {
            return this.hocPhiRepo.findAllByOrderByIdDesc(pageable);
        }
    }

}
