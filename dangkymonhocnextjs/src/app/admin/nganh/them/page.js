'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemNganh = () => {

    const info = [{
        label: "Tên ngành",
        field: "tenNganh",
        type: "text",
    }];
    const [newNganh, setNewNganh] = useState({
        khoaId: {},
    });
    const [listKhoa, setListKhoa] = useState([]);
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    useEffect(() => {
        const loadKhoa = async () => {
            try {
                let res = await Apis.get(endpoints['khoa']);
                console.info(res.data);
                setListKhoa(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadKhoa();
    }, []);

    const addNganh = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            await authApis().post(endpoints['themNganh'], newNganh);
            setMsg("Thêm ngành thành công!");
            setNewNganh({
                khoaId: {},
            });
        } catch (ex) {
            console.error(ex);
            setMsg("Thêm ngành thất bại!");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM NGÀNH HỌC</h1>
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
                <form onSubmit={addNganh} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mb-3 mt-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                type={i.type}
                                className="form-control"
                                placeholder={i.label}
                                value={newNganh[i.field] || ""}
                                onChange={e => setNewNganh({ ...newNganh, [i.field]: e.target.value })}
                                required
                            />
                        </div>
                    ))}

                    <div className="mt-3 mb-3">
                        <label htmlFor="khoaId" className="form-label">Khoa</label>
                        <select
                            className="form-select"
                            id="khoaId"
                            value={newNganh.khoaId.id || ""}
                            onChange={(e) => setNewNganh({ ...newNganh, khoaId: { ...newNganh.khoaId, id: e.target.value } })}
                            required
                        >
                            <option value="">-- Chọn khoa --</option>
                            {listKhoa.map(khoa => (
                                <option key={khoa.id} value={khoa.id}>{khoa.tenKhoa}</option>
                            ))}
                        </select>
                    </div>

                    <div className="text-center">
                        <button type="submit" className="btn btn-primary mb-3" disabled={loading}>
                            {loading ?
                                <>
                                    <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                    Đang thêm...
                                </> : "Thêm"}
                        </button>
                    </div>
                </form>
            </div>
        </div >
    );
}
export default ThemNganh;