'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const MonHoc = () => {

    const [listMonHoc, setListMonHoc] = useState([]);
    const [listKhoa, setListKhoa] = useState([]);
    const [selectedKhoa, setSelectedKhoa] = useState("");
    const [kw, setKw] = useState("");
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

    const loadMonHoc = async () => {
        setLoading(true);
        try {
            let url = endpoints['monHoc'];

            const params = new URLSearchParams();
            if (kw.trim() !== "") params.append("tenMon", kw);
            if (selectedKhoa !== "") params.append("khoaId", selectedKhoa);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await Apis.get(url);
            setListMonHoc(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadMonHoc();
        loadKhoa();
    }, []);

    useEffect(() => {
        loadMonHoc();
    }, [kw, selectedKhoa]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ MÔN HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control"
                    style={{ width: "250px" }}
                    placeholder="Tìm môn học..."
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
                    onClick={() => router.push('/admin/monhoc/them')}>
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
                                <th>Tên môn</th>
                                <th>Tín chỉ lý thuyết</th>
                                <th>Tín chỉ thực hành</th>
                                <th>Phần trăm giữa kỳ</th>
                                <th>Phần trăm cuối kỳ</th>
                                <th>Điểm qua môn</th>
                                <th>Thuộc khoa</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listMonHoc.map((mh, idx) => (
                                <tr key={mh.id}>
                                    <td>{idx + 1}</td>
                                    <td>{mh.tenMon}</td>
                                    <td>{mh.tinChiLyThuyet}</td>
                                    <td>{mh.tinChiThucHanh}</td>
                                    <td>{mh.phanTramGiuaKy}</td>
                                    <td>{mh.phanTramCuoiKy}</td>
                                    <td>{mh.diemQuaMon}</td>
                                    <td>{mh.khoaId.tenKhoa}</td>
                                    <td>
                                        <button className="btn btn-warning me-2" onClick={() => router.push(`/admin/monhoc/${mh.id}`)}>
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {listMonHoc.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy môn học nào</p>
                    )}
                </div>
            )}
        </div>
    );
}
export default MonHoc;