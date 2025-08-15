'use client'

import './sinhvien.css';

const SinhVien = () => {
    return (
        <div>
            <div className="text-center">
                <h1>THÔNG TIN</h1>
            </div>
            <div class="thong-tin-container">
                <h2>Thông tin sinh viên</h2>
                <div class="info-row"><strong>Mã SV:</strong> 2251012017</div>
                <div class="info-row"><strong>Họ và tên:</strong> Tô Quốc Bình</div>
                <div class="info-row"><strong>Ngày sinh:</strong> 21/02/2004</div>
                <div class="info-row"><strong>Giới tính:</strong> Nam</div>
                <div class="info-row"><strong>Điện thoại:</strong> 0762590966</div>
                <div class="info-row"><strong>Email:</strong> 2251012017Binh@ou.edu.vn</div>
            </div>

            <div class="thong-tin-container">
                <h2>Thông tin khóa học</h2>
                <div class="info-row"><strong>Lớp:</strong> DH22CS01</div>
                <div class="info-row"><strong>Ngành:</strong> Khoa học máy tính</div>
                <div class="info-row"><strong>Chuyên ngành:</strong> Công nghệ phần mềm</div>
                <div class="info-row"><strong>Niên khóa:</strong> 2022-2026</div>
            </div>

        </div>
    );
}
export default SinhVien;