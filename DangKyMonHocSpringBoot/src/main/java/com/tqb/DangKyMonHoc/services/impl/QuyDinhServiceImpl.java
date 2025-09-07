/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import com.tqb.DangKyMonHoc.repositories.QuyDinhRepository;
import com.tqb.DangKyMonHoc.services.QuyDinhService;
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
public class QuyDinhServiceImpl implements QuyDinhService {

    @Autowired
    private QuyDinhRepository quyDinhRepo;

    @Override
    public QuyDinh findById(int id) {
        return this.quyDinhRepo.findById(id);
    }

    @Override
    public Page<QuyDinh> findQuyDinhPage(Map<String, String> params) {
        String page = params.get("page");
        String quyDinh = params.get("quyDinh");
        boolean hasQuyDinh = quyDinh != null && !quyDinh.isEmpty();

        int size = 10;
        Pageable pageable = PageRequest.of(Integer.parseInt(page), size);

        if (hasQuyDinh) {
            return this.quyDinhRepo.findByTenContainingIgnoreCaseOrderByIdAsc(quyDinh, pageable);
        } else {
            return this.quyDinhRepo.findAllByOrderByIdAsc(pageable);
        }
    }

    @Override
    public QuyDinh addOrUpdate(QuyDinh quyDinh) {
        return this.quyDinhRepo.save(quyDinh);
    }

    @Override
    public QuyDinh delete(QuyDinh quyDinh) {
        this.quyDinhRepo.delete(quyDinh);
        return quyDinh;
    }

    @Override
    public QuyDinh findByTen(String ten) {
        return this.quyDinhRepo.findByTen(ten);
    }

}
