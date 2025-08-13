-- HỌC KỲ
CREATE TABLE hoc_ky (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ky VARCHAR(20) NOT NULL,
    nam_hoc VARCHAR(20) NOT NULL,
    UNIQUE(ky, nam_hoc)
);

-- PHÒNG
CREATE TABLE phong_hoc (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ten_phong VARCHAR(20) NOT NULL UNIQUE,
    loai ENUM('LyThuyet', 'ThucHanh') NOT NULL,
    UNIQUE(ten_phong, loai)
);

-- ========================
-- 1. KHOA (Faculty)
-- ========================
CREATE TABLE khoa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_khoa VARCHAR(100) NOT NULL UNIQUE
);

-- ========================
-- 2. NGÀNH (Major)
-- ========================
CREATE TABLE nganh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_nganh VARCHAR(100) NOT NULL,
    khoa_id INT NOT NULL,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id),
    UNIQUE (ten_nganh, khoa_id)
);

-- Tạo lớp
CREATE TABLE lop (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ma_lop VARCHAR(20) NOT NULL,
    si_so INT NOT NULL,
    khoa_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (ma_lop, khoa_id, hoc_ky_id)
);

-- ===============================
-- 3. Bảng Người Dùng (Chung)
-- ===============================
CREATE TABLE nguoi_dung (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mat_khau VARCHAR(255) NOT NULL,
    vai_tro ENUM('ROLE_SINH_VIEN', 'ROLE_GIANG_VIEN', 'ROLE_ADMIN') NOT NULL
);

-- ===============================
-- 4. Bảng Sinh Viên
-- ===============================
CREATE TABLE sinh_vien (
    id INT PRIMARY KEY,  -- Trùng với ID người dùng
    ngay_sinh DATE,
    khoa_hoc INT,
    so_tin_chi INT DEFAULT 0,
    khoa_id INT NOT NULL,
    nganh_id INT NOT NULL,
    lop_id INT NOT NULL, 
    FOREIGN KEY (id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id),
    FOREIGN KEY (nganh_id) REFERENCES nganh(id),
    FOREIGN KEY (lop_id) REFERENCES lop(id)
);

-- ===============================
-- 5. Bảng Giảng Viên
-- ===============================
CREATE TABLE giang_vien (
    id INT PRIMARY KEY,  -- Trùng với ID người dùng
    hoc_vi VARCHAR(100),
    khoa_id INT NOT NULL,
    FOREIGN KEY (id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id)
);

-- ========================
-- 6. MÔN HỌC
-- ========================
CREATE TABLE mon_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_mon VARCHAR(100) NOT NULL,
    mo_ta TEXT,
    tin_chi_ly_thuyet INT NOT NULL,
    tin_chi_thuc_hanh INT NOT NULL,
    phan_tram_giua_ky INT NOT NULL DEFAULT 30,
    phan_tram_cuoi_ky INT NOT NULL DEFAULT 70,
    diem_qua_mon DECIMAL(4,2) NOT NULL DEFAULT 5.0,
    khoa_id INT NOT NULL,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id)
);

-- ========================
-- 7. MÔN TIÊN QUYẾT (Self Join)
-- ========================
CREATE TABLE mon_hoc_lien_quan (
    mon_hoc_id INT NOT NULL,
    lien_quan_id INT NOT NULL,
    nganh_id INT NOT NULL,
    loai ENUM('TIEN_QUYET', 'HOC_TRUOC') NOT NULL,
    PRIMARY KEY (mon_hoc_id, lien_quan_id, nganh_id, loai),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (lien_quan_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (nganh_id) REFERENCES nganh(id)
);

-- ========================
-- 8. MÔN HỌC (Thuộc 1 ngành duy nhất)
-- ========================
CREATE TABLE nganh_mon_hoc (
    nganh_id INT NOT NULL,
    mon_hoc_id INT NOT NULL,
    PRIMARY KEY (nganh_id, mon_hoc_id),
    FOREIGN KEY (nganh_id) REFERENCES nganh(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id)
);

-- ĐIỂM (Điểm của học sinh với môn học)
CREATE TABLE diem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinh_vien_id INT NOT NULL,
    mon_hoc_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    lan_hoc INT NOT NULL DEFAULT 1,
    loai ENUM('GIUA_KY', 'CUOI_KY', 'TONG_KET') NOT NULL,
    diem DECIMAL(4,2) CHECK (diem BETWEEN 0 AND 10),
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (sinh_vien_id, mon_hoc_id, lan_hoc, loai)  -- tránh trùng điểm cùng loại
);

-- ========================
-- 9. BUỔI HỌC (Lịch học cụ thể)
-- ========================
CREATE TABLE buoi_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mon_hoc_id INT NOT NULL,
    giang_vien_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    si_so INT DEFAULT 50,
    loai ENUM('LT', 'LT-TH'),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (giang_vien_id) REFERENCES giang_vien(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (mon_hoc_id, giang_vien_id, hoc_ky_id, si_so)
);

-- THỜI KHÓA BIỂU
CREATE TABLE lich_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    buoi_hoc_id INT NOT NULL,
    thu VARCHAR(20),            -- VD: 'Thứ 2'
    gio_bat_dau TIME NOT NULL,
    gio_ket_thuc TIME NOT NULL,
    ngay_bat_dau DATE NOT NULL,
    ngay_ket_thuc DATE NOT NULL,
    phong_hoc_id INT NOT NULL,
    loai ENUM('LyThuyet', 'ThucHanh'),
    lan INT DEFAULT 1,
    FOREIGN KEY (buoi_hoc_id) REFERENCES buoi_hoc(id),
    FOREIGN KEY (phong_hoc_id) REFERENCES phong_hoc(id)
);

-- ========================
-- 10. ĐĂNG KÝ MÔN HỌC
-- ========================
CREATE TABLE dang_ky (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinh_vien_id INT NOT NULL,
    buoi_hoc_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    trang_thai ENUM('DANG_KY', 'HOAN_THANH', 'TRUOT') DEFAULT 'DANG_KY',
    ngay_dang_ky TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (buoi_hoc_id) REFERENCES buoi_hoc(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (sinh_vien_id, buoi_hoc_id)
);

-- THỜI KHÓA BIỂU
CREATE TABLE thoi_khoa_bieu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinh_vien_id INT,
    lich_hoc_id INT,
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (lich_hoc_id) REFERENCES lich_hoc(id),
    UNIQUE (sinh_vien_id, lich_hoc_id)
);

-- ========================
-- 11. QUY ĐỊNH HỆ THỐNG (VD: Giới hạn tín chỉ)
-- ========================
CREATE TABLE quy_dinh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(100) UNIQUE NOT NULL,
    gia_tri INT NOT NULL
);

-- ========================
-- 12. DỮ LIỆU MẪU
-- ========================
INSERT INTO quy_dinh (ten, gia_tri) VALUES 
    ('Số tiền 1 tín chỉ', 300000),
    ('Số tín chỉ tối đa', 24),
    ('Số buổi 1 tín chỉ lý thuyết', 4),
    ('Số buổi 1 tín chỉ thực hành', 6),
    ('Số giờ 1 buổi học', 4);
    

-- Thêm học kỳ
-- Kỳ 1: 10,11,12,1
-- Kỳ 2: 2,3,4,5
-- kỳ 3: 6,7,8,9
INSERT INTO hoc_ky (ky, nam_hoc)
VALUES
	('Học kỳ 1', '2022-2023'),
    ('Học kỳ 2', '2022-2023'),
    ('Học kỳ 3', '2022-2023'),
    ('Học kỳ 1', '2023-2024'),
    ('Học kỳ 2', '2023-2024'),
    ('Học kỳ 3', '2023-2024'),
    ('Học kỳ 1', '2024-2025'),
    ('Học kỳ 2', '2024-2025'),
    ('Học kỳ 3', '2024-2025');

-- Thêm phòng học
INSERT INTO phong_hoc (ten_phong, loai)
VALUES
	("101",'LyThuyet'),
    ("102",'LyThuyet'),
    ("103",'LyThuyet'),
    ("104",'LyThuyet'),
    ("201",'LyThuyet'),
    ("202",'LyThuyet'),
    ("203",'LyThuyet'),
    ("204",'LyThuyet'),
    ("301",'LyThuyet'),
    ("302",'LyThuyet'),
    ("303",'LyThuyet'),
    ("304",'LyThuyet'),
    ("PM.101", 'ThucHanh'),
    ("PM.102", 'ThucHanh'),
    ("PM.103", 'ThucHanh'),
    ("PM.201", 'ThucHanh'),
    ("PM.202", 'ThucHanh'),
    ("PM.203", 'ThucHanh'),
    ("PM.301", 'ThucHanh'),
    ("PM.302", 'ThucHanh'),
    ("PM.303", 'ThucHanh');

-- Thêm khoa
INSERT INTO khoa (ten_khoa)
VALUES ('Công nghệ thông tin');

-- Thêm các ngành thuộc khoa đó
INSERT INTO nganh (ten_nganh, khoa_id)
VALUES 
	('Khoa học máy tính', 1),
	('Hệ thống thông tin quản lý', 1),
	('Công nghệ thông tin', 1);
    
-- Thêm lớp
INSERT INTO lop (ma_lop, si_so, hoc_ky_id, khoa_id)
VALUES
	("DH22CS01", 10, 1, 1),
    ("DH22IT01", 10, 7, 1);

-- ==========================
-- THÊM GIẢNG VIÊN
-- ==========================
INSERT INTO nguoi_dung (ho_ten, email, mat_khau, vai_tro) VALUES
    ('Nguyễn Văn Ón', 'onnguyenvan@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Trần Thị Tuyết', 'tuyettranthi@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Lê Minh Bảo', 'baoleminh@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Hữu Khuê', 'khuenguyenhuu@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Phạm Minh Trang', 'trangphamminh@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Đỗ Quang Hùng', 'hungdoquang@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Phùng Thị Anh', 'anhphungthi@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Võ Văn Kiệt', 'kietvovan@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Dương Minh Công', 'congminhduong@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Lộc Thành', 'thanhnguenloc@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Trần Tuấn Khải', 'khaitrantuan@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Đỗ Kim Tuấn', 'tuandokim@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Trần Hà Thanh', 'thanhtranha@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Quốc Thiên', 'thiennguyenquoc@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Phương Trang', 'trangnguyephuong@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Ngô Tiến Đạt', 'datngotien@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Trương Trí Kiệt', 'kiettruongtri@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN'),
    ('Ngô Ngọc Hậu', 'haungongoc@giangvien.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_GIANG_VIEN');

-- Giả sử id tự động tăng từ 1 đến 14 cho giảng viên

INSERT INTO giang_vien (id, hoc_vi, khoa_id) VALUES
    (1, 'Thạc sĩ', 1),
    (2, 'Tiến sĩ', 1),
    (3, 'Thạc sĩ', 1),
    (4, 'Tiến sĩ', 1),
    (5, 'Tiến sĩ', 1),
    (6, 'Thạc sĩ', 1),
    (7, 'Thạc sĩ', 1),
    (8, 'Thạc sĩ', 1),
    (9, 'Thạc sĩ', 1),
    (10, 'Thạc sĩ', 1),
    (11, 'Tiến sĩ', 1),
    (12, 'Tiến sĩ', 1),
    (13, 'Thạc sĩ', 1),
    (14, 'Tiến sĩ', 1),
    (15, 'Thạc sĩ', 1),
    (16, 'Thạc sĩ', 1),
    (17, 'Thạc sĩ', 1),
    (18, 'Thạc sĩ', 1);

-- ==========================
-- THÊM SINH VIÊN
-- ==========================
INSERT INTO nguoi_dung (ho_ten, email, mat_khau, vai_tro) VALUES
    ('Tô Quốc Bình', 'binh@student.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_SINH_VIEN'),
    ('Trần Huỳnh Sang', 'sang@student.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_SINH_VIEN'),
    ('Nguyễn Đăng Khôi', 'khoi@student.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_SINH_VIEN'),
    ('Trần Thị Trang', 'trang@student.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_SINH_VIEN'),
    ('Võ Quốc Bảo', 'bao@student.edu.vn', '$2a$10$Tl8uVg4Yq2h6zSUx3mPngOvPZYQ3UFHiHV5bFa//v5G/cR0gBwhZm', 'ROLE_SINH_VIEN');

-- Giả sử id tiếp tục từ 15 đến 19

INSERT INTO sinh_vien (id, ngay_sinh, khoa_hoc, so_tin_chi, khoa_id, nganh_id, lop_id) VALUES
    (19, '2004-02-21', 2022, 71, 1, 1, 1),
    (20, '2004-03-24', 2022, 71, 1, 1, 1),
    (21, '2004-05-27', 2022, 71, 1, 1, 1),
    (22, '2006-05-15', 2024, 46, 1, 3, 2);

-- Thêm admin
INSERT INTO nguoi_dung (ho_ten, email, mat_khau, vai_tro)
VALUES ('Admin Hệ Thống', 'admin@admin.vn', '$2a$10$YJWUKd2V8ZTn/uiiz6UQGOrEmScXYjxJCCQDrLRU3iD3vaYmmjNPm', 'ROLE_ADMIN'); -- admin123

    
-- Thêm môn học
INSERT INTO mon_hoc (ten_mon, mo_ta, tin_chi_ly_thuyet, tin_chi_thuc_hanh, phan_tram_giua_ky, phan_tram_cuoi_ky, diem_qua_mon, khoa_id)
VALUES
('Nhập môn tin học', '', 2, 1, 40, 60, 5, 1),
('Cơ sở lập trình', '', 3, 1, 40, 60, 5, 1),
('Hệ điều hành và Kiến trúc máy tính', '', 3, 0, 30, 70, 5, 1),
('Kỹ thuật lập trình', '', 3, 1, 40, 60, 5, 1),
('Ứng dụng web', '', 2, 1, 30, 70, 5, 1),
('Cấu trúc dữ liệu và thuật giải 1', '', 3, 1, 40, 60, 5, 1),
('Cơ sở dữ liệu', '', 3, 1, 40, 60, 5, 1),
('Toán rời rạc', '', 4, 0, 40, 60, 5, 1),
('Cấu trúc dữ liệu và thuật giải 2', '', 2, 1, 40, 60, 5, 1),
('Mạng máy tính', '', 3, 1, 40, 60, 5, 1),
('Lập trình hướng đối tượng', '', 3, 1, 40, 60, 5, 1),
('Kỹ năng nghề nghiệp', '', 3, 0, 40, 60, 5, 1),
('Phân tích thiết kế hệ thống', '', 4, 0, 50, 50, 5, 1),
('Mẫu thiết kế hướng đối tượng', '', 2, 1, 40, 60, 5, 1),
('Trí tuệ nhân tạo', '', 2, 1, 50, 50, 5, 1),
('Quản trị hệ cơ sở dữ liệu', '', 2, 1, 40, 60, 5, 1),
('Công nghệ phần mềm', '', 2, 1, 40, 60, 5, 1),
('Các công nghệ lập trình hiện đại', '', 2, 1, 40, 60, 5, 1),
('Máy học', '', 2, 1, 40, 60, 5, 1),
('Kiểm thử phần mềm', '', 2, 1, 40, 60, 5, 1),
('Phát triển hệ thống web', '', 2, 1, 30, 70, 5, 1),
('Thiết kế web', '', 2, 1, 40, 60, 5, 1),
('Cấu trúc dữ liệu và thuật giải', '', 3, 1, 40, 60, 5, 1),
('Công nghệ mã nguồn mở', '', 2, 1, 50, 50, 5, 1),
('Lập trình giao diện', '', 2, 1, 50, 50, 5, 1),
('Quản trị mạng', '', 2, 1, 40, 60, 5, 1),
('An toàn hệ thống thông tin', '', 2, 1, 40, 60, 5, 1),
('Hệ thống quản lý nguồn lực doanh nghiệp', '', 2, 1, 50, 50, 5, 1),
('Hệ thống thông tin quản lý', '', 3, 0, 50, 50, 5, 1),
('Lập trình cơ sở dữ liệu', '', 2, 1, 50, 50, 5, 1),
('Phát triển hệ thống thông tin quản lý', '', 3, 0, 50, 50, 5, 1),
('Đồ án ngành', '', 0, 4, 40, 60, 5, 1),
('Thực tập tốt nghiệp', '', 0, 4, 30, 70, 5, 1),
('Khóa luận tốt nghiệp', '', 0, 6, 0, 100, 5, 1),
('Đồ án ngành Hệ thống thông tin quản lý', '', 0, 4, 40, 60, 5, 1),
('Thực tập tốt nghiệp', '', 0, 4, 30, 70, 5, 1),
('Khóa luận tốt nghiệp', '', 0, 6, 0, 100, 5, 1);

-- Thêm môn liên quan
INSERT INTO mon_hoc_lien_quan (mon_hoc_id, lien_quan_id, nganh_id, loai)
VALUES
	-- KHOA HỌC MÁY TÍNH
	(3,1,1,'HOC_TRUOC'),
    (4,2,1,'HOC_TRUOC'),
    (5,2,1,'HOC_TRUOC'),
    (6,4,1,'HOC_TRUOC'),
    (7,1,1,'HOC_TRUOC'),
    (9,6,1,'HOC_TRUOC'),
    (10,3,1,'HOC_TRUOC'),
    (11,4,1,'HOC_TRUOC'),
    (13,7,1,'HOC_TRUOC'),
    (14,11,1,'HOC_TRUOC'),
    (15,8,1,'HOC_TRUOC'),
    (15,11,1,'HOC_TRUOC'),
    (16,7,1,'HOC_TRUOC'),
    (17,7,1,'HOC_TRUOC'),
    (17,11,1,'HOC_TRUOC'),
    (18,22,1,'HOC_TRUOC'),
    (18,11,1,'HOC_TRUOC'),
    (19,4,1,'HOC_TRUOC'),
    (20,13,1,'HOC_TRUOC'),
    (21,7,1,'HOC_TRUOC'),
    (21,11,1,'HOC_TRUOC'),
    (32,6,1,'TIEN_QUYET'),
	(33,32,1,'HOC_TRUOC'),
	(34,32,1,'TIEN_QUYET'),
    -- CÔNG NGHỆ THÔNG TIN
    (3,1,3,'HOC_TRUOC'),
    (4,2,3,'HOC_TRUOC'),
    (22,1,3,'HOC_TRUOC'),
    (23,4,3,'HOC_TRUOC'),
    (10,3,3,'HOC_TRUOC'),
    (11,4,3,'HOC_TRUOC'),
    (24,3,3,'HOC_TRUOC'),
    (7,1,3,'HOC_TRUOC'),
    (25,4,3,'HOC_TRUOC'),
    (13,7,3,'HOC_TRUOC'),
    (26,10,3,'HOC_TRUOC'),
    (14,11,3,'HOC_TRUOC'),
    (17,7,3,'HOC_TRUOC'),
    (17,11,3,'HOC_TRUOC'),
    (16,7,3,'HOC_TRUOC'),
    (27,10,3,'HOC_TRUOC'),
    (27,4,3,'HOC_TRUOC'),
    (20,13,3,'HOC_TRUOC'),
    (15,8,3,'HOC_TRUOC'),
    (15,11,3,'HOC_TRUOC'),
    (32,23,3,'TIEN_QUYET'),
	(33,32,3,'HOC_TRUOC'),
    -- HỆ THỐNG THÔNG TIN QUẢN LÝ
    (4,2,2,'HOC_TRUOC'),
    (23,4,2,'HOC_TRUOC'),
    (3,1,2,'HOC_TRUOC'),
    (7,1,2,'HOC_TRUOC'),
    (10,3,2,'HOC_TRUOC'),
    (11,4,2,'HOC_TRUOC'),
    (25,4,2,'HOC_TRUOC'),
    (13,7,2,'HOC_TRUOC'),
    (28,13,2,'HOC_TRUOC'),
    (16,7,2,'HOC_TRUOC'),
    (30,7,2,'HOC_TRUOC'),
    (30,25,2,'HOC_TRUOC'),
    (31,29,2,'HOC_TRUOC'),
    (35,23,2,'HOC_TRUOC'),
    (36,35,2,'HOC_TRUOC'),
    (37,35,2,'HOC_TRUOC');
	
-- Thêm ngành_môn học
INSERT INTO nganh_mon_hoc(nganh_id, mon_hoc_id)
VALUES
	-- Khoa học máy tính
	(1,1),
    (1,2),
    (1,3),
    (1,4),
    (1,5),
    (1,6),
    (1,7),
    (1,8),
    (1,9),
    (1,10),
    (1,11),
    (1,12),
    (1,13),
    (1,14),
    (1,15),
    (1,16),
    (1,17),
    (1,18),
    (1,19),
    (1,20),
    (1,21),
    (1,32),
    (1,33),
    (1,34),
    -- Công nghệ thông tin
    (3,1),
    (3,2),
    (3,3),
    (3,4),
    (3,22),
    (3,23),
    (3,10),
    (3,8),
    (3,11),
    (3,24),
    (3,7),
    (3,25),
    (3,12),
    (3,13),
    (3,26),
    (3,14),
    (3,17),
    (3,16),
    (3,27),
    (3,20),
    (3,15),
    (3,32),
    (3,33),
    (3,34),
    -- Hệ thống thông tin quản lý
    (2,1),
    (2,2),
    (2,4),
    (2,23),
    (2,3),
    (2,7),
    (2,10),
    (2,11),
    (2,25),
    (2,13),
    (2,8),
    (2,28),
    (2,29),
    (2,16),
    (2,30),
    (2,31),
    (2,35),
    (2,36),
    (2,37);
	
-- Thêm điểm
INSERT INTO diem(sinh_vien_id, mon_hoc_id, hoc_ky_id, loai, diem)
VALUES
	-- Tô Quốc Bình
	(19,1,1, "GIUA_KY",9),
    (19,1,1, "CUOI_KY",7.6),
    (19,1,1, "TONG_KET",8.2),
    (19,2,1, "GIUA_KY",6),
    (19,2,1, "CUOI_KY",8),
    (19,2,1, "TONG_KET",7.2),
    (19,3,2, "GIUA_KY",7.6),
    (19,3,2, "CUOI_KY",4.7),
    (19,3,2, "TONG_KET",4.7),
    (19,4,2, "GIUA_KY",4.2),
    (19,4,2, "CUOI_KY",6.5),
    (19,4,2, "TONG_KET",5.6),
    (19,5,3, "GIUA_KY",5.5),
    (19,5,3, "CUOI_KY",4),
    (19,5,3, "TONG_KET",4.8),
    (19,6,3, "GIUA_KY",7.2),
    (19,6,3, "CUOI_KY",7),
    (19,6,3, "TONG_KET",7.1),
    (19,7,3, "GIUA_KY",7.7),
    (19,7,3, "CUOI_KY",9),
    (19,7,3, "TONG_KET",8.5),
    (19,8,4, "GIUA_KY",9.6),
    (19,8,4, "CUOI_KY",9.5),
    (19,8,4, "TONG_KET",9.5),
    (19,9,4, "GIUA_KY",9),
    (19,9,4, "CUOI_KY",8),
    (19,9,4, "TONG_KET",8.4),
    (19,10,4, "GIUA_KY",10),
    (19,10,4, "CUOI_KY",4.3),
    (19,10,4, "TONG_KET",6.6),
    (19,11,5, "GIUA_KY",5.5),
    (19,11,5, "CUOI_KY",8),
    (19,11,5, "TONG_KET",7),
    (19,12,5, "GIUA_KY",8.7),
    (19,12,5, "CUOI_KY",8),
    (19,12,5, "TONG_KET",8.3),
    (19,13,5, "GIUA_KY",7.5),
    (19,13,5, "CUOI_KY",9),
    (19,13,5, "TONG_KET",8.3),
	(19,14,6, "GIUA_KY",5.5),
	(19,14,6, "CUOI_KY",8),
	(19,14,6, "TONG_KET",7),
	(19,15,6, "GIUA_KY",7.9),
	(19,15,6, "CUOI_KY",8),
	(19,15,6, "TONG_KET",8),
	(19,16,7, "GIUA_KY",10),
	(19,16,7, "CUOI_KY",9.8),
	(19,16,7, "TONG_KET",9.9),
	(19,17,7, "GIUA_KY",7.5),
	(19,17,7, "CUOI_KY",6.5),
	(19,17,7, "TONG_KET",6.9),
	(19,18,7, "GIUA_KY",6.5),
	(19,18,7, "CUOI_KY",7),
	(19,18,7, "TONG_KET",6.8),
	(19,19,8, "GIUA_KY",10),
	(19,19,8, "CUOI_KY",4.4),
	(19,19,8, "TONG_KET",7.2),
	(19,20,8, "GIUA_KY",7),
	(19,20,8, "CUOI_KY",6),
	(19,20,8, "TONG_KET",6.4),
	(19,21,8, "GIUA_KY",6.5),
	(19,21,8, "CUOI_KY",7),
	(19,21,8, "TONG_KET",6.8),
    
    -- Trần Huỳnh Sang
    (20,1,1, "GIUA_KY",10),
    (20,1,1, "CUOI_KY",7.4),
    (20,1,1, "TONG_KET",8.4),
    (20,2,1, "GIUA_KY",10),
    (20,2,1, "CUOI_KY",10),
    (20,2,1, "TONG_KET",10),
    (20,3,2, "GIUA_KY",7.6),
    (20,3,2, "CUOI_KY",6.8),
    (20,3,2, "TONG_KET",7),
    (20,4,2, "GIUA_KY",5.1),
    (20,4,2, "CUOI_KY",7),
    (20,4,2, "TONG_KET",6.2),
    (20,5,3, "GIUA_KY",7.6),
    (20,5,3, "CUOI_KY",4),
    (20,5,3, "TONG_KET",5.8),
    (20,6,3, "GIUA_KY",8.3),
    (20,6,3, "CUOI_KY",6.5),
    (20,6,3, "TONG_KET",7.2),
    (20,7,3, "GIUA_KY",8.3),
    (20,7,3, "CUOI_KY",9),
    (20,7,3, "TONG_KET",8.7),
    (20,8,4, "GIUA_KY",9.6),
    (20,8,4, "CUOI_KY",10),
    (20,8,4, "TONG_KET",9.8),
    (20,9,4, "GIUA_KY",8.4),
    (20,9,4, "CUOI_KY",9),
    (20,9,4, "TONG_KET",8.8),
    (20,10,4, "GIUA_KY",5),
    (20,10,4, "CUOI_KY",5),
    (20,10,4, "TONG_KET",5),
    (20,11,5, "GIUA_KY",6),
    (20,11,5, "CUOI_KY",8),
    (20,11,5, "TONG_KET",7.2),
    (20,12,5, "GIUA_KY",8.7),
    (20,12,5, "CUOI_KY",8.2),
    (20,12,5, "TONG_KET",8.4),
    (20,13,5, "GIUA_KY",7.5),
    (20,13,5, "CUOI_KY",7.5),
    (20,13,5, "TONG_KET",7.5),
	(20,14,6, "GIUA_KY",6),
	(20,14,6, "CUOI_KY",8),
	(20,14,6, "TONG_KET",7.2),
	(20,15,6, "GIUA_KY",10),
	(20,15,6, "CUOI_KY",6.4),
	(20,15,6, "TONG_KET",8.2),
	(20,16,7, "GIUA_KY",9),
	(20,16,7, "CUOI_KY",8.8),
	(20,16,7, "TONG_KET",8.9),
	(20,17,7, "GIUA_KY",6.5),
	(20,17,7, "CUOI_KY",6),
	(20,17,7, "TONG_KET",6.2),
	(20,18,7, "GIUA_KY",7),
	(20,18,7, "CUOI_KY",7),
	(20,18,7, "TONG_KET",7),
	(20,19,8, "GIUA_KY",10),
	(20,19,8, "CUOI_KY",6.4),
    (20,19,8, "TONG_KET",8.2),
	(20,20,8, "GIUA_KY",7),
	(20,20,8, "CUOI_KY",5.5),
	(20,20,8, "TONG_KET",6.1),
	(20,21,8, "GIUA_KY",5.5),
	(20,21,8, "CUOI_KY",7),
	(20,21,8, "TONG_KET",6.4),
    
    -- Nguyễn Đăng Khôi
    (21,1,1, "GIUA_KY",8),
    (21,1,1, "CUOI_KY",3.9),
    (21,1,1, "TONG_KET",5.5),
    (21,2,1, "GIUA_KY",5),
    (21,2,1, "CUOI_KY",2),
    (21,2,1, "TONG_KET",3.2),
    (21,3,2, "GIUA_KY",6),
    (21,3,2, "CUOI_KY",0.5),
    (21,3,2, "TONG_KET",2.7),
    (21,4,2, "GIUA_KY",4.5),
    (21,4,2, "CUOI_KY",6),
    (21,4,2, "TONG_KET",5.4),
    (21,5,3, "GIUA_KY",5.1),
    (21,5,3, "CUOI_KY",3.3),
    (21,5,3, "TONG_KET",4.2),
    (21,6,3, "GIUA_KY",6.5),
    (21,6,3, "CUOI_KY",4.8),
    (21,6,3, "TONG_KET",5.5),
    (21,7,3, "GIUA_KY",8.2),
    (21,7,3, "CUOI_KY",6),
    (21,7,3, "TONG_KET",6.9),
    (21,8,4, "GIUA_KY",9.6),
    (21,8,4, "CUOI_KY",8),
    (21,8,4, "TONG_KET",8.6),
    (21,9,4, "GIUA_KY",6),
    (21,9,4, "CUOI_KY",4.1),
    (21,9,4, "TONG_KET",4.9),
    (21,10,4, "GIUA_KY",9),
    (21,10,4, "CUOI_KY",2),
    (21,10,4, "TONG_KET",4.8),
    (21,11,5, "GIUA_KY",2.5),
    (21,11,5, "CUOI_KY",1.5),
    (21,11,5, "TONG_KET",1.9),
    (21,12,5, "GIUA_KY",8.5),
    (21,12,5, "CUOI_KY",8),
    (21,12,5, "TONG_KET",8.2),
    (21,13,5, "GIUA_KY",7.5),
    (21,13,5, "CUOI_KY",3),
    (21,13,5, "TONG_KET",5.3),
	(21,14,6, "GIUA_KY",2.5),
	(21,14,6, "CUOI_KY",1.5),
	(21,14,6, "TONG_KET",1.9),
	(21,15,6, "GIUA_KY",10),
	(21,15,6, "CUOI_KY",4.4),
	(21,15,6, "TONG_KET",7.2),
	(21,16,7, "GIUA_KY",8.5),
	(21,16,7, "CUOI_KY",8.8),
	(21,16,7, "TONG_KET",8.7),
	(21,17,7, "GIUA_KY",6.5),
	(21,17,7, "CUOI_KY",2),
	(21,17,7, "TONG_KET",3.8),
	(21,18,7, "GIUA_KY",3),
	(21,18,7, "CUOI_KY",5),
	(21,18,7, "TONG_KET",4.2),
	(21,19,8, "GIUA_KY",8.5),
	(21,19,8, "CUOI_KY",4.8),
	(21,19,8, "TONG_KET",6.3),
	(21,20,8, "GIUA_KY",6),
	(21,20,8, "CUOI_KY",4.8),
	(21,20,8, "TONG_KET",5.3),
	(21,21,8, "GIUA_KY",3),
	(21,21,8, "CUOI_KY",5.5),
	(21,21,8, "TONG_KET",4.5),
    
    -- Võ Quốc Bảo
    (22,1,7, "GIUA_KY",6),
    (22,1,7, "CUOI_KY",9),
    (22,1,7, "TONG_KET",7.8),
    (22,2,7, "GIUA_KY",9),
    (22,2,7, "CUOI_KY",9.5),
    (22,2,7, "TONG_KET",9.3),
    (22,3,8, "GIUA_KY",9.9),
    (22,3,8, "CUOI_KY",7.6),
    (22,3,8, "TONG_KET",8.3),
    (22,4,8, "GIUA_KY",9.5),
    (22,4,8, "CUOI_KY",8),
    (22,4,8, "TONG_KET",8.6);
    -- (21,22,9, "GIUA_KY",6.5),
--     (21,22,9, "CUOI_KY",4.5),
--     (21,22,9, "TONG_KET",5.3),
--     (21,23,9, "GIUA_KY",9),
--     (21,23,9, "CUOI_KY",5.5),
--     (21,23,9, "TONG_KET",6.9),
--     (21,10,9, "GIUA_KY",10),
--     (21,10,9, "CUOI_KY",3.3),
--     (21,10,9, "TONG_KET",6),
--     (21,8,10, "GIUA_KY",10),
--     (21,8,10, "CUOI_KY",10),
--     (21,8,10, "TONG_KET",10),
--     (21,11,10, "GIUA_KY",7.5),
--     (21,11,10, "CUOI_KY",7),
--     (21,11,10, "TONG_KET",7.2),
--     (21,24,10, "GIUA_KY",8),
--     (21,24,10, "CUOI_KY",8),
--     (21,24,10, "TONG_KET",8),
--     (21,7,11, "GIUA_KY",9.3),
--     (21,7,11, "CUOI_KY",9.3),
--     (21,7,11, "TONG_KET",9.3),
--     (21,25,11, "GIUA_KY",9.8),
--     (21,25,11, "CUOI_KY",8),
--     (21,25,11, "TONG_KET",8.9),
--     (21,12,11, "GIUA_KY",9.2),
--     (21,12,11, "CUOI_KY",8.4),
--     (21,12,11, "TONG_KET",8.7);
    
-- Thêm điểm học lại lần 2
INSERT INTO diem(sinh_vien_id, mon_hoc_id, hoc_ky_id, lan_hoc, loai, diem)
VALUES
	-- Nguyễn Đăng Khôi
	(21,3,8,2,"GIUA_KY",3),
    (21,3,8,2,"CUOI_KY",5.8),
    (21,3,8,2,"TONG_KET",4.7);
    
-- Thêm buổi học
INSERT INTO buoi_hoc(mon_hoc_id, giang_vien_id, hoc_ky_id, si_so, loai)
VALUES
	(1,14,1,10,'LT-TH'),
    (2,4,1,10,'LT-TH'),
    (3,6,2,10,'LT'),
    (4,2,2,10,'LT-TH'),
    (5,5,3,10,'LT-TH'),
    (6,2,3,10,'LT-TH'),
    (7,7,3,10,'LT-TH'),
    (8,17,4,10,'LT'),
    (9,3,4,10,'LT-TH'),
    (10,5,4,10,'LT-TH'),
    (11,10,5,10,'LT-TH'),
    (12,4,5,10,'LT'),
    (13,9,5,10,'LT'),
    (14,10,6,10,'LT-TH'),
    (15,12,6,10,'LT-TH'),
    (16,11,7,10,'LT-TH'),
    (17,10,7,10,'LT-TH'),
    (18,10,7,10,'LT-TH'),
    (19,12,8,10,'LT-TH'),
    (20,13,8,10,'LT-TH'),
    (21,10,8,10,'LT-TH'),
    
    (1,13,7,10,'LT-TH'),
    (2,18,7,10,'LT-TH'),
    (3,6,8,10,'LT'),
    (4,18,8,10,'LT-TH');
	-- (13,9,12,10,'LT'),
--     (26,11,12,10,'LT-TH'),
--     (14,10,12,10,'LT-TH');
    
INSERT INTO lich_hoc(buoi_hoc_id, thu, gio_bat_dau, gio_ket_thuc, ngay_bat_dau, ngay_ket_thuc, phong_hoc_id, loai, lan)
VALUES
	(1, "Thứ 2", "07:30:00", "11:30:00", "2022-10-10", "2022-11-28", 8, 'LyThuyet', 1),
    (1, "Thứ 2", "13:00:00", "17:00:00", "2022-10-10", "2022-11-14", 15, 'ThucHanh', 1),
    (2, "Thứ 4", "07:30:00", "11:30:00", "2022-10-12", "2022-12-28", 5, 'LyThuyet', 1),
    (2, "Thứ 4", "13:00:00", "17:00:00", "2022-10-12", "2022-11-16", 17, 'ThucHanh', 1),
    (3, "Thứ 2", "07:30:00", "11:30:00", "2023-02-06", "2023-04-24", 5, 'LyThuyet', 1),
    (4, "Thứ 5", "07:30:00", "11:30:00", "2023-02-09", "2023-04-27", 5, 'LyThuyet', 1),
    (4, "Thứ 5", "13:00:00", "17:00:00", "2023-02-09", "2023-03-16", 17, 'ThucHanh', 1),
    (5, "Thứ 4", "07:30:00", "11:30:00", "2023-06-07", "2023-07-26", 5, 'LyThuyet', 1),
    (5, "Thứ 4", "13:00:00", "17:00:00", "2023-06-07", "2023-07-12", 17, 'ThucHanh', 1),
    (6, "Thứ 2", "07:30:00", "11:30:00", "2023-06-05", "2023-08-21", 5, 'LyThuyet', 1),
    (6, "Thứ 2", "13:00:00", "17:00:00", "2023-06-05", "2023-07-10", 17, 'ThucHanh', 1),
    (7, "Thứ 7", "07:30:00", "11:30:00", "2023-06-10", "2023-08-26", 5, 'LyThuyet', 1),
    (7, "Thứ 7", "13:00:00", "17:00:00", "2023-06-10", "2023-07-15", 17, 'ThucHanh', 1),
    (8, "Thứ 4", "07:30:00", "11:30:00", "2023-10-04", "2024-01-17", 5, 'LyThuyet', 1),
    (9, "Thứ 3", "07:30:00", "11:30:00", "2023-10-03", "2023-12-05", 5, 'LyThuyet', 1),
    (9, "Thứ 3", "13:00:00", "17:00:00", "2023-10-03", "2023-11-07", 17, 'ThucHanh', 1),
    (10, "Thứ 6", "07:30:00", "11:30:00", "2023-10-06", "2023-12-22", 5, 'LyThuyet', 1),
    (10, "Thứ 6", "13:00:00", "17:00:00", "2023-10-06", "2023-11-10", 17, 'ThucHanh', 1),
    (11, "Thứ 6", "07:30:00", "11:30:00", "2024-02-09", "2024-04-26", 5, 'LyThuyet', 1),
    (11, "Thứ 6", "13:00:00", "17:00:00", "2024-02-09", "2024-03-15", 17, 'ThucHanh', 1),
    (12, "Thứ 3", "07:30:00", "11:30:00", "2024-02-06", "2024-04-23", 5, 'LyThuyet', 1),
    (13, "Thứ 4", "07:30:00", "11:30:00", "2024-02-07", "2024-05-22", 5, 'LyThuyet', 1),
    (14, "Thứ 5", "07:30:00", "11:30:00", "2024-06-13", "2024-08-01", 5, 'LyThuyet', 1),
    (14, "Thứ 5", "13:00:00", "17:00:00", "2024-06-13", "2024-07-18", 17, 'ThucHanh', 1),
    (15, "Thứ 3", "07:30:00", "11:30:00", "2024-06-11", "2024-07-30", 5, 'LyThuyet', 1),
    (15, "Thứ 3", "13:00:00", "17:00:00", "2024-06-11", "2024-07-16", 17, 'ThucHanh', 1),
    (16, "Thứ 2", "07:30:00", "11:30:00", "2024-10-07", "2024-11-25", 5, 'LyThuyet', 1),
    (16, "Thứ 2", "13:00:00", "17:00:00", "2024-10-07", "2024-11-11", 17, 'ThucHanh', 1),
    (17, "Thứ 7", "07:30:00", "11:30:00", "2024-10-12", "2024-11-30", 5, 'LyThuyet', 1),
    (17, "Thứ 7", "13:00:00", "17:00:00", "2024-10-12", "2024-11-16", 17, 'ThucHanh', 1),
    (18, "Chủ nhật", "07:30:00", "11:30:00", "2024-10-13", "2024-12-01", 5, 'LyThuyet', 1),
    (18, "Chủ nhật", "13:00:00", "17:00:00", "2024-10-13", "2024-11-17", 17, 'ThucHanh', 1),
    (19, "Thứ 3", "07:30:00", "11:30:00", "2025-02-25", "2025-04-15", 5, 'LyThuyet', 1),
    (19, "Thứ 3", "13:00:00", "17:00:00", "2025-02-25", "2025-04-01", 17, 'ThucHanh', 1),
    (20, "Thứ 4", "07:30:00", "11:30:00", "2025-02-26", "2025-04-16", 5, 'LyThuyet', 1),
    (20, "Thứ 4", "13:00:00", "17:00:00", "2025-02-26", "2025-04-02", 17, 'ThucHanh', 1),
    (21, "Thứ 6", "07:30:00", "11:30:00", "2025-02-28", "2025-04-18", 5, 'LyThuyet', 1),
    (21, "Thứ 6", "13:00:00", "17:00:00", "2025-02-28", "2025-04-04", 17, 'ThucHanh', 1),
    
    (22, "Thứ 3", "07:30:00", "11:30:00", "2024-10-08", "2024-11-26", 5, 'LyThuyet', 1),
    (22, "Thứ 3", "13:00:00", "17:00:00", "2024-10-08", "2024-11-12", 17, 'ThucHanh', 1),
    (23, "Thứ 5", "07:30:00", "11:30:00", "2024-10-10", "2024-12-26", 5, 'LyThuyet', 1),
    (23, "Thứ 5", "13:00:00", "17:00:00", "2024-10-10", "2024-11-14", 17, 'ThucHanh', 1),
    (24, "Thứ 7", "07:30:00", "11:30:00", "2025-02-15", "2025-05-03", 5, 'LyThuyet', 1),
    (24, "Thứ 6", "07:30:00", "11:30:00", "2025-02-15", "2025-05-03", 5, 'LyThuyet', 2),
    (25, "Thứ 2", "07:30:00", "11:30:00", "2025-02-10", "2025-04-28", 5, 'LyThuyet', 1),
    (25, "Thứ 2", "13:00:00", "17:00:00", "2025-02-10", "2025-03-17", 17, 'ThucHanh', 1);
    
    -- (2, "Thứ 3", "07:30:00", "11:30:00", "2023-10-10", "2023-11-14", 10, 'LyThuyet'),
--     (2, "Thứ 3", "13:00:00", "17:00:00", "2023-10-10", "2023-11-14", 14, 'ThucHanh'),
--     (4, "Thứ 5", "07:30:00", "11:30:00", "2023-10-12", "2023-12-07", 1, 'LyThuyet'),
--     (4, "Thứ 5", "13:00:00", "17:00:00", "2023-10-12", "2023-11-16", 13, 'ThucHanh');

	-- (3, "Thứ 4", "13:00:00", "17:00:00", "2025-10-08", "2025-12-24", 9, 'LyThuyet'),
--     (4, "Thứ 2", "07:30:00", "11:30:00", "2025-10-06", "2025-11-10", 7, 'LyThuyet'),
--     (4, "Thứ 2", "13:00:00", "17:00:00", "2025-10-06", "2025-11-10", 16, 'ThucHanh'),
--     (5, "Thứ 6", "13:00:00", "17:00:00", "2025-10-10", "2025-11-14", 5, 'LyThuyet'),
--     (5, "Thứ 6", "07:30:00", "11:30:00", "2025-10-10", "2025-11-14", 14, 'ThucHanh');
    
INSERT INTO dang_ky(sinh_vien_id, buoi_hoc_id, hoc_ky_id, trang_thai)
VALUES
	-- Tô Quốc Bình
	(19, 1, 1, "HOAN_THANH"),
    (19, 2, 1, "HOAN_THANH"),
    (19, 3, 2, "HOAN_THANH"),
    (19, 4, 2, "HOAN_THANH"),
    (19, 5, 3, "HOAN_THANH"),
    (19, 6, 3, "HOAN_THANH"),
    (19, 7, 3, "HOAN_THANH"),
    (19, 8, 4, "HOAN_THANH"),
    (19, 9, 4, "HOAN_THANH"),
    (19, 10, 4, "HOAN_THANH"),
    (19, 11, 5, "HOAN_THANH"),
    (19, 12, 5, "HOAN_THANH"),
    (19, 13, 5, "HOAN_THANH"),
    (19, 14, 6, "HOAN_THANH"),
    (19, 15, 6, "HOAN_THANH"),
    (19, 16, 7, "HOAN_THANH"),
    (19, 17, 7, "HOAN_THANH"),
    (19, 18, 7, "HOAN_THANH"),
    (19, 19, 8, "HOAN_THANH"),
    (19, 20, 8, "HOAN_THANH"),
    (19, 21, 8, "HOAN_THANH"),
    
    -- Trần Huỳnh Sang
	(20, 1, 1, "HOAN_THANH"),
    (20, 2, 1, "HOAN_THANH"),
    (20, 3, 2, "HOAN_THANH"),
    (20, 4, 2, "HOAN_THANH"),
    (20, 5, 3, "HOAN_THANH"),
    (20, 6, 3, "HOAN_THANH"),
    (20, 7, 3, "HOAN_THANH"),
    (20, 8, 4, "HOAN_THANH"),
    (20, 9, 4, "HOAN_THANH"),
    (20, 10, 4, "HOAN_THANH"),
    (20, 11, 5, "HOAN_THANH"),
    (20, 12, 5, "HOAN_THANH"),
    (20, 13, 5, "HOAN_THANH"),
    (20, 14, 6, "HOAN_THANH"),
    (20, 15, 6, "HOAN_THANH"),
    (20, 16, 7, "HOAN_THANH"),
    (20, 17, 7, "HOAN_THANH"),
    (20, 18, 7, "HOAN_THANH"),
    (20, 19, 8, "HOAN_THANH"),
    (20, 20, 8, "HOAN_THANH"),
    (20, 21, 8, "HOAN_THANH"),
    
    -- Nguyễn Đăng Khôi
	(21, 1, 1, "HOAN_THANH"),
    (21, 2, 1, "TRUOT"),
    (21, 3, 2, "TRUOT"),
    (21, 4, 2, "HOAN_THANH"),
    (21, 5, 3, "HOAN_THANH"),
    (21, 6, 3, "HOAN_THANH"),
    (21, 7, 3, "HOAN_THANH"),
    (21, 8, 4, "HOAN_THANH"),
    (21, 9, 4, "HOAN_THANH"),
    (21, 10, 4, "HOAN_THANH"),
    (21, 11, 5, "TRUOT"),
    (21, 12, 5, "HOAN_THANH"),
    (21, 13, 5, "HOAN_THANH"),
    (21, 14, 6, "TRUOT"),
    (21, 15, 6, "HOAN_THANH"),
    (21, 16, 7, "HOAN_THANH"),
    (21, 17, 7, "TRUOT"),
    (21, 18, 7, "HOAN_THANH"),
    (21, 19, 8, "HOAN_THANH"),
    (21, 20, 8, "HOAN_THANH"),
    (21, 21, 8, "HOAN_THANH"),
    
    (21, 24, 8, "HOAN_THANH"),
    
    -- Võ Quốc Bảo
	(22, 22, 7, "HOAN_THANH"),
    (22, 23, 7, "HOAN_THANH"),
    (22, 24, 8, "HOAN_THANH"),
    (22, 25, 8, "HOAN_THANH");
    
INSERT INTO thoi_khoa_bieu(sinh_vien_id, lich_hoc_id)
VALUES
	-- Tô Quốc Bình
	(19, 1),
    (19, 2),
    (19, 3),
    (19, 4),
    (19, 5),
    (19, 6),
    (19, 7),
    (19, 8),
    (19, 9),
    (19, 10),
    (19, 11),
    (19, 12),
    (19, 13),
    (19, 14),
    (19, 15),
    (19, 16),
    (19, 17),
    (19, 18),
    (19, 19),
    (19, 20),
    (19, 21),
    (19, 22),
    (19, 23),
    (19, 24),
    (19, 25),
    (19, 26),
    (19, 27),
    (19, 28),
    (19, 29),
    (19, 30),
    (19, 31),
    (19, 32),
    (19, 33),
    (19, 34),
    (19, 35),
    (19, 36),
    (19, 37),
    (19, 38),
    
    -- Trần Huỳnh Sang
    (20, 1),
    (20, 2),
    (20, 3),
    (20, 4),
    (20, 5),
    (20, 6),
    (20, 7),
    (20, 8),
    (20, 9),
    (20, 10),
    (20, 11),
    (20, 12),
    (20, 13),
    (20, 14),
    (20, 15),
    (20, 16),
    (20, 17),
    (20, 18),
    (20, 19),
    (20, 20),
    (20, 21),
    (20, 22),
    (20, 23),
    (20, 24),
    (20, 25),
    (20, 26),
    (20, 27),
    (20, 28),
    (20, 29),
    (20, 30),
    (20, 31),
    (20, 32),
    (20, 33),
    (20, 34),
    (20, 35),
    (20, 36),
    (20, 37),
    (20, 38),
    
    -- Nguyễn Đăng Khôi
    (21, 1),
    (21, 2),
    (21, 3),
    (21, 4),
    (21, 5),
    (21, 6),
    (21, 7),
    (21, 8),
    (21, 9),
    (21, 10),
    (21, 11),
    (21, 12),
    (21, 13),
    (21, 14),
    (21, 15),
    (21, 16),
    (21, 17),
    (21, 18),
    (21, 19),
    (21, 20),
    (21, 21),
    (21, 22),
    (21, 23),
    (21, 24),
    (21, 25),
    (21, 26),
    (21, 27),
    (21, 28),
    (21, 29),
    (21, 30),
    (21, 31),
    (21, 32),
    (21, 33),
    (21, 34),
    (21, 35),
    (21, 36),
    (21, 37),
    (21, 38),
    
    (21, 43),
    
	-- Võ Quốc Bảo
    (22, 39),
    (22, 40),
    (22, 41),
    (22, 42),
    (22, 43),
    (22, 44),
    (22, 45);