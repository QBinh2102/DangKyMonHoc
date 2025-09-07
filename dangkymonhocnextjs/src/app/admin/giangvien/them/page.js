'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemGiangVien = () => {

    const info = [{
        label: "Họ Tên",
        field: "hoTen",
        type: "text",
    }, {
        label: "Ngày Sinh",
        field: "ngaySinh",
        type: "date",
    }, {
        label: "Số Điện Thoại",
        field: "soDienThoai",
        type: "tel",
    }, {
        label: "Email",
        field: "email",
        type: "email",
    }, {
        label: "Căn cước công dân",
        field: "cccd",
        type: "text",
    }];
    const hocViOptions = ["Thạc sĩ", "Tiến sĩ", "Phó giáo sư", "Giáo sư"];
    const [newGiangVien, setNewGiangVien] = useState({
        hocVi: "",
        nguoiDung: {},
        khoaId: {},
    });
    const [listKhoa, setListKhoa] = useState([]);
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    useEffect(() => {
        const loadKhoa = async () => {
            try {
                let res = await Apis.get(endpoints['khoa']);
                setListKhoa(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadKhoa();
    }, []);

    const addGiangVien = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            console.log(newGiangVien);
            await authApis().post(endpoints['themHoacLayGiangVien'], newGiangVien);
            setNewGiangVien({
                hocVi: "",
                nguoiDung: {},
                khoaId: {},
            });
            setMsg("Thêm giảng viên thành công!");
        } catch (ex) {
            setMsg("Thêm giảng viên thất bại!");
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM GIẢNG VIÊN</h1>
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

            <div>
                <form onSubmit={addGiangVien} className="w-50 mx-auto">
                    <div className="mb-3 mt-3">
                        <label htmlFor="gioiTinh" className="form-label">Giới tính</label>
                        <select
                            id="gioiTinh"
                            className="form-select"
                            value={newGiangVien.nguoiDung.gioiTinh || ""}
                            onChange={e => setNewGiangVien({ ...newGiangVien, nguoiDung: { ...newGiangVien.nguoiDung, gioiTinh: e.target.value } })}
                            required
                        >
                            <option value="">-- Chọn giới tính --</option>
                            <option value="nam">Nam</option>
                            <option value="nữ">Nữ</option>
                        </select>
                    </div>

                    {info.map(i => (
                        <div className="mt-3 mb-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                type={i.type}
                                value={newGiangVien.nguoiDung[i.field] || ""}
                                className="form-control"
                                placeholder={i.label}
                                onChange={e => setNewGiangVien({ ...newGiangVien, nguoiDung: { ...newGiangVien.nguoiDung, [i.field]: e.target.value } })}
                                required
                            />
                        </div>
                    ))}

                    <div className="mb-3 mt-3">
                        <label htmlFor="hocVi" className="form-label">Học vị</label>
                        <select
                            id="hocVi"
                            className="form-select"
                            value={newGiangVien.hocVi}
                            onChange={e => setNewGiangVien({ ...newGiangVien, hocVi: e.target.value })}
                            required
                        >
                            <option value="">-- Chọn học vị --</option>
                            {hocViOptions.map((hv, index) => (
                                <option key={index} value={hv}>{hv}</option>
                            ))}
                        </select>
                    </div>

                    <div className="mt-3 mb-3">
                        <label htmlFor="khoaId" className="form-label">Khoa</label>
                        <select
                            className="form-select"
                            id="khoaId"
                            value={newGiangVien.khoaId.id || ""}
                            onChange={(e) => setNewGiangVien({ ...newGiangVien, khoaId: { ...newGiangVien.khoaId, id: e.target.value } })}
                            required
                        >
                            <option value="">-- Chọn khoa --</option>
                            {listKhoa.map(khoa => (
                                <option key={khoa.id} value={khoa.id}>{khoa.tenKhoa}</option>
                            ))}
                        </select>
                    </div>

                    <div className="mt-3 mb-3">
                        <label htmlFor="avatar" className="form-label">Ảnh đại diện</label>
                        <input
                            id="avatar"
                            type="file"
                            accept="image/*"
                            className="form-control"
                            onChange={e => {
                                const file = e.target.files[0];
                                if (file) {
                                    const reader = new FileReader();
                                    reader.onloadend = () => {
                                        setNewGiangVien({
                                            ...newGiangVien,
                                            nguoiDung: {
                                                ...newGiangVien.nguoiDung,
                                                avatar: reader.result // base64 string
                                            }
                                        });
                                    };
                                    reader.readAsDataURL(file); // convert file -> base64
                                }
                            }}
                        />
                    </div>

                    {newGiangVien.nguoiDung.avatar && (
                        <div className="mt-3 text-center">
                            <img
                                src={newGiangVien.nguoiDung.avatar}
                                alt="Ảnh đại diện"
                                style={{ maxWidth: "150px"}}
                            />
                        </div>
                    )}


                    <div className="text-center mt-3 mb-3">
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ?
                                <>
                                    <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                    Đang thêm...
                                </> : "Thêm"}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default ThemGiangVien;