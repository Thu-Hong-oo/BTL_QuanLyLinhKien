package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	ConnectDB con = ConnectDB.getInstance();

	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<TaiKhoan>();
		try {
			con.connect();
			String sql = "Select *FROM TaiKhoan";
			Statement statement = con.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsTaiKhoan
						.add(new TaiKhoan(rs.getString("MaNV"), rs.getString("TenDangNhap"), rs.getString("MatKhau")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTaiKhoan;
	}

	public TaiKhoan getTaiKhoanByTenDangNhap(String tenDangNhap) throws SQLException {
		TaiKhoan tk = null;
		try {
			con.connect();
			String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
			PreparedStatement pstatement = con.getConnection().prepareStatement(sql);
			pstatement.setString(1, tenDangNhap);
			ResultSet rs = pstatement.executeQuery();
			while (rs.next()) {
				tk = new TaiKhoan(rs.getString("MaNV"), rs.getString("TenDangNhap"), rs.getString("MatKhau"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tk;

	}

	public String getTenByMaNV(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String tenNV = "";
		String sql = "SELECT HoTen FROM NhanVien  where MaNV = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tenNV = rs.getString("HoTen");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return tenNV;
	}

	public boolean checkLogin(String tenDangNhap, String matKhau) throws SQLException {
		TaiKhoan tk = getTaiKhoanByTenDangNhap(tenDangNhap);
		if (tk != null && tk.getMatKhau().equals(matKhau)) {
			return true;
		}
		return false;
	}

	public void themTaiKhoan(TaiKhoan tk) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO TaiKhoan (MaNV,TenDangNhap, MatKhau) VALUES (?, ?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getMaNhanVien());
			stmt.setString(2, tk.getTenDangNhap());
			stmt.setString(3, tk.getMatKhau());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void suaTaiKhoan(TaiKhoan tk) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE TaiKhoan SET TenDangNhap = ?, MatKhau = ? WHERE TenDangNhap = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getTenDangNhap());
			stmt.setString(2, tk.getMatKhau());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void xoaTaiKhoan(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM TaiKhoan WHERE maNV = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public DefaultComboBoxModel<String> loadComBoBox(String tenCot, String tenBang) {
		ArrayList<String> data = new ArrayList<String>();
		try {
			con.connect();
			String sql = "SELECT " + tenCot + " FROM " + tenBang;
			Statement statement = con.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String item = rs.getString(tenCot);
				data.add(item);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new DefaultComboBoxModel<String>(data.toArray(new String[0]));

	}

}
