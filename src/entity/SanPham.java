package entity;

import java.util.Objects;

public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	private String danhMuc;
//	private int maDanhMuc;
	private String nhaCungCap;
	private long giaNhap;
	private long giaBan;
	private int soLuong;
	private String moTa;
	
	
	
	public SanPham() {
	
	}



	public SanPham(String maSanPham, String tenSanPham, String danhMuc, String nhaCungCap, long giaNhap, long giaBan,
			int soLuong, String moTa) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.danhMuc = danhMuc;
		this.nhaCungCap = nhaCungCap;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
		this.moTa = moTa;
	}



	public String getMaSanPham() {
		return maSanPham;
	}



	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}



	public String getTenSanPham() {
		return tenSanPham;
	}



	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}



	public String getDanhMuc() {
		return danhMuc;
	}



	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}



	public String getNhaCungCap() {
		return nhaCungCap;
	}



	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}



	public long getGiaNhap() {
		return giaNhap;
	}



	public void setGiaNhap(long giaNhap) {
		this.giaNhap = giaNhap;
	}



	public long getGiaBan() {
		return giaBan;
	}



	public void setGiaBan(long giaBan) {
		this.giaBan = giaBan;
	}



	public int getSoLuong() {
		return soLuong;
	}



	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}



	public String getMoTa() {
		return moTa;
	}



	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}



	
	
	
}
	
