'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const GiangVien = () => {

    const [listGiangVien, setListGiangVien] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadGiangVien = async () => {
            setLoading(true);
            try {
                let res = await authApis().get(endpoints['themHoacLayGiangVien']);
                setListGiangVien(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadGiangVien();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ GIẢNG VIÊN</h1>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/giangvien/them')}>
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
                </div>
            )}
        </div>
    );
}
export default GiangVien;