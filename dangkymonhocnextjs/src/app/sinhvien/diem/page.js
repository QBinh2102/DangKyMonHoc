'use client'

import { authApis, endpoints } from "@/configs/Apis";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import "./diem.css"
import { useEffect, useRef, useState } from "react";

const Diem = () => {

    const [sinhVien, setSinhVien] = useState({});
    const [listDiem, setListDiem] = useState([]);
    const [listTongKet, setListTongKet] = useState([]);
    const [loading, setLoading] = useState(false);
    const exportRef = useRef(null);

    const loadDiem = async () => {
        try {
            let res = await authApis().get(endpoints['diemSinhVien']);
            setListDiem(res.data);
            console.log(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadTongKet = async () => {
        try {
            let res = await authApis().get(endpoints['tongKetSinhVien']);
            setListTongKet(res.data);
            
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadSinhVien = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['profile-sinhvien']);
            setSinhVien(res.data);
            loadDiem();
            loadTongKet();
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }



    useEffect(() => {
        loadSinhVien();
    }, []);

    const groupByHocKyAndMonHoc = (dsDiem) => {
        return dsDiem.reduce((acc, item) => {
            const hocKyKey = `${item.hocKy}_${item.namHoc}`;
            const tenMon = item.tenMonHoc;
            const soTinChi = (item.tinChiLyThuyet + item.tinChiThucHanh);
            if (!acc[hocKyKey]) {
                acc[hocKyKey] = {};
            }
            if (!acc[hocKyKey][tenMon]) {
                acc[hocKyKey][tenMon] = [];
            }
            acc[hocKyKey][tenMon].push({
                maLop: item.maLop,
                soTinChi: soTinChi,
                diem_gk: item.diemGiuaKy,
                diem_ck: item.diemCuoiKy,
                diem_tk: item.diemTongKet,
                trangThai: item.trangThai,
            })
            return acc;
        }, {});
    };

    const grouped = groupByHocKyAndMonHoc(listDiem);

    const getKetQua = (trangThai) => {
        if (trangThai === "HOAN_THANH") {
            return "✔️";
        } else if (trangThai === "TRUOT") {
            return "✖️";
        } else {
            return "";
        }
    }

    const exportPDF = async () => {
        if (!exportRef.current) return;

        const canvas = await html2canvas(exportRef.current, { scale: 2 });
        const imgData = canvas.toDataURL("image/png");

        const pdf = new jsPDF("p", "mm", "a4");
        const pageWidth = pdf.internal.pageSize.getWidth();
        const pageHeight = pdf.internal.pageSize.getHeight();

        const imgWidth = pageWidth - 20; // chừa lề
        const imgHeight = (canvas.height * imgWidth) / canvas.width;

        let heightLeft = imgHeight;
        let position = 10;

        // Trang đầu tiên
        pdf.addImage(imgData, "PNG", 10, position, imgWidth, imgHeight);
        heightLeft -= pageHeight - 20;

        // Các trang tiếp theo
        while (heightLeft > 0) {
            position = heightLeft - imgHeight;
            pdf.addPage();
            pdf.addImage(imgData, "PNG", 10, position, imgWidth, imgHeight);
            heightLeft -= pageHeight - 20;
        }

        pdf.save("bangdiem.pdf");
    };

    return (
        <div>
            {loading ? (
                <p>Đang tải dữ liệu...</p>
            ) : (
                <div>
                    <h1 className="text-center mt-3 mb-3">
                        Điểm sinh viên
                    </h1>
                    <div>
                        <button className="btn btn-primary mt-3 mb-3" onClick={exportPDF}>
                            In PDF
                        </button>
                    </div>
                    <div ref={exportRef}>
                        <div className="text-center mb-3" style={{ fontSize: "30px" }}>
                            <div><strong>Họ Tên: </strong> {sinhVien?.nguoiDung?.hoTen}</div>
                            <div><strong>Ngành: </strong> {sinhVien?.nganhId?.tenNganh}</div>
                        </div>

                        {Object.keys(grouped).map((hocKyKey) => {
                            const monHocs = grouped[hocKyKey];
                            const [hocKyId, namHoc] = hocKyKey.split("_");

                            const tongKet = listTongKet.find(
                                tk => tk.ky === hocKyId && tk.namHoc === namHoc
                            );
                            return (
                                <div className="mb-3" key={hocKyKey}>
                                    <h2 className="title-hocky my-0">{hocKyId} - Năm học {namHoc}</h2>

                                    <table
                                        className="table table-bordered text-center mb-0"
                                        style={{ border: "1px solid black" }}
                                    >
                                        <thead>
                                            <tr>
                                                <th style={{ width: "40%" }}>Môn học</th>
                                                <th style={{ width: "10%" }}>Nhóm</th>
                                                <th style={{ width: "10%" }}>Số tín chỉ</th>
                                                <th style={{ width: "10%" }}>Giữa kỳ</th>
                                                <th style={{ width: "10%" }}>Cuối kỳ</th>
                                                <th style={{ width: "10%" }}>Tổng kết</th>
                                                <th style={{ width: "10%" }}>Kết quả</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {Object.keys(monHocs).sort().map((tenMon) => {
                                                return monHocs[tenMon].map((i, idx) => (
                                                    <tr key={idx}>
                                                        <td>{tenMon}</td>
                                                        <td>{i.maLop}</td>
                                                        <td>{i.soTinChi}</td>
                                                        <td>{i.diem_gk !== null ? i.diem_gk : "-"}</td>
                                                        <td>{i.diem_ck !== null ? i.diem_ck : "-"}</td>
                                                        <td>{i.diem_tk !== null ? i.diem_tk : "-"}</td>
                                                        <td>{getKetQua(i.trangThai)}</td>
                                                    </tr>
                                                ));
                                            })}
                                        </tbody>
                                    </table>

                                    {tongKet && (
                                        <div className="info-hocsinh p-2 border rounded bg-light d-flex mt-0">
                                            <div className="me-5"><strong>Số tín chỉ đạt (HK):</strong> {tongKet.tinChiDatHk}</div>
                                            <div className="me-5"><strong>Tổng số tín chỉ tích lũy:</strong> {tongKet.tinChiTichLuy}</div>
                                            <div className="me-5"><strong>Điểm trung bình học kỳ:</strong> {tongKet.diemTbHk}</div>
                                            <div className="me-5"><strong>Điểm trung bình tích lũy:</strong> {tongKet.diemTbTichLuy}</div>
                                        </div>
                                    )}
                                </div>
                            );
                        })}

                    </div>
                </div>
            )}
        </div>
    );
}
export default Diem;