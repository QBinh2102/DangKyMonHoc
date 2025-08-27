/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import com.tqb.DangKyMonHoc.pojo.LichHoc;
import com.tqb.DangKyMonHoc.pojo.MonHoc;
import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import com.tqb.DangKyMonHoc.repositories.BuoiHocRepository;
import com.tqb.DangKyMonHoc.repositories.LichHocRepository;
import com.tqb.DangKyMonHoc.repositories.QuyDinhRepository;
import com.tqb.DangKyMonHoc.services.BuoiHocService;
import com.tqb.DangKyMonHoc.services.LichHocService;
import com.tqb.DangKyMonHoc.services.QuyDinhService;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private QuyDinhRepository quyDinhRepo;

    @Autowired
    private BuoiHocRepository buoiHocRepo;

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
        if (lichHoc.getId() == null) {
            Date ngayBatDau = lichHoc.getNgayBatDau();
            BuoiHoc buoiHoc = this.buoiHocRepo.findById(lichHoc.getBuoiHocId().getId())
                    .orElseThrow(() -> new RuntimeException("BuoiHoc không tồn tại!"));
            MonHoc monHoc = buoiHoc.getMonHocId();

            // Set ngày kết thúc
            int soBuoi = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(ngayBatDau);
            if (lichHoc.getLoai().equals("LyThuyet")) {
                QuyDinh soBuoiTinChiLT = this.quyDinhRepo.findByTen("Số buổi 1 tín chỉ lý thuyết");
                if (soBuoiTinChiLT != null) {
                    soBuoi = soBuoiTinChiLT.getGiaTri() * monHoc.getTinChiLyThuyet();
                }
            } else {
                QuyDinh soBuoiTinChiTH = this.quyDinhRepo.findByTen("Số buổi 1 tín chỉ thực hành");
                if (soBuoiTinChiTH != null) {
                    soBuoi = soBuoiTinChiTH.getGiaTri() * monHoc.getTinChiThucHanh();
                }
            }
            cal.add(Calendar.WEEK_OF_YEAR, soBuoi - 1);
            Date ngayKetThuc = cal.getTime();
            lichHoc.setNgayKetThuc(ngayKetThuc);

            boolean isTrungLichHoc = this.lichHocRepo.existsLichHocTrung(lichHoc.getPhongHocId().getId(), lichHoc.getTietHocId().getGioBatDau(), ngayBatDau, ngayKetThuc);
            if (isTrungLichHoc) {
                throw new IllegalArgumentException("Trùng lịch học: phòng và giờ này đã được sử dụng trong thời gian đó.");
            }

//            // Set giờ kết thúc
//            QuyDinh soGioMotBuoi = this.quyDinhService.findByTen("Số giờ 1 buổi học");
//            if (soGioMotBuoi != null) {
//                int soGioHoc = soGioMotBuoi.getGiaTri();
//                LocalTime gioKetThuc = gioBatDau.plusHours(soGioHoc);
//                lichHoc.setGioKetThuc(gioKetThuc);
//            }
            // Set thứ
            Calendar calThu = Calendar.getInstance();
            calThu.setTime(ngayBatDau);
            int thu = calThu.get(Calendar.DAY_OF_WEEK) - 1;
            String[] tenThu = {
                "Chủ nhật",
                "Thứ 2",
                "Thứ 3",
                "Thứ 4",
                "Thứ 5",
                "Thứ 6",
                "Thứ 7"
            };

            lichHoc.setThu(tenThu[thu]);
        }
        return this.lichHocRepo.save(lichHoc);
    }

}
