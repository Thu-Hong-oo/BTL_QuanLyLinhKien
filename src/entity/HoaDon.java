package entity;

import java.sql.Date;

public class HoaDon {
    private String maHD;
    private String maKH;
    private String maNV;
 
    private long tongTien;
    private long tienDua;
    private long tienTra;
    private String hTTT;
    private String hTGH;
    private Date ngayLap;
    private String ghiChu;
	public HoaDon(String maHD, String maKH, String maNV, long tongTien, long tienDua, long tienTra, String hTTT,
			String hTGH, Date ngayLap, String ghiChu) {
		super();
		this.maHD = maHD;
		this.maKH = maKH;
		this.maNV = maNV;
		this.tongTien = tongTien;
		this.tienDua = tienDua;
		this.tienTra = tienTra;
		this.hTTT = hTTT;
		this.hTGH = hTGH;
		this.ngayLap = ngayLap;
		this.ghiChu = ghiChu;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public long getTongTien() {
		return tongTien;
	}
	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}
	public long getTienDua() {
		return tienDua;
	}
	public void setTienDua(long tienDua) {
		this.tienDua = tienDua;
	}
	public long getTienTra() {
		return tienTra;
	}
	public void setTienTra(long tienTra) {
		this.tienTra = tienTra;
	}
	public String gethTTT() {
		return hTTT;
	}
	public void sethTTT(String hTTT) {
		this.hTTT = hTTT;
	}
	public String gethTGH() {
		return hTGH;
	}
	public void sethTGH(String hTGH) {
		this.hTGH = hTGH;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

   
}
