'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaNganh = () => {

    const info = [{
        label: "Tên ngành",
        field: "tenNganh",
        type: "text",
    }]
    const { id } = useParams();
    const [nganh, setNganh] = useState({});
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadNganh = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['nganhId'](id));
            console.info(res.data);
            setNganh(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadNganh();
    }, [id]);

    const updateNganh = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaNganhId'](id), {
                ...nganh,
                khoaId: nganh.khoaId.id,
            });
            setMsg("Cập nhật thành công!");
            await loadNganh();
        } catch (ex) {
            console.error(ex);
            setMsg("Cập nhật thất bại!");
        } finally {
            setUpdateLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN NGÀNH</h1>
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
                    <form onSubmit={updateNganh} className="w-50 mx-auto">
                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    value={nganh[i.field] || ""}
                                    onChange={(e) => setNganh({ ...nganh, [i.field]: e.target.value })}
                                    required
                                />
                            </div>
                        ))}

                        <div className="mb-3 mt-3">
                            <label className="form-label">Tên khoa</label>
                            <input
                                type="text"
                                className="form-control"
                                value={nganh.khoaId?.tenKhoa || ""}
                                disabled
                            />
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
                </div>
            )}
        </div>
    );
}
export default SuaNganh;