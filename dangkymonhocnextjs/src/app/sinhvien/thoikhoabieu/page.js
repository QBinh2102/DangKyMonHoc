'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import React, { useEffect, useState } from "react";
import "./thoikhoabieu.css";
import dayjs from "dayjs";

const ThoiKhoaBieu = () => {

    const [listHocKy, setListHocKy] = useState([]);
    const [selectedHocKy, setSelectedHocKy] = useState("");
    const [listTietHoc, setListTietHoc] = useState([]);
    const [listThoiKhoaBieu, setListThoiKhoaBieu] = useState([]);
    const [weeks, setWeeks] = useState([]);
    const [selectedTuan, setSelectedTuan] = useState("");

    const thu = [
        { label: "Thứ 2" },
        { label: "Thứ 3" },
        { label: "Thứ 4" },
        { label: "Thứ 5" },
        { label: "Thứ 6" },
        { label: "Thứ 7" },
        { label: "Chủ nhật" },
    ];

    const loadTietHoc = async () => {
        try {
            let res = await Apis.get(endpoints['tietHoc']);
            setListTietHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadHocKy = async () => {
        try {
            let res = await authApis().get(endpoints['hocKyBySinhVien']);
            setListHocKy(res.data);
            setSelectedHocKy(res.data[res.data.length - 1].id);

            const lastHocKy = res.data[res.data.length - 1];
            if (lastHocKy) {
                generateTuan(lastHocKy.ngayBatDau, lastHocKy.ngayKetThuc);
            }
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadHocKy();
        loadTietHoc();
    }, []);

    const loadThoiKhoaBieu = async (tuanId) => {
        try {
            const currentTuan = weeks.find(i => i.id == tuanId);
            let res = await authApis().get(endpoints['thoiKhoaBieuSinhVien'], {
                params: {
                    hocKyId: parseInt(selectedHocKy),
                    ngayBatDau: currentTuan?.ngayBatDau,
                    ngayKetThuc: currentTuan?.ngayKetThuc
                }
            });
            setListThoiKhoaBieu(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const chooseHocKy = async (e) => {
        const hocKyId = e.target.value;
        setSelectedHocKy(hocKyId);

        const hocKy = listHocKy.find(hk => hk.id == hocKyId);
        if (hocKy) {
            generateTuan(hocKy.ngayBatDau, hocKy.ngayKetThuc);
        }
    }

    const chooseTuan = async (e) => {
        const tuanId = e.target.value;
        setSelectedTuan(e.target.value);
        if (tuanId && await loadThoiKhoaBieu(tuanId));
    }

    const generateTuan = (start, end) => {
        const startDate = dayjs(start);
        const endDate = dayjs(end);

        let weeksArr = [];
        let currentStart = startDate;

        let index = 1;
        while (currentStart.isBefore(endDate) || currentStart.isSame(endDate)) {
            let currentEnd = currentStart.add(6, "day");
            if (currentEnd.isAfter(endDate))
                currentEnd = endDate;

            weeksArr.push({
                id: index,
                ngayBatDau: currentStart.format("YYYY-MM-DD"),
                ngayKetThuc: currentEnd.format("YYYY-MM-DD"),
            });

            currentStart = currentEnd.add(1, 'day');
            index++;
        }

        setWeeks(weeksArr);

        const today = dayjs();
        const currentWeek = weeksArr.find(
            (w) =>
                today.isAfter(dayjs(w.ngayBatDau).subtract(1, "day")) &&
                today.isBefore(dayjs(w.ngayKetThuc).add(1, "day"))
        );

        let defaultWeek;
        if (currentWeek) {
            defaultWeek = currentWeek.id;
        } else if (weeksArr.length > 0) {
            defaultWeek = weeksArr[0].id;
        }

        setSelectedTuan(defaultWeek);
    };


    const chooseTuanTruoc = () => {
        if (selectedTuan !== 1) {
            const newSelected = parseInt(selectedTuan) - 1;
            setSelectedTuan(newSelected);
        }
    }

    const chooseTuanSau = () => {
        if (selectedTuan !== weeks.length) {
            const newSelected = parseInt(selectedTuan) + 1;
            setSelectedTuan(newSelected);
        }
    }

    useEffect(() => {
        if (weeks.length > 0 && selectedTuan) {
            loadThoiKhoaBieu(selectedTuan);
        }
    }, [weeks, selectedTuan]);

    return (
        <div>
            <h1 className="text-center mt-3 mb-3">
                Thời khóa biểu
            </h1>

            <div className="select-container">
                <select
                    className="custom-select"
                    value={selectedHocKy}
                    onChange={chooseHocKy}
                >
                    {listHocKy.map((i) => (
                        <option key={i.id} value={i.id}>
                            {i.ky} - {i.namHoc}
                        </option>
                    ))}
                </select>
            </div>

            <div className="select-container">
                <select
                    className="custom-select"
                    value={selectedTuan}
                    onChange={chooseTuan}
                >
                    {weeks.map((i) => {
                        const ngayBatDau = new Date(i.ngayBatDau).toLocaleDateString("vi-VN");
                        const ngayKetThuc = new Date(i.ngayKetThuc).toLocaleDateString("vi-VN");
                        return (
                            <option key={i.id} value={i.id}>
                                Từ ngày {ngayBatDau} đến ngày {ngayKetThuc}
                            </option>
                        );
                    })}
                </select>
            </div>

            <div className="timetable">
                <div className="header-cell"></div>
                {thu.map((i, idx) => (
                    <div key={idx} className="header-cell">
                        {i.label}
                    </div>
                ))}

                {listTietHoc.map((i) => (
                    <React.Fragment key={i.id}>
                        <div key={i.id} className="time-cell">
                            <div>{i.tiet}</div>
                            <div>{i.gioBatDau} - {i.gioKetThuc}</div>

                        </div>

                        {thu.map((t, idx) => {
                            const mon = listThoiKhoaBieu.find(
                                tkb => i.tiet === tkb.lichHocId?.tietHocId?.tiet && idx === thu.findIndex(t => t.label === tkb.lichHocId?.thu)
                            );
                            return (
                                <div
                                    key={`${i.id}-${idx}`}
                                    className={`cell ${mon?.lichHocId?.loai === "LyThuyet" ? "subject blue" : mon?.lichHocId?.loai === "ThucHanh" ? "subject red" : ""}`}
                                >
                                    {mon && (
                                        <>
                                            <div><span className="subject-title">{mon?.lichHocId?.buoiHocId?.monHocId?.tenMon}</span></div>
                                            <div><strong>GV:</strong> {mon?.lichHocId?.buoiHocId?.giangVienId?.nguoiDung?.hoTen}</div>
                                            <div><strong>Phòng:</strong> {mon?.lichHocId?.phongHocId?.tenPhong}</div>
                                        </>
                                    )}
                                </div>
                            );
                        })}
                    </React.Fragment>
                ))}
            </div>

            <div className="d-flex justify-content-end">
                <button className="btn btn-lg" onClick={chooseTuanTruoc}> ◀ </button>
                <button className="btn btn-lg" onClick={chooseTuanSau}> ▶ </button>
            </div>
        </div>
    );
}
export default ThoiKhoaBieu;