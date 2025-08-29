'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const PhongHoc = () => {

    const [listPhongHoc, setListPhongHoc] = useState([]);
    const [kw, setKw] = useState("");
    const [loai, setLoai] = useState("");
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadPhongHoc = async () => {
        setLoading(true);
        try {
            let url = endpoints['phongHoc'];

            const params = new URLSearchParams();
            if (kw.trim() !== "") params.append("tenPhong", kw);
            if (loai !== "") params.append("loai", loai);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await Apis.get(url);
            setListPhongHoc(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadPhongHoc();
    }, [kw, loai]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ PHÒNG HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm phòng..."
                    value={kw}
                    onChange={(e) => setKw(e.target.value)}
                />

                <select
                    className="form-select w-auto ms-2"
                    value={loai}
                    onChange={(e) => setLoai(e.target.value)}
                >
                    <option value="">-- Chọn loại --</option>
                    <option value="LyThuyet">Lý thuyết</option>
                    <option value="ThucHanh">Thực hành</option>
                </select>

                <button
                    className="btn btn-success ms-2"
                    onClick={() => router.push('/admin/phonghoc/them')}
                >
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
                                <th>Phòng</th>
                                <th>Loại</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listPhongHoc.map((ph, idx) => (
                                <tr key={ph.id}>
                                    <td>{idx + 1}</td>
                                    <td>{ph.tenPhong}</td>
                                    <td>{ph.loai === "LyThuyet" ? "Lý Thuyết" : "Thực Hành"}</td>
                                    <td>
                                        <button
                                            className="btn btn-warning me-2"
                                            onClick={() => router.push(`/admin/phonghoc/${ph.id}`)}
                                        >
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {listPhongHoc.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy phòng học nào</p>
                    )}
                </div>
            )}
        </div>
    );
}
export default PhongHoc;