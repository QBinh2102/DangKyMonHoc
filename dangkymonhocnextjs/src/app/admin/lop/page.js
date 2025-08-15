'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const Lop = () => {

    const [listLop, setListLop] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadLop = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['lop']);
            setListLop(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadLop();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ LỚP</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/lop/them')}>
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
                                <th>Mã Lớp</th>
                                <th>Sĩ Số</th>
                                <th>Ngành</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listLop.map((lop) => (
                                <tr key={lop.id}>
                                    <td>{lop.id}</td>
                                    <td>{lop.maLop}</td>
                                    <td>{lop.siSo}</td>
                                    <td>{lop.nganhId.tenNganh}</td>
                                    <td>
                                        <button className="btn btn-warning me-2" onClick={() => router.push(`/admin/lop/${lop.id}`)}>
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
export default Lop;