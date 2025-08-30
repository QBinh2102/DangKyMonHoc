/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import com.tqb.DangKyMonHoc.pojo.ChiTietHocPhi;
import com.tqb.DangKyMonHoc.pojo.HocPhi;
import com.tqb.DangKyMonHoc.pojo.MonHoc;
import com.tqb.DangKyMonHoc.repositories.BuoiHocRepository;
import com.tqb.DangKyMonHoc.repositories.ChiTietHocPhiRepository;
import com.tqb.DangKyMonHoc.repositories.HocKyRepository;
import com.tqb.DangKyMonHoc.repositories.HocPhiRepository;
import com.tqb.DangKyMonHoc.repositories.QuyDinhRepository;
import com.tqb.DangKyMonHoc.services.ChiTietHocPhiService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class ChiTietHocPhiServiceImpl implements ChiTietHocPhiService {

    @Autowired
    private ChiTietHocPhiRepository chiTietHocPhiRepo;

    @Autowired
    private HocPhiRepository hocPhiRepo;

    @Autowired
    private HocKyRepository hocKyRepo;

    @Autowired
    private BuoiHocRepository buoiHocRepo;

    @Autowired
    private QuyDinhRepository quyDinhRepo;

    @Override
    public List<ChiTietHocPhi> findChiTietHocPhiBySinhVienAndHocKy(int sinhVienId, int hocKyId) {
        return this.chiTietHocPhiRepo.findByHocPhiId_SinhVienId_IdAndHocPhiId_HocKyId_IdOrderByIdAsc(sinhVienId, hocKyId);
    }

    @Override
    public ChiTietHocPhi add(Map<String, String> params) {
        int hocPhiId = Integer.parseInt(params.get("hocPhiId"));
        int buoiHocId = Integer.parseInt(params.get("buoiHocId"));
        BuoiHoc buoiHoc = this.buoiHocRepo.findById(buoiHocId);
        MonHoc monHoc = buoiHoc.getMonHocId();
        
        int tongTinChi = monHoc.getTinChiLyThuyet() + monHoc.getTinChiThucHanh();
        BigDecimal donGia = BigDecimal.valueOf(this.quyDinhRepo.findByTen("Số tiền 1 tín chỉ").getGiaTri());
        BigDecimal chiPhi = BigDecimal.valueOf(tongTinChi).multiply(donGia);

        ChiTietHocPhi newChiTietHocPhi = new ChiTietHocPhi();
        newChiTietHocPhi.setHocPhiId(this.hocPhiRepo.findById(hocPhiId));
        newChiTietHocPhi.setMonHocId(monHoc);
        newChiTietHocPhi.setChiPhi(chiPhi);

        ChiTietHocPhi saved = this.chiTietHocPhiRepo.save(newChiTietHocPhi);
        
        HocPhi hocPhi = this.hocPhiRepo.findById(hocPhiId);
        BigDecimal tongTien = this.chiTietHocPhiRepo.sumSoTienByHocPhiId(hocPhiId);
        hocPhi.setTongTien(tongTien);
        this.hocPhiRepo.save(hocPhi);

        return saved;
    }

    @Override
    public void delete(int sinhVienId, int buoiHocId) {
        HocPhi hocPhi = this.hocPhiRepo.findBySinhVienId_IdAndHocKyId_Id(sinhVienId, this.hocKyRepo.findTopByOrderByIdDesc().getId());
        
        BuoiHoc buoiHoc = this.buoiHocRepo.findById(buoiHocId);
        this.chiTietHocPhiRepo.deleteByHocPhiId_IdAndMonHocId_Id(hocPhi.getId(), buoiHoc.getMonHocId().getId());

        boolean conChiTietHocPhi = this.chiTietHocPhiRepo.existsByHocPhiId_Id(hocPhi.getId());

        if (!conChiTietHocPhi) {
            this.hocPhiRepo.delete(hocPhi);
        } else {
            BigDecimal tongTien = this.chiTietHocPhiRepo.sumSoTienByHocPhiId(hocPhi.getId());
            hocPhi.setTongTien(tongTien);
            this.hocPhiRepo.save(hocPhi);
        }
    }

    @Override
    public List<ChiTietHocPhi> findChiTietHocPhiByHocPhiId(int hocPhiId) {
        return this.chiTietHocPhiRepo.findByHocPhiId_Id(hocPhiId);
    }

}
