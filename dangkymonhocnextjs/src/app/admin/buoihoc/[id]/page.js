'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaBuoiHoc = () => {

    const info = [{
        label: "Loại",
        field: "loai",
        type: "text",
        disabled: true,
    }, {
        label: "Sĩ số",
        field: "siSo",
        type: "number",
        disabled: false,
        min: 0,
    }]
    const { id } = useParams();
    const [buoiHoc, setBuoiHoc] = useState({});
    const [listLichHoc, setListLichHoc] = useState([]);
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadLichHocTheoBuoiHoc = async (buoiHocId) => {
        try {
            let res = await Apis.get(`${endpoints['lichHoc']}?buoiHocId=${buoiHocId}`);
            setListLichHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadBuoiHoc = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['suaHoacLayBuoiHocId'](id));
            setBuoiHoc(res.data);
            await loadLichHocTheoBuoiHoc(res.data.id);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }



    useEffect(() => {
        loadBuoiHoc();
    }, []);

    const getLoaiBuoiHoc = (loai) => {
        if (loai === "LT") {
            return "Lý thuyết";
        } else if (loai === "TH") {
            return "Thực hành";
        } else {
            return "Lý thuyết - Thực hành";
        }
    }

    const getLoaiLichHoc = (loai) => {
        if (loai === "LyThuyet") {
            return "Lý thuyết";
        } else {
            return "Thực hành";
        }
    }

    const updateBuoiHoc = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaHoacLayBuoiHocId'](id), buoiHoc);
            setMsg("Cập nhật thành công!");
            await loadBuoiHoc();
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
                <h1>THÔNG TIN BUỔI HỌC</h1>
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
                    <form onSubmit={updateBuoiHoc} className="w-50 mx-auto">
                        <div className="mt-3 mb-3">
                            <label htmlFor="monHocId" className="form-label">Môn học</label>
                            <input
                                id="monHocId"
                                type="text"
                                className="form-control"
                                value={buoiHoc.monHocId?.tenMon || ""}
                                disabled
                            />
                        </div>

                        <div className="mt-3 mb-3">
                            <label htmlFor="giangVienId" className="form-label">Giảng viên</label>
                            <input
                                id="giangVienId"
                                type="text"
                                className="form-control"
                                value={buoiHoc.giangVienId?.nguoiDung?.hoTen || ""}
                                disabled
                            />
                        </div>

                        <div className="mt-3 mb-3">
                            <label htmlFor="lopId" className="form-label">Mã lớp</label>
                            <input
                                id="lopId"
                                type="text"
                                className="form-control"
                                value={buoiHoc.lopId?.maLop || ""}
                                disabled
                            />
                        </div>

                        <div className="mt-3 mb-3">
                            <label htmlFor="hocKyId" className="form-label">Học kỳ</label>
                            <input
                                id="hocKyId"
                                type="text"
                                className="form-control"
                                value={`${buoiHoc.hocKyId?.ky} - ${buoiHoc.hocKyId?.namHoc}` || ""}
                                disabled
                            />
                        </div>

                        {info.map(i => (
                            <div className="mt-3 mb-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    value={i.field === "loai" ? getLoaiBuoiHoc(buoiHoc[i.field]) : buoiHoc[i.field] ?? ""}
                                    disabled={i.disabled}
                                    min={i.min !== undefined ? i.min : undefined}
                                    onChange={(e) => setBuoiHoc({ ...buoiHoc, [i.field]: e.target.value })}
                                    required
                                />
                            </div>
                        ))}

                        {listLichHoc.map(lh => (
                            <div key={lh.id} className="mt-3 mb-3">
                                <label htmlFor={lh.id} className="form-label">{getLoaiLichHoc(lh.loai)}</label>
                                <p id={lh.id}>{lh.thu}, {lh.tietHocId.gioBatDau} - {lh.tietHocId.gioKetThuc}, {lh.ngayBatDau} - {lh.ngayKetThuc}, phòng {lh.phongHocId.tenPhong}</p>
                            </div>
                        ))}

                        <div className="text-center mt-3 mb-3">
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
export default SuaBuoiHoc;