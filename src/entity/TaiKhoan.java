package entity;

import java.util.Objects;

public class TaiKhoan {
	private String maNhanVien;
	private String tenDangNhap;
	private String matKhau;
	public TaiKhoan( String maNhanVien, String tenDangNhap, String matKhau) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}
	public TaiKhoan() {
		
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien, tenDangNhap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(maNhanVien, other.maNhanVien) && Objects.equals(tenDangNhap, other.tenDangNhap);
	}

	
}
