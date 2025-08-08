'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const MonHoc = () => {

    const [listMonHoc, setListMonHoc] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadMonHoc = async () => {
            setLoading(true);
            try {
                let res = await Apis.get(endpoints['monHoc']);
                setListMonHoc(res.data)
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadMonHoc();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ MÔN HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/monhoc/them')}>
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
                            {listMonHoc.map((mh) => (
                                <tr key={mh.id}>
                                    <td>{mh.id}</td>
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
                </div>
            )}
        </div>
    );
}
export default MonHoc;