'use client'

import Link from "next/link";
import { useRouter } from "next/navigation";
import cookie from "react-cookies";

const menuItems = [
    { label: "Học Kỳ", href: "/admin/hocky" },
    { label: "Khoa", href: "/admin/khoa" },
    { label: "Ngành", href: "/admin/nganh" },
    { label: "Môn Học", href: "/admin/monhoc"},
    { label: "Giảng Viên", href: "/admin/giangvien" },
    { label: "Sinh Viên", href: "/admin/sinhvien" },
    { label: "Buổi Học", href: "/admin/buoihoc" },
    { label: "Lịch Học", href: "/admin/lichhoc" },
    { label: "Quy Định", href: "/admin/quydinh" }
]

const AdminLayout = ({ children }) => {
    const router = useRouter();

    const dangXuat = () =>{
        cookie.remove('token'),
        router.replace('/dangnhap');
    }

    return (
        <div>
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark">
                <div className="container-fluid">
                    <Link className="navbar-brand" href="/admin">Trang chủ</Link>
                    <div className="collapse navbar-collapse" id="mynavbar">
                        <ul className="navbar-nav me-auto">
                            {menuItems.map((item, idx) => (
                                <li className="nav-item" key={idx}>
                                    <Link className="nav-link" href={item.href}>{item.label}</Link>
                                </li>
                            ))}
                        </ul>
                        <button className="btn btn-danger btn-outline-light ms-2" onClick={dangXuat}>
                            Đăng xuất
                        </button>
                    </div>
                </div>
            </nav>
            <div className="container mt-3">{children}</div>
        </div>
    );

}
export default AdminLayout;