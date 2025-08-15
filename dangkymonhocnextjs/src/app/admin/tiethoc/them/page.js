'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useState } from "react";

const ThemTietHoc = () => {

    const info = [{
        label: "Tiết",
        field: "tiet",
        type: "text",
    }, {
        label: "Giờ bắt đầu",
        field: "gioBatDau",
        type: "time",
    }, {
        label: "Giờ kết thúc",
        field: "gioKetThuc",
        type: "time",
    }];
    const [newTietHoc, setNewTietHoc] = useState({});
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const addTietHoc = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themTietHoc'], newTietHoc);
            setNewTietHoc({});
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
                <h1>THÊM TIẾT HỌC</h1>
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
                <form onSubmit={addTietHoc} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mb-3 mt-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                className="form-control"
                                type={i.type}
                                value={newTietHoc[i.field] || ''}
                                onChange={(e) => {
                                    let value = e.target.value;
                                    if (i.type === "time" && value && value.length === 5) {
                                        value += ":00";
                                    }
                                    setNewTietHoc({ ...newTietHoc, [i.field]: value });
                                }}
                                placeholder={i.label}
                                required
                            />
                        </div>
                    ))}

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
export default ThemTietHoc;