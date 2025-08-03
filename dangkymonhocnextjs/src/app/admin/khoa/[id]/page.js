'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaKhoa = () => {

    const info = [{
        label: "Tên khoa",
        field: "tenKhoa",
        type: "text",
    }];
    const { id } = useParams();
    const [khoa, setKhoa] = useState({});
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadKhoa = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['khoaId'](id));
            setKhoa(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadKhoa();
    }, [id]);

    const updateKhoa = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaKhoaId'](id), { ...khoa });
            setMsg("Cập nhật thành công!");
            await loadKhoa();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN KHOA</h1>
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
                    <form onSubmit={updateKhoa} className="w-50 mx-auto">
                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    placeholder={i.label}
                                    value={khoa[i.field]}
                                    onChange={(e) => setKhoa({ ...khoa, [i.field]: e.target.value })}
                                    required
                                />
                            </div>
                        ))}

                        <div className="text-center">
                            <button type="submit" className="text-center btn btn-primary" disabled={updateLoading}>
                                {updateLoading ?
                                    <>
                                        <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                        Đang lưu...
                                    </> : "Lưu thay đổi"}
                            </button>
                        </div>
                    </form>
                </div >
            )}

        </div >
    );
}
export default SuaKhoa;