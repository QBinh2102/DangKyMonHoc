'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const HocKy = () => {

    const [listHocKy, setListHocKy] = useState([]);
    const [loading, setLoading] = useState(true);
    const router = useRouter();

    useEffect(() => {
        const loadHocKy = async () => {
            try {
                let res = await authApis().get(endpoints.hocKy);
                setListHocKy(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadHocKy();
    }, []);

    const formatDate = (date) => {
        return new Date(date).toLocaleDateString("vi-VN");
    }

    return (
        <div>
            <div className="text-center mb-5 mt-5">
                <h1>QUẢN LÝ HỌC KỲ</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/hocky/them')}>Thêm</button>
            </div>

            {loading ? (
                <div className="text-center mt-5">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (<div>
                <table className="table text-center">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Kỳ</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                        </tr>
                    </thead>
                    <tbody>
                        {listHocKy.map((hk, idx) => (
                            <tr key={hk.id}>
                                <td>{idx+1}</td>
                                <td>{hk.ky} - Năm học {hk.namHoc}</td>
                                <td>{formatDate(hk.ngayBatDau)}</td>
                                <td>{formatDate(hk.ngayKetThuc)}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>)}
        </div>
    );
}
export default HocKy;