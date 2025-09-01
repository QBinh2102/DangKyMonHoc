const Footer = () => {
    return (
        <footer style={{ background: "#222", padding: "20px", color: "#fff" }}>
            <div className="d-flex justify-content-center">
                <div style={{ maxWidth: "900px", width: "100%" }}>
                    <div className="d-flex justify-content-between">
                        <div>
                            <h5 style={{ color: "#fff", fontSize: "30px" }}>Trường đại học TQB</h5>
                        </div>

                        <div>
                            <h5>Thông tin</h5>
                            <hr/>
                            <p>Liên hệ: 0762590966</p>
                            <p>Email: 2251012017binh@ou.edu.vn</p>
                            <p>Địa chỉ: Phường Sài Gòn, Quận 5, TP. Hồ Chí Minh</p>
                        </div>
                    </div>

                    {/* Dòng cuối cùng căn giữa */}
                    <div className="text-center mt-3">
                        <p>© 2025 Hệ thống đăng ký môn học</p>
                    </div>
                </div>
            </div>
        </footer>
    );
}
export default Footer;