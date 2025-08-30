'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const HocKy = () => {

    const [listHocKy, setListHocKy] = useState([]);
    const [loading, setLoading] = useState(true);
    const [trang, setTrang] = useState(0);
    const [tongTrang, setTongTrang] = useState(0);
    const router = useRouter();

    const loadHocKy = async (page = 0) => {
        try {
            let url = endpoints['hocKyPage'];

            const params = new URLSearchParams();
            params.append("page", page);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await authApis().get(url);
            setListHocKy(res.data.content);
            setTongTrang(res.data.totalPages);
            setTrang(res.data.number);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
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
            ) : (
                <div className="d-flex flex-column" style={{ minHeight: "650px" }}>
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
                                    <td>{idx + 1}</td>
                                    <td>{hk.ky} - Năm học {hk.namHoc}</td>
                                    <td>{formatDate(hk.ngayBatDau)}</td>
                                    <td>{formatDate(hk.ngayKetThuc)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    <div className="d-flex justify-content-center mt-auto mb-3">
                        {Array.from({ length: tongTrang }, (_, i) => (
                            <button
                                key={i}
                                className={`btn btn-sm mx-1 ${i === trang ? 'btn-primary' : 'btn-outline-primary'}`}
                                onClick={() => loadHocKy(i)}
                            >
                                {i + 1}
                            </button>
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
}
export default HocKy;