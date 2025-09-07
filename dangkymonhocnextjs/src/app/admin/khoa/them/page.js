'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useState } from "react";

const ThemKhoa = () => {

    const info = [{
        label: "Tên khoa",
        field: "tenKhoa",
        type: "text",
    }];
    const [newKhoa, setNewKhoa] = useState({});
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const addKhoa = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themKhoa'], newKhoa);
            setNewKhoa({});
            setMsg("Thêm khoa thành công!");
        } catch (ex) {
            console.error(ex);
            setMsg("Thêm khoa thất bại!");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM KHOA</h1>
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
                <form onSubmit={addKhoa} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mb-3 mt-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                type={i.type}
                                className="form-control"
                                placeholder={i.label}
                                value={newKhoa[i.field] || ""}
                                onChange={e => setNewKhoa({ ...newKhoa, [i.field]: e.target.value })}
                                required
                            />
                        </div>
                    ))}

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
export default ThemKhoa;