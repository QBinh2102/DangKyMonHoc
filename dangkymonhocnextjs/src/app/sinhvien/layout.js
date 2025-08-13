'use client'

import { useRouter } from "next/navigation";
import cookie from "react-cookies";

export default function SinhVienLayout({ children }) {
    const router = useRouter();

    const menuItems = [
        { label: "Thông tin", href: "/sinhvien" },
        { label: "Đề cương môn học", href: "/sinhvien" },
        { label: "Xem điểm", href: "/sinhvien" },
        { label: "Thời khóa biểu", href: "/sinhvien" },
        { label: "Đăng ký môn học", href: "/sinhvien" },
        { label: "Xem học phí", href: "/sinhvien" },
    ]

    const dangXuat = () => {
        cookie.remove('token');
        router.replace('/dangnhap');
    }

    return (
        <div style={{
            display: "flex",
            minHeight: "100vh"
        }}>
            {/* Nội dung chính */}
            <div style={{
                flex: 1,
                padding: "20px",
                backgroundColor: "#f9f9f9"
            }}>
                <div className="container mt-3">{children}</div>
            </div>

            {/* Sidebar bên phải */}
            <div style={{
                width: "250px",
                backgroundColor: "#eee",
                padding: "20px",
                display: "flex",
                flexDirection: "column",
                gap: "10px"
            }}>
                {menuItems.map((i, idx) => (
                    <button key={idx} className="btn btn-primary" onClick={() => router.push(`${i.href}`)}>{i.label}</button>
                ))}
                <button className="btn btn-danger" onClick={dangXuat}>Đăng Xuất</button>
            </div>
        </div>
    );
}
