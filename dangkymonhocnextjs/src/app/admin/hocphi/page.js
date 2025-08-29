'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const HocPhi = () => {

    const [listHocPhi, setListHocPhi] = useState([]);
    const [kw, setKw] = useState("");
    const [selectedHocKy, setSelectedHocKy] = useState("");
    const [selectedTrangThai, setSelectedTrangThai] = useState("CHUA_THANH_TOAN");
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadHocPhi = async () => {
        setLoading(true);
        try {
            let url = endpoints['hocPhi'];

            const params = new URLSearchParams();
            if (kw.trim() !== "") params.append("hoTen", kw);
            if (selectedHocKy !== "") params.append("hocKyId", selectedHocKy);
            if (selectedTrangThai !== "") params.append("trangThai", selectedTrangThai);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await authApis().get(url);
            setListHocPhi(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadHocPhi();
    }, [kw, selectedHocKy, selectedTrangThai]);

    const hocKyOptions = Array.from(
        new Map(
            listHocPhi.map(hp => [
                hp.hocKyId.id,
                {
                    id: hp.hocKyId.id,
                    label: `${hp.hocKyId.ky}-${hp.hocKyId.namHoc}`
                }
            ])
        ).values()
    );

    const formatTien = (soTien) => {
        if (!soTien) return "0 đ";
        return new Intl.NumberFormat("vi-VN", {
            style: "currency",
            currency: "VND"
        }).format(soTien);
    };

    const formatTrangThai = (trangThai) => {
        if (trangThai === "CHUA_THANH_TOAN") {
            return "Chưa thanh toán";
        } else {
            return "Đã thanh toán";
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ HỌC PHÍ</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm theo tên sinh viên..."
                    value={kw}
                    onChange={(e) => setKw(e.target.value)}
                />

                <select
                    className="form-select w-auto ms-2"
                    value={selectedHocKy}
                    onChange={(e) => setSelectedHocKy(e.target.value)}
                >
                    <option value="">Tất cả học kỳ</option>
                    {hocKyOptions.map((hk) => (
                        <option key={hk.id} value={hk.id}>{hk.label}</option>
                    ))}
                </select>

                <select
                    className="form-select w-auto ms-2"
                    value={selectedTrangThai}
                    onChange={(e) => setSelectedTrangThai(e.target.value)}
                >
                    <option value="CHUA_THANH_TOAN">Chưa thanh toán</option>
                    <option value="DA_THANH_TOAN">Đã thanh toán</option>
                </select>
            </div>

            {loading ? (
                <div className="text-center">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (
                <div>
                    <table className="table text-center">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Sinh viên</th>
                                <th>Học kỳ</th>
                                <th>Tổng tiền</th>
                                <th>Trạng thái</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listHocPhi.map((hp, idx) => (
                                <tr key={hp.id}>
                                    <td>{idx + 1}</td>
                                    <td>{hp.sinhVienId?.nguoiDung?.hoTen}</td>
                                    <td>{hp.hocKyId?.ky} - Năm {hp.hocKyId?.namHoc}</td>
                                    <td>{formatTien(hp.tongTien)}</td>
                                    <td>{formatTrangThai(hp.trangThai)}</td>
                                    <td>
                                        <button className="btn btn-warning me-2" onClick={() => router.push(`/admin/hocphi/${hp.id}`)}>
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {listHocPhi.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy học phí nào</p>
                    )}
                </div>
            )}
        </div>
    );
}
export default HocPhi;