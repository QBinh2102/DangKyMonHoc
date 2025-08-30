'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const BuoiHoc = () => {

    const [listBuoiHoc, setListBuoiHoc] = useState([]);
    const [selectedMaLop, setSelectedMaLop] = useState("");
    const [trang, setTrang] = useState(0);
    const [tongTrang, setTongTrang] = useState(0);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const loadBuoiHoc = async (page = 0) => {
        setLoading(true);
        try {
            let url = endpoints['buoiHocPage'];

            const params = new URLSearchParams();
            params.append("page", page);
            if (selectedMaLop.trim() !== "") params.append("maLop", selectedMaLop);

            if (params.toString() !== "") url += `?${params.toString()}`;

            let res = await authApis().get(url);
            setListBuoiHoc(res.data.content);
            setTongTrang(res.data.totalPages);
            setTrang(res.data.number);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadBuoiHoc();
    }, [selectedMaLop]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ BUỔI HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <input
                    type="text"
                    className="form-control ms-2"
                    style={{ width: "250px" }}
                    placeholder="Tìm theo mã lớp..."
                    value={selectedMaLop}
                    onChange={(e) => setSelectedMaLop(e.target.value)}
                />

                <button
                    className="btn btn-success ms-2"
                    onClick={() => router.push('/admin/buoihoc/them')}
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
                                <th>Môn Học</th>
                                <th>Mã Lớp</th>
                                <th>Giảng Viên</th>
                                <th>Học Kỳ</th>
                                <th>Sĩ Số</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listBuoiHoc.map((bh, idx) => (
                                <tr key={bh.id}>
                                    <td>{idx + 1}</td>
                                    <td>{bh.monHocId.tenMon}</td>
                                    <td>{bh.lopId.maLop}</td>
                                    <td>{bh.giangVienId?.nguoiDung.hoTen}</td>
                                    <td>{bh.hocKyId.ky} - {bh.hocKyId.namHoc}</td>
                                    <td>{bh.siSo}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => router.push(`/admin/buoihoc/${bh.id}`)}>
                                            <span className="text-xl">✏️</span>
                                            <span className="text-sm">Sửa</span>
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {listBuoiHoc.length === 0 && (
                        <p className="text-center mt-3 text-muted">Không tìm thấy buổi học nào</p>
                    )}

                    <div className="d-flex justify-content-center mt-auto mb-3">
                        {Array.from({ length: tongTrang }, (_, i) => (
                            <button
                                key={i}
                                className={`btn btn-sm mx-1 ${i === trang ? 'btn-primary' : 'btn-outline-primary'}`}
                                onClick={() => loadBuoiHoc(i)}
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
export default BuoiHoc;