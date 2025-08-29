'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const TietHoc = () => {

    const [listTietHoc, setListTietHoc] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadTietHoc = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['tietHoc']);
            setListTietHoc(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadTietHoc();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ TIẾT HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/tiethoc/them')}>
                    Thêm
                </button>
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
                                <th>Tiết</th>
                                <th>Giờ Bắt Đầu</th>
                                <th>Giờ Kết Thúc</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listTietHoc.map((tietHoc, idx) => (
                                <tr key={tietHoc.id}>
                                    <td>{idx+1}</td>
                                    <td>{tietHoc.tiet}</td>
                                    <td>{tietHoc.gioBatDau}</td>
                                    <td>{tietHoc.gioKetThuc}</td>
                                    <td>
                                        <button className="btn btn-warning me-2" onClick={() => router.push(`/admin/tiethoc/${tietHoc.id}`)}>
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
export default TietHoc;