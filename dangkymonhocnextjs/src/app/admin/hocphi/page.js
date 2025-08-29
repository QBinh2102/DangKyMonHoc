'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const HocPhi = () => {

    const [listHocPhi, setListHocPhi] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadHocPhi = async () => {
        setLoading(true);
        try{
            let res = await authApis().get(endpoints['hocPhi']);
            setListHocPhi(res.data);
        }catch(ex){
            console.error(ex);
        }finally{
            setLoading(false);
        }
    }

    useEffect(() => {
        loadHocPhi();
    }, []);

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
                </div>
            )}
        </div>
    );
}
export default HocPhi;