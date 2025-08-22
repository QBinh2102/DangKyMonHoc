'use client';

import { MyDispatchContext } from "@/configs/Contexts";
import { useContext, useEffect, useState } from "react";
import Apis, { authApis, endpoints } from "@/configs/Apis";
import cookie from 'react-cookies';
import { useRouter } from "next/navigation";
import "./dangnhap.css"; // Thêm file CSS riêng

const DangNhap = () => {
  const info = [
    { label: "Email", field: "email", type: "email" },
    { label: "Mật khẩu", field: "matKhau", type: "password" }
  ];

  const [user, setUser] = useState({});
  const [msg, setMsg] = useState('');
  const [loading, setLoading] = useState(false);
  const dispatch = useContext(MyDispatchContext);
  const router = useRouter();
  const [captcha, setCaptcha] = useState('');
  const [inputCaptcha, setInputCaptcha] = useState('');

  const generateCaptcha = () => {
    const chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let result = "";
    for (let i = 0; i < 4; i++) {
      result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
  };

  const dangNhap = async (e) => {
    e.preventDefault();
    setMsg('');

    if (inputCaptcha !== captcha) {
      setMsg("Mã bảo vệ không hợp lệ!");
      setInputCaptcha("");
      setCaptcha(generateCaptcha());
      return;
    }

    try {
      setLoading(true);
      let res = await Apis.post(endpoints['dangNhap'], { ...user });
      console.info(res.data.token);
      cookie.save('token', res.data.token);

      let u = await authApis().get(endpoints['current-user']);
      console.info(u.data);
      dispatch({
        type: "login",
        payload: u.data
      });

      if (u.data.vaiTro === "ROLE_ADMIN") {
        router.replace("/admin");
      } else if (u.data.vaiTro === "ROLE_SINH_VIEN") {
        router.replace("/sinhvien");
      } else {
        setMsg("Không xác định quyền truy cập!");
      }
    } catch (ex) {
      console.error(ex);
      if (ex.response && ex.response.status === 401) {
        setMsg("Email hoặc mật khẩu không đúng!");
      } else {
        setMsg("Đã xảy ra lỗi, vui lòng thử lại!");
      }
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    setCaptcha(generateCaptcha());
  }, []);

  return (
    <div className="d-flex flex-column justify-content-center align-items-center vh-100 bg-light">
      <div className="login-form-container">
        <h1 className="text-center text-primary mb-3">TRƯỜNG ĐẠI HỌC TQB</h1>
        <h3 className="text-center text-primary mb-3">Đăng nhập</h3>

        {msg && (
          <div className="alert alert-warning text-center" role="alert">
            {msg}
          </div>
        )}

        <form onSubmit={dangNhap}>
          {info.map(i => (
            <div className="mb-3" key={i.field}>
              <input
                className="form-control login-input"
                type={i.type}
                value={user[i.field] || ''}
                onChange={e => setUser({ ...user, [i.field]: e.target.value })}
                placeholder={i.label}
                required
              />
            </div>
          ))}

          <div className="mb-3 d-flex align-items-center">
            <input
              className="form-control login-input me-2"
              value={inputCaptcha}
              onChange={(e) => setInputCaptcha(e.target.value)}
              placeholder="Nhập mã bảo mật"
              required
            />
            <div className="captcha-box" onClick={() => setCaptcha(generateCaptcha())} title="Click để làm mới">
              <strong>{captcha}</strong>
            </div>
          </div>

          <div className="d-flex justify-content-center">
            {loading ? (
              <div className="spinner-border text-primary"></div>
            ) : (
              <button type="submit" className="btn btn-primary login-btn">
                Đăng nhập
              </button>
            )}
          </div>
        </form>
      </div>
    </div>
  );
}

export default DangNhap;
