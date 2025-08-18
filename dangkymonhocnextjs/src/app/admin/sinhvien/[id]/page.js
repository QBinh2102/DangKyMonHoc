'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaSinhVien = () => {

    const info = [{
        label: "Họ tên",
        field: "hoTen",
        type: "text",
        disabled: true,
    }, {
        label: "Ngày sinh",
        field: "ngaySinh",
        type: "text",
        disabled: true,
    }, {
        label: "Giới tính",
        field: "gioiTinh",
        type: "text",
        disabled: true,
    }, {
        label: "Số điện thoại",
        field: "soDienThoai",
        type: "tel",
        disabled: false,
    }, {
        label: "Email",
        field: "email",
        type: "email",
        disabled: true,
    }, {
        label: "Mật khẩu",
        field: "matKhau",
        type: "password",
        disabled: false,
    }, {
        label: "Số tín chỉ",
        field: "soTinChi",
        type: "text",
        disabled: true,
    }];
    const { id } = useParams();
    const [sinhVien, setSinhVien] = useState({
        nguoiDung: {},
    });
    const [newPassword, setNewPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadSinhVien = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['suaHoacLaySinhVienId'](id));
            setSinhVien(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadSinhVien();
    }, [id]);

    const getGioiTinh = (gt) => {
        if (gt === "nam") {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    const updateSinhVien = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);

        const updatedSV = { ...sinhVien };
        if (newPassword.trim() !== "") {
            updatedSV.nguoiDung.matKhau = newPassword;
        } else {
            delete updatedSV.nguoiDung.matKhau;
        }

        try {
            await authApis().put(endpoints['suaHoacLaySinhVienId'](id), updatedSV);
            setMsg("Cập nhật thành công!");
            setNewPassword("");
            await loadSinhVien();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
            console.error(ex);
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center">
                <h1>THÔNG TIN SINH VIÊN</h1>
            </div>

            {msg && (
                msg.includes("thành công") ? (
                    <div className="alert alert-success text-center" role="alert">
                        {msg}
                    </div>
                ) : (
                    <div className="alert alert-warning text-center" role="alert">
                        {msg}
                    </div>
                )
            )}

            {loading ? (
                <div className="text-center">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (
                <div>
                    <form onSubmit={updateSinhVien} className="w-50 mx-auto">
                        {sinhVien.nguoiDung.avatar && (
                            <div className="mt-3 text-center">
                                <img
                                    src={sinhVien.nguoiDung.avatar}
                                    alt="Ảnh đại diện"
                                    style={{ maxWidth: "150px" }}
                                />
                            </div>
                        )}

                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    value={
                                        i.field === "matKhau"
                                            ? newPassword
                                            : i.field === "gioiTinh"
                                                ? getGioiTinh(sinhVien.nguoiDung[i.field])
                                                : i.field === "soTinChi"
                                                ? sinhVien[i.field]
                                                : sinhVien.nguoiDung[i.field] || ""
                                    }

                                    placeholder={i.field === "matKhau" ? "Chỉ nhập nếu muốn đổi mật khẩu" : (sinhVien.nguoiDung[i.field])}
                                    disabled={i.disabled}
                                    onChange={(e) => {
                                        i.field === "matKhau" ?
                                            setNewPassword(e.target.value) :
                                            setSinhVien({ ...sinhVien, nguoiDung: { ...sinhVien.nguoiDung, [i.field]: e.target.value } })
                                    }}
                                    required={i.field !== "matKhau"}
                                />
                            </div>
                        ))}

                        <div className="mb-3 mt-3">
                            <label className="form-label">Thuộc khoa</label>
                            <input
                                type="text"
                                className="form-control"
                                value={sinhVien.khoaId?.tenKhoa || ""}
                                disabled
                            />
                        </div>

                        <div className="mb-3 mt-3">
                            <label className="form-label">Thuộc ngành</label>
                            <input
                                type="text"
                                className="form-control"
                                value={sinhVien.nganhId?.tenNganh || ""}
                                disabled
                            />
                        </div>

                        <div className="text-center mt-3 mb-3">
                            <button type="submit" className="text-center btn btn-primary" disabled={updateLoading}>
                                {updateLoading ?
                                    <>
                                        <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                        Đang lưu...
                                    </> : "Lưu thay đổi"}
                            </button>
                        </div>
                    </form>
                </div>
            )}
        </div>
    );
}
export default SuaSinhVien;