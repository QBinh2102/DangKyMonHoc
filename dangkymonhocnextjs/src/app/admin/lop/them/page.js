'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemLop = () => {

    const info = [{
        label: "Mã Lớp",
        field: "maLop",
        type: "text",
    }, {
        label: "Sĩ Số",
        field: "siSo",
        type: "number",
        min: 0,
    }];
    const [newLop, setNewLop] = useState({});
    const [listKhoa, setListKhoa] = useState([]);
    const [selectedKhoaId, setSelectedKhoaId] = useState("");
    const [listNganh, setListNganh] = useState([]);
    const [selectedNganhId, setSelectedNganhId] = useState("");
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadNganhTheoKhoa = async (khoaId) => {
        try {
            let res = await Apis.get(`${endpoints['nganh']}?khoaId=${khoaId}`);
            setListNganh(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadKhoa = async () => {
        try {
            let res = await Apis.get(endpoints['khoa']);
            setListKhoa(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadKhoa();
    }, []);

    const chooseKhoa = async (e) => {
        const khoaId = e.target.value;
        setSelectedKhoaId(khoaId);
        { khoaId ? await loadNganhTheoKhoa(khoaId) : setListNganh([]) };
    };

    const addLop = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themLop'], newLop);
            setNewLop({});
            setMsg("Thêm thành công!");
        } catch (ex) {
            console.error(ex);
            setMsg("Thêm thất bại!");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM LỚP</h1>
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
                <form onSubmit={addLop} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mb-3 mt-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                className="form-control"
                                type={i.type}
                                value={newLop[i.field] || ''}
                                min={i.min !== undefined ? i.min : undefined}
                                onChange={(e) => { setNewLop({ ...newLop, [i.field]: e.target.value }) }}
                                placeholder={i.label}
                                required
                            />
                        </div>
                    ))}

                    <div className="mt-3 mb-3">
                        <label htmlFor="khoaId" className="form-label">Thuộc khoa</label>
                        <select
                            id="khoaId"
                            className="form-select"
                            value={selectedKhoaId}
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
                        <label htmlFor="nganhId" className="form-label">Thuộc ngành</label>
                        <select
                            id="nganhId"
                            className="form-select"
                            value={newLop.nganhId?.id || ""}
                            onChange={(e) => setNewLop({ ...newLop, nganhId: { id: e.target.value } })}
                            required
                        >
                            <option value="">-- Chọn ngành --</option>
                            {listNganh.map(nganh => (
                                <option key={nganh.id} value={nganh.id}>{nganh.tenNganh}</option>
                            ))}
                        </select>
                    </div>

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
export default ThemLop;