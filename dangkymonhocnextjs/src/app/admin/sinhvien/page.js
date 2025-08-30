'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const SinhVien = () => {

    const [listSinhVien, setListSinhVien] = useState([]);
    const [kw, setKw] = useState("");
    const [trang, setTrang] = useState(0);
    const [tongTrang, setTongTrang] = useState(0);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadSinhVien = async (page = 0) => {
        setLoading(true);
        try {
            let url = endpoints['sinhVienPage'];

            const params = new URLSearchParams();
            params.append("page", page);
            if (kw.trim() !== "") params.append("hoTen", kw);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await authApis().get(url);
            setListSinhVien(res.data.content);
            setTongTrang(res.data.totalPages);
            setTrang(res.data.number);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadSinhVien();
    }, [kw]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ SINH VIÊN</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm sinh viên..."
                    value={kw}
                    onChange={(e) => setKw(e.target.value)}
                />

                <button
                    className="btn btn-success ms-2"
                    onClick={() => router.push('/admin/sinhvien/them')}
                >
                    Thêm
                </button>
            </div>

            {loading ? (
                <div className="text-center">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (
                <div className="d-flex flex-column" style={{ minHeight: "650px" }}>
                    <table className="table text-center">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Họ Tên</th>
                                <th>Mã Lớp</th>
                                <th>Email</th>
                                <th>Khóa Học</th>
                                <th>Học Ngành</th>
                                <th>Học Khoa</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listSinhVien.map(sv => (
                                <tr key={sv.id}>
                                    <td>{sv.id}</td>
                                    <td>{sv.nguoiDung.hoTen}</td>
                                    <td>{sv.lopId.maLop}</td>
                                    <td>{sv.nguoiDung.email}</td>
                                    <td>{sv.khoaHoc}</td>
                                    <td>{sv.nganhId.tenNganh}</td>
                                    <td>{sv.khoaId.tenKhoa}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => router.push(`/admin/sinhvien/${sv.id}`)}>
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {listSinhVien.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy sinh viên nào</p>
                    )}

                    <div className="d-flex justify-content-center mt-auto mb-3">
                        {Array.from({ length: tongTrang }, (_, i) => (
                            <button
                                key={i}
                                className={`btn btn-sm mx-1 ${i === trang ? 'btn-primary' : 'btn-outline-primary'}`}
                                onClick={() => loadSinhVien(i)}
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
export default SinhVien;