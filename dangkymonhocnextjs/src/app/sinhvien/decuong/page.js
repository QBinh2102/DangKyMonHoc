'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";
import "./decuong.css";
import { useRouter } from "next/navigation";
import Link from "next/link";

const DeCuong = () => {

    const [sinhVien, setSinhVien] = useState({});
    const [listMonHoc, setListMonHoc] = useState([]);
    const [loading, setLoading] = useState(false);

    const loadMonHocTheoNganh = async (nganhId) => {
        setListMonHoc([]);
        setLoading(true);
        try {
            let res = await Apis.get(`${endpoints['nganhMonHoc']}?nganhId=${nganhId}`);
            setListMonHoc(res.data);
            console.log(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    const loadSinhVien = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['profile-sinhvien']);
            setSinhVien(res.data);
            console.log(res.data);
            await loadMonHocTheoNganh(res.data.nganhId.id);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadSinhVien();
    }, []);

    const groupedByKy = listMonHoc.reduce((acc, item) => {
        const ky = item.ky;
        if (!acc[ky]) acc[ky] = [];
        acc[ky].push(item);

        return acc;
    }, {});

    return (
        <div>
            <h1 className="text-center mt-3 mb-3">
                Đề cương môn học
            </h1>

            {loading ? (
                <p>Đang tải dữ liệu...</p>
            ) : (
                <div className="hocKy-grid">
                    {Object.keys(groupedByKy)
                        .sort((a, b) => a - b)
                        .map((ky) => (
                            <div key={ky} className="hocKy-card">
                                <h2 className="hocKy-title">Học kỳ {ky}</h2>
                                <ul>
                                    {groupedByKy[ky].map((mh) => (
                                        <li key={mh.monHoc.id}>
                                            {mh.monHoc.deCuong !== "" ? (
                                                <Link
                                                    className="decuong-link"
                                                    href={mh.monHoc.deCuong}
                                                    target="_blank"
                                                    rel="noopener noreferrer"
                                                >
                                                    {mh.monHoc.tenMon}
                                                </Link>
                                            ) : (
                                                <p className="decuong-item">{mh.monHoc.tenMon}</p>
                                            )}

                                        </li>
                                    ))}
                                </ul>
                            </div>
                        ))}
                </div>
            )}
        </div>

    );
}
export default DeCuong;