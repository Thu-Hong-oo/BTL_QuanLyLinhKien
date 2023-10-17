package entity;

import java.sql.Date;

public class NhanVien {
	private String maNhanVien;
	private String hoten;
	private String diaChi;
	private String dienThoai;
	private String email;
	private Date ngaySinh;
	private String gioiTinh;
	private long luong;
	private String chucVu;
	
	
	public NhanVien() {
		super();
	}


	public NhanVien(String maNhanVien, String hoten, String diaChi, String dienThoai, String email, Date ngaySinh,
			String gioiTinh, long luong, String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoten = hoten;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.luong = luong;
		this.chucVu = chucVu;
	}


	public String getMaNhanVien() {
		return maNhanVien;
	}


	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}


	public String getHoten() {
		return hoten;
	}


	public void setHoten(String hoten) {
		this.hoten = hoten;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public String getDienThoai() {
		return dienThoai;
	}


	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public String getGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public long getLuong() {
		return luong;
	}


	public void setLuong(long luong) {
		this.luong = luong;
	}


	public String getChucVu() {
		return chucVu;
	}


	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	

}