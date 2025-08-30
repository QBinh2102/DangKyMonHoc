'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const Nganh = () => {

    const [listNganh, setListNganh] = useState([]);
    const [listKhoa, setListKhoa] = useState([]);
    const [selectedKhoa, setSelectedKhoa] = useState("");
    const [kw, setKw] = useState("");
    const [trang, setTrang] = useState(0);
    const [tongTrang, setTongTrang] = useState(0);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadKhoa = async () => {
        try {
            let res = await Apis.get(endpoints['khoa']);
            setListKhoa(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadNganh = async (page = 0) => {
        setLoading(true);
        try {
            let url = endpoints['nganhPage'];

            const params = new URLSearchParams();
            params.append("page", page);
            if (kw.trim() !== "") params.append("tenNganh", kw);
            if (selectedKhoa !== "") params.append("khoaId", selectedKhoa);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await Apis.get(url);
            setListNganh(res.data.content);
            setTongTrang(res.data.totalPages);
            setTrang(res.data.number);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadNganh();
        loadKhoa();
    }, []);

    useEffect(() => {
        loadNganh();
    }, [kw, selectedKhoa]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ NGÀNH HỌC</h1>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm ngành..."
                    value={kw}
                    onChange={(e) => setKw(e.target.value)}
                />

                <select
                    className="form-select w-auto ms-2"
                    value={selectedKhoa}
                    onChange={(e) => setSelectedKhoa(e.target.value)}
                >
                    <option value="">-- Chọn khoa --</option>
                    {listKhoa.map(k => (
                        <option key={k.id} value={k.id}>{k.tenKhoa}</option>
                    ))}
                </select>

                <button
                    className="btn btn-success ms-2"
                    onClick={() => router.push('/admin/nganh/them')}
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
                                <th>STT</th>
                                <th>Tên Ngành</th>
                                <th>Tên Khoa</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listNganh.map((nganh, idx) => (
                                <tr key={nganh.id}>
                                    <td>{idx + 1}</td>
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

                    {listNganh.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy ngành nào</p>
                    )}

                    <div className="d-flex justify-content-center mt-auto mb-3">
                        {Array.from({ length: tongTrang }, (_, i) => (
                            <button
                                key={i}
                                className={`btn btn-sm mx-1 ${i === trang ? 'btn-primary' : 'btn-outline-primary'}`}
                                onClick={() => loadNganh(i)}
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
export default Nganh;