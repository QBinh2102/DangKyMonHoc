/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.dto.TongKetHocKyDTO;
import com.tqb.DangKyMonHoc.repositories.DiemRepository;
import com.tqb.DangKyMonHoc.services.TongKetService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class TongKetServiceImpl implements TongKetService {

    @Autowired
    private DiemRepository diemRepo;

    @Override
    public List<TongKetHocKyDTO> getTongKetHocKy(int sinhVienId) {
        return diemRepo.callTongKetHocKy(sinhVienId)
                .stream()
                .map(row -> new TongKetHocKyDTO(
                ((Number) row[0]).intValue(),
                row[1].toString(),
                row[2].toString(),
                ((Number) row[3]).doubleValue(),
                ((Number) row[4]).intValue(),
                ((Number) row[5]).doubleValue(),
                ((Number) row[6]).intValue()
        ))
                .collect(Collectors.toList());
    }

}
