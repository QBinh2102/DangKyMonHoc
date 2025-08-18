'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";
import "./decuong.css";
import { useRouter } from "next/navigation";

const DeCuong = () => {

    const [sinhVien, setSinhVien] = useState({});
    const [listMonHoc, setListMonHoc] = useState([]);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

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
            <h1 className="text-center">Đề cương môn học</h1>
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
                                            <button
                                                type="button"
                                                class="btn btn-outline-secondary"
                                                onClick={() => router.push(`/sinhvien/decuong/${mh.monHoc.id}`)}
                                            >
                                                {mh.monHoc.tenMon}
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        ))}
                </div>
            )
            }
        </div>

    );
}
export default DeCuong;