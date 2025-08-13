'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import "../../admin.css";

const SuaBuoiHoc = () => {

    const info = [{
        label: "Loại",
        field: "loai",
        type: "text",
        disabled: true,
    }, {
        label: "Sĩ số",
        field: "siSo",
        type: "number",
        disabled: false,
        min: 0,
    }]
    const { id } = useParams();
    const [buoiHoc, setBuoiHoc] = useState({
        monHocId: {},
        giangVienId: {},
        hocKyId: {},
    });
    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const [newLichHoc, setNewLichHoc] = useState({
        lyThuyet: {
            gioBatDau: "",
            ngayBatDau: "",
            phong: "",
        },
        thucHanh: {
            gioBatDau: "",
            ngayBatDau: "",
            phong: "",
        }
    });

    const [listPhongHoc, setListPhongHoc] = useState({ phongLyThuyet: [], phongThucHanh: [] });
    const [listLichHoc, setListLichHoc] = useState([]);

    const loadPhongHoc = async (loai) => {
        try {
            let dsPhongLT = await Apis.get(`${endpoints['phongHoc']}?loai=LyThuyet`);
            let phongLyThuyet = dsPhongLT.data;
            let phongThucHanh = [];
            if (loai === "LT-TH") {
                let dsPhongTH = await Apis.get(`${endpoints['phongHoc']}?loai=ThucHanh`);
                phongThucHanh = dsPhongTH.data;
            }
            setListPhongHoc({
                phongLyThuyet,
                phongThucHanh,
            });
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadLichHoc = async (buoiHocId) => {
        try {
            let res = await Apis.get(`${endpoints['lichHoc']}?buoiHocId=${buoiHocId}`);
            setListLichHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadBuoiHoc = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['suaHoacLayBuoiHocId'](id));
            setBuoiHoc(res.data);
            await loadPhongHoc(res.data.loai);
            await loadLichHoc(res.data.id);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadBuoiHoc();
    }, [id]);

    const getLoaiBuoiHoc = (loai) => {
        if (loai === "LT") return "Lý thuyết";
        if (loai === "LT-TH") return "Lý thuyết - thực hành";
        return loai ?? "";
    }

    const getLoaiLichHoc = (loai) => {
        if (loai === "LyThuyet") return "Lý thuyết";
        if (loai === "ThucHanh") return "Thực hành";
        return loai ?? "";
    }

    const validateLichHoc = () => {
        if (!newLichHoc.lyThuyet.gioBatDau || !newLichHoc.lyThuyet.ngayBatDau || !newLichHoc.lyThuyet.phong)
            return false;

        if (buoiHoc.loai === "LT-TH") {
            if (!newLichHoc.thucHanh.gioBatDau || !newLichHoc.thucHanh.ngayBatDau || !newLichHoc.thucHanh.phong)
                return false;
        }

        return true;
    }

    const addLichHoc = async (e) => {
        e.preventDefault();
        if (!validateLichHoc) {
            alert("Vui lòng nhập đủ thông tin trước khi thêm.");
            return;
        }

        setMsg("");
        try {
            const newLichHocLT = {
                ...newLichHoc.lyThuyet,
                buoiHocId: parseInt(id),
                loai: "LyThuyet",
            }
            console.log(newLichHocLT);
            await authApis().post(endpoints['themLichHoc'], newLichHocLT); // Thêm lịch học lý thuyết

            if (buoiHoc.loai === "LT-TH") {
                const newLichHocTH = {
                    ...newLichHoc.thucHanh,
                    buoiHocId: parseInt(id),
                    loai: "ThucHanh",
                }
                await authApis().post(endpoints['themLichHoc'], newLichHocTH); // Thêm lịch học thực hành
            }
            setMsg("Thêm thành công!");
            setNewLichHoc({
                lyThuyet: {},
                thucHanh: {}
            });
            await loadBuoiHoc();
        } catch (ex) {
            setMsg("Thêm thất bại!");
        }
    }

    const updateBuoiHoc = async (e) => {
        e.preventDefault();
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaHoacLayBuoiHocId'](id), buoiHoc);
            setMsg("Cập nhật thành công!");
        } catch (ex) {
            setMsg("Cập nhật thất bại!");
            console.error(ex);
        } finally {
            setUpdateLoading(true);
        }
    }

    const grouped = listLichHoc.reduce((groups, item) => {
        if (!groups[item.lan]) groups[item.lan] = [];
        groups[item.lan].push(item);
        return groups;
    }, {});

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN BUỔI HỌC</h1>
            </div>

            {msg && (
                msg.includes("thành công") ? (
                    <div className="alert alert-success text-center" role="alert">
                        {msg}
                    </div>
                ) : (
                    <div className="alert alert-warning text-center" role="alert">
                        {msg}
                    </div>
                )
            )}

            {loading ? (
                <div className="text-center">
                    <p>Đang tải dữ liệu...</p>
                </div>
            ) : (
                <div>
                    <form onSubmit={updateBuoiHoc} className="w-50 mx-auto">
                        <div className="mt-3 mb-3">
                            <label htmlFor="monHocId" className="form-label">Môn học</label>
                            <input
                                id="monHocId"
                                type="text"
                                className="form-control"
                                value={buoiHoc.monHocId.tenMon || ""}
                                disabled
                                required
                            />
                        </div>

                        <div className="mt-3 mb-3">
                            <label htmlFor="giangVienId" className="form-label">Giảng viên</label>
                            <input
                                id="giangVienId"
                                type="text"
                                className="form-control"
                                value={buoiHoc.giangVienId.nguoiDung?.hoTen || ""}
                                disabled
                                required
                            />
                        </div>

                        <div className="mt-3 mb-3">
                            <label htmlFor="hocKyId" className="form-label">Học kỳ</label>
                            <input
                                id="hocKyId"
                                type="text"
                                className="form-control"
                                value={`${buoiHoc.hocKyId.ky} - ${buoiHoc.hocKyId.namHoc}` || ""}
                                disabled
                                required
                            />
                        </div>

                        {info.map(i => (
                            <div className="mt-3 mb-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    value={i.field === "loai" ? getLoaiBuoiHoc(buoiHoc[i.field]) : buoiHoc[i.field] ?? ""}
                                    disabled={i.disabled}
                                    min={i.min !== undefined ? i.min : undefined}
                                    onChange={(e) => setBuoiHoc({ ...buoiHoc, [i.field]: e.target.value })}
                                    required
                                />
                            </div>
                        ))}

                        <div>
                            <h2>Lịch học</h2>
                            <div className="lich-hoc-group">
                                <h5>Lý thuyết</h5>
                                <div className="row mt-3 mb-3 align-items-center">
                                    <div className="col-md-4">
                                        <input
                                            type="time"
                                            className="form-control"
                                            value={newLichHoc.lyThuyet.gioBatDau || ""}
                                            onChange={(e) => {
                                                let timeValue = e.target.value;
                                                if (timeValue && timeValue.length === 5) {
                                                    timeValue += ":00";
                                                }
                                                setNewLichHoc({
                                                    ...newLichHoc,
                                                    lyThuyet: { ...newLichHoc.lyThuyet, gioBatDau: timeValue }
                                                });
                                            }}
                                        />
                                    </div>

                                    <div className="col-md-4">
                                        <input
                                            type="date"
                                            className="form-control"
                                            value={newLichHoc.lyThuyet.ngayBatDau || ""}
                                            onChange={(e) => setNewLichHoc({ ...newLichHoc, lyThuyet: { ...newLichHoc.lyThuyet, ngayBatDau: e.target.value } })}
                                        />
                                    </div>

                                    <div className="col-md-4">
                                        <select
                                            className="form-select"
                                            value={newLichHoc.lyThuyet.phongHocId || ""}
                                            onChange={(e) => setNewLichHoc({ ...newLichHoc, lyThuyet: { ...newLichHoc.lyThuyet, phongHocId: e.target.value } })}
                                        >
                                            <option value="">--Chọn phòng --</option>
                                            {listPhongHoc.phongLyThuyet.map(i => (
                                                <option key={i.id} value={i.id}>{i.tenPhong}</option>
                                            ))}
                                        </select>
                                    </div>
                                </div>
                                {buoiHoc.loai === "LT-TH" && (
                                    <>
                                        <h5>Thực hành</h5>
                                        <div className="row mt-3 mb-3 align-items-center">
                                            <div className="col-md-4">
                                                <input
                                                    type="time"
                                                    className="form-control"
                                                    value={newLichHoc.thucHanh.gioBatDau || ""}
                                                    onChange={(e) => {
                                                        let timeValue = e.target.value;
                                                        if (timeValue && timeValue.length === 5) {
                                                            timeValue += ":00";
                                                        }
                                                        setNewLichHoc({
                                                            ...newLichHoc,
                                                            thucHanh: { ...newLichHoc.thucHanh, gioBatDau: timeValue }
                                                        });
                                                    }}
                                                />
                                            </div>

                                            <div className="col-md-4">
                                                <input
                                                    type="date"
                                                    className="form-control"
                                                    value={newLichHoc.thucHanh.ngayBatDau || ""}
                                                    onChange={(e) => setNewLichHoc({ ...newLichHoc, thucHanh: { ...newLichHoc.thucHanh, ngayBatDau: e.target.value } })}
                                                />
                                            </div>

                                            <div className="col-md-4">
                                                <select
                                                    className="form-select"
                                                    value={newLichHoc.thucHanh.phongHocId || ""}
                                                    onChange={(e) => setNewLichHoc({ ...newLichHoc, thucHanh: { ...newLichHoc.thucHanh, phongHocId: e.target.value } })}
                                                >
                                                    <option value="">--Chọn phòng --</option>
                                                    {listPhongHoc.phongThucHanh.map(i => (
                                                        <option key={i.id} value={i.id}>{i.tenPhong}</option>
                                                    ))}
                                                </select>
                                            </div>
                                        </div>
                                    </>
                                )}

                                <div className="col-md-2 ms-auto">
                                    <button type="button" className="btn btn-success w-100" onClick={addLichHoc}>Thêm</button>
                                </div>
                            </div>

                        </div>

                        <table className="table table-bordered">
                            <tbody>
                                {Object.entries(grouped).map(([lan, items]) => {
                                    // Sắp lý thuyết trước, thực hành sau
                                    const sortedItems = items.sort((a, b) =>
                                        a.loai === 'LyThuyet' ? -1 : 1
                                    );

                                    return sortedItems.map((i, idx) => (
                                        <tr key={i.id}>
                                            {/* Chỉ in số "lần" ở hàng đầu tiên của nhóm */}
                                            {idx === 0 && (
                                                <td rowSpan={sortedItems.length} style={{ fontWeight: 'bold', textAlign: 'center' }}>
                                                    Lần {lan}
                                                </td>
                                            )}
                                            <td>
                                                {getLoaiLichHoc(i.loai)} - {i.thu}, {i.gioBatDau}, {i.ngayBatDau} - {i.ngayKetThuc}, {i.phongHocId.tenPhong}
                                            </td>
                                        </tr>
                                    ));
                                })}
                            </tbody>
                        </table>

                        <div className="text-center mt-3 mb-3">
                            <button type="submit" className="text-center btn btn-primary" disabled={updateLoading}>
                                {updateLoading ?
                                    <>
                                        <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                        Đang lưu...
                                    </> : "Lưu thay đổi"}
                            </button>
                        </div>
                    </form>
                </div>
            )}
        </div>
    );
}
export default SuaBuoiHoc;