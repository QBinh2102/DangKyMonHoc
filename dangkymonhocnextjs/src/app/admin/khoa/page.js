'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const Khoa = () => {

    const [listKhoa, setListKhoa] = useState([]);
    const [kw, setKw] = useState("");
    const [loading, setLoading] = useState(false);
    const [trang, setTrang] = useState(0);
    const [tongTrang, setTongTrang] = useState(0);
    const router = useRouter();

    const loadKhoa = async (page = 0) => {
        setLoading(true);
        try {
            let url = endpoints['khoaPage'];

            const params = new URLSearchParams();
            params.append("page", page);
            if (kw.trim() !== "") params.append("tenKhoa", kw);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await Apis.get(url);
            setListKhoa(res.data.content);
            setTongTrang(res.data.totalPages);
            setTrang(res.data.number);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadKhoa();
    }, [kw]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ KHOA</h1>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm khoa..."
                    value={kw}
                    onChange={(e) => setKw(e.target.value)}
                />

                <button
                    className="btn btn-success ms-2"
                    onClick={() => router.push('/admin/khoa/them')}
                >
                    Thêm
                </button>
            </div>
            <div>
                {loading ? (
                    <div className="text-center">
                        <p>Đang tải dữ liệu...</p>
                    </div>
                ) : (
                    <div className="d-flex flex-column" style={{ minHeight: "650px" }}>
                        <table className="table text-center">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên Khoa</th>
                                    <th>Công Cụ</th>
                                </tr>
                            </thead>
                            <tbody>
                                {listKhoa.map((khoa, idx) => (
                                    <tr key={khoa.id}>
                                        <td>{idx + 1}</td>
                                        <td>{khoa.tenKhoa}</td>
                                        <td>
                                            <button className="btn btn-warning" onClick={() => router.push(`/admin/khoa/${khoa.id}`)}>
                                                <span className="text-xl">✏️</span>
                                                <span className="text-sm">Sửa</span>
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>

                        {listKhoa.length === 0 && (
                            <p className="text-center mt-3 text-muted">Không tìm thấy khoa nào</p>
                        )}

                        <div className="d-flex justify-content-center mt-auto mb-3">
                            {Array.from({ length: tongTrang }, (_, i) => (
                                <button
                                    key={i}
                                    className={`btn btn-sm mx-1 ${i === trang ? 'btn-primary' : 'btn-outline-primary'}`}
                                    onClick={() => loadKhoa(i)}
                                >
                                    {i + 1}
                                </button>
                            ))}
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}
export default Khoa;