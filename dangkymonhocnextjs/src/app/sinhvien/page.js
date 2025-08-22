'use client'

import { useEffect, useState } from 'react';
import './sinhvien.css';
import { authApis, endpoints } from '@/configs/Apis';

const SinhVien = () => {

    const infoNguoiDung = [{
        label: "Họ và tên",
        field: "hoTen",
    }, {
        label: "Ngày sinh",
        field: "ngaySinh",
    }, {
        label: "Giới tính",
        field: "gioiTinh",
    }, {
        label: "Điện thoại",
        field: "soDienThoai",
    }, {
        label: "Email",
        field: "email",
    }]
    const [sinhVien, setSinhVien] = useState({});
    const [loading, setLoading] = useState(false);
    const [showChangePassword, setShowChangePassword] = useState(false);
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [msg, setMsg] = useState("");

    const getGioiTinh = (gt) => {
        if (gt === "nam") {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    const formatDate = (date) => {
        return new Date(date).toLocaleDateString("vi-VN");
    }

    const loadSinhVien = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['profile-sinhvien']);
            setSinhVien(res.data);
            console.log(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadSinhVien();
    }, []);

    const changePassword = async (e) => {
        e.preventDefault();
        setMsg("");
        try {
            await authApis().post(endpoints['change-password'], {
                oldPassword: oldPassword,
                newPassword: newPassword
            });
            setMsg("Đổi mật khẩu thành công!");
            setOldPassword("");
            setNewPassword("");
            setShowChangePassword(false);
        } catch (err) {
            console.error(err);
            setMsg("Đổi mật khẩu thất bại! Vui lòng kiểm tra lại.");
        }
    }

    return (
        <div>
            <div className="text-center mt-3 mb-3">
                <h1>THÔNG TIN</h1>
            </div>

            {loading ? (
                <div className='text-center'>
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (
                <div className="profile-container">
                    <div className="tool-section">
                        <div className='mb-3'>
                            <img
                                src={sinhVien.nguoiDung?.avatar}
                                alt="Ảnh đại diện"
                                style={{ maxWidth: "150px" }}
                            />
                        </div>
                        <button
                            className='btn btn-primary'
                            onClick={() => setShowChangePassword(!showChangePassword)}
                        >
                            Đổi mật khẩu
                        </button>

                        {
                            showChangePassword && (
                                <form onSubmit={changePassword} className="mt-3">
                                    <div className="mb-2">
                                        <input
                                            type="password"
                                            placeholder="Mật khẩu cũ"
                                            className="form-control"
                                            value={oldPassword}
                                            onChange={(e) => setOldPassword(e.target.value)}
                                            required
                                        />
                                    </div>
                                    <div className="mb-2">
                                        <input
                                            type="password"
                                            placeholder="Mật khẩu mới"
                                            className="form-control"
                                            value={newPassword}
                                            onChange={(e) => setNewPassword(e.target.value)}
                                            required
                                        />
                                    </div>
                                    <button type="submit" className="btn btn-success">
                                        Xác nhận
                                    </button>
                                </form>
                            )
                        }

                        {
                            msg && (
                                <p className="mt-2" style={{ color: msg.includes("thành công") ? "green" : "red" }}>
                                    {msg}
                                </p>
                            )
                        }
                    </div >

                    <div className="info-section">
                        <div className="thong-tin-container">
                            <h2>Thông tin sinh viên</h2>
                            {infoNguoiDung.map(nd => (
                                <div key={nd.field} className="info-row">
                                    <strong>{nd.label}</strong>: {nd.field === "gioiTinh" ? getGioiTinh(sinhVien.nguoiDung?.[nd.field]) :
                                        nd.field === "ngaySinh" ? formatDate(sinhVien.nguoiDung?.[nd.field]) : sinhVien.nguoiDung?.[nd.field]}
                                </div>
                            ))}
                        </div>

                        <div className="thong-tin-container">
                            <h2>Thông tin khóa học</h2>
                            <div className="info-row"><strong>Lớp:</strong> {sinhVien.lopId?.maLop}</div>
                            <div className="info-row"><strong>Khoa:</strong> {sinhVien.khoaId?.tenKhoa}</div>
                            <div className="info-row"><strong>Chuyên ngành:</strong> {sinhVien.nganhId?.tenNganh}</div>
                            <div className="info-row"><strong>Niên khóa:</strong> {sinhVien.khoaHoc}</div>
                        </div>
                    </div>
                </div >
            )}
        </div >
    );
}
export default SinhVien;