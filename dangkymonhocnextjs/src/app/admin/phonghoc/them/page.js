'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useState } from "react";

const ThemPhongHoc = () => {

    const info = [{
        label: "Phòng",
        field: "tenPhong",
        type: "text",
    }];
    const [newPhongHoc, setNewPhongHoc] = useState({});
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const addPhongHoc = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themPhongHoc'], newPhongHoc);
            setMsg("Thêm thành công!");
            setNewPhongHoc({});
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
                <h1>THÊM PHÒNG HỌC</h1>
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
                <form onSubmit={addPhongHoc} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mb-3 mt-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                className="form-control"
                                type={i.type}
                                value={newPhongHoc[i.field] || ''}
                                onChange={e => setNewPhongHoc({ ...newPhongHoc, [i.field]: e.target.value })}
                                placeholder={i.label}
                                required
                            />
                        </div>
                    ))}

                    <div className="mt-3 mb-3">
                        <label htmlFor="loai" className="form-label">Loại</label>
                        <select
                            id="loai"
                            className="form-select"
                            value={newPhongHoc.loai || ""}
                            onChange={(e) => setNewPhongHoc({ ...newPhongHoc, loai: e.target.value })}
                            required
                        >
                            <option value="">-- Chọn loại --</option>
                            <option value="LyThuyet">Lý Thuyết</option>
                            <option value="ThucHanh">Thực Hành</option>
                        </select>
                    </div>

                    <div className="text-center">
                        <button type="submit" className="text-center btn btn-primary mb-3" disabled={loading}>
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
export default ThemPhongHoc;