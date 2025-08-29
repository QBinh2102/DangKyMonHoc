'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";
import "./dangkymonhoc.css";

const DangKyMonHoc = () => {

    const [sinhVien, setSinhVien] = useState({});
    const [listBuoiHoc, setListBuoiHoc] = useState([]);
    const [hocKyMoiNhat, setHocKyMoiNhat] = useState({});
    const [loading, setLoading] = useState(false);
    const [listMonHoc, setListMonHoc] = useState([]);
    const [listDangKy, setListDangKy] = useState([]);
    const [selectedMonHoc, setSelectedMonHoc] = useState("");

    const loai = [
        { label: "Môn học mở theo lớp sinh viên", field: "lopId" },
        { label: "Lọc theo môn học", field: "monHoc" },
    ]
    const [selectedLoai, setSelectedLoai] = useState("");

    const loadDangKy = async () => {
        try {
            let res = await authApis().get(endpoints['danhSachDangKy']);
            setListDangKy(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadHocKyMoiNhat = async () => {
        try {
            let res = await authApis().get(endpoints['hocKyMoiNhat']);
            setHocKyMoiNhat(res.data);
            loadDangKy(res.data.id);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadSinhVien = async () => {
        try {
            let res = await authApis().get(endpoints['profile-sinhvien']);
            setSinhVien(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadBuoiHoc = async () => {
        setLoading(true);
        try {
            if (selectedLoai === "monHoc") {
                let res = await authApis().get(`${endpoints['layBuoiHoc']}?monHocId=${selectedMonHoc}`);
                setListBuoiHoc(res.data);
            } else {
                let res = await authApis().get(`${endpoints['layBuoiHoc']}?lopId=${sinhVien?.lopId?.id}`);
                setListBuoiHoc(res.data);
            }
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadSinhVien();
    }, []);

    useEffect(() => {
        if (sinhVien?.id) {
            loadBuoiHoc();
            loadHocKyMoiNhat();
        }
    }, [sinhVien]);

    const loadMonHoc = async () => {
        try {
            let res = await Apis.get(endpoints['monHoc']);
            setListMonHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        if (sinhVien?.id && selectedLoai === "lopId") {
            loadBuoiHoc();
        }
    }, [sinhVien, selectedLoai]);

    const chooseLoai = async (e) => {
        const l = e.target.value;
        setSelectedLoai(l);

        if (l === "monHoc") {
            await loadMonHoc();
        }
        else {
            setSelectedMonHoc("");
            setListMonHoc([]);
        }
    }

    useEffect(() => {
        if (selectedLoai === "monHoc") {
            if (selectedMonHoc) loadBuoiHoc();
            else setListBuoiHoc([]);
        }
    }, [selectedLoai, selectedMonHoc]);

    const addDangKy = async (buoiHocId, listLichHoc) => {
        const newDangKy = {
            sinhVienId: sinhVien.id,
            buoiHocId,
        };
        try {
            await authApis().post(endpoints['dangKy'], newDangKy);

            for (const lh of listLichHoc) {
                const newLichHoc = {
                    sinhVienId: sinhVien.id,
                    lichHocId: lh.id,
                };
                await authApis().post(endpoints['thoiKhoaBieuSinhVien'], newLichHoc);
            }

            let hocPhi = await authApis().get(endpoints['hocPhiMoiNhat']);
            console.log(hocPhi.data);
            await authApis().post(endpoints['chiTietHocPhi'], {}, {
                params: {
                    hocPhiId: hocPhi.data.id,
                    buoiHocId: buoiHocId
                }
            });
            
            loadDangKy();
            loadBuoiHoc();
        } catch (ex) {
            if (ex.response && ex.response.data) {
                alert(ex.response.data);
            } else {
                console.error(ex);
                alert("Có lỗi xảy ra, vui lòng thử lại.");
            }
        }
    }

    const deleteDangKy = async (dangKyId, buoiHocId) => {
        try {
            await authApis().delete(`${endpoints['chiTietHocPhi']}?buoiHocId=${buoiHocId}`);
            await authApis().delete(`${endpoints['thoiKhoaBieuSinhVien']}?dangKyId=${dangKyId}`);
            await authApis().delete(endpoints['xoaDangKy'](dangKyId));
            
            await loadDangKy();
            await loadBuoiHoc();
        } catch (ex) {
            console.error(ex);
        }
    }

    const formatNgayDangKy = (ngayDangKy) => {
        const date = new Date(ngayDangKy);
        const formatted =
            String(date.getDate()).padStart(2, "0") + "/" +
            String(date.getMonth() + 1).padStart(2, "0") + "/" +
            date.getFullYear() + " " +
            String(date.getHours()).padStart(2, "0") + ":" +
            String(date.getMinutes()).padStart(2, "0") + ":" +
            String(date.getSeconds()).padStart(2, "0");
        return formatted;
    }


    return (
        <div>
            <h1 className="text-center mt-3 mb-3">ĐĂNG KÝ MÔN HỌC {hocKyMoiNhat.ky} - NĂM HỌC {hocKyMoiNhat.namHoc}</h1>

            <div className="d-flex">
                <div className="select-container me-3">
                    <select
                        className="custom-select"
                        value={selectedLoai}
                        onChange={chooseLoai}
                    >
                        {loai.map((i) => (
                            <option key={i.field} value={i.field}>
                                {i.label}
                            </option>
                        ))}
                    </select>
                </div>

                {selectedLoai === "monHoc" &&
                    <div className="select-container">
                        <select
                            className="custom-select"
                            value={selectedMonHoc}
                            onChange={(e) => setSelectedMonHoc(e.target.value)}
                        >
                            <option value="">-- Chọn môn học --</option>
                            {listMonHoc.map((mh) => (
                                <option key={mh.id} value={mh.id}>
                                    {mh.tenMon}
                                </option>
                            ))}
                        </select>
                    </div>
                }
            </div>

            <div>
                <table className="table table-bordered table-dangky">
                    <thead>
                        <tr>
                            <th style={{ width: "30%" }}>Tên môn học</th>
                            <th style={{ width: "5%" }}>Số tín chỉ</th>
                            <th style={{ width: "7%" }}>Lớp</th>
                            <th style={{ width: "5%" }}>Số lượng</th>
                            <th style={{ width: "5%" }}>Còn lại</th>
                            <th style={{ width: "40%" }}>Thời khóa biểu</th>
                            <th style={{ width: "7%" }}></th>
                        </tr>
                    </thead>
                    <tbody>
                        {listBuoiHoc.map(bh => (
                            <tr key={bh.buoiHocId}>
                                <td className="format-name">{bh.tenMon}</td>
                                <td className="format-properties">{bh.soTinChi}</td>
                                <td className="format-properties">{bh.lop}</td>
                                <td className="format-properties">{bh.soLuong === 0 ? "" : bh.soLuong}</td>
                                <td className="format-properties">{bh.conLai === 0 ? "" : bh.conLai}</td>
                                <td>
                                    {bh.listLichHoc.map(lh => {
                                        const ngayBatDau = new Date(lh.ngayBatDau).toLocaleDateString("vi-VN");
                                        const ngayKetThuc = new Date(lh.ngayKetThuc).toLocaleDateString("vi-VN");

                                        return (
                                            <p key={lh.id} style={{ fontSize: "0.8rem", margin: 0 }}>
                                                {lh.thu}, từ {lh.tietHocId?.gioBatDau} đến {lh.tietHocId?.gioKetThuc},
                                                phòng {lh.phongHocId?.tenPhong},
                                                gv {lh.buoiHocId?.giangVienId?.nguoiDung?.hoTen},
                                                {ngayBatDau} đến {ngayKetThuc}
                                            </p>
                                        );
                                    })}
                                </td>
                                <td className="format-properties">
                                    {(() => {
                                        const dk = listDangKy.find(d => d.buoiHocId?.id === bh.buoiHocId);
                                        return dk ? (
                                            <button
                                                className="btn btn-danger"
                                                style={{ height: '40px', width: '60px' }}
                                                onClick={() => deleteDangKy(dk.id, bh.buoiHocId)}
                                            >
                                                Xóa
                                            </button>
                                        ) : (
                                            <button
                                                className="btn btn-success"
                                                style={{ height: '40px', width: '60px' }}
                                                onClick={() => addDangKy(bh.buoiHocId, bh.listLichHoc)}
                                            >
                                                Thêm
                                            </button>
                                        );
                                    })()}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            <div>
                <h3 className="mt-3 mb-3">Danh sách môn học đã đăng ký</h3>
                <table className="table table-bordered table-dangky">
                    <thead>
                        <tr>
                            <th style={{ width: "30%" }}>Tên môn học</th>
                            <th style={{ width: "5%" }}>Số tín chỉ</th>
                            <th style={{ width: "7%" }}>Lớp</th>
                            <th style={{ width: "40%" }}>Ngày đăng ký</th>
                            <th style={{ width: "7%" }}>Xóa</th>
                        </tr>
                    </thead>
                    <tbody>
                        {listDangKy.map(dk => (
                            <tr key={dk.id}>
                                <td className="format-name">{dk.buoiHocId?.monHocId?.tenMon}</td>
                                <td className="format-properties">{dk.buoiHocId?.monHocId?.tinChiLyThuyet + dk.buoiHocId?.monHocId?.tinChiThucHanh}</td>
                                <td className="format-properties">{dk.buoiHocId?.lopId?.maLop}</td>
                                <td className="format-properties">{formatNgayDangKy(dk.ngayDangKy)}</td>
                                <td className="format-properties">
                                    <button className="btn" onClick={() => deleteDangKy(dk.id)}>❌</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
export default DangKyMonHoc;