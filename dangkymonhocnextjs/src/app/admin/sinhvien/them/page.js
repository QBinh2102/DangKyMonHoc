'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemSinhVien = () => {

    const info = [{
        label: "Họ Tên",
        field: "hoTen",
        type: "text",
    }, {
        label: "Email",
        field: "email",
        type: "email",
    }, {
        label: "Ngày Sinh",
        field: "ngaySinh",
        type: "date",
    }];
    const [newSinhVien, setNewSinhVien] = useState({
        nguoiDung: {},
        khoaId: {},
        nganhId: {},
    });
    const [listKhoa, setListKhoa] = useState([]);
    const [listNganh, setListNganh] = useState([]);
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

    const loadNganhTheoKhoa = async (khoaId) => {
        try {
            let res = await Apis.get(`${endpoints['nganh']}?khoaId=${khoaId}`);
            setListNganh(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const chooseKhoa = async (e) => {
        const khoaId = e.target.value;
        setNewSinhVien({
            ...newSinhVien,
            khoaId: {
                id: khoaId,
            },
            nganhId: {},
        })

        { khoaId ? await loadNganhTheoKhoa(khoaId) : setListNganh([]) };
    }

    const addSinhVien = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themHoacLaySinhVien'], newSinhVien);
            setMsg("Thêm thành công!");
            setNewSinhVien({
                nguoiDung: {},
                khoaId: {},
                nganhId: {},
            })
        } catch (ex) {
            setMsg("Thêm thất bại!");
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM SINH VIÊN</h1>
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

            <form onSubmit={addSinhVien} className="w-50 mx-auto">
                {info.map(i => (
                    <div className="mt-3 mb-3" key={i.field}>
                        <label htmlFor={i.field} className="form-label">{i.label}</label>
                        <input
                            id={i.field}
                            type={i.type}
                            value={i.field === "ngaySinh" ? newSinhVien[i.field] || "" : newSinhVien.nguoiDung[i.field] || ""}
                            className="form-control"
                            placeholder={i.label}
                            onChange={(e) => {
                                i.field === "ngaySinh" ?
                                    setNewSinhVien({ ...newSinhVien, [i.field]: e.target.value }) :
                                    setNewSinhVien({ ...newSinhVien, nguoiDung: { ...newSinhVien.nguoiDung, [i.field]: e.target.value } })
                            }}
                            required
                        />
                    </div>
                ))}

                <div className="mt-3 mb-3">
                    <label htmlFor="khoaId" className="form-label">Khoa</label>
                    <select
                        className="form-select"
                        id="khoaId"
                        value={newSinhVien.khoaId.id || ""}
                        onChange={chooseKhoa}
                        required
                    >
                        <option value="">-- Chọn khoa --</option>
                        {listKhoa.map(khoa => (
                            <option key={khoa.id} value={khoa.id}>{khoa.tenKhoa}</option>
                        ))}
                    </select>
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="nganhId" className="form-label">Ngành</label>
                    <select
                        className="form-select"
                        id="nganhId"
                        value={newSinhVien.nganhId.id || ""}
                        onChange={(e) => setNewSinhVien({ ...newSinhVien, nganhId: { id: e.target.value } })}
                        required
                    >
                        <option value="">-- Chọn ngành --</option>
                        {listNganh.map(nganh => (
                            <option key={nganh.id} value={nganh.id}>{nganh.tenNganh}</option>
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
    );
}
export default ThemSinhVien;