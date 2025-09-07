'use client'

import { useContext, useEffect, useRef, useState, Suspense } from "react";
import { authApis, endpoints } from "@/configs/Apis";
import { useRouter, useSearchParams } from "next/navigation";
import { MyUserContext } from "@/configs/Contexts";

export const dynamic = "force-dynamic";

const VNPayResultContent = () => {
    const user = useContext(MyUserContext);
    const searchParams = useSearchParams();
    const [status, setStatus] = useState("loading");
    const [message, setMessage] = useState("");
    const [transactionId, setTransactionId] = useState("");
    const [id, setId] = useState("");
    const router = useRouter();
    const hasHandledRef = useRef(false);

    useEffect(() => {
        if (hasHandledRef.current) return;
        hasHandledRef.current = true;

        const responseCode = searchParams.get("vnp_ResponseCode");
        const txnId = searchParams.get("vnp_TransactionNo");
        const orderInfo = searchParams.get("vnp_OrderInfo");

        if (!orderInfo) {
            setStatus("fail");
            setMessage("❌ Không tìm thấy thông tin giao dịch.");
            return;
        }

        const hocPhiId = orderInfo.replace("HOC_PHI", "");
        setId(hocPhiId);
        setTransactionId(txnId);

        const updateHocPhi = async (trangThai) => {
            try {
                let res = await authApis().get(endpoints['layHoacSuaHocPhiId'](hocPhiId));
                const hocPhi = res.data;
                hocPhi.trangThai = trangThai;
                await authApis().put(endpoints['layHoacSuaHocPhiId'](hocPhiId), hocPhi);
            } catch (ex) {
                console.error("Lỗi cập nhật trạng thái học phí:", ex);
            }
        };

        const handlePaymentResult = async () => {
            if (responseCode === "00") {
                setStatus("success");
                setMessage("✅ Thanh toán thành công!");
                await updateHocPhi("DA_THANH_TOAN");
            } else {
                const friendlyMsg = getVNPayErrorMessage(responseCode);
                setStatus("fail");
                setMessage(`❌ Thanh toán thất bại: ${friendlyMsg}`);
            }
        };

        handlePaymentResult();
    }, [searchParams]);

    const chuyenTrang = () => {
        if (user?.vaiTro === "ROLE_ADMIN") {
            router.push(`/admin/hocphi/${id}`);
        } else {
            router.push(`/sinhvien/hocphi`);
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.card}>
                {status === "loading" && <p>Đang xử lý kết quả thanh toán...</p>}
                {status === "success" && (
                    <>
                        <h2 style={{ color: "green" }}>{message}</h2>
                        <p>Mã giao dịch: <strong>{transactionId}</strong></p>
                    </>
                )}
                {status === "fail" && (
                    <>
                        <h2 style={{ color: "red" }}>{message}</h2>
                    </>
                )}
                <button onClick={chuyenTrang} style={styles.button}>Quay lại</button>
            </div>
        </div>
    );
}

const VNPayResult = () => {
    return (
        <Suspense fallback={<p>Đang tải dữ liệu giao dịch...</p>}>
            <VNPayResultContent />
        </Suspense>
    );
};

export default VNPayResult;


function getVNPayErrorMessage(code) {
    const messages = {
        "24": "Giao dịch bị hủy bởi người dùng.",
        "07": "Giao dịch bị nghi ngờ gian lận.",
        "09": "Chưa đăng ký Internet Banking.",
        "10": "Xác thực thất bại.",
        "11": "Sai thông tin xác thực.",
        "12": "Tài khoản bị khóa hoặc không đủ tiền.",
        "13": "Hết thời gian thanh toán.",
        "51": "Không đủ số dư.",
        "65": "Vượt hạn mức giao dịch.",
        "99": "Lỗi không xác định.",
    };
    return messages[code] || "Không xác định được nguyên nhân.";
}

const styles = {
    container: {
        display: "flex", justifyContent: "center", alignItems: "center",
        height: "100vh", backgroundColor: "#f0f2f5",
    },
    card: {
        padding: "2rem", borderRadius: "1rem",
        backgroundColor: "#fff", boxShadow: "0 0 10px rgba(0,0,0,0.1)",
        textAlign: "center", minWidth: "320px",
    },
    button: {
        display: "inline-block", marginTop: "1rem",
        backgroundColor: "#007bff", color: "#fff",
        padding: "0.5rem 1rem", borderRadius: "0.5rem",
        textDecoration: "none",
    },
};
