'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const HocKy = () => {

    const [listHocKy, setListHocKy] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadHocKy = async () => {
            try {
                let res = await authApis().get(endpoints.hocKy);
                setListHocKy(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }

        loadHocKy();
    }, []);

    return (
        <div>
            <div className="text-center mb-5 mt-5">
                <h1>QUẢN LÝ HỌC KỲ</h1>
            </div>

            {loading ? (
                <div className="text-center mt-5">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (<div>
                <table className="table text-center">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Học Kỳ</th>
                            <th>Năm Học</th>
                        </tr>
                    </thead>
                    <tbody>
                        {listHocKy.map((hk, idx) => (
                            <tr key={hk.id}>
                                <td>{idx+1}</td>
                                <td>{hk.ky}</td>
                                <td>{hk.namHoc}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>)}
        </div>
    );
}
export default HocKy;