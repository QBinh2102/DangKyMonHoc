import axios from "axios";
import cookie from 'react-cookies';

const BASE_URL = "http://localhost:8080/api";

export const endpoints = {
    // Người dùng
    'current-user': '/secure/profile',
    'profile-sinhvien': '/secure/sinhvien/me',
    'change-password': '/secure/profile/changepassword',
    'dangNhap': '/login',
    // Học kỳ
    'hocKy': '/secure/admin/hocky',
    'hocKyPage': '/secure/admin/hocky-page',
    'hocKyMoiNhat': '/secure/hocky/latest',
    'hocKyBySinhVien': '/secure/me/hocky',
    'hocKyId': (hocKyId) => `/secure/admin/hocky/${hocKyId}`,
    // Tiết học
    'tietHoc': '/tiethoc',
    'tietHocId': (tietHocId) => `/tiethoc/${tietHocId}`,
    'themTietHoc': '/secure/admin/tiethoc',
    'suaTietHocId': (tietHocId) => `/secure/admin/tiethoc/${tietHocId}`,
    // Phòng học
    'phongHoc': '/phonghoc',
    'phongHocPage': '/phonghoc-page',
    'phongHocId': (phongHocId) => `/phonghoc/${phongHocId}`,
    'themPhongHoc': '/secure/admin/phonghoc',
    'suaPhongHocId': (phongHocId) => `/secure/admin/phonghoc/${phongHocId}`,
    // Khoa
    'khoa': '/khoa',
    'khoaPage': '/khoa-page',
    'khoaId': (khoaId) => `/khoa/${khoaId}`,
    'themKhoa': '/secure/admin/khoa',
    'suaKhoaId': (khoaId) => `/secure/admin/khoa/${khoaId}`,
    // Ngành
    'nganh': '/nganh',
    'nganhPage': '/nganh-page',
    'nganhId': (nganhId) => `/nganh/${nganhId}`,
    'themNganh': '/secure/admin/nganh',
    'suaNganhId': (nganhId) => `/secure/admin/nganh/${nganhId}`,
    // Môn học
    'monHoc': '/monhoc',
    'monHocPage': '/monhoc-page',
    'monHocId': (monHocId) => `/monhoc/${monHocId}`,
    'themMonHoc': '/secure/admin/monhoc',
    'suaMonHocId': (monHocId) => `/secure/admin/monhoc/${monHocId}`,
    // Ngành môn học
    'nganhMonHoc': '/nganhmonhoc',
    'themNganhMonHoc': '/secure/admin/nganhmonhoc',
    'xoaNganhMonHocTheoMonHocId': (monHocId) => `/secure/admin/nganhmonhoc/${monHocId}`,
    // Môn học liên quan
    'monHocLienQuan': '/monhoclienquan',
    'themHoacXoaMonHocLienQuan': '/secure/admin/monhoclienquan',
    // Lớp
    'lop': '/lop',
    'lopPage': '/lop-page',
    'lopId': (lopId) => `/lop/${lopId}`,
    'themLop': '/secure/admin/lop',
    'suaLopId': (lopId) => `/secure/admin/lop/${lopId}`,
    // Giảng viên
    'giangVienPage': '/secure/admin/giangvien-page',
    'themHoacLayGiangVien': '/secure/admin/giangvien',
    'suaHoacLayGiangVienId': (giangVienId) => `/secure/admin/giangvien/${giangVienId}`,
    // Sinh viên
    'sinhVienPage': '/secure/admin/sinhvien-page',
    'themHoacLaySinhVien': '/secure/admin/sinhvien',
    'suaHoacLaySinhVienId': (sinhVienId) => `/secure/admin/sinhvien/${sinhVienId}`,
    // Buổi học
    'buoiHocPage': '/secure/admin/buoihoc-page',
    'themHoacLayBuoiHoc': '/secure/admin/buoihoc',
    'suaHoacLayBuoiHocId': (buoiHocId) => `/secure/admin/buoihoc/${buoiHocId}`,
    // Lịch học
    'lichHoc': '/lichhoc',
    'lichHocId': (lichHocId) => `/lichhoc/${lichHocId}`,
    'themLichHoc': '/secure/admin/lichhoc',
    // Quy định
    'quyDinhPage': '/quydinh-page',
    'quyDinhId': (quyDinhId) => `/quydinh/${quyDinhId}`,
    'themQuyDinh': '/secure/admin/quydinh',
    'suaHoacXoaQuyDinhId': (quyDinhId) => `/secure/admin/quydinh/${quyDinhId}`,
    // Thống kê
    'thongKe': '/secure/admin/thongke',
    'thongKeTheoLop': '/secure/admin/thongketheolop',
    // Điểm
    'diemSinhVien': '/secure/me/diem',
    // Tổng kết
    'tongKetSinhVien': '/secure/me/tongket',
    // Thời khóa biểu
    'thoiKhoaBieuChoSinhVien': '/secure/admin/thoikhoabieu',
    'thoiKhoaBieuSinhVien': '/secure/me/thoikhoabieu',
    // Đăng ký
    'layBuoiHoc': '/secure/buoihoc',
    'dangKyChoSinhVien': '/secure/admin/dangky',
    'dangKy': '/secure/me/dangky',
    'xoaDangKy': (dangKyId) => `/secure/me/dangky/${dangKyId}`,
    // Học phí
    'hocPhiPage': '/secure/admin/hocphi-page',
    'layHoacSuaHocPhiId': (hocPhiId) => `/secure/hocphi/${hocPhiId}`,
    'hocPhiMoiNhatBySinhVien': '/secure/admin/hocphi/latest',
    'hocPhiMoiNhat': '/secure/me/hocphi/latest',
    'hocPhiSinhVien': '/secure/me/hocphi',
    // Chi tiểt học phí
    'chiTietHocPhi': '/secure/admin/chitiethocphi',
    'chiTietHocPhiSinhVien': '/secure/me/chitiethocphi',

}

export const authApis = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
})