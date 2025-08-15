'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const SinhVien = () => {

    const [listSinhVien, setListSinhVien] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadSinhVien = async () => {
            setLoading(true);
            try {
                let res = await authApis().get(endpoints['themHoacLaySinhVien']);
                setListSinhVien(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadSinhVien();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ SINH VIÊN</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/sinhvien/them')}>
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
                </div>
            )}
        </div>
    );
}
export default SinhVien;