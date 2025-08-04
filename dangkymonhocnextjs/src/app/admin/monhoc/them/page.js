'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemMonHoc = () => {

    const info = [{
        label: "Tên môn",
        field: "tenMon",
        type: "text",
    }, {
        label: "Số tín chỉ",
        field: "soTinChi",
        type: "number",
        min: 0,
    }, {
        label: "Điểm qua môn",
        field: "diemQuaMon",
        type: "number",
        min: 0,
        max: 10,
    }]

    const loaiDiem = [{
        label: "Giữa kỳ 30% - Cuối kỳ 70%",
        value: "30-70",
    }, {
        label: "Giữa kỳ 40% - Cuối kỳ 60%",
        value: "40-60",
    }, {
        label: "Giữa kỳ 50% - Cuối kỳ 50%",
        value: "50-50",
    }]

    const [listKhoa, setListKhoa] = useState([]);
    const [listNganh, setListNganh] = useState([]);
    const [selectedNganhs, setSelectedNganhs] = useState([]);
    const [newMonHoc, setNewMonHoc] = useState({
        khoaId: {},
    });
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadKhoa = async () => {
        try {
            let res = await Apis.get(endpoints['khoa']);
            setListKhoa(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

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

        setNewMonHoc({ ...newMonHoc, khoaId: { ...newMonHoc.khoaId, id: e.target.value } })

        { khoaId ? await loadNganhTheoKhoa(khoaId) : setListNganh([]) };
    }

    useEffect(() => {
        loadKhoa();
    }, []);

    const chooseLoaiDiem = (e) => {
        const value = e.target.value;
        const [phanTramGiuaKy, phanTramCuoiKy] = value.split('-').map(Number);
        setNewMonHoc({
            ...newMonHoc,
            phanTramGiuaKy,
            phanTramCuoiKy,
        })
    }

    const updateMonHoc = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themMonHoc'], newMonHoc);
            setMsg("Thêm môn học thành công!");
        } catch (ex) {
            setMsg("Thêm môn học thất bại!");
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM MÔN HỌC</h1>
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
                <form onSubmit={updateMonHoc} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mt-3 mb-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                type={i.type}
                                className="form-control"
                                value={newMonHoc[i.field] || ""}
                                placeholder={i.label}
                                min={i.min !== undefined ? i.min : undefined}
                                max={i.max !== undefined ? i.max : undefined}
                                onChange={(e) => setNewMonHoc({ ...newMonHoc, [i.field]: e.target.value })}
                                required
                            />
                        </div>
                    ))}

                    <div className="mt-3 mb-3">
                        <label htmlFor="loaiDiem" className="form-label">Chọn loại điểm</label>
                        <select
                            id="loaiDiem"
                            className="form-select"
                            onChange={chooseLoaiDiem}
                            required
                        >
                            <option value="">-- Chọn loại điểm --</option>
                            {loaiDiem.map((i, idx) => (
                                <option key={idx} value={i.value}>{i.label}</option>
                            ))}
                        </select>
                    </div>

                    <div className="mt-3 mb-3">
                        <label htmlFor="khoaId" className="form-label">Thuộc khoa</label>
                        <select
                            id="khoaId"
                            className="form-select"
                            onChange={chooseKhoa}
                            required
                        >
                            <option value="">-- Chọn khoa --</option>
                            {listKhoa.map(khoa => (
                                <option key={khoa.id} value={khoa.id}>{khoa.tenKhoa}</option>
                            ))}
                        </select>
                    </div>

                    {listNganh.length > 0 && (
                        <div className="mt-3 mb-3">
                            <label className="form-label">Thuộc ngành</label>
                            {listNganh.map(nganh => (
                                <div key={nganh.id} className="form-check">
                                    <input
                                        className="form-check-input"
                                        type="checkbox"
                                        id={nganh.id}
                                        value={nganh.id}
                                        checked={selectedNganhs.includes(nganh.id)}
                                        onChange={(e) => {
                                            if (e.target.checked) {
                                                setSelectedNganhs([...selectedNganhs, e.target.value]);
                                            } else {
                                                setSelectedNganhs(selectedNganhs.filter(item => item !== e.target.value));
                                            }
                                        }}
                                    />
                                    <label className="form-check-label" htmlFor={`nganh-${nganh.id}`}>
                                        {nganh.tenNganh}
                                    </label>
                                </div>
                            ))}
                        </div>
                    )}

                    <div className="text-center">
                        <button type="submit" className="text-center btn btn-primary" disabled={loading}>
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
export default ThemMonHoc;