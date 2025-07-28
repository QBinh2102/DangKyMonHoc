package com.tqb.DangKyMonHoc;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import com.tqb.DangKyMonHoc.services.HocKyService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DangKyMonHocApplicationTests {

    @Autowired
    private HocKyService hocKyService;
    
    @Test
    void contextLoads() {
        List<HocKy> hocKyList = hocKyService.findAllByOrderByIdAsc();
        assertNotNull(hocKyList);
        hocKyList.forEach(h -> System.out.printf("Học kỳ %s năm học %s\n", h.getKy(), h.getNamHoc()));
    }

}
