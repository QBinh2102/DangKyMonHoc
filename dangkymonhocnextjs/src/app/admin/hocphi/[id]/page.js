'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import "./chitiethocphi.css";

const ChiTietHocPhi = () => {

    const { id } = useParams();
    const [hocPhi, setHocPhi] = useState({});
    const [listChiTietHocPhi, setListChiTietHocPhi] = useState([]);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadHocPhi = async () => {
        try {
            let res = await authApis().get(endpoints['layHoacSuaHocPhiId'](id));
            setHocPhi(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadChiTietHocPhi = async () => {
        try {
            let res = await authApis().get(`${endpoints['chiTietHocPhi']}?hocPhiId=${id}`);
            setListChiTietHocPhi(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadChiTietHocPhi();
        loadHocPhi();
    }, [id]);

    const updateHocPhi = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['layHoacSuaHocPhiId'](id), hocPhi);
            setMsg("Cập nhật thành công!");
            await loadHocPhi();
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
            console.error(ex);
        } finally {
            setUpdateLoading(false);
        }
    }

    const tongTien = listChiTietHocPhi.reduce((sum, ct) => sum + (ct.chiPhi || 0), 0);

    const formatTien = (soTien) => {
        if (!soTien) return "0 đ";
        return new Intl.NumberFormat("vi-VN", {
            style: "currency",
            currency: "VND"
        }).format(soTien);
    };

    return (
        <div>
            <h1 className="text-center mt-3 mb-3">CHI TIẾT HỌC PHÍ SINH VIÊN</h1>

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

            <form onSubmit={updateHocPhi} className="w-50 mx-auto">
                <div className="mt-3 mb-3">
                    <label htmlFor="sinhVienId" className="form-label">Sinh viên</label>
                    <input
                        id="sinhVienId"
                        type="text"
                        className="form-control"
                        value={hocPhi.sinhVienId?.nguoiDung?.hoTen || ""}
                        disabled
                    />
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="hocKyId" className="form-label">Học kỳ</label>
                    <input
                        id="hocKyId"
                        type="text"
                        className="form-control"
                        value={
                            hocPhi.hocKyId
                                ? `${hocPhi.hocKyId.ky} - Năm học ${hocPhi.hocKyId.namHoc}`
                                : ""
                        }
                        disabled
                    />
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="trangThai" className="form-label">Trạng thái</label>
                    <select
                        id="trangThai"
                        className="form-select"
                        value={hocPhi.trangThai || ""}
                        onChange={(e) => setHocPhi({ ...hocPhi, trangThai: e.target.value })}
                        disabled={hocPhi.trangThai === "DA_THANH_TOAN"}
                    >
                        <option value="CHUA_THANH_TOAN">Chưa thanh toán</option>
                        <option value="DA_THANH_TOAN">Đã thanh toán</option>
                    </select>
                </div>

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

            {listChiTietHocPhi.length > 0 &&
                <table className="table table-bordered table-hocphi mt-3 mb-3">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên môn</th>
                            <th>Tín chỉ</th>
                            <th>Số tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        {listChiTietHocPhi.map((ct, idx) => (
                            <tr key={idx}>
                                <td className="format-properties">{idx + 1}</td>
                                <td className="format-properties">{ct.monHocId?.tenMon}</td>
                                <td className="format-properties">{ct.monHocId?.tinChiLyThuyet + ct.monHocId?.tinChiThucHanh}</td>
                                <td className="format-properties">{ct.chiPhi}</td>
                            </tr>
                        ))}
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colSpan="3" className="text-end fw-bold">Tổng tiền</td>
                            <td className="fw-bold format-properties">{formatTien(tongTien)}</td>
                        </tr>
                    </tfoot>
                </table>
            }
        </div>
    );
}
export default ChiTietHocPhi;