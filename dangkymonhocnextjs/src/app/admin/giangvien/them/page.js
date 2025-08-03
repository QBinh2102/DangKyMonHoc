'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemGiangVien = () => {

    const info = [{
        label: "Họ Tên",
        field: "hoTen",
        type: "text",
    }, {
        label: "Email",
        field: "email",
        type: "email",
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

                    <div className="text-center">
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