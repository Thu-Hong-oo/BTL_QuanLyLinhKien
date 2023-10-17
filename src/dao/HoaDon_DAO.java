package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;
import entity.ThongKe;

public class HoaDon_DAO {
	ConnectDB con = ConnectDB.getInstance();

	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		try {
			con.connect();
			String sql = "SELECT * FROM HoaDon";

			Statement statement = con.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsHoaDon.add(new HoaDon(rs.getString("MaHD"), rs.getString("MaKH"), rs.getString("MaNV"),
						rs.getLong("TongTien"), rs.getLong("TienDua"), rs.getLong("TienTra"), rs.getString("HTTT"),
						rs.getString("HTGH"), rs.getDate("NgayLap"), rs.getString("GhiChu")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	public String taoMaHoaDon() {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String maHoaDon;
		boolean isDuplicate;
		do {
			// Tạo mã hóa đơn ngẫu nhiên
			Random rand = new Random();
			maHoaDon = "HD" + rand.nextInt(1000000);
			// Kiểm tra xem mã hóa đơn có bị trùng không
			isDuplicate = false;
			String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, maHoaDon);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					isDuplicate = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} while (isDuplicate);
		return maHoaDon;
	}

	public void themHoaDon(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO HoaDon(MaHD, MaKH, MaNV, TongTien, TienDua, TienTra, HTTT, HTGH, NgayLap, GhiChu)  VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getMaKH());
			stmt.setString(3, hd.getMaNV());
			stmt.setLong(4, hd.getTongTien());
			stmt.setLong(5, hd.getTienDua());
			stmt.setLong(6, hd.getTienTra());
			stmt.setString(7, hd.gethTTT());
			stmt.setString(8, hd.gethTGH());
			stmt.setDate(9, hd.getNgayLap());
			stmt.setString(10, hd.getGhiChu());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void xoaHoaDon(String maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "Delete from HoaDon where MaHD = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonByMaHD(String maHoaDon) {
		ArrayList<ChiTietHoaDon> dsctHoaDon = new ArrayList<ChiTietHoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM ChiTietHoaDon where MaHD= ?  ";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHoaDon);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsctHoaDon.add(new ChiTietHoaDon(rs.getString("MaHD"), rs.getString("MaSP"), rs.getLong("DonGia"),
						rs.getInt("SoLuong"), rs.getLong("ThanhTien")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsctHoaDon;
	}

	public String getTenSPMuaNhieuNhatThang(int thang, int nam) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String getTen = "";
		String sqlGetMa = "SELECT TenSP FROM SanPham WHERE MaSP IN (SELECT TOP 1 MaSP "
				+ " FROM ChiTietHoaDon WHERE MaHD IN(SELECT MaHD FROM HoaDon WHERE Month(NgayLap) = ? "
				+ " AND YEAR(NgayLap) = ?) "
				+ " GROUP BY MaSP ORDER BY SUM(SoLuong) DESC);";
		try {
			stmt = con.prepareStatement(sqlGetMa);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				getTen = rs.getString("TenSP");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return getTen;
	}
	public String getTenSPMuaNhieuNhatNam( int nam) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String getTen = "";
		String sqlGetMa = "SELECT TenSP FROM SanPham WHERE MaSP IN (SELECT TOP 1 MaSP "
				+ " FROM ChiTietHoaDon WHERE MaHD IN(SELECT MaHD FROM HoaDon WHERE  "
				+ "  YEAR(NgayLap) = ?) "
				+ " GROUP BY MaSP ORDER BY SUM(SoLuong) DESC);";
		try {
			stmt = con.prepareStatement(sqlGetMa);
			stmt.setInt(1, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				getTen = rs.getString("TenSP");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return getTen;
	}
	public String getTenSPMuaNhieuNhat( ) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String getTen = "";
		String sqlGetMa = "SELECT TenSP FROM SanPham WHERE MaSP IN (SELECT TOP 1 MaSP "
				+ " FROM ChiTietHoaDon "
				+ " GROUP BY MaSP ORDER BY SUM(SoLuong) DESC);";
		try {
			stmt = con.prepareStatement(sqlGetMa);
		//	stmt.setInt(1, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				getTen = rs.getString("TenSP");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return getTen;
	}

	public ThongKe getThongKeByMonth(int thang, int nam) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		ThongKe tk = new ThongKe();
		String sqlGetDoanhThu = "SELECT SUM(TongTien) AS TongDoanhThuThang,  COUNT(DISTINCT HoaDon.MaHD) AS TongHoaDon, COUNT(DISTINCT HoaDon.MaKH) AS TongKhachHang "
				+ "from HoaDon  where Month(NgayLap) = ?  and Year(NgayLap) = ?";
		try {
			String tenSPMuaNhieu = getTenSPMuaNhieuNhatThang(thang,nam);
			stmt = con.prepareStatement(sqlGetDoanhThu);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tk.setDoanhThu(rs.getLong("TongDoanhThuThang")) ;
				tk.setSoHoaDon(rs.getInt("TongHoaDon"));
				tk.setTongKhach(rs.getInt("TongKhachHang"));
			}
			tk.setTenSPMuaNhieu(tenSPMuaNhieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	public ThongKe getThongKeByYear(int nam) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		ThongKe tk = new ThongKe();
		String sqlGetDoanhThu = "SELECT SUM(TongTien) AS TongDoanhThuThang,  COUNT(DISTINCT HoaDon.MaHD) AS TongHoaDon, COUNT(DISTINCT HoaDon.MaKH) AS TongKhachHang "
				+ "from HoaDon  where  Year(NgayLap) = ?";
		try {
			String tenSPMuaNhieu = getTenSPMuaNhieuNhatNam(nam);
			stmt = con.prepareStatement(sqlGetDoanhThu);
			stmt.setInt(1, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tk.setDoanhThu(rs.getLong("TongDoanhThuThang")) ;
				tk.setSoHoaDon(rs.getInt("TongHoaDon"));
				tk.setTongKhach(rs.getInt("TongKhachHang"));
			}
			tk.setTenSPMuaNhieu(tenSPMuaNhieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	public ThongKe getThongKe() {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		ThongKe tk = new ThongKe();
		String sqlGetDoanhThu = "SELECT SUM(TongTien) AS TongDoanhThuThang,  COUNT(DISTINCT HoaDon.MaHD) AS TongHoaDon, COUNT(DISTINCT HoaDon.MaKH) AS TongKhachHang "
				+ "from HoaDon ";
		try {
			String tenSPMuaNhieu = getTenSPMuaNhieuNhat();
			stmt = con.prepareStatement(sqlGetDoanhThu);
//			stmt.setInt(1, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tk.setDoanhThu(rs.getLong("TongDoanhThuThang")) ;
				tk.setSoHoaDon(rs.getInt("TongHoaDon"));
				tk.setTongKhach(rs.getInt("TongKhachHang"));
			}
			tk.setTenSPMuaNhieu(tenSPMuaNhieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}

	public void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
