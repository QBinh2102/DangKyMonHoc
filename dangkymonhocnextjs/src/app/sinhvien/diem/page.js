'use client'

import { authApis, endpoints } from "@/configs/Apis";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import { useEffect, useRef, useState } from "react";

const Diem = () => {

    const [listDiem, setListDiem] = useState([]);
    const [loading, setLoading] = useState(false);
    const exportRef = useRef(null);

    const loadDiem = async () => {
        setLoading(true);
        try {
            let res = await authApis().get(endpoints['diemSinhVien']);
            setListDiem(res.data);
            console.info(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadDiem();
    }, []);

    const groupByHocKyAndMonHoc = (dsDiem) => {
        return dsDiem.reduce((acc, item) => {
            const hocKy = item.hocKy;
            const namHoc = item.namHoc;
            const hocKyKey = `${hocKy} - năm ${namHoc}`;
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
            return "✓";
        } else if (trangThai === "TRUOT") {
            return "ｘ";
        } else {
            return "";
        }
    }

    const exportPDF = async () => {
        if (!exportRef.current) return;

        // Chụp màn hình phần cần export
        const canvas = await html2canvas(exportRef.current, { scale: 2 });
        const imgData = canvas.toDataURL('image/png');

        const pdf = new jsPDF('p', 'mm', 'a4');
        const pageWidth = pdf.internal.pageSize.getWidth();

        // Tính kích thước ảnh cho vừa trang
        const imgWidth = pageWidth - 20; // chừa lề
        const imgHeight = (canvas.height * imgWidth) / canvas.width;

        pdf.addImage(imgData, 'PNG', 10, 10, imgWidth, imgHeight);
        pdf.save(``);
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
                        {Object.keys(grouped).map((hocKyKey) => {
                            const monHocs = grouped[hocKyKey];
                            return (
                                <div key={hocKyKey}>
                                    <h2 className="mb-2">{hocKyKey}</h2>
                                    <table
                                        className="table table-bordered text-center"
                                        style={{ border: "1px solid black" }}
                                    >
                                        <thead>
                                            <tr>
                                                <th>Môn học</th>
                                                <th>Nhóm</th>
                                                <th>Số tín chỉ</th>
                                                <th>Giữa kỳ</th>
                                                <th>Cuối kỳ</th>
                                                <th>Tổng kết</th>
                                                <th>Kết quả</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {Object.keys(monHocs).sort().map((tenMon) => {
                                                return monHocs[tenMon].map((i, idx) => (
                                                    <tr key={idx}>
                                                        <td style={{ width: "30%" }}>{tenMon}</td>
                                                        <td style={{ width: "10%" }}>{i.maLop}</td>
                                                        <td style={{ width: "10%" }}>{i.soTinChi}</td>
                                                        <td style={{ width: "10%" }}>{i.diem_gk !== null ? i.diem_gk : "-"}</td>
                                                        <td style={{ width: "10%" }}>{i.diem_ck !== null ? i.diem_ck : "-"}</td>
                                                        <td style={{ width: "10%" }}>{i.diem_tk !== null ? i.diem_tk : "-"}</td>
                                                        <td style={{ width: "10%" }}>{getKetQua(i.trangThai)}</td>
                                                    </tr>
                                                ));
                                            })}
                                        </tbody>
                                    </table>
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