-- HỌC KỲ
CREATE TABLE hoc_ky (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ky VARCHAR(20) NOT NULL,
    nam_hoc VARCHAR(20) NOT NULL,
    ngay_bat_dau DATE NOT NULL,
    ngay_ket_thuc DATE NOT NULL,
    UNIQUE(ky, nam_hoc)
);

-- PHÒNG
CREATE TABLE phong_hoc (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ten_phong VARCHAR(20) NOT NULL UNIQUE,
    loai ENUM('LyThuyet', 'ThucHanh') NOT NULL,
    UNIQUE(ten_phong, loai)
);

-- KHOA 
CREATE TABLE khoa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_khoa VARCHAR(100) NOT NULL UNIQUE
);

-- NGÀNH
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
    nganh_id INT NOT NULL,
    khoa_hoc INT,
    FOREIGN KEY (nganh_id) REFERENCES nganh(id),
    UNIQUE (ma_lop, nganh_id, khoa_hoc)
);

-- Người Dùng
CREATE TABLE nguoi_dung (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100) NOT NULL,
    ngay_sinh DATE,
    gioi_tinh ENUM('nam', 'nu'),
    so_dien_thoai VARCHAR(15),
    email VARCHAR(100) NOT NULL UNIQUE,
    cccd VARCHAR(15),
    mat_khau VARCHAR(255) NOT NULL,
    avatar VARCHAR(255),
    vai_tro ENUM('ROLE_SINH_VIEN', 'ROLE_GIANG_VIEN', 'ROLE_ADMIN') NOT NULL
);

-- Sinh Viên
CREATE TABLE sinh_vien (
    id INT PRIMARY KEY,  -- Trùng với ID người dùng
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

-- Giảng Viên
CREATE TABLE giang_vien (
    id INT PRIMARY KEY,  -- Trùng với ID người dùng
    hoc_vi VARCHAR(100),
    khoa_id INT NOT NULL,
    FOREIGN KEY (id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id)
);

-- MÔN HỌC
CREATE TABLE mon_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_mon VARCHAR(100) NOT NULL,
    tin_chi_ly_thuyet INT NOT NULL,
    tin_chi_thuc_hanh INT NOT NULL,
    phan_tram_giua_ky INT NOT NULL DEFAULT 30,
    phan_tram_cuoi_ky INT NOT NULL DEFAULT 70,
    diem_qua_mon DECIMAL(4,2) NOT NULL DEFAULT 5.0,
    de_cuong VARCHAR(255),
    khoa_id INT NOT NULL,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id)
);

-- MÔN TIÊN QUYẾT
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

-- NGÀNH MÔN HỌC
CREATE TABLE nganh_mon_hoc (
    nganh_id INT NOT NULL,
    mon_hoc_id INT NOT NULL,
    ky INT NOT NULL,
    PRIMARY KEY (nganh_id, mon_hoc_id),
    FOREIGN KEY (nganh_id) REFERENCES nganh(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id)
);

-- ĐIỂM 
CREATE TABLE diem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinh_vien_id INT NOT NULL,
    giang_vien_id INT NOT NULL,
    mon_hoc_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    lan_hoc INT NOT NULL DEFAULT 1,
    loai ENUM('GIUA_KY', 'CUOI_KY', 'TONG_KET') NOT NULL,
    diem DECIMAL(4,2) CHECK (diem BETWEEN 0 AND 10),
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (giang_vien_id) REFERENCES giang_vien(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (sinh_vien_id, mon_hoc_id, lan_hoc, loai)
);

-- BUỔI HỌC
CREATE TABLE buoi_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mon_hoc_id INT NOT NULL,
    giang_vien_id INT,
    hoc_ky_id INT NOT NULL,
    si_so INT DEFAULT 50,
    loai ENUM('LT', 'TH', 'LT-TH'),
    lop_id INT NOT NULL,
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (giang_vien_id) REFERENCES giang_vien(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    FOREIGN KEY (lop_id) REFERENCES lop(id),
    UNIQUE (mon_hoc_id, hoc_ky_id, lop_id)
);

-- TIẾT HỌC
CREATE TABLE tiet_hoc (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tiet VARCHAR(10) NOT NULL,
    gio_bat_dau TIME NOT NULL,
    gio_ket_thuc TIME NOT NULL
);

-- LỊCH HỌC
CREATE TABLE lich_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    buoi_hoc_id INT NOT NULL,
    thu VARCHAR(20),
    tiet_hoc_id INT NOT NULL,
    ngay_bat_dau DATE NOT NULL,
    ngay_ket_thuc DATE NOT NULL,
    phong_hoc_id INT NOT NULL,
    loai ENUM('LyThuyet', 'ThucHanh'),
    FOREIGN KEY (buoi_hoc_id) REFERENCES buoi_hoc(id),
    FOREIGN KEY (tiet_hoc_id) REFERENCES tiet_hoc(id),
    FOREIGN KEY (phong_hoc_id) REFERENCES phong_hoc(id),
    unique (thu, tiet_hoc_id, ngay_bat_dau, phong_hoc_id)
);

-- ĐĂNG KÝ MÔN HỌC
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
    sinh_vien_id INT NOT NULL,
    lich_hoc_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (lich_hoc_id) REFERENCES lich_hoc(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (sinh_vien_id, lich_hoc_id)
);

-- HỌC PHÍ
CREATE TABLE hoc_phi (
	id INT PRIMARY KEY AUTO_INCREMENT,
    sinh_vien_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    tong_tien DECIMAL(18,2),
    trang_thai ENUM('DA_THANH_TOAN', 'CHUA_THANH_TOAN') DEFAULT 'CHUA_THANH_TOAN',
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (sinh_vien_id, hoc_ky_id)
);

-- CHI TIẾT HỌC PHÍ
CREATE TABLE chi_tiet_hoc_phi (
	id INT PRIMARY KEY AUTO_INCREMENT,
    hoc_phi_id INT NOT NULL,
    mon_hoc_id INT NOT NULL,
    chi_phi DECIMAL(18,2),
    FOREIGN KEY (hoc_phi_id) REFERENCES hoc_phi(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    UNIQUE (hoc_phi_id, mon_hoc_id)
);

-- QUY ĐỊNH
CREATE TABLE quy_dinh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(100) UNIQUE NOT NULL,
    gia_tri INT NOT NULL
);

-- ========================
-- DỮ LIỆU MẪU
-- ========================
INSERT INTO quy_dinh (ten, gia_tri) VALUES 
    ('Số tiền 1 tín chỉ', 700000),
    ('Số tín chỉ tối đa', 24),
    ('Số buổi 1 tín chỉ lý thuyết', 3),
    ('Số buổi 1 tín chỉ thực hành', 5),
    ('Số tuần 1 học kỳ', 15);

-- Thêm học kỳ
-- Kỳ 1: 10,11,12,1
-- Kỳ 2: 2,3,4,5
-- kỳ 3: 6,7,8,9
INSERT INTO hoc_ky (ky, nam_hoc, ngay_bat_dau, ngay_ket_thuc)
VALUES
	('Học kỳ 1', '2022-2023', "2022-10-03", "2023-01-15"),
    ('Học kỳ 2', '2022-2023', "2023-02-06", "2023-05-21"),
    ('Học kỳ 3', '2022-2023', "2023-06-05", "2023-09-17"),
    ('Học kỳ 1', '2023-2024', "2023-10-16", "2024-01-28"),
    ('Học kỳ 2', '2023-2024', "2024-02-26", "2024-06-09"),
    ('Học kỳ 3', '2023-2024', "2024-06-17", "2024-09-29"),
    ('Học kỳ 1', '2024-2025', "2024-10-07", "2025-01-19"),
    ('Học kỳ 2', '2024-2025', "2025-02-10", "2025-05-25"),
    ('Học kỳ 3', '2024-2025', "2025-06-09", "2025-09-21"),
    ('Học kỳ 1', '2025-2026', "2025-10-06", "2026-01-18");

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
INSERT INTO lop (ma_lop, si_so, khoa_hoc, nganh_id)
VALUES
	("DH22CS01", 10, 2022, 1),
    ("DH24IT01", 10, 2024, 3),
    ("DH24CS01", 10, 2024, 1),
    ("DH24IM01", 10, 2024, 2),
    ("DH24IT02", 10, 2024, 3),
    
    ("DH25CS01", 10, 2025, 1),
    ("DH25IT01", 10, 2025, 3),
    ("DH25IM01", 10, 2025, 2);

-- ==========================
-- THÊM GIẢNG VIÊN
-- ==========================
INSERT INTO nguoi_dung (ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, email, cccd, mat_khau, avatar, vai_tro) VALUES
    ('Nguyễn Văn Ón', '1975-10-07', 'nam', '0123456789', 'onnguyenvan@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Trần Thị Tuyết', '1975-10-07', 'nu', '0123456789', 'tuyettranthi@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Lê Minh Bảo', '1975-10-07', 'nam', '0123456789', 'baoleminh@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Hữu Khuê', '1975-10-07', 'nam', '0123456789', 'khuenguyenhuu@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Phạm Minh Trang', '1975-10-07', 'nu', '0123456789', 'trangphamminh@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Đỗ Quang Hùng', '1975-10-07', 'nam', '0123456789', 'hungdoquang@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Phùng Thị Anh', '1975-10-07', 'nu', '0123456789', 'anhphungthi@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Võ Văn Kiệt', '1975-10-07', 'nam', '0123456789', 'kietvovan@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Dương Minh Công', '1975-10-07', 'nam', '0123456789', 'congminhduong@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Lộc Thành', '1975-10-07', 'nam', '0123456789', 'thanhnguenloc@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Trần Tuấn Khải', '1975-10-07', 'nam', '0123456789', 'khaitrantuan@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Đỗ Kim Tuấn', '1975-10-07', 'nam', '0123456789', 'tuandokim@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Trần Hà Thanh', '1975-10-07', 'nu', '0123456789', 'thanhtranha@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Quốc Thiên', '1975-10-07', 'nam', '0123456789', 'thiennguyenquoc@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Nguyễn Phương Trang', '1975-10-07', 'nu', '0123456789', 'trangnguyephuong@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Ngô Tiến Đạt', '1975-10-07', 'nam', '0123456789', 'datngotien@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Trương Trí Kiệt', '1975-10-07', 'nam', '0123456789', 'kiettruongtri@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN'),
    ('Ngô Ngọc Hậu', '1975-10-07', 'nam', '0123456789', 'haungongoc@giangvien.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_GIANG_VIEN');

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
INSERT INTO nguoi_dung (ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, email, cccd, mat_khau, avatar, vai_tro) VALUES
    ('Tô Quốc Bình', '2004-02-21', 'nam', '0762590966', 'binh@student.edu.vn', '079204036719', '$2a$10$ovRvIJrlNrhHGxX2140Lj.0OyoPdePXeU/.tls11mdwOuWefISv0O', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_SINH_VIEN'),
    ('Trần Huỳnh Sang', '2004-03-24', 'nam', '0762514230', 'sang@student.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_SINH_VIEN'),
    ('Nguyễn Đăng Khôi', '2004-05-27', 'nam', '0903234139', 'khoi@student.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_SINH_VIEN'),
    ('Võ Quốc Bảo', '2006-05-15', 'nam', '0789764093', 'bao@student.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_SINH_VIEN'),
    ('Trần Quốc Phong', '2006-05-15', 'nam', '0789764094', 'phong@student.edu.vn', '012345678912', '$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S', 'https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg', 'ROLE_SINH_VIEN');

INSERT INTO sinh_vien (id, khoa_hoc, so_tin_chi, khoa_id, nganh_id, lop_id) VALUES
    (19, 2022, 75, 1, 1, 1),
    (20, 2022, 75, 1, 1, 1),
    (21, 2022, 75, 1, 1, 1),
    (22, 2024, 25, 1, 3, 2),
    (23, 2024, 25, 1, 3, 5);

-- Thêm admin
INSERT INTO nguoi_dung (ho_ten, email, mat_khau, vai_tro)
VALUES ('Admin Hệ Thống', 'admin@admin.vn', '$2a$10$YJWUKd2V8ZTn/uiiz6UQGOrEmScXYjxJCCQDrLRU3iD3vaYmmjNPm', 'ROLE_ADMIN'); -- admin123

    
-- Thêm môn học
INSERT INTO mon_hoc (ten_mon, tin_chi_ly_thuyet, tin_chi_thuc_hanh, phan_tram_giua_ky, phan_tram_cuoi_ky, diem_qua_mon, de_cuong, khoa_id)
VALUES
('Nhập môn tin học', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/198W-7oDbPwOyywOBJxV43h2r6x-t1FPX/view", 1),
('Cơ sở lập trình', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/1u16wa2QtA9t3uNE4sNmLwzc4xcC4AaPt/view", 1),
('Hệ điều hành và Kiến trúc máy tính', 3, 0, 30, 70, 4, "https://drive.google.com/file/d/1Gu7Fu1TBvQCFOx4_jV7-Hw7vfyQmUX0_/view", 1),
('Kỹ thuật lập trình', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/10HvLCq1H_wIVUEeAVjlX8dBJVDeh5OR7/view", 1),
('Ứng dụng web', 2, 1, 30, 70, 4, "https://drive.google.com/file/d/1u3wyjwELaaKjTZmEBq14ouZ3BowbHWTf/view", 1),
('Cấu trúc dữ liệu và thuật giải 1', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/1MZNVPUn9AT_tDAmkZavLflIFiqurj-K5/view", 1),
('Cơ sở dữ liệu', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/1ugGNJM5ortwEVVTnZKlLStxQQdzehLJT/view", 1),
('Toán rời rạc', 4, 0, 40, 60, 4, "https://drive.google.com/file/d/1kh6LUdul3RO7tCxyTUrqFjBfvAA6nGb2/view", 1),
('Cấu trúc dữ liệu và thuật giải 2', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1ELcy6tS4nGNauM4KK3IgIH-vm2yYiL2h/view", 1),
('Mạng máy tính', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/1HiBtOs0_28rhAy19hs3dIqOk1TLpQJKW/view", 1),
('Lập trình hướng đối tượng', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/1kM7_LaeLQOmm6fnhcy6w7GTbcTwKweZE/view", 1),
('Kỹ năng nghề nghiệp', 3, 0, 40, 60, 4, "https://drive.google.com/file/d/18oELjie0gEm2NVbOFCSzHZq410Sh6QfG/view", 1),
('Phân tích thiết kế hệ thống', 4, 0, 50, 50, 4, "https://drive.google.com/file/d/12O19tePNmdDFrmJw8fBvQJiWJ9jVfwz6/view", 1),
('Mẫu thiết kế hướng đối tượng', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1BWqRmZyZLQpPJFe3agrXnOhJME_7yWNk/view", 1),
('Trí tuệ nhân tạo', 2, 1, 50, 50, 4, "https://drive.google.com/file/d/119fJuQz0efJ8e420KqfELV4KXnZTrqzH/view", 1),
('Quản trị hệ cơ sở dữ liệu', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1U_4mOWDXdZiGO0hJB_PDD-XCjZSd2vhC/view", 1),
('Công nghệ phần mềm', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/10r_eakrX0Ua1iPEA5v6gTbN2Tb6Fkxf9/view", 1),
('Các công nghệ lập trình hiện đại', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1jISbit7fuegHcsYYUc8kP8N3sdbFCfs0/view", 1),
('Máy học', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1nLQ5gDal-P1qQYkXp4QDlUrzWzbDhRlf/view", 1),
('Kiểm thử phần mềm', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1W0Q0vSBdb6DYAbnPMpq6l-bMNi-h6PwO/view", 1),
('Phát triển hệ thống web', 2, 1, 30, 70, 4, "https://drive.google.com/file/d/1wd9pYdZdvO9fXiI3Eis0iu5JpCrRmmbA/view", 1),
('Thiết kế web', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1CdjdfVoqMAQtjxYQOLgZ38XAV6rGaE31/view", 1),
('Cấu trúc dữ liệu và thuật giải', 3, 1, 40, 60, 4, "https://drive.google.com/file/d/1a06bnqxH7B1WbghJlPwv4ByN50kWXXvY/view", 1),
('Công nghệ mã nguồn mở', 2, 1, 50, 50, 4, "https://drive.google.com/file/d/1Ae2rmjuu26eGNwx7bsh4-c2pMwcGFd7R/view", 1),
('Lập trình giao diện', 2, 1, 50, 50, 4, "https://drive.google.com/file/d/11LFZ90i4ZnZkRAnjPb7JsOhYuouyRNW8/view", 1),
('Quản trị mạng', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1P83GF327WR8xe0kBuF4fMPfrjmlJQ4Qb/view", 1),
('An toàn hệ thống thông tin', 2, 1, 40, 60, 4, "https://drive.google.com/file/d/1EDeBU2FwN9Qut2wBsZc3Q8wL4_eb3eEg/view", 1),
('Hệ thống quản lý nguồn lực doanh nghiệp', 2, 1, 50, 50, 4, "https://drive.google.com/file/d/1vjV3GcC4eoW_VoN0CvKRbuSrDuHWlbd4/view", 1),
('Hệ thống thông tin quản lý', 3, 0, 50, 50, 4, "https://drive.google.com/file/d/1wfL9Kt7R1PSmO51L5pe9bgpkK_v5Msg-/view", 1),
('Lập trình cơ sở dữ liệu', 2, 1, 50, 50, 4, "https://drive.google.com/file/d/1ykLOwQRy1hJ1TQtYFdulmb-K_uIHDyJS/view", 1),
('Phát triển hệ thống thông tin quản lý', 3, 0, 50, 50, 4, "https://drive.google.com/file/d/14UbF2kI6-Gys-5FXEYNPxvwxy3s5VJ7V/view", 1),
('Đồ án ngành', 0, 4, 40, 60, 4, "https://drive.google.com/file/d/1l4o1d3awqQ7hM0KGjDrfmZKUqlYJ3BzK/view", 1),
('Thực tập tốt nghiệp', 0, 4, 30, 70, 4, "https://drive.google.com/file/d/1tc_cVh5YPdez6gVUxu_56ZJA9vwYCTh0/view", 1),
('Khóa luận tốt nghiệp', 0, 6, 0, 100, 4, "https://drive.google.com/file/d/1B0uVr9x9w3BAaE7I41rfSFUvzlk0l-DM/view", 1),
('Đồ án ngành Hệ thống thông tin quản lý', 0, 4, 40, 60, 4, "https://drive.google.com/file/d/1oOuMvC4fWjUIflbMrGnBB3LYpvCMPYsy/view", 1),
('Thực tập tốt nghiệp', 0, 4, 30, 70, 4, "https://drive.google.com/file/d/1E0Gp6-qEdRAvlfj9h1ko8ZswhtZmjrX3/view", 1),
('Khóa luận tốt nghiệp', 0, 6, 0, 100, 4, "https://drive.google.com/file/d/1Up69mRDQRcGvdpuD-DEJU6KIkYyYDgll/view", 1);

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
    (11,4,3,'TIEN_QUYET'), --
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
INSERT INTO nganh_mon_hoc(nganh_id, mon_hoc_id, ky)
VALUES
	-- Khoa học máy tính
	(1,1,1),
    (1,2,1),
    (1,3,2),
    (1,4,2),
    (1,5,3),
    (1,6,3),
    (1,7,3),
    (1,8,4),
    (1,9,4),
    (1,10,4),
    (1,11,5),
    (1,12,5),
    (1,13,5),
    (1,14,6),
    (1,15,6),
    (1,16,7),
    (1,17,7),
    (1,18,7),
    (1,19,8),
    (1,20,8),
    (1,21,8),
    (1,32,9),
    (1,33,10),
    (1,34,11),
    -- Công nghệ thông tin
    (3,1,1),
    (3,2,1),
    (3,3,2),
    (3,4,2),
    (3,22,3),
    (3,23,3),
    (3,10,3),
    (3,8,4),
    (3,11,4),
    (3,24,4),
    (3,7,5),
    (3,25,5),
    (3,12,5),
    (3,13,6),
    (3,26,6),
    (3,14,6),
    (3,17,7),
    (3,16,7),
    (3,27,7),
    (3,20,8),
    (3,15,8),
    (3,32,9),
    (3,33,10),
    (3,34,11),
    -- Hệ thống thông tin quản lý
    (2,1,1),
    (2,2,1),
    (2,4,2),
    (2,23,3),
    (2,3,3),
    (2,7,4),
    (2,10,4),
    (2,11,4),
    (2,25,5),
    (2,13,5),
    (2,8,6),
    (2,28,6),
    (2,29,7),
    (2,16,7),
    (2,30,8),
    (2,31,8),
    (2,35,9),
    (2,36,10),
    (2,37,11);
	
-- Thêm điểm
INSERT INTO diem(sinh_vien_id, giang_vien_id, mon_hoc_id, hoc_ky_id, loai, diem)
VALUES
	-- Tô Quốc Bình
	(19,14,1,1, "GIUA_KY",9),
    (19,14,1,1, "CUOI_KY",7.6),
    (19,14,1,1, "TONG_KET",8.2),
    (19,4,2,1, "GIUA_KY",6),
    (19,4,2,1, "CUOI_KY",8),
    (19,4,2,1, "TONG_KET",7.2),
    (19,6,3,2, "GIUA_KY",7.6),
    (19,6,3,2, "CUOI_KY",4.7),
    (19,6,3,2, "TONG_KET",4.7),
    (19,2,4,2, "GIUA_KY",4.2),
    (19,2,4,2, "CUOI_KY",6.5),
    (19,2,4,2, "TONG_KET",5.6),
    (19,15,5,3, "GIUA_KY",5.5),
    (19,15,5,3, "CUOI_KY",4),
    (19,15,5,3, "TONG_KET",4.8),
    (19,2,6,3, "GIUA_KY",7.2),
    (19,2,6,3, "CUOI_KY",7),
    (19,2,6,3, "TONG_KET",7.1),
    (19,7,7,3, "GIUA_KY",7.7),
    (19,7,7,3, "CUOI_KY",9),
    (19,7,7,3, "TONG_KET",8.5),
    (19,17,8,4, "GIUA_KY",9.6),
    (19,17,8,4, "CUOI_KY",9.5),
    (19,17,8,4, "TONG_KET",9.5),
    (19,3,9,4, "GIUA_KY",9),
    (19,3,9,4, "CUOI_KY",8),
    (19,3,9,4, "TONG_KET",8.4),
    (19,15,10,4, "GIUA_KY",10),
    (19,15,10,4, "CUOI_KY",4.3),
    (19,15,10,4, "TONG_KET",6.6),
    (19,10,11,5, "GIUA_KY",5.5),
    (19,10,11,5, "CUOI_KY",8),
    (19,10,11,5, "TONG_KET",7),
    (19,4,12,5, "GIUA_KY",8.7),
    (19,4,12,5, "CUOI_KY",8),
    (19,4,12,5, "TONG_KET",8.3),
    (19,9,13,5, "GIUA_KY",7.5),
    (19,9,13,5, "CUOI_KY",9),
    (19,9,13,5, "TONG_KET",8.3),
	(19,10,14,6, "GIUA_KY",5.5),
	(19,10,14,6, "CUOI_KY",8),
	(19,10,14,6, "TONG_KET",7),
	(19,12,15,6, "GIUA_KY",7.9),
	(19,12,15,6, "CUOI_KY",8),
	(19,12,15,6, "TONG_KET",8),
	(19,11,16,7, "GIUA_KY",10),
	(19,11,16,7, "CUOI_KY",9.8),
	(19,11,16,7, "TONG_KET",9.9),
	(19,10,17,7, "GIUA_KY",7.5),
	(19,10,17,7, "CUOI_KY",6.5),
	(19,10,17,7, "TONG_KET",6.9),
	(19,10,18,7, "GIUA_KY",6.5),
	(19,10,18,7, "CUOI_KY",7),
	(19,10,18,7, "TONG_KET",6.8),
	(19,12,19,8, "GIUA_KY",10),
	(19,12,19,8, "CUOI_KY",4.4),
	(19,12,19,8, "TONG_KET",7.2),
	(19,13,20,8, "GIUA_KY",7),
	(19,13,20,8, "CUOI_KY",6),
	(19,13,20,8, "TONG_KET",6.4),
	(19,10,21,8, "GIUA_KY",6.5),
	(19,10,21,8, "CUOI_KY",7),
	(19,10,21,8, "TONG_KET",6.8),
    (19,12,32,9, "GIUA_KY",7),
	(19,12,32,9, "CUOI_KY",7),
 	(19,12,32,9, "TONG_KET",7),
    
    -- Trần Huỳnh Sang
    (20,14,1,1, "GIUA_KY",10),
    (20,14,1,1, "CUOI_KY",7.4),
    (20,14,1,1, "TONG_KET",8.4),
    (20,4,2,1, "GIUA_KY",10),
    (20,4,2,1, "CUOI_KY",10),
    (20,4,2,1, "TONG_KET",10),
    (20,6,3,2, "GIUA_KY",7.6),
    (20,6,3,2, "CUOI_KY",6.8),
    (20,6,3,2, "TONG_KET",7),
    (20,2,4,2, "GIUA_KY",5.1),
    (20,2,4,2, "CUOI_KY",7),
    (20,2,4,2, "TONG_KET",6.2),
    (20,15,5,3, "GIUA_KY",7.6),
    (20,15,5,3, "CUOI_KY",4),
    (20,15,5,3, "TONG_KET",5.8),
    (20,2,6,3, "GIUA_KY",8.3),
    (20,2,6,3, "CUOI_KY",6.5),
    (20,2,6,3, "TONG_KET",7.2),
    (20,7,7,3, "GIUA_KY",8.3),
    (20,7,7,3, "CUOI_KY",9),
    (20,7,7,3, "TONG_KET",8.7),
    (20,17,8,4, "GIUA_KY",9.6),
    (20,17,8,4, "CUOI_KY",10),
    (20,17,8,4, "TONG_KET",9.8),
    (20,3,9,4, "GIUA_KY",8.4),
    (20,3,9,4, "CUOI_KY",9),
    (20,3,9,4, "TONG_KET",8.8),
    (20,15,10,4, "GIUA_KY",5),
    (20,15,10,4, "CUOI_KY",5),
    (20,15,10,4, "TONG_KET",5),
    (20,10,11,5, "GIUA_KY",6),
    (20,10,11,5, "CUOI_KY",8),
    (20,10,11,5, "TONG_KET",7.2),
    (20,4,12,5, "GIUA_KY",8.7),
    (20,4,12,5, "CUOI_KY",8.2),
    (20,4,12,5, "TONG_KET",8.4),
    (20,9,13,5, "GIUA_KY",7.5),
    (20,9,13,5, "CUOI_KY",7.5),
    (20,9,13,5, "TONG_KET",7.5),
	(20,10,14,6, "GIUA_KY",6),
	(20,10,14,6, "CUOI_KY",8),
	(20,10,14,6, "TONG_KET",7.2),
	(20,12,15,6, "GIUA_KY",10),
	(20,12,15,6, "CUOI_KY",6.4),
	(20,12,15,6, "TONG_KET",8.2),
	(20,11,16,7, "GIUA_KY",9),
	(20,11,16,7, "CUOI_KY",8.8),
	(20,11,16,7, "TONG_KET",8.9),
	(20,10,17,7, "GIUA_KY",6.5),
	(20,10,17,7, "CUOI_KY",6),
	(20,10,17,7, "TONG_KET",6.2),
	(20,10,18,7, "GIUA_KY",7),
	(20,10,18,7, "CUOI_KY",7),
	(20,10,18,7, "TONG_KET",7),
	(20,12,19,8, "GIUA_KY",10),
	(20,12,19,8, "CUOI_KY",6.4),
    (20,12,19,8, "TONG_KET",8.2),
	(20,13,20,8, "GIUA_KY",7),
	(20,13,20,8, "CUOI_KY",5.5),
	(20,13,20,8, "TONG_KET",6.1),
	(20,10,21,8, "GIUA_KY",5.5),
	(20,10,21,8, "CUOI_KY",7),
	(20,10,21,8, "TONG_KET",6.4),
    (20,18,32,9, "GIUA_KY",8),
	(20,18,32,9, "CUOI_KY",8),
	(20,18,32,9, "TONG_KET",8),
    
    -- Nguyễn Đăng Khôi
    (21,14,1,1, "GIUA_KY",8),
    (21,14,1,1, "CUOI_KY",3.9),
    (21,14,1,1, "TONG_KET",5.5),
    (21,4,2,1, "GIUA_KY",5),
    (21,4,2,1, "CUOI_KY",2),
    (21,4,2,1, "TONG_KET",3.2),
    (21,6,3,2, "GIUA_KY",6),
    (21,6,3,2, "CUOI_KY",0.5),
    (21,6,3,2, "TONG_KET",2.7),
    (21,2,4,2, "GIUA_KY",4.5),
    (21,2,4,2, "CUOI_KY",6),
    (21,2,4,2, "TONG_KET",5.4),
    (21,15,5,3, "GIUA_KY",5.1),
    (21,15,5,3, "CUOI_KY",3.3),
    (21,15,5,3, "TONG_KET",4.2),
    (21,2,6,3, "GIUA_KY",6.5),
    (21,2,6,3, "CUOI_KY",4.8),
    (21,2,6,3, "TONG_KET",5.5),
    (21,7,7,3, "GIUA_KY",8.2),
    (21,7,7,3, "CUOI_KY",6),
    (21,7,7,3, "TONG_KET",6.9),
    (21,17,8,4, "GIUA_KY",9.6),
    (21,17,8,4, "CUOI_KY",8),
    (21,17,8,4, "TONG_KET",8.6),
    (21,3,9,4, "GIUA_KY",6),
    (21,3,9,4, "CUOI_KY",4.1),
    (21,3,9,4, "TONG_KET",4.9),
    (21,15,10,4, "GIUA_KY",9),
    (21,15,10,4, "CUOI_KY",2),
    (21,15,10,4, "TONG_KET",4.8),
    (21,10,11,5, "GIUA_KY",2.5),
    (21,10,11,5, "CUOI_KY",1.5),
    (21,10,11,5, "TONG_KET",1.9),
    (21,4,12,5, "GIUA_KY",8.5),
    (21,4,12,5, "CUOI_KY",8),
    (21,4,12,5, "TONG_KET",8.2),
    (21,9,13,5, "GIUA_KY",7.5),
    (21,9,13,5, "CUOI_KY",3),
    (21,9,13,5, "TONG_KET",5.3),
	(21,10,14,6, "GIUA_KY",2.5),
	(21,10,14,6, "CUOI_KY",1.5),
	(21,10,14,6, "TONG_KET",1.9),
	(21,12,15,6, "GIUA_KY",10),
	(21,12,15,6, "CUOI_KY",4.4),
	(21,12,15,6, "TONG_KET",7.2),
	(21,11,16,7, "GIUA_KY",8.5),
	(21,11,16,7, "CUOI_KY",8.8),
	(21,11,16,7, "TONG_KET",8.7),
	(21,10,17,7, "GIUA_KY",6.5),
	(21,10,17,7, "CUOI_KY",2),
	(21,10,17,7, "TONG_KET",3.8),
	(21,10,18,7, "GIUA_KY",3),
	(21,10,18,7, "CUOI_KY",5),
	(21,10,18,7, "TONG_KET",4.2),
	(21,12,19,8, "GIUA_KY",8.5),
	(21,12,19,8, "CUOI_KY",4.8),
	(21,12,19,8, "TONG_KET",6.3),
	(21,13,20,8, "GIUA_KY",6),
	(21,13,20,8, "CUOI_KY",4.8),
	(21,13,20,8, "TONG_KET",5.3),
	(21,10,21,8, "GIUA_KY",3),
	(21,10,21,8, "CUOI_KY",5.5),
	(21,10,21,8, "TONG_KET",4.5),
    (21,11,32,9, "GIUA_KY",7),
	(21,11,32,9, "CUOI_KY",7),
	(21,11,32,9, "TONG_KET",7),
    
    -- Võ Quốc Bảo
    (22,13,1,7, "GIUA_KY",6),
    (22,13,1,7, "CUOI_KY",9),
    (22,13,1,7, "TONG_KET",7.8),
    (22,18,2,7, "GIUA_KY",9),
    (22,18,2,7, "CUOI_KY",9.5),
    (22,18,2,7, "TONG_KET",9.3),
    (22,6,3,8, "GIUA_KY",9.9),
    (22,6,3,8, "CUOI_KY",7.6),
    (22,6,3,8, "TONG_KET",8.3),
    (22,4,4,8, "GIUA_KY",3),
    (22,4,4,8, "CUOI_KY",2),
    (22,4,4,8, "TONG_KET",2.4),
	(22,15,10,9, "GIUA_KY",10),
    (22,15,10,9, "CUOI_KY",3.3),
    (22,15,10,9, "TONG_KET",6),
    (22,15,22,9, "GIUA_KY",6.5),
    (22,15,22,9, "CUOI_KY",4.5),
    (22,15,22,9, "TONG_KET",5.3),
    (22,4,23,9, "GIUA_KY",9),
    (22,4,23,9, "CUOI_KY",5.5),
    (22,4,23,9, "TONG_KET",6.9),
    
    -- Trần Quốc Phong
    (23,13,1,7, "GIUA_KY",6),
    (23,13,1,7, "CUOI_KY",9),
    (23,13,1,7, "TONG_KET",7.8),
    (23,18,2,7, "GIUA_KY",9),
    (23,18,2,7, "CUOI_KY",9.5),
    (23,18,2,7, "TONG_KET",9.3),
    (23,6,3,8, "GIUA_KY",9.9),
    (23,6,3,8, "CUOI_KY",7.6),
    (23,6,3,8, "TONG_KET",8.3),
    (23,4,4,8, "GIUA_KY",8),
    (23,4,4,8, "CUOI_KY",6),
    (23,4,4,8, "TONG_KET",6.8),
	(23,15,10,9, "GIUA_KY",10),
    (23,15,10,9, "CUOI_KY",3.3),
    (23,15,10,9, "TONG_KET",6),
    (23,15,22,9, "GIUA_KY",6.5),
    (23,15,22,9, "CUOI_KY",4.5),
    (23,15,22,9, "TONG_KET",5.3),
    (23,4,23,9, "GIUA_KY",9),
    (23,4,23,9, "CUOI_KY",5.5),
    (23,4,23,9, "TONG_KET",6.9);
    
-- Thêm điểm học lại lần 2
INSERT INTO diem(sinh_vien_id, giang_vien_id, mon_hoc_id, hoc_ky_id, lan_hoc, loai, diem)
VALUES
	-- Nguyễn Đăng Khôi
	(21,6,3,8,2,"GIUA_KY",3),
    (21,6,3,8,2,"CUOI_KY",5.8),
    (21,6,3,8,2,"TONG_KET",4.7);
    
-- Thêm buổi học
INSERT INTO buoi_hoc(mon_hoc_id, giang_vien_id, hoc_ky_id, si_so, loai, lop_id)
VALUES
	-- 2022
	(1,14,1,10,'LT-TH', 1),
    (2,4,1,10,'LT-TH', 1),
    (3,6,2,10,'LT', 1),
    (4,2,2,10,'LT-TH', 1),
    (5,5,3,10,'LT-TH', 1),
    (6,2,3,10,'LT-TH', 1),
    (7,7,3,10,'LT-TH', 1),
    (8,17,4,10,'LT', 1),
    (9,3,4,10,'LT-TH', 1),
    (10,5,4,10,'LT-TH', 1),
    (11,10,5,10,'LT-TH', 1),
    (12,4,5,10,'LT', 1),
    (13,9,5,10,'LT', 1),
    (14,10,6,10,'LT-TH', 1),
    (15,12,6,10,'LT-TH', 1),
    (16,11,7,10,'LT-TH', 1),
    (17,10,7,10,'LT-TH', 1),
    (18,10,7,10,'LT-TH', 1),
    (19,12,8,10,'LT-TH', 1),
    (20,13,8,10,'LT-TH', 1),
    (21,10,8,10,'LT-TH', 1),
    (32,null,9,null,'TH', 1),
    -- buổi học cho kỳ mới 2022
    (33,null,10,null,'TH',1),
    
    -- 2024
    -- 24IT01
    (1,13,7,10,'LT-TH', 2),
    (2,18,7,10,'LT-TH', 2),
    (3,6,8,10,'LT', 2),
    (4,18,8,10,'LT-TH', 2),
    (10,15,9,10,'LT-TH', 2),
    (22,15,9,10,'LT-TH', 2),
    (23,4,9,10,'LT-TH', 2),
    -- 24IT02
    (1,13,7,10,'LT-TH', 5),
    (2,18,7,10,'LT-TH', 5),
    (3,6,8,10,'LT', 5),
    (4,18,8,10,'LT-TH', 5),
    (10,15,9,10,'LT-TH', 5),
    (22,15,9,10,'LT-TH', 5),
    (23,4,9,10,'LT-TH', 5),
    -- buổi học cho kỳ mới 2024
    (8,17,10,10,'LT', 2),
    (8,1,10,10,'LT', 3),
    (11,10,10,10,'LT-TH', 2),
    (11,10,10,10,'LT-TH', 4),
    (24,15,10,10,'LT-TH', 2),
    (24,14,10,10,'LT-TH', 5),
    (8,17,10,10,'LT', 5),
    (11,10,10,10,'LT-TH', 5),
    
    -- Buổi học cho năm 1
    (1,14,10,10,'LT-TH', 6),
    (2,4,10,10,'LT-TH', 6),
    (1,3,10,10,'LT-TH', 7),
    (2,4,10,10,'LT-TH', 7),
    (1,6,10,10,'LT-TH', 8),
    (2,5,10,10,'LT-TH', 8);
    
-- Thêm tiết học
INSERT INTO tiet_hoc(tiet, gio_bat_dau, gio_ket_thuc)
VALUES
	("Tiết 1", "07:30:00", "11:30:00"),
    ("Tiết 2", "13:00:00", "17:00:00");
    
-- Thêm lịch học
INSERT INTO lich_hoc(buoi_hoc_id, thu, tiet_hoc_id, ngay_bat_dau, ngay_ket_thuc, phong_hoc_id, loai)
VALUES
	-- 2022
	(1, "Thứ 2", 1, "2022-10-10", "2022-11-14", 8, 'LyThuyet'),		/*1*/
    (1, "Thứ 2", 2, "2022-10-10", "2022-11-07", 15, 'ThucHanh'),	/*2*/
    (2, "Thứ 4", 1, "2022-10-12", "2022-12-07", 5, 'LyThuyet'),		/*3*/
    (2, "Thứ 4", 2, "2022-10-12", "2022-11-09", 17, 'ThucHanh'),	/*4*/
    (3, "Thứ 2", 1, "2023-02-06", "2023-04-03", 4, 'LyThuyet'),		/*5*/
    (4, "Thứ 5", 1, "2023-02-09", "2023-04-06", 6, 'LyThuyet'),		/*6*/
    (4, "Thứ 5", 2, "2023-02-09", "2023-03-09", 13, 'ThucHanh'),	/*7*/
    (5, "Thứ 4", 1, "2023-06-07", "2023-07-12", 7, 'LyThuyet'),		/*8*/
    (5, "Thứ 4", 2, "2023-06-07", "2023-07-05", 15, 'ThucHanh'),	/*9*/
    (6, "Thứ 2", 1, "2023-06-05", "2023-07-31", 5, 'LyThuyet'),		/*10*/
    (6, "Thứ 2", 2, "2023-06-05", "2023-07-03", 17, 'ThucHanh'),	/*11*/
    (7, "Thứ 7", 1, "2023-06-10", "2023-08-05", 1, 'LyThuyet'),		/*12*/
    (7, "Thứ 7", 2, "2023-06-10", "2023-07-08", 16, 'ThucHanh'),	/*13*/
    (8, "Thứ 4", 1, "2023-10-04", "2023-12-20", 8, 'LyThuyet'),		/*14*/
    (9, "Thứ 3", 1, "2023-10-03", "2023-11-07", 9, 'LyThuyet'),		/*15*/
    (9, "Thứ 3", 2, "2023-10-03", "2023-10-31", 21, 'ThucHanh'),	/*16*/
    (10, "Thứ 6", 1, "2023-10-06", "2023-12-01", 10, 'LyThuyet'),	/*17*/
    (10, "Thứ 6", 2, "2023-10-06", "2023-11-03", 17, 'ThucHanh'),	/*18*/
    (11, "Thứ 6", 1, "2024-02-09", "2024-04-05", 11, 'LyThuyet'),	/*19*/
    (11, "Thứ 6", 2, "2024-02-09", "2024-03-08", 18, 'ThucHanh'),	/*20*/
    (12, "Thứ 3", 1, "2024-02-06", "2024-04-02", 3, 'LyThuyet'),	/*21*/
    (13, "Thứ 4", 1, "2024-02-07", "2024-04-24", 12, 'LyThuyet'),	/*22*/
    (14, "Thứ 5", 1, "2024-06-13", "2024-07-18", 5, 'LyThuyet'),	/*23*/
    (14, "Thứ 5", 2, "2024-06-13", "2024-07-11", 17, 'ThucHanh'),	/*24*/
    (15, "Thứ 3", 1, "2024-06-11", "2024-07-16", 6, 'LyThuyet'),	/*25*/
    (15, "Thứ 3", 2, "2024-06-11", "2024-07-09", 20, 'ThucHanh'),	/*26*/
    (16, "Thứ 2", 1, "2024-10-07", "2024-11-11", 5, 'LyThuyet'),	/*27*/
    (16, "Thứ 2", 2, "2024-10-07", "2024-11-04", 19, 'ThucHanh'),	/*28*/
    (17, "Thứ 7", 1, "2024-10-12", "2024-11-16", 3, 'LyThuyet'),	/*29*/
    (17, "Thứ 7", 2, "2024-10-12", "2024-11-09", 18, 'ThucHanh'),	/*30*/
    (18, "Chủ nhật", 1, "2024-10-13", "2024-11-17", 7, 'LyThuyet'),	/*31*/
    (18, "Chủ nhật", 2, "2024-10-13", "2024-11-10", 17, 'ThucHanh'),/*32*/
    (19, "Thứ 3", 1, "2025-02-25", "2025-04-01", 4, 'LyThuyet'),	/*33*/
    (19, "Thứ 3", 2, "2025-02-25", "2025-03-25", 14, 'ThucHanh'),	/*34*/
    (20, "Thứ 4", 1, "2025-02-26", "2025-04-02", 8, 'LyThuyet'),	/*35*/
    (20, "Thứ 4", 2, "2025-02-26", "2025-03-26", 17, 'ThucHanh'),	/*36*/
    (21, "Thứ 6", 1, "2025-02-28", "2025-04-04", 5, 'LyThuyet'),	/*37*/
    (21, "Thứ 6", 2, "2025-02-28", "2025-03-28", 17, 'ThucHanh'),	/*38*/
    
    -- 2024
    (24, "Thứ 3", 1, "2024-10-08", "2024-11-12", 1, 'LyThuyet'),	/*39*/
    (24, "Thứ 3", 2, "2024-10-08", "2024-11-05", 15, 'ThucHanh'),	/*40*/
    (25, "Thứ 5", 1, "2024-10-10", "2024-12-05", 2, 'LyThuyet'),	/*41*/
    (25, "Thứ 5", 2, "2024-10-10", "2024-11-07", 17, 'ThucHanh'),	/*42*/
    (26, "Thứ 7", 1, "2025-02-15", "2025-04-12", 9, 'LyThuyet'),	/*43*/
    (27, "Thứ 2", 1, "2025-02-10", "2025-04-07", 10, 'LyThuyet'),	/*44*/
    (27, "Thứ 2", 2, "2025-02-10", "2025-03-10", 21, 'ThucHanh'),	/*45*/
    (28, "Thứ 2", 1, "2025-06-09", "2025-08-04", 12, 'LyThuyet'),	/*46*/
    (28, "Thứ 2", 2, "2025-06-09", "2025-07-07", 20, 'ThucHanh'),	/*47*/
    (29, "Thứ 3", 1, "2025-06-10", "2025-07-15", 8, 'LyThuyet'),	/*48*/
    (29, "Thứ 3", 2, "2025-06-10", "2025-07-08", 19, 'ThucHanh'),	/*49*/
    (30, "Thứ 6", 1, "2025-06-13", "2025-08-08", 7, 'LyThuyet'),	/*50*/
    (30, "Thứ 6", 2, "2025-06-13", "2025-07-11", 20, 'ThucHanh'),	/*51*/
    
    (31, "Thứ 4", 1, "2024-10-09", "2024-11-13", 1, 'LyThuyet'),	/*52*/
    (31, "Thứ 4", 2, "2024-10-09", "2024-11-06", 15, 'ThucHanh'),	/*53*/
    (32, "Thứ 6", 1, "2024-10-11", "2024-12-06", 2, 'LyThuyet'),	/*54*/
    (32, "Thứ 6", 2, "2024-10-11", "2024-11-08", 17, 'ThucHanh'),	/*55*/
    (33, "Thứ 7", 1, "2025-02-15", "2025-04-12", 10, 'LyThuyet'),	/*56*/
    (34, "Thứ 3", 1, "2025-02-11", "2025-04-08", 10, 'LyThuyet'),	/*57*/
    (34, "Thứ 3", 2, "2025-02-11", "2025-03-11", 21, 'ThucHanh'),	/*58*/
    (35, "Thứ 3", 1, "2025-06-10", "2025-08-05", 12, 'LyThuyet'),	/*59*/
    (35, "Thứ 3", 2, "2025-06-10", "2025-07-08", 20, 'ThucHanh'),	/*60*/
    (36, "Thứ 4", 1, "2025-06-11", "2025-07-16", 8, 'LyThuyet'),	/*61*/
    (36, "Thứ 4", 2, "2025-06-11", "2025-07-09", 19, 'ThucHanh'),	/*62*/
    (37, "Thứ 7", 1, "2025-06-14", "2025-08-09", 7, 'LyThuyet'),	/*63*/
    (37, "Thứ 7", 2, "2025-06-14", "2025-07-12", 20, 'ThucHanh'),	/*64*/
    
    
    
    (38, "Thứ 2", 1, "2025-10-06", "2025-12-22", 6, 'LyThuyet'),	/*65*/
    (39, "Thứ 6", 1, "2025-10-10", "2025-12-26", 5, 'LyThuyet'),	/*66*/
    (40, "Thứ 4", 1, "2025-10-08", "2025-12-03", 8, 'LyThuyet'),	/*67*/
    (40, "Thứ 4", 2, "2025-10-08", "2025-11-05", 20, 'ThucHanh'),	/*68*/
    (41, "Thứ 3", 1, "2025-10-07", "2025-12-02", 7, 'LyThuyet'),	/*69*/
    (41, "Thứ 3", 2, "2025-10-07", "2025-11-04", 20, 'ThucHanh'),	/*70*/
    (42, "Thứ 6", 1, "2025-10-10", "2025-11-14", 2, 'LyThuyet'),	/*71*/
    (42, "Thứ 6", 2, "2025-10-10", "2025-11-07", 14, 'ThucHanh'),	/*72*/
    (43, "Thứ 7", 1, "2025-10-11", "2025-11-15", 10, 'LyThuyet'),	/*73*/
    (43, "Thứ 7", 2, "2025-10-11", "2025-11-08", 17, 'ThucHanh'),	/*74*/
    (44, "Thứ 3", 1, "2025-10-07", "2025-12-23", 6, 'LyThuyet'),	/*75*/
    (45, "Thứ 5", 1, "2025-10-09", "2025-12-04", 8, 'LyThuyet'),	/*76*/
    (45, "Thứ 5", 2, "2025-10-09", "2025-11-06", 20, 'ThucHanh'),	/*77*/
    
    -- 2025
    (46, "Thứ 2", 1, "2025-10-06", "2025-11-10", 9, 'LyThuyet'),
    (46, "Thứ 2", 2, "2025-10-06", "2025-11-03", 19, 'ThucHanh'),
    (47, "Thứ 4", 1, "2025-10-08", "2025-12-03", 1, 'LyThuyet'),
    (47, "Thứ 4", 2, "2025-10-08", "2025-11-05", 18, 'ThucHanh'),
    (48, "Thứ 5", 1, "2025-10-09", "2025-11-13", 3, 'LyThuyet'),
    (48, "Thứ 5", 2, "2025-10-09", "2025-11-06", 16, 'ThucHanh'),
    (49, "Thứ 3", 1, "2025-10-07", "2025-12-02", 2, 'LyThuyet'),
    (49, "Thứ 3", 2, "2025-10-07", "2025-11-04", 18, 'ThucHanh'),
    (50, "Thứ 3", 1, "2025-10-07", "2025-11-11", 4, 'LyThuyet'),
    (50, "Thứ 3", 2, "2025-10-07", "2025-11-04", 21, 'ThucHanh'),
    (51, "Thứ 7", 1, "2025-10-11", "2025-12-06", 3, 'LyThuyet'),
    (51, "Thứ 7", 2, "2025-10-11", "2025-11-08", 13, 'ThucHanh');
    
-- Thêm đăng ký 
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
    (19, 22, 9, "HOAN_THANH"),
    
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
    (20, 22, 9, "HOAN_THANH"),
    
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
    (21, 22, 9, "HOAN_THANH"),
    
    (21, 26, 8, "HOAN_THANH"),
    
    -- Võ Quốc Bảo
	(22, 24, 7, "HOAN_THANH"),
    (22, 25, 7, "HOAN_THANH"),
    (22, 26, 8, "HOAN_THANH"),
    (22, 27, 8, "TRUOT"), 
    (22, 28, 9, "HOAN_THANH"),
    (22, 29, 9, "HOAN_THANH"),
    (22, 30, 9, "HOAN_THANH"),
    (22, 38, 10, "DANG_KY"),
    
    -- Trần Quốc Phong
	(23, 31, 7, "HOAN_THANH"),
    (23, 32, 7, "HOAN_THANH"),
    (23, 33, 8, "HOAN_THANH"),
    (23, 34, 8, "HOAN_THANH"), 
    (23, 35, 9, "HOAN_THANH"),
    (23, 36, 9, "HOAN_THANH"),
    (23, 37, 9, "HOAN_THANH");
    
    
-- Thêm thời khóa biểu
INSERT INTO thoi_khoa_bieu(sinh_vien_id, lich_hoc_id, hoc_ky_id)
VALUES
	-- Tô Quốc Bình
	(19, 1, 1),
    (19, 2, 1),
    (19, 3, 1),
    (19, 4, 1),
    (19, 5, 2),
    (19, 6, 2),
    (19, 7, 2),
    (19, 8, 3),
    (19, 9, 3),
    (19, 10, 3),
    (19, 11, 3),
    (19, 12, 3),
    (19, 13, 3),
    (19, 14, 4),
    (19, 15, 4),
    (19, 16, 4),
    (19, 17, 4),
    (19, 18, 4),
    (19, 19, 5),
    (19, 20, 5),
    (19, 21, 5),
    (19, 22, 5),
    (19, 23, 6),
    (19, 24, 6),
    (19, 25, 6),
    (19, 26, 6),
    (19, 27, 7),
    (19, 28, 7),
    (19, 29, 7),
    (19, 30, 7),
    (19, 31, 7),
    (19, 32, 7),
    (19, 33, 8),
    (19, 34, 8),
    (19, 35, 8),
    (19, 36, 8),
    (19, 37, 8),
    (19, 38, 8),
    
    -- Trần Huỳnh Sang
    (20, 1, 1),
    (20, 2, 1),
    (20, 3, 1),
    (20, 4, 1),
    (20, 5, 2),
    (20, 6, 2),
    (20, 7, 2),
    (20, 8, 3),
    (20, 9, 3),
    (20, 10, 3),
    (20, 11, 3),
    (20, 12, 3),
    (20, 13, 3),
    (20, 14, 4),
    (20, 15, 4),
    (20, 16, 4),
    (20, 17, 4),
    (20, 18, 4),
    (20, 19, 5),
    (20, 20, 5),
    (20, 21, 5),
    (20, 22, 5),
    (20, 23, 6),
    (20, 24, 6),
    (20, 25, 6),
    (20, 26, 6),
    (20, 27, 7),
    (20, 28, 7),
    (20, 29, 7),
    (20, 30, 7),
    (20, 31, 7),
    (20, 32, 7),
    (20, 33, 8),
    (20, 34, 8),
    (20, 35, 8),
    (20, 36, 8),
    (20, 37, 8),
    (20, 38, 8),
    
    -- Nguyễn Đăng Khôi
    (21, 1, 1),
    (21, 2, 1),
    (21, 3, 1),
    (21, 4, 1),
    (21, 5, 2),
    (21, 6, 2),
    (21, 7, 2),
    (21, 8, 3),
    (21, 9, 3),
    (21, 10, 3),
    (21, 11, 3),
    (21, 12, 3),
    (21, 13, 3),
    (21, 14, 4),
    (21, 15, 4),
    (21, 16, 4),
    (21, 17, 4),
    (21, 18, 4),
    (21, 19, 5),
    (21, 20, 5),
    (21, 21, 5),
    (21, 22, 5),
    (21, 23, 6),
    (21, 24, 6),
    (21, 25, 6),
    (21, 26, 6),
    (21, 27, 7),
    (21, 28, 7),
    (21, 29, 7),
    (21, 30, 7),
    (21, 31, 7),
    (21, 32, 7),
    (21, 33, 8),
    (21, 34, 8),
    (21, 35, 8),
    (21, 36, 8),
    (21, 37, 8),
    (21, 38, 8),
    
    (21, 43, 8),
    
	-- Võ Quốc Bảo
    (22, 39, 7),
    (22, 40, 7),
    (22, 41, 7),
    (22, 42, 7),
    (22, 43, 8),
    (22, 44, 8),
    (22, 45, 8),
    (22, 46, 9),
    (22, 47, 9),
    (22, 48, 9),
    (22, 49, 9),
    (22, 50, 9),
    (22, 51, 9),
    (22, 65, 10),
    
    -- Trần Quốc Phong
    (23, 52, 7),
    (23, 53, 7),
    (23, 54, 7),
    (23, 55, 7),
    (23, 56, 8),
    (23, 57, 8),
    (23, 58, 8),
    (23, 59, 9),
    (23, 60, 9),
    (23, 61, 9),
    (23, 62, 9),
    (23, 63, 9),
    (23, 64, 9);
    
INSERT INTO hoc_phi(sinh_vien_id, hoc_ky_id, tong_tien, trang_thai)
VALUES
	-- Tô Quốc Bình
	(19,1,4900000,'DA_THANH_TOAN'),
    (19,2,4900000,'DA_THANH_TOAN'),
    (19,3,7700000,'DA_THANH_TOAN'),
    (19,4,7700000,'DA_THANH_TOAN'),
    (19,5,7700000,'DA_THANH_TOAN'),
    (19,6,4200000,'DA_THANH_TOAN'),
    (19,7,6300000,'DA_THANH_TOAN'),
    (19,8,6300000,'DA_THANH_TOAN'),
    (19,9,2800000,'DA_THANH_TOAN'),
    
    -- Trần Huỳnh Sang
	(20,1,4900000,'DA_THANH_TOAN'),
    (20,2,4900000,'DA_THANH_TOAN'),
    (20,3,7700000,'DA_THANH_TOAN'),
    (20,4,7700000,'DA_THANH_TOAN'),
    (20,5,7700000,'DA_THANH_TOAN'),
    (20,6,4200000,'DA_THANH_TOAN'),
    (20,7,6300000,'DA_THANH_TOAN'),
    (20,8,6300000,'DA_THANH_TOAN'),
    (20,9,2800000,'DA_THANH_TOAN'),
    
    -- Nguyễn Đăng Khôi
	(21,1,4900000,'DA_THANH_TOAN'),
    (21,2,4900000,'DA_THANH_TOAN'),
    (21,3,7700000,'DA_THANH_TOAN'),
    (21,4,7700000,'DA_THANH_TOAN'),
    (21,5,7700000,'DA_THANH_TOAN'),
    (21,6,4200000,'DA_THANH_TOAN'),
    (21,7,6300000,'DA_THANH_TOAN'),
    (21,8,8400000,'DA_THANH_TOAN'),
    (21,9,2800000,'DA_THANH_TOAN'),
    
    -- Võ Quốc Bảo
    (22,7,4900000,'DA_THANH_TOAN'),
    (22,8,4900000,'DA_THANH_TOAN'),
    (22,9,7700000,'DA_THANH_TOAN'),
    (22,10,2800000,'CHUA_THANH_TOAN'),
    
    -- Trần Quốc Phong
    (23,7,4900000,'DA_THANH_TOAN'),
    (23,8,4900000,'DA_THANH_TOAN'),
    (23,9,7700000,'DA_THANH_TOAN');
    
INSERT INTO chi_tiet_hoc_phi(hoc_phi_id, mon_hoc_id, chi_phi)
VALUES
	-- Tô Quốc Bình
	(1,1,2100000),
    (1,2,2800000),
    (2,3,2100000),
    (2,4,2800000),
    (3,5,2100000),
    (3,6,2800000),
    (3,7,2800000),
    (4,8,2800000),
    (4,9,2100000),
    (4,10,2800000),
    (5,11,2800000),
    (5,12,2100000),
    (5,13,2800000),
    (6,14,2100000),
    (6,15,2100000),
    (7,16,2100000),
    (7,17,2100000),
    (7,18,2100000),
    (8,19,2100000),
    (8,20,2100000),
    (8,21,2100000),
    (9,32,2800000),
    
    -- Trần Huỳnh Sang
	(10,1,2100000),
    (10,2,2800000),
    (11,3,2100000),
    (11,4,2800000),
    (12,5,2100000),
    (12,6,2800000),
    (12,7,2800000),
    (13,8,2800000),
    (13,9,2100000),
    (13,10,2800000),
    (14,11,2800000),
    (14,12,2100000),
    (14,13,2800000),
    (15,14,2100000),
    (15,15,2100000),
    (16,16,2100000),
    (16,17,2100000),
    (16,18,2100000),
    (17,19,2100000),
    (17,20,2100000),
    (17,21,2100000),
    (18,32,2800000),
    
    -- Nguyễn Đăng Khôi
	(19,1,2100000),
    (19,2,2800000),
    (20,3,2100000),
    (20,4,2800000),
    (21,5,2100000),
    (21,6,2800000),
    (21,7,2800000),
    (22,8,2800000),
    (22,9,2100000),
    (22,10,2800000),
    (23,11,2800000),
    (23,12,2100000),
    (23,13,2800000),
    (24,14,2100000),
    (24,15,2100000),
    (25,16,2100000),
    (25,17,2100000),
    (25,18,2100000),
    (26,19,2100000),
    (26,20,2100000),
    (26,21,2100000),
    (26,3,2100000),
    (27,32,2800000),
    
    -- Võ Quốc Bảo
	(28,1,2100000),
    (28,2,2800000),
    (29,3,2100000),
    (29,4,2800000),
    (30,10,2800000),
    (30,22,2100000),
    (30,23,2800000),
    (31,8,2800000),
    
    -- Trần Quốc Phong
	(32,1,2100000),
    (32,2,2800000),
    (33,3,2100000),
    (33,4,2800000),
    (34,10,2800000),
    (34,22,2100000),
    (34,23,2800000);
    