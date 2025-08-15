'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const BuoiHoc = () => {

    const [listBuoiHoc, setListBuoiHoc] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadBuoiHoc = async () => {
            setLoading(true);
            try{
                let res = await authApis().get(endpoints['themHoacLayBuoiHoc']);
                setListBuoiHoc(res.data);
                console.info(res.data);
            }catch(ex){
                console.error(ex);
            }finally{
                setLoading(false);
            }
        }

        loadBuoiHoc();
    },[]);

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>QUẢN LÝ BUỔI HỌC</h1>
            </div>

            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => router.push('/admin/buoihoc/them')}>Thêm</button>
            </div>

            {loading ? (
                <div className="text-center">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ):(
                <div>
                    <table className="table text-center">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Môn Học</th>
                                <th>Mã Lớp</th>
                                <th>Giảng Viên</th>
                                <th>Học Kỳ</th>
                                <th>Sĩ Số</th>
                                <th>Công Cụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listBuoiHoc.map(bh => (
                                <tr key={bh.id}>
                                    <td>{bh.id}</td>
                                    <td>{bh.monHocId.tenMon}</td>
                                    <td>{bh.lopId.maLop}</td>
                                    <td>{bh.giangVienId.nguoiDung.hoTen}</td>
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
                </div>
            )}
        </div>
    );
}
export default BuoiHoc;