'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const GiangVien = () => {

    const [listGiangVien, setListGiangVien] = useState([]);
    const [kw, setKw] = useState("");
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadGiangVien = async () => {
        setLoading(true);
        try {
            let url = endpoints['themHoacLayGiangVien'];

            const params = new URLSearchParams();
            if (kw.trim() !== "") params.append("hoTen", kw);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await authApis().get(url);
            setListGiangVien(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadGiangVien();
    }, [kw]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ GIẢNG VIÊN</h1>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm giảng viên..."
                    value={kw}
                    onChange={(e) => setKw(e.target.value)}
                />

                <button
                    className="btn btn-success ms-2"
                    onClick={() => router.push('/admin/giangvien/them')}>
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
                                <th>Id</th>
                                <th>Họ Tên</th>
                                <th>Email</th>
                                <th>Học Vị</th>
                                <th>Thuộc Khoa</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listGiangVien.map((gv) => (
                                <tr key={gv.id}>
                                    <td>{gv.id}</td>
                                    <td>{gv.nguoiDung.hoTen}</td>
                                    <td>{gv.nguoiDung.email}</td>
                                    <td>{gv.hocVi}</td>
                                    <td>{gv.khoaId.tenKhoa}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => router.push(`/admin/giangvien/${gv.id}`)}>
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {listGiangVien.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy giảng viên nào</p>
                    )}
                </div>
            )}
        </div>
    );
}
export default GiangVien;