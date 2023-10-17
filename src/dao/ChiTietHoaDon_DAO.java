package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class ChiTietHoaDon_DAO {
	ConnectDB con = ConnectDB.getInstance();

	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() {
		ArrayList<ChiTietHoaDon> dsChiTietHD = new ArrayList<ChiTietHoaDon>();
		try {
			con.connect();
			String sql = "SELECT * FROM ChiTietHoaDon ";

			Statement statement = con.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsChiTietHD.add(new ChiTietHoaDon(rs.getString("MaHD"), rs.getString("MaSP"), rs.getLong("DonGia"),
						rs.getInt("SoLuong"), rs.getLong("ThanhTien")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHD;
	}

	public void themChiTietHD(ChiTietHoaDon ctHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ChiTietHoaDon(MaHD, MaSP, DonGia, SoLuong, ThanhTien)  VALUES (?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ctHD.getMaHD());
			stmt.setString(2, ctHD.getMaSP());
			stmt.setLong(3, ctHD.getDonGia());
			stmt.setInt(4, ctHD.getSoLuong());
			stmt.setLong(5, ctHD.getThanhTien());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void xoaChiTietHD(String maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "Delete from CHiTietHoaDon where MaHD = ?";
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

	public int getSoLuongMua(String tenSP) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int getSL = 0;
		String sql = "SELECT SUM(SoLuong) as SoLuongMua FROM ChiTietHoaDon WHERE MaSP IN (SELECT MaSP FROM SanPham WHERE TenSP = ?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenSP);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				getSL = rs.getInt("SoLuongMua");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return getSL;
	
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
