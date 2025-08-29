'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const Khoa = () => {

    const [listKhoa, setListKhoa] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadKhoa = async () => {
            setLoading(true);
            try {
                let res = await Apis.get(endpoints['khoa']);
                setListKhoa(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadKhoa();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ KHOA</h1>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/khoa/them')}>
                    Thêm
                </button>
            </div>
            <div>
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
                                    <th>Tên Khoa</th>
                                    <th>Công Cụ</th>
                                </tr>
                            </thead>
                            <tbody>
                                {listKhoa.map((khoa, idx) => (
                                    <tr key={khoa.id}>
                                        <td>{idx+1}</td>
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
                    </div>
                )}
            </div>
        </div>
    );
}
export default Khoa;