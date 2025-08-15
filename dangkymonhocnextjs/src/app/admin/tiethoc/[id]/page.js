'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaTietHoc = () => {

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

    const { id } = useParams();
    const [tietHoc, setTietHoc] = useState({});
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadTietHoc = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['tietHocId'](id));
            setTietHoc(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
            setMsg("Không thể tải dữ liệu!");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadTietHoc();
    }, [id]);

    const updateTietHoc = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaTietHocId'](id), tietHoc);
            setMsg("Cập nhật thành công!");
            await loadTietHoc();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN TIẾT HỌC</h1>
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
                    <form onSubmit={updateTietHoc} className="w-50 mx-auto">
                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    placeholder={i.label}
                                    value={tietHoc[i.field] || ''}
                                    onChange={(e) => {
                                        let value = e.target.value;
                                        if (i.type === "time" && value && value.length === 5) {
                                            value += ":00";
                                        }
                                        setTietHoc({ ...tietHoc, [i.field]: value });
                                    }}
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
export default SuaTietHoc;