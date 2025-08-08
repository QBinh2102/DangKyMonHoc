'use client'

import Apis, { authApis, endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const SuaMonHoc = () => {

    const info = [{
        label: "Tên môn",
        field: "tenMon",
        type: "text",
    }, {
        label: "Tín chỉ lý thuyết",
        field: "tinChiLyThuyet",
        type: "number",
        min: 0,
    }, {
        label: "Tín chỉ thực hành",
        field: "tinChiThucHanh",
        type: "number",
        min: 0,
    }, {
        label: "Điểm qua môn",
        field: "diemQuaMon",
        type: "number",
        min: 0,
        max: 10,
    }];
    const loaiDiem = [{
        label: "Giữa kỳ 30% - Cuối kỳ 70%",
        value: "30-70",
    }, {
        label: "Giữa kỳ 40% - Cuối kỳ 60%",
        value: "40-60",
    }, {
        label: "Giữa kỳ 50% - Cuối kỳ 50%",
        value: "50-50",
    }];
    const { id } = useParams();
    const [monHoc, setMonHoc] = useState({
        khoaId: {},
    });
    const [listNganh, setListNganh] = useState([]);
    const [selectedLoaiDiem, setSelectedLoaiDiem] = useState("");
    const [selectedNganhs, setSelectedNganhs] = useState([]);

    const [listMonHocLienQuan, setListMonHocLienQuan] = useState([]);
    const [listMonHoc, setListMonHoc] = useState([]);
    const [newMonHocLienQuanPK, setNewMonHocLienQuanPK] = useState({
        monHocId: "",
        lienQuanId: "",
        nganhId: "",
        loai: "",
    });

    const [loading, setLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const loadNganhTheoKhoa = async (khoaId) => {
        try {
            let res = await Apis.get(`${endpoints['nganh']}?khoaId=${khoaId}`);
            setListNganh(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const loadMonHoc = async () => {
        setLoading(true);
        try {
            // Lấy thông tin môn học
            let res = await Apis.get(endpoints['monHocId'](id));
            setMonHoc(res.data);
            if (res.data.phanTramGiuaKy === 30) {
                setSelectedLoaiDiem("30-70");
            } else if (res.data.phanTramGiuaKy === 40) {
                setSelectedLoaiDiem("40-60");
            } else {
                setSelectedLoaiDiem("50-50");
            }
            { res.data.khoaId?.id ? await loadNganhTheoKhoa(res.data.khoaId?.id) : setListNganh([]) };

            // Lấy danh sách ngành
            let dsNganh = await Apis.get(`${endpoints['nganhMonHoc']}?monHocId=${res.data.id}`);
            setSelectedNganhs(dsNganh.data.map(item => item.id.nganhId.toString()));

            //Lấy các môn học liên quan
            let dsMonHocLienQuan = await Apis.get(`${endpoints['monHocLienQuan']}?monHocId=${res.data.id}`);
            setListMonHocLienQuan(dsMonHocLienQuan.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadMonHoc();

    }, [id]);

    const chooseLoaiDiem = (e) => {
        const value = e.target.value;
        setSelectedLoaiDiem(value);
        const [phanTramGiuaKy, phanTramCuoiKy] = value.split('-').map(Number);
        setMonHoc({
            ...monHoc,
            phanTramGiuaKy,
            phanTramCuoiKy,
        });
    };

    const loadMonHocTheoNganh = async (nganhId) => {
        try {
            let res = await Apis.get(`${endpoints['nganhMonHoc']}?nganhId=${nganhId}`);
            console.log(res.data);
            const monKhongTrung = res.data.filter(mh => mh.monHoc.id !== parseInt(id));
            setListMonHoc(monKhongTrung);
            console.log(monKhongTrung);
        } catch (ex) {
            console.error(ex);
        }
    }

    const chooseNganh = async (e) => {
        const nganhId = e.target.value;

        setNewMonHocLienQuanPK({ ...newMonHocLienQuanPK, nganhId: nganhId });
        { nganhId ? await loadMonHocTheoNganh(nganhId) : setListMonHoc([]) };
    }

    const addMonHocLienQuan = async (e) => {
        e.preventDefault();
        const { nganhId, lienQuanId, loai } = newMonHocLienQuanPK;

        if (!nganhId || !lienQuanId || !loai) {
            alert("Vui lòng chọn đầy đủ ngành, môn và loại trước khi thêm.");
            return;
        }
        setMsg("");
        try {
            const payload = {
                monHocLienQuanPK: {
                    monHocId: parseInt(id),
                    lienQuanId: parseInt(lienQuanId),
                    nganhId: parseInt(nganhId),
                    loai: loai
                }
            };
            await authApis().post(endpoints['themHoacXoaMonHocLienQuan'], payload);
            setMsg("Thêm thành công!");
            await loadMonHoc();
            setNewMonHocLienQuanPK({
                monHocId: "",
                lienQuanId: "",
                nganhId: "",
                loai: "",
            })
        } catch (ex) {
            setMsg("Thêm thất bại!");
            console.error(ex);
        }
    }

    const deleteMonHocLienQuan = async (monHocLienQuanPK) => {
        try {
            console.log(monHocLienQuanPK);
            await authApis().delete(endpoints['themHoacXoaMonHocLienQuan'], {
                data: monHocLienQuanPK
            });
            setMsg("Xóa thành công!");
            await loadMonHoc();
        } catch (ex) {
            console.error(ex);
            setMsg("Xóa thất bại!");
        }
    }

    const updateMonHoc = async (e) => {
        e.preventDefault();
        setMsg("");
        setUpdateLoading(true);
        try {
            await authApis().put(endpoints['suaMonHocId'](id), monHoc);

            await authApis().delete(endpoints['xoaNganhMonHocTheoMonHocId'](id));
            if (selectedNganhs.length > 0) {
                const uploads = selectedNganhs.map(i => ({
                    id: {
                        nganhId: parseInt(i),
                        monHocId: id,
                    }
                }))

                for (const upload of uploads) {
                    await authApis().post(endpoints['themNganhMonHoc'], upload);
                }
            }

            setMsg("Cập nhật thành công!");
            await loadMonHoc();
        } catch (ex) {
            console.error(ex);
            setMsg("Cập nhật thất bại!");
        } finally {
            setUpdateLoading(false);
        }
    };

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÔNG TIN MÔN HỌC</h1>
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
                    <form onSubmit={updateMonHoc} className="w-50 mx-auto">
                        {info.map(i => (
                            <div className="mb-3 mt-3" key={i.field}>
                                <label htmlFor={i.field} className="form-label">{i.label}</label>
                                <input
                                    id={i.field}
                                    type={i.type}
                                    className="form-control"
                                    value={monHoc[i.field] || ""}
                                    min={i.min !== undefined ? i.min : undefined}
                                    max={i.max !== undefined ? i.max : undefined}
                                    onChange={(e) => setMonHoc({ ...monHoc, [i.field]: e.target.value })}
                                    required
                                />
                            </div>
                        ))}

                        <div className="mt-3 mb-3">
                            <label htmlFor="loaiDiem" className="form-label">Chọn loại điểm</label>
                            <select
                                id="loaiDiem"
                                className="form-select"
                                value={selectedLoaiDiem}
                                onChange={chooseLoaiDiem}
                                required
                            >
                                <option value="">-- Chọn loại điểm --</option>
                                {loaiDiem.map((i, idx) => (
                                    <option key={idx} value={i.value}>{i.label}</option>
                                ))}
                            </select>
                        </div>

                        <div className="mb-3 mt-3">
                            <label className="form-label">Tên khoa</label>
                            <input
                                type="text"
                                className="form-control"
                                value={monHoc.khoaId?.tenKhoa || ""}
                                disabled
                            />
                        </div>

                        {listNganh.length > 0 && (
                            <div className="mt-3 mb-3">
                                <label className="form-label">Thuộc ngành</label>
                                {listNganh.map(nganh => (
                                    <div key={nganh.id} className="form-check">
                                        <input
                                            className="form-check-input"
                                            type="checkbox"
                                            id={nganh.id}
                                            value={nganh.id}
                                            checked={selectedNganhs.includes(nganh.id.toString())}
                                            onChange={(e) => {
                                                if (e.target.checked) {
                                                    setSelectedNganhs([...selectedNganhs, e.target.value]);
                                                } else {
                                                    setSelectedNganhs(selectedNganhs.filter(item => item !== e.target.value));
                                                }
                                            }}
                                        />
                                        <label className="form-check-label" htmlFor={`nganh-${nganh.id}`}>
                                            {nganh.tenNganh}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        )}

                        <div>
                            <h2>Các môn học liên quan</h2>
                        </div>

                        {/* Mục thêm mặc định môn học liên quan */}
                        <div className="row mt-3 mb-3 align-items-center">
                            <div className="col-md-4">
                                <select
                                    className="form-select"
                                    value={newMonHocLienQuanPK?.nganhId || ""}
                                    onChange={chooseNganh}
                                >
                                    <option value="">-- Chọn ngành --</option>
                                    {listNganh
                                        .filter(nganh => selectedNganhs.includes(nganh.id.toString()))
                                        .map(nganh => (
                                            <option key={nganh.id} value={nganh.id}>{nganh.tenNganh}</option>
                                        ))}
                                </select>
                            </div>
                            <div className="col-md-4">
                                <select
                                    className="form-select"
                                    value={newMonHocLienQuanPK?.lienQuanId || ""}
                                    onChange={(e) => setNewMonHocLienQuanPK({ ...newMonHocLienQuanPK, lienQuanId: e.target.value })}
                                >
                                    <option value="">-- Chọn môn --</option>
                                    {listMonHoc.map(mh => (
                                        <option key={mh.monHoc.id} value={mh.monHoc.id}>{mh.monHoc.tenMon}</option>
                                    ))}
                                </select>
                            </div>
                            <div className="col-md-2">
                                <select
                                    className="form-select"
                                    value={newMonHocLienQuanPK.loai}
                                    onChange={(e) => setNewMonHocLienQuanPK({ ...newMonHocLienQuanPK, loai: e.target.value })}
                                >
                                    <option value="">-- Loại --</option>
                                    <option value="TIEN_QUYET">Tiên quyết</option>
                                    <option value="HOC_TRUOC">Học trước</option>
                                </select>
                            </div>
                            <div className="col-md-2 ms-auto">
                                <button type="button" className="btn btn-success w-100" onClick={addMonHocLienQuan}>Thêm</button>
                            </div>
                        </div>

                        {/* Danh sách các môn học liên quan */}
                        {listMonHocLienQuan.length > 0 && listMonHocLienQuan.map((mhlq, idx) => (
                            <div className="row mt-2 mb-2 align-items-center" key={idx}>
                                <div className="col-md-4">
                                    <input
                                        className="form-control"
                                        value={mhlq.nganh?.tenNganh || ""}
                                        disabled
                                    />
                                </div>
                                <div className="col-md-4">
                                    <input
                                        className="form-control"
                                        value={mhlq.monHoc1?.tenMon || ""}
                                        disabled
                                    />
                                </div>
                                <div className="col-md-2">
                                    <input
                                        className="form-control"
                                        value={mhlq.loai === "TIEN_QUYET" ? "Tiên quyết" : "Học trước"}
                                        disabled
                                    />
                                </div>
                                <div className="col-md-2 ms-auto">
                                    <button
                                        type="button"
                                        className="btn btn-danger w-100"
                                        onClick={() => {
                                            if (window.confirm(`Bạn có chắc muốn xóa không?`)) {
                                                deleteMonHocLienQuan(mhlq.monHocLienQuanPK);
                                            };
                                        }}
                                    >
                                        Xóa
                                    </button>
                                </div>
                            </div>
                        ))}

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
    )
}
export default SuaMonHoc;