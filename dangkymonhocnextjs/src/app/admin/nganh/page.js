'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const Nganh = () => {

    const [listNganh, setListNganh] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadNganh = async () => {
            setLoading(true);
            try {
                let res = await Apis.get(endpoints['nganh']);
                setListNganh(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadNganh();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ NGÀNH HỌC</h1>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/nganh/them')}>
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
                                <th>Tên Ngành</th>
                                <th>Tên Khoa</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listNganh.map((nganh) => (
                                <tr key={nganh.id}>
                                    <td>{nganh.id}</td>
                                    <td>{nganh.tenNganh}</td>
                                    <td>{nganh.khoaId.tenKhoa}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => router.push(`/admin/nganh/${nganh.id}`)}>
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
export default Nganh;