'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useEffect, useState } from "react";

const ThemSinhVien = () => {

    const info = [{
        label: "Họ Tên",
        field: "hoTen",
        type: "text",
    }, {
        label: "Ngày Sinh",
        field: "ngaySinh",
        type: "date",
    }, {
        label: "Số Điện Thoại",
        field: "soDienThoai",
        type: "tel",
    }, {
        label: "Email",
        field: "email",
        type: "email",
    }, {
        label: "Căn cước công dân",
        field: "cccd",
        type: "text",
    }];
    const [newSinhVien, setNewSinhVien] = useState({
        nguoiDung: {},
        khoaId: {},
        nganhId: {},
    });
    const [listKhoa, setListKhoa] = useState([]);
    const [listNganh, setListNganh] = useState([]);
    const [listLop, setListLop] = useState([]);
    const [selectedNganh, setSelectedNganh] = useState("");
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    useEffect(() => {
        const loadKhoa = async () => {
            try {
                let res = await Apis.get(endpoints['khoa']);
                setListKhoa(res.data);
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadKhoa();
    }, []);

    const loadNganhTheoKhoa = async (khoaId) => {
        try {
            let res = await Apis.get(`${endpoints['nganh']}?khoaId=${khoaId}`);
            setListNganh(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const chooseKhoa = async (e) => {
        const khoaId = e.target.value;
        setNewSinhVien({
            ...newSinhVien,
            khoaId: {
                id: khoaId,
            },
            nganhId: {},
            lopId: {},
        })

        { khoaId ? await loadNganhTheoKhoa(khoaId) : setListNganh([]), setListLop([]); };
    }

    const chooseNganh = async (e) => {
        const nganhId = e.target.value;
        setSelectedNganh(nganhId);
        setNewSinhVien({ ...newSinhVien, nganhId: { id: e.target.value } });
    }

    const loadLop = async (nganhId) => {
        try {
            let res = await Apis.get(`${endpoints['lop']}?nganhId=${nganhId}`);
            setListLop(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        if (selectedNganh) {
            loadLop(selectedNganh);
        } else {
            setListLop([]);
        }
    }, [selectedNganh]);

    const loadBuoiHocTheoMaLop = async (lopId) => {
        try {
            let res = await authApis().get(`${endpoints['themHoacLayBuoiHoc']}?lopId=${lopId}`);
            return res.data;
        } catch (ex) {
            console.error(ex);
        }
    }

    //Gán thời khóa biểu, học phí
    const addKhoaHoc = async (sinhVien) => {
        console.log(sinhVien);
        const listBuoiHoc = await loadBuoiHocTheoMaLop(sinhVien.lopId?.id);
        console.log(listBuoiHoc);

        for (const bh of listBuoiHoc) {
            //Đăng ký
            const newDangKy = {
                sinhVienId: sinhVien.id,
                buoiHocId: bh.id,
            };
            console.log(newDangKy);
            await authApis().post(endpoints['dangKyChoSinhVien'], newDangKy);

            //Thời khóa biểu
            let res = await Apis.get(`${endpoints['lichHoc']}?buoiHocId=${bh.id}`);
            let listLichHocByBuoiHocId = res.data
            console.log(listLichHocByBuoiHocId);
            for (const lh of listLichHocByBuoiHocId) {
                const newLichHoc = {
                    sinhVienId: sinhVien.id,
                    lichHocId: lh.id,
                };
                console.log(newLichHoc);
                await authApis().post(endpoints['thoiKhoaBieuChoSinhVien'], newLichHoc);
            };

            //Học phí
            let hocPhi = await authApis().get(`${endpoints['hocPhiMoiNhatBySinhVien']}?sinhVienId=${sinhVien.id}`);
            await authApis().post(endpoints['chiTietHocPhi'], {}, {
                params: {
                    hocPhiId: hocPhi.data.id,
                    buoiHocId: bh.id,
                }
            });
        }

    }

    const addSinhVien = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            let res = await authApis().post(endpoints['themHoacLaySinhVien'], newSinhVien);

            await addKhoaHoc(res.data);

            setMsg("Thêm thành công!");
            setNewSinhVien({
                nguoiDung: {},
                khoaId: {},
                nganhId: {},
            })
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
                <h1>THÊM SINH VIÊN</h1>
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

            <form onSubmit={addSinhVien} className="w-50 mx-auto">
                <div className="mb-3 mt-3">
                    <label htmlFor="gioiTinh" className="form-label">Giới tính</label>
                    <select
                        id="gioiTinh"
                        className="form-select"
                        value={newSinhVien.nguoiDung.gioiTinh || ""}
                        onChange={e => setNewSinhVien({ ...newSinhVien, nguoiDung: { ...newSinhVien.nguoiDung, gioiTinh: e.target.value } })}
                        required
                    >
                        <option value="">-- Chọn giới tính --</option>
                        <option value="nam">Nam</option>
                        <option value="nữ">Nữ</option>
                    </select>
                </div>

                {info.map(i => (
                    <div className="mt-3 mb-3" key={i.field}>
                        <label htmlFor={i.field} className="form-label">{i.label}</label>
                        <input
                            id={i.field}
                            type={i.type}
                            value={newSinhVien.nguoiDung[i.field] || ""}
                            className="form-control"
                            placeholder={i.label}
                            onChange={e => setNewSinhVien({ ...newSinhVien, nguoiDung: { ...newSinhVien.nguoiDung, [i.field]: e.target.value } })}
                            required
                        />
                    </div>
                ))}

                <div className="mt-3 mb-3">
                    <label htmlFor="khoaId" className="form-label">Khoa</label>
                    <select
                        className="form-select"
                        id="khoaId"
                        value={newSinhVien.khoaId.id || ""}
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
                        value={newSinhVien.nganhId.id || ""}
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
                    <label htmlFor="lopId" className="form-label">Lớp</label>
                    <select
                        className="form-select"
                        id="lopId"
                        value={newSinhVien.lopId?.id || ""}
                        onChange={(e) => setNewSinhVien({ ...newSinhVien, lopId: { id: e.target.value } })}
                        required
                    >
                        <option value="">-- Chọn lớp --</option>
                        {listLop.map(lop => (
                            <option key={lop.id} value={lop.id}>{lop.maLop}</option>
                        ))}
                    </select>
                </div>

                <div className="mt-3 mb-3">
                    <label htmlFor="avatar" className="form-label">Ảnh đại diện</label>
                    <input
                        id="avatar"
                        type="file"
                        accept="image/*"
                        className="form-control"
                        onChange={e => {
                            const file = e.target.files[0];
                            if (file) {
                                const reader = new FileReader();
                                reader.onloadend = () => {
                                    setNewSinhVien({
                                        ...newSinhVien,
                                        nguoiDung: {
                                            ...newSinhVien.nguoiDung,
                                            avatar: reader.result 
                                        }
                                    });
                                };
                                reader.readAsDataURL(file);
                            }
                        }}
                    />
                </div>

                {newSinhVien.nguoiDung.avatar && (
                    <div className="mt-3 text-center">
                        <img
                            src={newSinhVien.nguoiDung.avatar}
                            alt="Ảnh đại diện"
                            style={{ maxWidth: "150px" }}
                        />
                    </div>
                )}

                <div className="text-center mt-3 mb-3">
                    <button type="submit" className="btn btn-primary mb-3" disabled={loading}>
                        {loading ?
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                Đang thêm...
                            </> : "Thêm"}
                    </button>
                </div>
            </form>
        </div>
    );
}
export default ThemSinhVien;