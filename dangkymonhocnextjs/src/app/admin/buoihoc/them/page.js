'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemBuoiHoc = () => {

    const info = [{
        label: "Sĩ số",
        field: "siSo",
        type: "number",
        min: 0,
    }];
    const [listKhoa, setListKhoa] = useState([]);
    const [listNganh, setListNganh] = useState([]);
    const [listMonHoc, setListMonHoc] = useState([]);
    const [selectedMonHoc, setSelectedMonHoc] = useState("");
    const [listGiangVien, setListGiangVien] = useState([]);
    const [listLop, setListLop] = useState([]);
    const [loai, setLoai] = useState("");
    const [newBuoiHoc, setNewBuoiHoc] = useState({
        monHocId: {},
        giangVienId: {},
        hocKyId: {},
        siSo: 0,
    });

    const [listTietHoc, setListTietHoc] = useState([]);
    const [selectedNgay, setSelectedNgay] = useState("");
    const [newLichHoc, setNewLichHoc] = useState({
        lyThuyet: {
            tietHocId: "",
        },
        thucHanh: {
            tietHocId: "",
        }
    });
    const [listPhongHoc, setListPhongHoc] = useState({ phongLyThuyet: [], phongThucHanh: [] });
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadTietHoc = async () => {
        setListTietHoc([]);
        try {
            let res = await Apis.get(endpoints['tietHoc']);
            setListTietHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadKhoa = async () => {
        setListKhoa([]);
        try {
            let res = await Apis.get(endpoints['khoa']);
            setListKhoa(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadKhoa();
        loadTietHoc();
    }, []);

    const loadNganhTheoKhoa = async (khoaId) => {
        try {
            let res = await Apis.get(`${endpoints['nganh']}?khoaId=${khoaId}`);
            setListNganh(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadGiangVienTheoKhoa = async (khoaId) => {
        try {
            let res = await authApis().get(`${endpoints['themHoacLayGiangVien']}?khoaId=${khoaId}`);
            setListGiangVien(res.data);
        } catch (ex) {
            console.error(res.data);
        }
    }

    const chooseKhoa = async (e) => {
        const khoaId = e.target.value;
        { khoaId ? (await loadNganhTheoKhoa(khoaId), await loadGiangVienTheoKhoa(khoaId)) : (setListNganh([]), setListGiangVien([])) };
    }

    const loadMonHocTheoNganh = async (nganhId) => {
        try {
            let res = await Apis.get(`${endpoints['nganhMonHoc']}?nganhId=${nganhId}`);
            setListMonHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadLop = async (nganhId) => {
        try {
            let res = await Apis.get(`${endpoints['lop']}?nganhId=${nganhId}`);
            setListLop(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const chooseNganh = async (e) => {
        const nganhId = e.target.value;
        { nganhId ? (await loadMonHocTheoNganh(nganhId), await loadLop(nganhId)) : (setListMonHoc([]), setListLop([])) };
    }

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

    const loadMonHoc = async (monHocId) => {
        try {
            let res = await Apis.get(endpoints['monHocId'](monHocId));

            let loaiTmp = "";
            if (res.data.tinChiLyThuyet !== 0 && res.data.tinChiThucHanh !== 0) {
                loaiTmp = "LT-TH";
            } else if (res.data.tinChiLyThuyet !== 0) {
                loaiTmp = "LT";
            } else {
                loaiTmp = "TH";
            }

            setLoai(loaiTmp);
            setNewBuoiHoc(prev => ({ ...prev, loai: loaiTmp })); // dùng prev để không mất dữ liệu cũ
            await loadPhongHoc(loaiTmp);

        } catch (ex) {
            console.error(ex);
        }
    };

    const chooseMonHoc = async (e) => {
        const monHocId = e.target.value;
        setSelectedMonHoc(monHocId);

        if (monHocId) {
            await loadMonHoc(monHocId);
            setNewBuoiHoc(prev => ({ ...prev, monHocId: { ...prev.monHocId, id: monHocId } }));
        } else {
            setListMonHoc([]);
        }
    };

    const addLichHoc = async (buoiHocId) => {
        try {
            if (loai === "LT" || loai === "LT-TH") {
                const newLichHocLT = {
                    ...newLichHoc.lyThuyet,
                    buoiHocId: buoiHocId,
                    loai: "LyThuyet",
                }
                await authApis().post(endpoints['themLichHoc'], newLichHocLT); // Thêm lịch học lý thuyết
            };
            if (loai === "TH" || loai === "LT-TH") {
                const newLichHocTH = {
                    ...newLichHoc.thucHanh,
                    buoiHocId: buoiHocId,
                    loai: "ThucHanh",
                }
                await authApis().post(endpoints['themLichHoc'], newLichHocTH); // Thêm lịch học thực hành
            }
        } catch (ex) {
            console.error(ex);
        }
    }

    const addBuoiHoc = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMsg("");
        try {
            //Thêm buổi học
            let res = await authApis().post(endpoints['themHoacLayBuoiHoc'], newBuoiHoc);

            //Thêm lịch học
            await addLichHoc(res.data.id);

            setMsg("Thêm thành công!");
            setNewBuoiHoc({
                monHocId: {},
                giangVienId: {},
                hocKyId: {},
                siSo: 0,
            });
            setNewLichHoc({
                lyThuyet: {},
                thucHanh: {}
            });
            await loadKhoa();
            setSelectedMonHoc("");
            setListNganh([]);
            setListGiangVien([]);
            setListMonHoc([]);
        } catch (ex) {
            setMsg("Thêm thất bại!");
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM BUỔI HỌC</h1>
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

            <form onSubmit={addBuoiHoc} className="w-50 mx-auto">
                <div className="mt-3 mb-3">
                    <label htmlFor="khoaId" className="form-label">Khoa</label>
                    <select
                        className="form-select"
                        id="khoaId"
                        onChange={chooseKhoa}
                        required
                    >
                        <option value="">-- Chọn khoa --</option>
                        {listKhoa.map(khoa => (
                            <option key={khoa.id} value={khoa.id}>{khoa.tenKhoa}</option>
                        ))}
                    </select>
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="nganhId" className="form-label">Ngành</label>
                    <select
                        className="form-select"
                        id="nganhId"
                        onChange={chooseNganh}
                        required
                    >
                        <option value="">-- Chọn ngành --</option>
                        {listNganh.map(nganh => (
                            <option key={nganh.id} value={nganh.id}>{nganh.tenNganh}</option>
                        ))}
                    </select>
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="monHocId" className="form-label">Môn học</label>
                    <select
                        className="form-select"
                        id="monHocId"
                        onChange={chooseMonHoc}
                        required
                    >
                        <option value="">-- Chọn môn học --</option>
                        {listMonHoc.map(mh => (
                            <option key={mh.monHoc.id} value={mh.monHoc.id}>{mh.monHoc.tenMon}</option>
                        ))}
                    </select>
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="giangVienId" className="form-label">Giảng viên</label>
                    <select
                        className="form-select"
                        id="giangVienId"
                        onChange={(e) => setNewBuoiHoc({ ...newBuoiHoc, giangVienId: { ...newBuoiHoc.giangVienId, id: e.target.value } })}
                        required
                    >
                        <option value="">-- Chọn giảng viên --</option>
                        {listGiangVien.map(gv => (
                            <option key={gv.id} value={gv.id}>{gv.nguoiDung.hoTen}</option>
                        ))}
                    </select>
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="lopId" className="form-label">Mã lớp</label>
                    <select
                        className="form-select"
                        id="lopId"
                        value={newBuoiHoc.lopId?.id || ""}
                        onChange={(e) => setNewBuoiHoc({ ...newBuoiHoc, lopId: { id: e.target.value } })}
                        required
                    >
                        <option value="">-- Chọn lớp --</option>
                        {listLop.map(lop => (
                            <option key={lop.id} value={lop.id}>{lop.maLop}</option>
                        ))}
                    </select>
                </div>

                {info.map(i => (
                    <div className="mt-3 mb-3" key={i.field}>
                        <label htmlFor={i.field} className="form-label">{i.label}</label>
                        <input
                            id={i.field}
                            type={i.type}
                            className="form-control"
                            value={newBuoiHoc[i.field] ?? ""}
                            min={i.min !== undefined ? i.min : undefined}
                            onChange={(e) => setNewBuoiHoc({ ...newBuoiHoc, [i.field]: e.target.value })}
                            required
                        />
                    </div>
                ))}

                {selectedMonHoc !== "" && ( // Chọn lịch học
                    <>
                        <h2>THÊM LỊCH HỌC</h2>
                        <label className="form-label">Ngày bắt đầu</label>
                        <div className="col-md-4">
                            <input
                                type="date"
                                className="form-control"
                                value={selectedNgay || ""}
                                onChange={(e) => (
                                    setSelectedNgay(e.target.value),
                                    setNewLichHoc({
                                        ...newLichHoc,
                                        lyThuyet: {
                                            ...newLichHoc.lyThuyet,
                                            ngayBatDau: selectedNgay,
                                        },
                                        thucHanh: {
                                            ...newLichHoc.thucHanh,
                                            ngayBatDau: selectedNgay,
                                        },
                                    })
                                )
                                }
                            />
                        </div>
                        {(loai === "LT" || loai === "LT-TH") && (
                            <div className="row mt-3 mb-3 align-items-center">
                                <label className="form-label">Lịch lý thuyết</label>
                                <div className="col-md-4">
                                    <select
                                        className="form-select"
                                        value={newLichHoc.lyThuyet?.tietHocId || ""}
                                        onChange={(e) =>
                                            setNewLichHoc({
                                                ...newLichHoc,
                                                lyThuyet: {
                                                    ...newLichHoc.lyThuyet,
                                                    tietHocId: Number(e.target.value),
                                                },
                                            })
                                        }
                                    >
                                        <option value="">-- Chọn tiết --</option>
                                        {listTietHoc
                                            .filter(t => t.id !== Number(newLichHoc.thucHanh?.tietHocId))
                                            .map((i) => (
                                                <option key={i.id} value={i.id}>
                                                    {i.tiet}
                                                </option>
                                            ))
                                        }
                                    </select>
                                </div>



                                <div className="col-md-4">
                                    <select
                                        className="form-select"
                                        value={newLichHoc.lyThuyet?.phongHocId || ""}
                                        onChange={(e) =>
                                            setNewLichHoc({
                                                ...newLichHoc,
                                                lyThuyet: {
                                                    ...newLichHoc.lyThuyet,
                                                    phongHocId: e.target.value,
                                                },
                                            })
                                        }
                                    >
                                        <option value="">--Chọn phòng --</option>
                                        {listPhongHoc.phongLyThuyet?.map((i) => (
                                            <option key={i.id} value={i.id}>
                                                {i.tenPhong}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                        )}

                        {(loai === "TH" || loai === "LT-TH") && (
                            <div className="row mt-3 mb-3 align-items-center">
                                <label className="form-label">Lịch thực hành</label>
                                <div className="col-md-4">
                                    <select
                                        className="form-select"
                                        value={newLichHoc.thucHanh?.tietHocId || ""}
                                        onChange={(e) =>
                                            setNewLichHoc({
                                                ...newLichHoc,
                                                thucHanh: {
                                                    ...newLichHoc.thucHanh,
                                                    tietHocId: Number(e.target.value),
                                                },
                                            })
                                        }
                                    >
                                        <option value="">-- Chọn tiết --</option>
                                        {listTietHoc
                                            .filter(t => t.id !== Number(newLichHoc.lyThuyet?.tietHocId))
                                            .map((i) => (
                                                <option key={i.id} value={i.id}>
                                                    {i.tiet}
                                                </option>
                                            ))
                                        }
                                    </select>
                                </div>


                                <div className="col-md-4">
                                    <select
                                        className="form-select"
                                        value={newLichHoc.thucHanh?.phongHocId || ""}
                                        onChange={(e) =>
                                            setNewLichHoc({
                                                ...newLichHoc,
                                                thucHanh: {
                                                    ...newLichHoc.thucHanh,
                                                    phongHocId: e.target.value,
                                                },
                                            })
                                        }
                                    >
                                        <option value="">--Chọn phòng --</option>
                                        {listPhongHoc.phongThucHanh?.map((i) => (
                                            <option key={i.id} value={i.id}>
                                                {i.tenPhong}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                        )}
                    </>
                )}

                <div className="text-center mt-3 mb-3">
                    <button type="submit" className="btn btn-primary" disabled={loading}>
                        {loading ?
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                Đang thêm...
                            </> : "Thêm"}
                    </button>
                </div>
            </form >
        </div >
    );
}
export default ThemBuoiHoc;