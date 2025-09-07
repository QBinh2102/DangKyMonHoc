'use client'

import { useEffect, useRef, useState } from "react";
import Chart from 'chart.js/auto';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import Apis, { authApis, endpoints } from "@/configs/Apis";
import ChartDataLabels from "chartjs-plugin-datalabels";
Chart.register(ChartDataLabels);

const Admin = () => {

    const [listHocKy, setListHocKy] = useState([]);
    const [hocKyId, setHocKyId] = useState("");
    const [listKhoa, setListKhoa] = useState([]);
    const [khoaId, setKhoaId] = useState("");
    const [listMonHoc, setListMonHoc] = useState([]);
    const [monHocId, setMonHocId] = useState("");
    const [data, setData] = useState({ hoanThanh: 0, truot: 0 });
    const [dataByLop, setDataByLop] = useState([]);
    const [showThongKe, setShowThongKe] = useState(false);

    const chartRef = useRef(null);
    const chartInstance = useRef(null);
    const barChartRef = useRef(null);
    const barChartInstance = useRef(null);
    const exportRef = useRef(null);

    const loadHocKy = async () => {
        try {
            let res = await authApis().get(endpoints['hocKy']);
            setListHocKy(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadKhoa = async () => {
        try {
            let res = await Apis.get(endpoints['khoa']);
            setListKhoa(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadHocKy();
        loadKhoa();
    }, []);

    const loadMonHoc = async (hocKyId, khoaId) => {
        try {
            let res = await Apis.get(`${endpoints['monHoc']}?hocKyId=${hocKyId}&khoaId=${khoaId}`);
            setListMonHoc(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        if (hocKyId && khoaId) {
            loadMonHoc(hocKyId, khoaId);
        } else {
            setListMonHoc([]);
        }
    }, [hocKyId, khoaId]);

    const loadThongKe = async (hocKyId, khoaId, monHocId) => {
        try {
            let res = await authApis().get(`${endpoints['thongKe']}?hocKyId=${hocKyId}&khoaId=${khoaId}&monHocId=${monHocId}`);
            setData(res.data);
            setShowThongKe(true);
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadThongKeTheoLop = async (hocKyId, monHocId) => {
        try {
            let res = await authApis().get(`${endpoints['thongKeTheoLop']}?hocKyId=${hocKyId}&monHocId=${monHocId}`);
            setDataByLop(res.data);
            setShowThongKe(true);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        if (showThongKe && chartRef.current) {
            if (chartInstance.current) {
                chartInstance.current.destroy();
            }
            chartInstance.current = new Chart(chartRef.current, {
                type: "pie",
                data: {
                    labels: ["Hoàn thành", "Trượt"],
                    datasets: [
                        {
                            label: "Số lượng",
                            data: [data.hoanThanh, data.truot],
                            backgroundColor: ["#36a2eb", "#ff6384"],
                        },
                    ],
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        datalabels: {
                            formatter: (value, context) => {
                                const dataArr = context.chart.data.datasets[0].data;
                                const total = dataArr.reduce((acc, curr) => acc + curr, 0);
                                const percentage = ((value / total) * 100).toFixed(1);
                                return percentage + '%';
                            },
                            color: '#fff',
                            font: {
                                weight: 'bold',
                            },
                        },
                    },
                },
                plugins: [ChartDataLabels],
            });
        }
    }, [showThongKe, data]);

    useEffect(() => {
        if (showThongKe && barChartRef.current) {
            if (barChartInstance.current) barChartInstance.current.destroy();

            barChartInstance.current = new Chart(barChartRef.current, {
                type: "bar",
                data: {
                    labels: dataByLop.map((lop) => lop.maLop), // tên lớp trên trục X
                    datasets: [
                        {
                            label: "Hoàn thành",
                            data: dataByLop.map((lop) => lop.soHoanThanh),
                            backgroundColor: "#4CAF50", // xanh
                        },
                        {
                            label: "Trượt",
                            data: dataByLop.map((lop) => lop.soTruot),
                            backgroundColor: "#F44336", // đỏ
                        },
                    ],
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        datalabels: {
                            anchor: "end",
                            align: "top",
                            formatter: (value) => value,
                            color: "#000",
                            font: { weight: "bold" },
                        },
                    },
                },
                plugins: [ChartDataLabels],
            });
        }
    }, [showThongKe, dataByLop]);


    useEffect(() => {
        if (hocKyId && khoaId && monHocId) {
            loadThongKe(hocKyId, khoaId, monHocId);
            loadThongKeTheoLop(hocKyId, monHocId);
        } else {
            setShowThongKe(false);
        }
    }, [hocKyId, khoaId, monHocId]);

    const exportPDF = async () => {
        if (!exportRef.current) return;

        // Chụp màn hình phần cần export
        const canvas = await html2canvas(exportRef.current, { scale: 2 });
        const imgData = canvas.toDataURL('image/png');

        const pdf = new jsPDF('p', 'mm', 'a4');
        const pageWidth = pdf.internal.pageSize.getWidth();

        // Tính kích thước ảnh cho vừa trang
        const imgWidth = pageWidth - 20;
        const imgHeight = (canvas.height * imgWidth) / canvas.width;

        pdf.addImage(imgData, 'PNG', 10, 10, imgWidth, imgHeight);
        pdf.save(`ThongKe_${hocKyId}_${khoaId}_${monHocId}.pdf`);
    };

    return (
        <div>
            <div ref={exportRef}>
                <h1 className="text-center mt-3 mb-3">Thống kê kết quả học tập</h1>

                <div className="mt-3 mb-3">
                    <label className="me-3">Học Kỳ: </label>
                    <select
                        className="me-3"
                        value={hocKyId}
                        onChange={e => setHocKyId(e.target.value)}
                    >
                        <option value="">--Chọn--</option>
                        {listHocKy.map(hk => (
                            <option key={hk.id} value={hk.id}>{hk.ky} - {hk.namHoc}</option>
                        ))}
                    </select>

                    <label className="me-3">Khoa: </label>
                    <select
                        className="me-3"
                        value={khoaId}
                        onChange={e => setKhoaId(e.target.value)}
                    >
                        <option value="">--Chọn--</option>
                        {listKhoa.map(k => (
                            <option key={k.id} value={k.id}>{k.tenKhoa}</option>
                        ))}
                    </select>

                    <label className="me-3">Môn Học: </label>
                    <select
                        className="me-3"
                        value={monHocId}
                        onChange={e => setMonHocId(e.target.value)}
                    >
                        <option value="">--Chọn--</option>
                        {listMonHoc.map(mh => (
                            <option key={mh.id} value={mh.id}>{mh.tenMon}</option>
                        ))}
                    </select>
                </div>

                {showThongKe &&
                    <div>
                        <div className="d-flex justify-content-center align-items-center gap-5 mt-4 mb-4">
                            <div style={{ width: "400px", height: "400px" }}>
                                <canvas ref={chartRef}></canvas>
                            </div>

                            <div style={{ width: "400px", height: "400px" }}>
                                <canvas ref={barChartRef}></canvas>
                            </div>
                        </div>


                        <table className="table mt-3" border="1">
                            <thead>
                                <tr>
                                    <th>Loại</th>
                                    <th>Số lượng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Hoàn thành</td>
                                    <td>{data.hoanThanh}</td>
                                </tr>
                                <tr>
                                    <td>Trượt</td>
                                    <td>{data.truot}</td>
                                </tr>
                                <tr>
                                    <td><strong>Tổng</strong></td>
                                    <td><strong>{data.hoanThanh + data.truot}</strong></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                }
            </div>

            {showThongKe &&
                <button className="btn btn-primary mt-3 mb-3" onClick={exportPDF}>
                    Xuất PDF
                </button>
            }
        </div>
    );
};
export default Admin;
