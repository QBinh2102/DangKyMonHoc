'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

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


    const loadBuoiHoc = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['suaHoacLayBuoiHocId'](id));
            setBuoiHoc(res.data);
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

    const getLoai = (loai) => {
        if (loai === "LT") return "Lý thuyết";
        if (loai === "LT-TH") return "Lý thuyết - thực hành";
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
                                    value={i.field === "loai" ? getLoai(buoiHoc[i.field]) : buoiHoc[i.field] ?? ""}
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
                                    <div className="col-md-3">
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

                                    <div className="col-md-3">
                                        <input
                                            type="date"
                                            className="form-control"
                                            value={newLichHoc.lyThuyet.ngayBatDau || ""}
                                            onChange={(e) => setNewLichHoc({ ...newLichHoc, lyThuyet: { ...newLichHoc.lyThuyet, ngayBatDau: e.target.value } })}
                                        />
                                    </div>

                                    <div className="col-md-3">
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="Phòng"
                                            value={newLichHoc.lyThuyet.phong || ""}
                                            onChange={(e) => setNewLichHoc({ ...newLichHoc, lyThuyet: { ...newLichHoc.lyThuyet, phong: e.target.value } })}
                                        />
                                    </div>
                                </div>
                                {buoiHoc.loai === "LT-TH" && (
                                    <>
                                        <h5>Thực hành</h5>
                                        <div className="row mt-3 mb-3 align-items-center">
                                            <div className="col-md-3">
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

                                            <div className="col-md-3">
                                                <input
                                                    type="date"
                                                    className="form-control"
                                                    value={newLichHoc.thucHanh.ngayBatDau || ""}
                                                    onChange={(e) => setNewLichHoc({ ...newLichHoc, thucHanh: { ...newLichHoc.thucHanh, ngayBatDau: e.target.value } })}
                                                />
                                            </div>

                                            <div className="col-md-3">
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    placeholder="Phòng"
                                                    value={newLichHoc.thucHanh.phong || ""}
                                                    onChange={(e) => setNewLichHoc({ ...newLichHoc, thucHanh: { ...newLichHoc.thucHanh, phong: e.target.value } })}
                                                />
                                            </div>
                                        </div>
                                    </>
                                )}

                                <div className="col-md-2 ms-auto">
                                    <button type="button" className="btn btn-success w-100" onClick={addLichHoc}>Thêm</button>
                                </div>
                            </div>

                        </div>

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