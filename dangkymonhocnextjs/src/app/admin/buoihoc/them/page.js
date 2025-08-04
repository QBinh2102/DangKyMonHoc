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
    const [listGiangVien, setListGiangVien] = useState([]);
    const [newBuoiHoc, setNewBuoiHoc] = useState({
        monHocId: {},
        giangVienId: {},
        hocKyId: {},
        siSo: 0,
    });
    const [loaiBuoiHoc, setLoaiBuoiHoc] = useState("");
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadHocKyMoiNhat = async () => {
        try {
            let res = await authApis().get(endpoints['hocKyMoiNhat']);
            setNewBuoiHoc({ ...newBuoiHoc, hocKyId: { id: res.data.id } });
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadKhoa = async () => {
        setListKhoa([]);
        try {
            let res = await Apis.get(endpoints['khoa']);
            setListKhoa(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadKhoa();
        loadHocKyMoiNhat();
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

    const loadGiangVienTheoKhoa = async (khoaId) => {
        try {
            let res = await authApis().get(`${endpoints['themHoacLayGiangVien']}?khoaId=${khoaId}`);
            setListGiangVien(res.data);
            console.info(res.data);
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
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const chooseNganh = async (e) => {
        const nganhId = e.target.value;
        { nganhId ? await loadMonHocTheoNganh(nganhId) : setListMonHoc([]) };
    }

    const addBuoiHoc = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMsg("");
        try {
            if (loaiBuoiHoc === "lt") {
                const payload = {
                    ...newBuoiHoc,
                    ca: ""
                };
                await authApis().post(endpoints['themHoacLayBuoiHoc'], payload);
            } else if (loaiBuoiHoc === "lt-th") {
                const siSo = parseInt(newBuoiHoc.siSo);
                const siSo1 = Math.ceil(siSo / 2);
                const siSo2 = siSo - siSo1;
                const buoi1 = {
                    ...newBuoiHoc,
                    ca: "01",
                    siSo: siSo1,
                };
                const buoi2 = {
                    ...newBuoiHoc,
                    ca: "02",
                    siSo: siSo2,
                }

                await authApis().post(endpoints['themHoacLayBuoiHoc'], buoi1);
                await authApis().post(endpoints['themHoacLayBuoiHoc'], buoi2);
            }

            setMsg("Thêm thành công!");
            setNewBuoiHoc({
                monHocId: {},
                giangVienId: {},
                hocKyId: {},
                siSo: 0,
            });
            await loadKhoa();
            await loadHocKyMoiNhat();
            setListNganh([]);
            setListGiangVien([]);
            setListMonHoc([]);
            setLoaiBuoiHoc("");
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
                        onChange={(e) => setNewBuoiHoc({ ...newBuoiHoc, monHocId: { ...newBuoiHoc.monHocId, id: e.target.value } })}
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
                    <label htmlFor="loaiBuoiHoc" className="form-label">Loại buổi học</label>
                    <select
                        className="form-select"
                        id="loaiBuoiHoc"
                        value={loaiBuoiHoc}
                        onChange={(e) => setLoaiBuoiHoc(e.target.value)}
                        required
                    >
                        <option value="">-- Chọn loại --</option>
                        <option value="lt">Lý thuyết</option>
                        <option value="lt-th">Lý thuyết - Thực hành</option>
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

                <div className="text-center">
                    <button type="submit" className="btn btn-primary" disabled={loading}>
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
export default ThemBuoiHoc;