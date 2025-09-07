/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.dto.BuoiHocDTO;
import com.tqb.DangKyMonHoc.pojo.DangKy;
import com.tqb.DangKyMonHoc.pojo.HocKy;
import com.tqb.DangKyMonHoc.pojo.HocPhi;
import com.tqb.DangKyMonHoc.pojo.MonHocLienQuan;
import com.tqb.DangKyMonHoc.pojo.SinhVien;
import com.tqb.DangKyMonHoc.repositories.BuoiHocRepository;
import com.tqb.DangKyMonHoc.repositories.DangKyRepository;
import com.tqb.DangKyMonHoc.repositories.HocKyRepository;
import com.tqb.DangKyMonHoc.repositories.HocPhiRepository;
import com.tqb.DangKyMonHoc.repositories.MonHocLienQuanRepository;
import com.tqb.DangKyMonHoc.repositories.SinhVienRepository;
import com.tqb.DangKyMonHoc.services.DangKyService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class DangKyServiceImpl implements DangKyService {

    @Autowired
    private DangKyRepository dangKyRepo;

    @Autowired
    private HocKyRepository hocKyRepo;

    @Autowired
    private BuoiHocRepository buoiHocRepo;

    @Autowired
    private SinhVienRepository sinhVienRepo;

    @Autowired
    private MonHocLienQuanRepository monHocLienQuanRepo;

    @Autowired
    private HocPhiRepository hocPhiRepo;

    @Override
    public DangKy findById(int id) {
        return this.dangKyRepo.findById(id);
    }

    @Override
    public List<DangKy> findDangKy(Map<String, String> params) {
        String sinhVienId = params.get("sinhVienId");
        boolean hasSinhVienId = sinhVienId != null && !sinhVienId.isEmpty();
        int hocKyLatest = this.hocKyRepo.findTopByOrderByIdDesc().getId();
        if (hasSinhVienId) {
            return this.dangKyRepo.findBySinhVienId_IdAndHocKyId_IdOrderByIdAsc(Integer.parseInt(sinhVienId), hocKyLatest);
        } else if (hasSinhVienId) {
            return this.dangKyRepo.findBySinhVienId_IdOrderByIdAsc(Integer.parseInt(sinhVienId));
        } else {
            return this.dangKyRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public DangKy add(DangKy dangKy) {
        int sinhVienId = dangKy.getSinhVienId().getId();
        int buoiHocId = dangKy.getBuoiHocId().getId();
        int monHocId = this.buoiHocRepo.findById(buoiHocId).getMonHocId().getId();
        int nganhId = this.sinhVienRepo.findById(sinhVienId).getNganhId().getId();
        HocKy hocKyLatest = this.hocKyRepo.findTopByOrderByIdDesc();

        //Kiểm tra sinh viên đã đăng ký môn học 
        DangKy existingDangKy = this.dangKyRepo.findBySinhVienId_IdAndBuoiHocId_MonHocId_IdAndHocKyId_Id(sinhVienId, monHocId, hocKyLatest.getId());
        if (existingDangKy != null) {
            throw new RuntimeException("Sinh viên đã đăng ký môn học này trong học kỳ hiện tại!");
        }

        //Kiểm tra đủ sĩ số
        BuoiHocDTO buoiHocDTO = this.buoiHocRepo.findBuoiHocTheoId(buoiHocId);
        if (buoiHocDTO.getConLai() == 0) {
            throw new RuntimeException("Lớp học này đã đủ sĩ số");
        }

        boolean isTrungLich = this.dangKyRepo.checkTrungLich(sinhVienId, hocKyLatest.getId(), buoiHocId);
        if (isTrungLich) {
            throw new RuntimeException("Bị trùng lịch học");
        }

        //Lấy các môn liên quan với môn đăng ký
        List<MonHocLienQuan> lienQuans = this.monHocLienQuanRepo.findByMonHocLienQuanPK_MonHocIdAndMonHocLienQuanPK_NganhId(monHocId, nganhId);
        for (MonHocLienQuan lq : lienQuans) {
            int monLienQuanId = lq.getMonHocLienQuanPK().getLienQuanId();

            //Duyệt đăng ký của sinh viên xem đã học môn đó chưa
            if (lq.getMonHocLienQuanPK().getLoai().equals("HOC_TRUOC")) {
                boolean isHocTruoc = this.dangKyRepo.checkHocTruoc(sinhVienId, hocKyLatest.getId(), monLienQuanId);
                if (!isHocTruoc) {
                    throw new RuntimeException("Bạn chưa học trước các môn yêu cầu! Mở đề cương môn học để biết thêm chi tiết.");
                }
            }

            //Duyệt đăng ký của sinh viên xem đã hoàn thành môn đó chưa
            if (lq.getMonHocLienQuanPK().getLoai().equals("TIEN_QUYET")) {
                boolean isTienQuyet = this.dangKyRepo.checkTienQuyet(sinhVienId, hocKyLatest.getId(), monLienQuanId);
                if (!isTienQuyet) {
                    throw new RuntimeException("Bạn chưa hoàn thành các môn yêu cầu! Mở đề cương môn học để biết thêm chi tiết.");
                }
            }
        }

        dangKy.setNgayDangKy(LocalDateTime.now());
        dangKy.setHocKyId(hocKyLatest);
        dangKy.setTrangThai("DANG_KY");

        //Tạo mới học phí cho sinh viên khi đăng ký môn kỳ mới
        HocPhi existingHocPhi = this.hocPhiRepo.findBySinhVienId_IdAndHocKyId_Id(sinhVienId, hocKyLatest.getId());
        if (existingHocPhi == null) {
            HocPhi newHocPhi = new HocPhi();
            newHocPhi.setSinhVienId(this.sinhVienRepo.findById(sinhVienId));
            newHocPhi.setHocKyId(hocKyLatest);
            newHocPhi.setTrangThai("CHUA_THANH_TOAN");
            this.hocPhiRepo.save(newHocPhi);
        }

        return this.dangKyRepo.save(dangKy);
    }

    @Override
    public DangKy delete(DangKy dangKy) {
        this.dangKyRepo.delete(dangKy);
        return dangKy;
    }

}
