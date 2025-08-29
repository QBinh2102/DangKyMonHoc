'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const QuyDinh = () => {

    const [listQuyDinh, setListQuyDinh] = useState([]);
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");
    const router = useRouter();

    const loadQuyDinh = async () => {
        setLoading(true);
        try {
            let res = await Apis.get(endpoints['quyDinh']);
            setListQuyDinh(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadQuyDinh();
    }, []);

    const deleteQuyDinh = async (id) => {
        try {
            await authApis().delete(endpoints['suaHoacXoaQuyDinhId'](id));
            setMsg("Xóa thành công!");
            await loadQuyDinh();
        } catch (ex) {
            console.error(ex);
            setMsg("Xóa thất bại!");
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ QUY ĐỊNH</h1>
            </div>

            {msg && (
                msg.includes("thành công") ? (
                    <div className="alert alert-success text-center" role="alert">
                        {msg}
                    </div>
                ) : (
                    <div className="alert alert-warning text-center" role="alert">
                        {msg}
                    </div>
                )
            )}

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/quydinh/them')}>
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
                                <th>Quy Định</th>
                                <th>Giá trị</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listQuyDinh.map((quyDinh, idx) => (
                                <tr key={quyDinh.id}>
                                    <td>{idx+1}</td>
                                    <td>{quyDinh.ten}</td>
                                    <td>{quyDinh.giaTri}</td>
                                    <td>
                                        <button className="btn btn-warning me-2" onClick={() => router.push(`/admin/quydinh/${quyDinh.id}`)}>
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                        <button
                                            className="btn btn-danger me-2"
                                            onClick={() => {
                                                if (window.confirm(`Bạn có chắc muốn xóa quy định "${quyDinh.ten}" không?`)) {
                                                    deleteQuyDinh(quyDinh.id);
                                                };
                                            }}
                                        >
                                            <span className="text-xl">🗑️</span>
                                            <span className="text-sm">Xóa</span>
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
export default QuyDinh;