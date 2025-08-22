/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu;
import com.tqb.DangKyMonHoc.repositories.ThoiKhoaBieuRepository;
import com.tqb.DangKyMonHoc.services.ThoiKhoaBieuService;
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
public class ThoiKhoaBieuServiceImpl implements ThoiKhoaBieuService{
    
    @Autowired
    private ThoiKhoaBieuRepository thoiKhoaBieuRepo;

    @Override
    public ThoiKhoaBieu findById(int id) {
        return this.thoiKhoaBieuRepo.findById(id);
    }

    @Override
    public List<ThoiKhoaBieu> findThoiKhoaBieu(Map<String, String> params) {
        String sinhVienId = params.get("sinhVienId");
        String hocKyId = params.get("hocKyId");
        boolean hasSinhVienId = sinhVienId!=null&&!sinhVienId.isEmpty();
        boolean hasHocKyId = hocKyId!=null&&!hocKyId.isEmpty();
        if(hasSinhVienId&&hasHocKyId){
            return this.thoiKhoaBieuRepo.findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(Integer.parseInt(sinhVienId), Integer.parseInt(hocKyId));
        }else{
            return this.thoiKhoaBieuRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public ThoiKhoaBieu add(ThoiKhoaBieu thoiKhoaBieu) {
        return this.thoiKhoaBieuRepo.save(thoiKhoaBieu);
    }

    @Override
    public ThoiKhoaBieu delete(ThoiKhoaBieu thoiKhoaBieu) {
        this.thoiKhoaBieuRepo.delete(thoiKhoaBieu);
        return thoiKhoaBieu;
    }

    @Override
    public List<ThoiKhoaBieu> findBySinhVienAndHocKy(int sinhVienId, int hocKyId, Date ngayBatDau, Date ngayKetThuc) {
        return this.thoiKhoaBieuRepo.findBySinhVienAndHocKy(sinhVienId, hocKyId, ngayBatDau, ngayKetThuc);
    }
    
}
