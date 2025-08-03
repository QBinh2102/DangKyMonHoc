import axios from "axios";
import cookie from 'react-cookies';

const BASE_URL = "http://localhost:8080/api";

export const endpoints = {
    'current-user': '/secure/profile',
    'dangNhap': '/login',
    //Học kỳ
    'hocKy': '/secure/admin/hocky',
    'hocKyMoiNhat': '/secure/admin/hocKy/latest',
    'hocKyId': (hocKyId) => `/secure/admin/hocky/${hocKyId}`,
    //Khoa
    'khoa': '/khoa',
    'khoaId': (khoaId) => `/khoa/${khoaId}`,
    'themKhoa': '/secure/admin/khoa',
    'suaKhoaId': (khoaId) => `/secure/admin/khoa/${khoaId}`,
    //Ngành
    'nganh': '/nganh',
    'nganhId': (nganhId) => `/nganh/${nganhId}`,
    'themNganh': '/secure/admin/nganh',
    'suaNganhId': (nganhId) => `/secure/admin/nganh/${nganhId}`,
    //Giảng viên
    'themHoacLayGiangVien': '/secure/admin/giangvien',
    'suaHoacLayGiangVienId': (giangVienId) => `/secure/admin/giangvien/${giangVienId}`,
    //Sinh viên
    'themHoacLaySinhVien': '/secure/admin/sinhvien',
    'suaHoacLaySinhVienId': (sinhVienId) => `/secure/admin/sinhvien/${sinhVienId}`,
    //Buổi học
    'themHoacLayBuoiHoc': '/secure/admin/buoihoc',
    'suaHoacLayBuoiHocId': (buoiHocId) => `/secure/admin/buoihoc/${buoiHocId}`,
    //Quy định
    'quyDinh': '/quydinh',
    'quyDinhId': (quyDinhId) => `/quydinh/${quyDinhId}`,
    'themQuyDinh': '/secure/admin/quydinh',
    'suaHoacXoaQuyDinhId': (quyDinhId) => `/secure/admin/quydinh/${quyDinhId}`,
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