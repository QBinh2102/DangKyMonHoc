'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaGiangVien = () => {

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
    }]
    const { id } = useParams();
    const [giangVien, setGiangVien] = useState({
        nguoiDung: {},
    });
    const [newPassword, setNewPassword] = useState("");
    const hocViOptions = ["Thạc sĩ", "Tiến sĩ", "Phó giáo sư", "Giáo sư"];
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadGiangVien = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['suaHoacLayGiangVienId'](id));
            setGiangVien(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadGiangVien();
    }, [id]);

    const getGioiTinh = (gt) => {
        if (gt === "nam") {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    const updateGiangVien = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);

        const updatedGV = { ...giangVien };
        if (newPassword.trim() !== "") {
            updatedGV.nguoiDung.matKhau = newPassword;
        } else {
            delete updatedGV.nguoiDung.matKhau;
        }

        try {
            await authApis().put(endpoints['suaHoacLayGiangVienId'](id), updatedGV);
            setMsg("Cập nhật thành công!");
            setNewPassword("");
            await loadGiangVien();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN GIẢNG VIÊN</h1>
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
                    <form onSubmit={updateGiangVien} className="w-50 mx-auto">
                        {giangVien.nguoiDung.avatar && (
                            <div className="mt-3 text-center">
                                <img
                                    src={giangVien.nguoiDung.avatar}
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
                                                ? getGioiTinh(giangVien.nguoiDung[i.field])
                                                : giangVien.nguoiDung[i.field] || ""
                                    }

                                    placeholder={i.field === "matKhau" ? "Chỉ nhập nếu muốn đổi mật khẩu" : (giangVien.nguoiDung[i.field])}
                                    disabled={i.disabled}
                                    onChange={(e) => {
                                        i.field === "matKhau" ?
                                            setNewPassword(e.target.value) :
                                            setGiangVien({ ...giangVien, nguoiDung: { ...giangVien.nguoiDung, [i.field]: e.target.value } })
                                    }}
                                    required={i.field !== "matKhau"}
                                />
                            </div>
                        ))}

                        <div className="mb-3 mt-3">
                            <label htmlFor="hocVi" className="form-label">Học vị</label>
                            <select
                                id="hocVi"
                                className="form-select"
                                value={giangVien.hocVi}
                                onChange={e => setGiangVien({ ...giangVien, hocVi: e.target.value })}
                                required
                            >
                                <option value="">-- Chọn học vị --</option>
                                {hocViOptions.map((hv, index) => (
                                    <option key={index} value={hv}>{hv}</option>
                                ))}
                            </select>
                        </div>

                        <div className="mb-3 mt-3">
                            <label className="form-label">Tên khoa</label>
                            <input
                                type="text"
                                className="form-control"
                                value={giangVien.khoaId?.tenKhoa || ""}
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
export default SuaGiangVien;