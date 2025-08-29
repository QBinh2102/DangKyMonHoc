'use client'
import { useEffect, useState } from "react";
import "./hocphi.css"
import { authApis, endpoints } from "@/configs/Apis";

const HocPhi = () => {

    const [listHocPhi, setListHocPhi] = useState([]);
    const [listChiTietHocPhi, setListChiTietHocPhi] = useState([]);
    const [listHocKy, setListHocKy] = useState([]);
    const [selectedHocKy, setSelectedHocKy] = useState("");
    const [loading, setLoading] = useState(false);

    const loadHocKy = async () => {
        try {
            let res = await authApis().get(endpoints['hocKyBySinhVien']);
            setListHocKy(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadHocPhi = async () => {
        setListChiTietHocPhi([]);
        try {
            let res = await authApis().get(endpoints['hocPhiSinhVien']);
            setListHocPhi(res.data);
            console.log(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadChiTietHocPhi = async (hocKyId) => {
        setListHocPhi([]);
        try {
            let res = await authApis().get(`${endpoints['chiTietHocPhiSinhVien']}?hocKyId=${hocKyId}`);
            setListChiTietHocPhi(res.data);
            console.log(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadHocPhi();
        loadHocKy();
    }, []);

    const chooseHocKy = async (e) => {
        const hocKyId = e.target.value;
        setSelectedHocKy(hocKyId);

        { hocKyId ? await loadChiTietHocPhi(hocKyId) : await loadHocPhi() }
    }

    const formatTien = (soTien) => {
        if (!soTien) return "0 đ";
        return new Intl.NumberFormat("vi-VN", {
            style: "currency",
            currency: "VND"
        }).format(soTien);
    };

    const tongTien = listChiTietHocPhi.reduce((sum, ct) => sum + (ct.chiPhi || 0), 0);

    const formatTrangThai = (trangThai) => {
        if (trangThai === "CHUA_THANH_TOAN") {
            return "Chưa thanh toán";
        } else {
            return "Đã thanh toán";
        }
    }

    return (
        <div>
            <h1 className="text-center mt-3 mb-3">Học phí của sinh viên</h1>

            <div className="select-container">
                <select
                    className="custom-select"
                    value={selectedHocKy}
                    onChange={chooseHocKy}
                >
                    <option value="">Học phí các kỳ</option>
                    {listHocKy.map((i) => (
                        <option key={i.id} value={i.id}>
                            {i.ky} - {i.namHoc}
                        </option>
                    ))}
                </select>
            </div>

            <div>
                {listHocPhi.length > 0 &&
                    <table className="table table-bordered table-hocphi">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Học kỳ</th>
                                <th>Học phí</th>
                                <th>Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listHocPhi.map((hp, idx) => (
                                <tr key={idx}>
                                    <td className="format-properties">{idx + 1}</td>
                                    <td className="format-properties">{hp.hocKyId?.ky} - Năm học {hp.hocKyId?.namHoc}</td>
                                    <td className="format-properties">{formatTien(hp.tongTien)}</td>
                                    <td className="format-properties">{formatTrangThai(hp.trangThai)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                }

                {listChiTietHocPhi.length > 0 &&
                    <table className="table table-bordered table-hocphi">
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
        </div>
    );
}
export default HocPhi;