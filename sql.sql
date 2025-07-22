-- HỌC KỲ
CREATE TABLE hoc_ky (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ky VARCHAR(20) NOT NULL,
    nam_hoc VARCHAR(20) NOT NULL,
    UNIQUE(ky, nam_hoc)
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
    FOREIGN KEY (khoa_id) REFERENCES khoa(id)
);

-- ========================
-- 4. SINH VIÊN
-- ========================
CREATE TABLE sinh_vien (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mat_khau VARCHAR(255) NOT NULL,
    ngay_sinh DATE,
    khoa_hoc INT NOT NULL,
    so_tin_chi INT NOT NULL DEFAULT 0,
    khoa_id INT NOT NULL,
    nganh_id INT NOT NULL,
    FOREIGN KEY (khoa_id) REFERENCES khoa(id),
    FOREIGN KEY (nganh_id) REFERENCES nganh(id)
);

-- ========================
-- 5. GIẢNG VIÊN
-- ========================
CREATE TABLE giang_vien (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    hoc_vi VARCHAR(50),
    mat_khau VARCHAR(255)
);

-- ========================
-- 6. MÔN HỌC
-- ========================
CREATE TABLE mon_hoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_mon VARCHAR(100) NOT NULL,
    mo_ta TEXT,
    so_tin_chi INT NOT NULL,
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
    lan_hoc INT NOT NULL DEFAULT 1,
    loai ENUM('GIUA_KY', 'CUOI_KY') NOT NULL,
    diem DECIMAL(4,2) CHECK (diem BETWEEN 0 AND 10),
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
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
    loai ENUM('LyThuyet', 'ThucHanh'),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (giang_vien_id) REFERENCES giang_vien(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id)
);

-- THỜI KHÓA BIỂU
CREATE TABLE thoi_khoa_bieu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    buoi_hoc_id INT NOT NULL,
    thu VARCHAR(20),            -- VD: 'Thứ 2'
    gio_bat_dau TIME NOT NULL,
    gio_ket_thuc TIME NOT NULL,
    ngay_bat_dau DATE NOT NULL,
    ngay_ket_thuc DATE NOT NULL,
    phong VARCHAR(50),
    FOREIGN KEY (buoi_hoc_id) REFERENCES buoi_hoc(id)
);

-- CA
CREATE TABLE ca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    buoi_hoc_id INT NOT NULL,
    ten_ca VARCHAR(20),
    si_so INT DEFAULT 50,
    FOREIGN KEY (buoi_hoc_id) REFERENCES buoi_hoc(id),
    UNIQUE (buoi_hoc_id, ten_ca)
);

-- ========================
-- 10. ĐĂNG KÝ MÔN HỌC
-- ========================
CREATE TABLE dang_ky (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinh_vien_id INT NOT NULL,
    mon_hoc_id INT NOT NULL,
    buoi_hoc_id INT NOT NULL,
    hoc_ky_id INT NOT NULL,
    trang_thai ENUM('DANG_KY', 'DA_HOC', 'HOAN_THANH', 'TRUOT') DEFAULT 'DANG_KY',
    ngay_dang_ky TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sinh_vien_id) REFERENCES sinh_vien(id),
    FOREIGN KEY (mon_hoc_id) REFERENCES mon_hoc(id),
    FOREIGN KEY (buoi_hoc_id) REFERENCES buoi_hoc(id),
    FOREIGN KEY (hoc_ky_id) REFERENCES hoc_ky(id),
    UNIQUE (sinh_vien_id, buoi_hoc_id)
);

-- ĐĂNG KÝ CA HỌC THỰC HÀNH
CREATE TABLE dang_ky_ca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dang_ky_id INT NOT NULL,
    ten_ca VARCHAR(20),
    FOREIGN KEY (dang_ky_id) REFERENCES dang_ky(id),
    UNIQUE (dang_ky_id)
);


-- ========================
-- 11. QUY ĐỊNH HỆ THỐNG (VD: Giới hạn tín chỉ)
-- ========================
CREATE TABLE quy_dinh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(100) UNIQUE NOT NULL,
    gia_tri VARCHAR(100) NOT NULL
);

-- ========================
-- 12. DỮ LIỆU MẪU
-- ========================
INSERT INTO quy_dinh (ten, gia_tri) VALUES 
    ('so_tin_chi_toi_da', '24'),
    ('so_tin_chi_toi_thieu', '12');

-- Thêm học kỳ
INSERT INTO hoc_ky (ky, nam_hoc)
VALUES
	('Học kỳ 1', '2021-2022'),
    ('Học kỳ 2', '2021-2022'),
    ('Học kỳ 3', '2021-2022'),
	('Học kỳ 1', '2022-2023'),
    ('Học kỳ 2', '2022-2023'),
    ('Học kỳ 3', '2022-2023'),
    ('Học kỳ 1', '2023-2024'),
    ('Học kỳ 2', '2023-2024'),
    ('Học kỳ 3', '2023-2024'),
    ('Học kỳ 1', '2024-2025'),
    ('Học kỳ 2', '2024-2025'),
    ('Học kỳ 3', '2024-2025'),
    ('Học kỳ 1', '2025-2026');

-- Thêm khoa
INSERT INTO khoa (ten_khoa)
VALUES ('Công nghệ thông tin');

-- Thêm các ngành thuộc khoa đó
INSERT INTO nganh (ten_nganh, khoa_id)
VALUES 
	('Khoa học máy tính', 1),
	('Hệ thống thông tin quản lý', 1),
	('Công nghệ thông tin', 1);

INSERT INTO sinh_vien (ho_ten, email, mat_khau, ngay_sinh, khoa_hoc, so_tin_chi, khoa_id, nganh_id)
VALUES
	('Tô Quốc Bình', 'binh@student.edu.vn', '123456', '2004-02-21', 2022, 71, 1, 1),
	('Trần Huỳnh Sang', 'sang@student.edu.vn', '123456', '2004-03-24', 2022, 71, 1, 1),
	('Nguyễn Đăng Khôi', 'khoi@student.edu.vn', '123456', '2004-05-27', 2022, 71, 1, 1),
	('Trần Thị Trang', 'trang@student.edu.vn', '123456', '2003-04-12', 2021, 0, 1, 2),
	('Võ Quốc Bảo', 'bao@student.edu.vn', '123456', '2005-05-15', 2023, 46, 1, 3);
  
-- Thêm giảng viên
INSERT INTO giang_vien (ho_ten, email, hoc_vi, mat_khau)
VALUES
	('Nguyễn Văn Ón', 'onnguyenvan@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Trần Thị Tuyết', 'tuyettranthi@giangvien.edu.vn', 'Tiến sĩ', '123456'),
	('Lê Minh Bảo', 'baoleminh@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Nguyễn Hữu Khuê', 'khuenguyenhuu@giangvien.edu.vn', 'Tiến sĩ', '123456'),
	('Phạm Minh Trang', 'trangphamminh@giangvien.edu.vn', 'Tiến sĩ', '123456'),
	('Đỗ Quang Hùng', 'hungdoquang@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Phùng Thị Anh', 'anhphungthi@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Võ Văn Kiệt', 'kietvovan@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Dương Minh Công', 'congminhduong@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Nguyễn Lộc Thành', 'thanhnguenloc@giangvien.edu.vn', 'Thạc sĩ', '123456'),
	('Trần Tuấn Khải', 'khaitrantuan@giangvien.edu.vn', 'Tiến sĩ', '123456'),
    ('Đỗ Kim Tuấn', 'tuandokim@giangvien.edu.vn', 'Tiến sĩ', '123456'),
    ('Trần Hà Thanh', 'thanhtranha@giangvien.edu.vn', 'Thạc sĩ', '123456'),
    ('Nguyễn Quốc Thiên', 'thiennguyenquoc@giangvien.edu.vn', 'Tiến sĩ', '123456');
-- Thêm môn học
INSERT INTO mon_hoc (ten_mon, mo_ta, so_tin_chi, phan_tram_giua_ky, phan_tram_cuoi_ky, diem_qua_mon, khoa_id)
VALUES
('Nhập môn tin học', '', 3, 40, 60, 5, 1),
('Cơ sở lập trình', '', 4, 40, 60, 5, 1),
('Hệ điều hành và Kiến trúc máy tính', '', 3, 30, 70, 5, 1),
('Kỹ thuật lập trình', '', 4, 40, 60, 5, 1),
('Ứng dụng web', '', 3, 30, 70, 5, 1),
('Cấu trúc dữ liệu và thuật giải 1', '', 4, 40, 60, 5, 1),
('Cơ sở dữ liệu', '', 4, 40, 60, 5, 1),
('Toán rời rạc', '', 4, 40, 60, 5, 1),
('Cấu trúc dữ liệu và thuật giải 2', '', 3, 40, 60, 5, 1),
('Mạng máy tính', '', 4, 40, 60, 5, 1),
('Lập trình hướng đối tượng', '', 4, 40, 60, 5, 1),
('Kỹ năng nghề nghiệp', '', 3, 40, 60, 5, 1),
('Phân tích thiết kế hệ thống', '', 4, 50, 50, 5, 1),
('Mẫu thiết kế hướng đối tượng', '', 3, 40, 60, 5, 1),
('Trí tuệ nhân tạo', '', 3, 50, 50, 5, 1),
('Quản trị hệ cơ sở dữ liệu', '', 3, 40, 60, 5, 1),
('Công nghệ phần mềm', '', 3, 40, 60, 5, 1),
('Các công nghệ lập trình hiện đại', '', 3, 40, 60, 5, 1),
('Máy học', '', 3, 40, 60, 5, 1),
('Kiểm thử phần mềm', '', 3, 40, 60, 5, 1),
('Phát triển hệ thống web', '', 3, 30, 70, 5, 1),
('Thiết kế web', '', 3, 40, 60, 5, 1),
('Cấu trúc dữ liệu và thuật giải', '', 4, 40, 60, 5, 1),
('Công nghệ mã nguồn mở', '', 3, 50, 50, 5, 1),
('Lập trình giao diện', '', 3, 50, 50, 5, 1),
('Quản trị mạng', '', 3, 40, 60, 5, 1),
('An toàn hệ thống thông tin', '', 3, 40, 60, 5, 1),
('Hệ thống quản lý nguồn lực doanh nghiệp', '', 3, 50, 50, 5, 1),
('Hệ thống thông tin quản lý', '', 3, 50, 50, 5, 1),
('Lập trình cơ sở dữ liệu', '', 3, 50, 50, 5, 1),
('Phát triển hệ thống thông tin quản lý', '', 3, 50, 50, 5, 1),
('Đồ án ngành', '', 4, 40, 60, 5, 1),
('Thực tập tốt nghiệp', '', 4, 30, 70, 5, 1),
('Khóa luận tốt nghiệp', '', 6, 0, 100, 5, 1),
('Đồ án ngành Hệ thống thông tin quản lý', '', 4, 40, 60, 5, 1),
('Thực tập tốt nghiệp', '', 4, 30, 70, 5, 1),
('Khóa luận tốt nghiệp', '', 6, 0, 100, 5, 1);

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
INSERT INTO diem(sinh_vien_id, mon_hoc_id, loai, diem)
VALUES
	-- Tô Quốc Bình
	(1,1, "GIUA_KY",9),
    (1,1, "CUOI_KY",7.6),
    (1,2, "GIUA_KY",6),
    (1,2, "CUOI_KY",8),
    (1,3, "GIUA_KY",6.6),
    (1,3, "CUOI_KY",3.7),
    (1,4, "GIUA_KY",4.2),
    (1,4, "CUOI_KY",6.5),
    (1,5, "GIUA_KY",5.5),
    (1,5, "CUOI_KY",4),
    (1,6, "GIUA_KY",7.2),
    (1,6, "CUOI_KY",7),
    (1,7, "GIUA_KY",7.7),
    (1,7, "CUOI_KY",9),
    (1,8, "GIUA_KY",9.6),
    (1,8, "CUOI_KY",9.5),
    (1,9, "GIUA_KY",9),
    (1,9, "CUOI_KY",8),
    (1,10, "GIUA_KY",10),
    (1,10, "CUOI_KY",4.3),
    (1,11, "GIUA_KY",5.5),
    (1,11, "CUOI_KY",8),
    (1,12, "GIUA_KY",8.7),
    (1,12, "CUOI_KY",8),
    (1,13, "GIUA_KY",7.5),
    (1,13, "CUOI_KY",9),
    (1,14, "GIUA_KY",5.5),
    (1,14, "CUOI_KY",8),
    (1,15, "GIUA_KY",7.9),
    (1,15, "CUOI_KY",8),
    (1,16, "GIUA_KY",10),
    (1,16, "CUOI_KY",9.8),
    (1,17, "GIUA_KY",7.5),
    (1,17, "CUOI_KY",6.5),
    (1,18, "GIUA_KY",6.5),
    (1,18, "CUOI_KY",7),
    (1,19, "GIUA_KY",10),
    (1,19, "CUOI_KY",4.4),
    (1,20, "GIUA_KY",7),
    (1,20, "CUOI_KY",6),
    (1,21, "GIUA_KY",6.5),
    (1,21, "CUOI_KY",7),
    -- Trần Huỳnh Sang
    (2,1, "GIUA_KY",10),
    (2,1, "CUOI_KY",7.4),
    (2,2, "GIUA_KY",10),
    (2,2, "CUOI_KY",10),
    (2,3, "GIUA_KY",7.6),
    (2,3, "CUOI_KY",6.8),
    (2,4, "GIUA_KY",5.1),
    (2,4, "CUOI_KY",7),
    (2,5, "GIUA_KY",7.6),
    (2,5, "CUOI_KY",4),
    (2,6, "GIUA_KY",8.3),
    (2,6, "CUOI_KY",6.5),
    (2,7, "GIUA_KY",8.3),
    (2,7, "CUOI_KY",9),
    (2,8, "GIUA_KY",9.6),
    (2,8, "CUOI_KY",10),
    (2,9, "GIUA_KY",8.4),
    (2,9, "CUOI_KY",9),
    (2,10, "GIUA_KY",5),
    (2,10, "CUOI_KY",5),
    (2,11, "GIUA_KY",6),
    (2,11, "CUOI_KY",8),
    (2,12, "GIUA_KY",8.7),
    (2,12, "CUOI_KY",8.2),
    (2,13, "GIUA_KY",7.5),
    (2,13, "CUOI_KY",7.5),
    (2,14, "GIUA_KY",6),
    (2,14, "CUOI_KY",8),
    (2,15, "GIUA_KY",10),
    (2,15, "CUOI_KY",6.4),
    (2,16, "GIUA_KY",9),
    (2,16, "CUOI_KY",8.8),
    (2,17, "GIUA_KY",6.5),
    (2,17, "CUOI_KY",6),
    (2,18, "GIUA_KY",7),
    (2,18, "CUOI_KY",7),
    (2,19, "GIUA_KY",10),
    (2,19, "CUOI_KY",6.4),
    (2,20, "GIUA_KY",7),
    (2,20, "CUOI_KY",5.5),
    (2,21, "GIUA_KY",5.5),
    (2,21, "CUOI_KY",7),
    -- Nguyễn Đăng Khôi
    (3,1, "GIUA_KY",8),
    (3,1, "CUOI_KY",3.9),
    (3,2, "GIUA_KY",5),
    (3,2, "CUOI_KY",2),
    (3,3, "GIUA_KY",6),
    (3,3, "CUOI_KY",0.5),
    (3,4, "GIUA_KY",4.5),
    (3,4, "CUOI_KY",6),
    (3,5, "GIUA_KY",5.1),
    (3,5, "CUOI_KY",3.3),
    (3,6, "GIUA_KY",6.5),
    (3,6, "CUOI_KY",4.8),
    (3,7, "GIUA_KY",8.2),
    (3,7, "CUOI_KY",6),
    (3,8, "GIUA_KY",9.6),
    (3,8, "CUOI_KY",8),
    (3,9, "GIUA_KY",6),
    (3,9, "CUOI_KY",4.1),
    (3,10, "GIUA_KY",9),
    (3,10, "CUOI_KY",2),
    (3,11, "GIUA_KY",2.5),
    (3,11, "CUOI_KY",1.5),
    (3,12, "GIUA_KY",8.5),
    (3,12, "CUOI_KY",8),
    (3,13, "GIUA_KY",7.5),
    (3,13, "CUOI_KY",3),
    (3,14, "GIUA_KY",2.5),
    (3,14, "CUOI_KY",1.5),
    (3,15, "GIUA_KY",10),
    (3,15, "CUOI_KY",4.9),
    (3,16, "GIUA_KY",8.5),
    (3,16, "CUOI_KY",8.8),
    (3,17, "GIUA_KY",6.5),
    (3,17, "CUOI_KY",2),
    (3,18, "GIUA_KY",3),
    (3,18, "CUOI_KY",5),
    (3,19, "GIUA_KY",8.5),
    (3,19, "CUOI_KY",4.8),
    (3,20, "GIUA_KY",6),
    (3,20, "CUOI_KY",4.8),
    (3,21, "GIUA_KY",3),
    (3,21, "CUOI_KY",5.5),
	-- Võ Quốc Bảo
    (5,1, "GIUA_KY",6),
    (5,1, "CUOI_KY",9),
    (5,2, "GIUA_KY",9),
    (5,2, "CUOI_KY",9.5),
    (5,3, "GIUA_KY",9.9),
    (5,3, "CUOI_KY",7.6),
    (5,4, "GIUA_KY",9.5),
    (5,4, "CUOI_KY",8),
    (5,22, "GIUA_KY",6.5),
    (5,22, "CUOI_KY",4.5),
    (5,23, "GIUA_KY",9),
    (5,23, "CUOI_KY",5.5),
    (5,10, "GIUA_KY",10),
    (5,10, "CUOI_KY",3.3),
    (5,8, "GIUA_KY",10),
    (5,8, "CUOI_KY",10),
    (5,11, "GIUA_KY",7.5),
    (5,11, "CUOI_KY",7),
    (5,24, "GIUA_KY",8),
    (5,24, "CUOI_KY",8),
    (5,7, "GIUA_KY",9.3),
    (5,7, "CUOI_KY",9.3),
    (5,25, "GIUA_KY",9.8),
    (5,25, "CUOI_KY",8),
    (5,12, "GIUA_KY",9.2),
    (5,12, "CUOI_KY",8.4);
-- Thêm điểm học lại lần 2
INSERT INTO diem(sinh_vien_id, mon_hoc_id, lan_hoc, loai, diem)
VALUES
	-- Nguyễn Đăng Khôi
	(3,3,2,"GIUA_KY",3),
    (3,3,2,"CUOI_KY",5.8);
    
-- Thêm buổi học
INSERT INTO buoi_hoc(mon_hoc_id, giang_vien_id, hoc_ky_id, si_so)
VALUES
	(1,4,4,5),
    