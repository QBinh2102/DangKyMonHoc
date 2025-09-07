/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.dto.ThongKeDTO;
import com.tqb.DangKyMonHoc.dto.ThongKeTheoLopDTO;
import com.tqb.DangKyMonHoc.repositories.DangKyRepository;
import com.tqb.DangKyMonHoc.services.ThongKeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    private DangKyRepository dangKyRepo;

    @Override
    public ThongKeDTO thongKe(int hocKyId, int khoaId, int monHocId) {
        return this.dangKyRepo.thongKe(hocKyId, khoaId, monHocId);
    }

    @Override
    public List<ThongKeTheoLopDTO> thongKeTheoLop(int hocKyId, int monHocId) {
        return this.dangKyRepo.thongKeTheoLop(hocKyId, monHocId);
    }

}
