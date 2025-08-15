'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaLop = () => {

    const info = [{
        label: "Mã lớp",
        field: "maLop",
        type: "text",
        disabled: true,
    }, {
        label: "Sĩ số",
        field: "siSo",
        type: "number",
        disabled: false,
        min: 0,
    }, {
        label: "Ngành",
        field: "nganhId",
        type: "text",
        disabled: true,
    }];

    const { id } = useParams();
    const [lop, setLop] = useState({});
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadLop = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['lopId'](id));
            setLop(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
            setMsg("Không thể tải dữ liệu!");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadLop();
    }, [id]);

    const updateLop = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaLopId'](id), lop);
            setMsg("Cập nhật thành công!");
            await loadLop();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN LỚP</h1>
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
                    <form onSubmit={updateLop} className="w-50 mx-auto">
                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    placeholder={i.label}
                                    value={i.field === "nganhId" ? lop[i.field]?.tenNganh || '' : lop[i.field] || ''}
                                    min={i.min !== undefined ? i.min : undefined}
                                    disabled={i.disabled}
                                    onChange={(e) => setLop({ ...lop, [i.field]: e.target.value })}
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
export default SuaLop;