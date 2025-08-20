'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";
import "./thoikhoabieu.css";

const ThoiKhoaBieu = () => {

    const [listHocKy, setListHocKy] = useState([]);
    const [selectedHocKy, setSelectedHocKy] = useState("");

    const loadHocKy = async () => {
        try {
            let res = await authApis().get(endpoints['hocKyBySinhVien']);
            setListHocKy(res.data);
            console.log(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadHocKy();
    }, []);

    

    return (
        <div>
            <h1 className="text-center mt-3 mb-3">
                Thời khóa biểu
            </h1>

            <div className="select-container">
                <select
                    className="custom-select"
                    value={selectedHocKy}
                    onChange={(e) => setSelectedHocKy(e.target.value)}
                >
                    {listHocKy.map((i) => (
                        <option key={i.id} value={i.id}>
                            Học kỳ {i.ky} - {i.namHoc}
                        </option>
                    ))}
                </select>
            </div>

        </div>
    );
}
export default ThoiKhoaBieu;