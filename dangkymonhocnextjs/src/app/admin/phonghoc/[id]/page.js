'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaPhongHoc = () => {

    const info = [{
        label: "Phòng",
        field: "tenPhong",
        type: "text",
    }];

    const { id } = useParams();
    const [phongHoc, setPhongHoc] = useState({});
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadPhongHoc = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['phongHocId'](id));
            setPhongHoc(res.data);
        } catch (ex) {
            console.error(ex);
            setMsg("Không thể tải dữ liệu!");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadPhongHoc();
    }, [id]);

    const updatePhongHoc = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaPhongHocId'](id), phongHoc);
            setMsg("Cập nhật thành công!");
            await loadPhongHoc();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
            console.error(ex);
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN PHÒNG HỌC</h1>
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
                    <form onSubmit={updatePhongHoc} className="w-50 mx-auto">
                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    placeholder={i.label}
                                    value={phongHoc[i.field] || ''}
                                    onChange={(e) => setPhongHoc({ ...phongHoc, [i.field]: e.target.value })}
                                    required
                                />
                            </div>
                        ))}

                        <div className="mt-3 mb-3">
                            <label htmlFor="loai" className="form-label">Loại</label>
                            <select
                                id="loai"
                                className="form-select"
                                value={phongHoc.loai || ""}
                                onChange={(e) => setPhongHoc({ ...phongHoc, loai: e.target.value })}
                                required
                            >
                                <option value="">--Chọn loại --</option>
                                <option value="LyThuyet">Lý Thuyết</option>
                                <option value="ThucHanh">Thực Hành</option>
                            </select>
                        </div>

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
export default SuaPhongHoc;