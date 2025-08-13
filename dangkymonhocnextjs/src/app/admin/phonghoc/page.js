'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const PhongHoc = () => {

    const [listPhongHoc, setListPhongHoc] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadPhongHoc = async () => {
            setLoading(true);
            try {
                let res = await Apis.get(endpoints['phongHoc']);
                setListPhongHoc(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadPhongHoc();
    }, []);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ PHÒNG HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/phonghoc/them')}>
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
                                <th>Phòng</th>
                                <th>Loại</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listPhongHoc.map(ph =>(
                                <tr key={ph.id}>
                                    <td>{ph.id}</td>
                                    <td>{ph.tenPhong}</td>
                                    <td>{ph.loai === "LyThuyet" ? "Lý Thuyết" : "Thực Hành"}</td>
                                    <td>
                                        <button className="btn btn-warning me-2" onClick={() => router.push(`/admin/phonghoc/${ph.id}`)}>
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
export default PhongHoc;