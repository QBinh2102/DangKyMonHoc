CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tongket_hocky`(
	IN p_sinh_vien_id INT
)
BEGIN
	WITH tb_hk AS (
		SELECT
			d.sinh_vien_id,
			hk.ky,
			hk.nam_hoc,
			ROUND(SUM(d.diem * (m.tin_chi_ly_thuyet + m.tin_chi_thuc_hanh)) /
				SUM(m.tin_chi_ly_thuyet + m.tin_chi_thuc_hanh), 2) AS diem_tb_hk,
			SUM(CASE WHEN d.diem >= m.diem_qua_mon
				 THEN (m.tin_chi_ly_thuyet + m.tin_chi_thuc_hanh)
				 ELSE 0 END) AS tin_chi_dat_hk,
			SUM(m.tin_chi_ly_thuyet + m.tin_chi_thuc_hanh) AS tong_tin_chi_hk
		FROM diem d
		JOIN mon_hoc m ON d.mon_hoc_id = m.id
		JOIN hoc_ky hk ON d.hoc_ky_id = hk.id
		WHERE d.loai = 'TONG_KET'
		  AND d.sinh_vien_id = p_sinh_vien_id
		GROUP BY d.sinh_vien_id, hk.ky, hk.nam_hoc
	)
	SELECT
		cur.sinh_vien_id,
		cur.ky,
		cur.nam_hoc,
		cur.diem_tb_hk,
		cur.tin_chi_dat_hk,
		ROUND(SUM(prev.diem_tb_hk * prev.tong_tin_chi_hk) / SUM(prev.tong_tin_chi_hk), 2) AS diem_tb_tich_luy,
		SUM(prev.tin_chi_dat_hk) AS tin_chi_tich_luy
	FROM tb_hk cur
	JOIN tb_hk prev
	  ON prev.sinh_vien_id = cur.sinh_vien_id
	 AND (prev.nam_hoc < cur.nam_hoc OR 
		 (prev.nam_hoc = cur.nam_hoc AND prev.ky <= cur.ky))
	GROUP BY cur.sinh_vien_id, cur.ky, cur.nam_hoc, cur.diem_tb_hk, cur.tin_chi_dat_hk
	ORDER BY cur.nam_hoc, cur.ky;
END