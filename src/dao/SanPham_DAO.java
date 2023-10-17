package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import connectDB.ConnectDB;

import entity.SanPham;
import entity.TaiKhoan;

public class SanPham_DAO {
	ConnectDB con = ConnectDB.getInstance();

	public ArrayList<SanPham> getAllSanPham() {
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		try {
			con.connect();
			String sql = "SELECT s.MaSP, s.TenSP, d.TenDM, c.TenNCC, s.GiaNhap, s.GiaBan,s.SoLuong,s.MoTa\r\n"
					+ "FROM SanPham as s\r\n" + "INNER JOIN DanhMucSanPham as d ON s.MaDM = d.MaDM\r\n"
					+ "INNER JOIN NhaCungCap as c ON s.MaNCC = c.MaNCC;";

			Statement statement = con.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsSanPham.add(new SanPham(rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("TenDM"),
						rs.getString("TenNCC"), rs.getLong("GiaNhap"), rs.getLong("GiaBan"), rs.getInt("SoLuong"),
						rs.getString("MoTa")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public boolean themSanPham(SanPham sanPham) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "Insert into SanPham (MaSP, TenSP, MaDM, MaNCC, GiaNhap, GiaBan, SoLuong, MoTa) VALUES (?,?,?,?,?,?,?,?)";
		try {
			// lấy mã nhà cung cấp từ tên nhà cung cấp
			String maNCC = getMa("MaNCC", "NhaCungCap", "TenNCC", sanPham.getNhaCungCap());
			String maDM = getMa("MaDM", "DanhMucSanPham", "TenDM", sanPham.getDanhMuc());
			// int maDM = maDmStr.isEmpty() ? 0 : Integer.parseInt(maDmStr);

			stmt = con.prepareStatement(sql);
			stmt.setString(1, sanPham.getMaSanPham());
			stmt.setString(2, sanPham.getTenSanPham());
			stmt.setString(3, maDM);
			stmt.setString(4, maNCC);
			stmt.setDouble(5, sanPham.getGiaNhap());
			stmt.setDouble(6, sanPham.getGiaBan());
			stmt.setInt(7, sanPham.getSoLuong());
			stmt.setString(8, sanPham.getMoTa());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Mã sp bị trùng");
			return false;
		} finally {
			close(stmt);
		}
		return true;

	}

	public String getMa(String ma, String bang, String tim, String string) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String getMa = "";
		String sqlGetMa = "SELECT " + ma + " FROM " + bang + " WHERE " + tim + " = ?";

		try {
		
			stmt = con.prepareStatement(sqlGetMa);
			stmt.setString(1, string);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				getMa = rs.getString(ma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return getMa;
	}

	public SanPham getByMa(String giaTri) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		SanPham sp = null;
		String sqlGetSP = "SELECT s.MaSP, s.TenSP, d.TenDM, c.TenNCC, s.GiaNhap, s.GiaBan,s.SoLuong,s.MoTa\r\n"
				+ "FROM SanPham as s\r\n" + "INNER JOIN DanhMucSanPham as d ON s.MaDM = d.MaDM\r\n"
				+ "INNER JOIN NhaCungCap as c ON s.MaNCC = c.MaNCC where MaSP = ?;";
		try {
			stmt = con.prepareStatement(sqlGetSP);
			stmt.setString(1, giaTri);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("TenDM"),
						rs.getString("TenNCC"), rs.getLong("GiaNhap"), rs.getLong("GiaBan"), rs.getInt("SoLuong"),
						rs.getString("MoTa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	public String getTenByMaSP(String maSP) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;

		String tenSP = "";
		String sqlGetSP = "SELECT TenSP from SanPham where MaSP = ?";
		try {
			stmt = con.prepareStatement(sqlGetSP);
			stmt.setString(1, maSP);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tenSP = rs.getString("TenSP");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenSP;
	}

	public void xoaSanPham(String maSP) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "Delete from SanPham where MaSP = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maSP);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void suaSanPham(SanPham sanPham) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update SanPham " + " set TenSP = ?, " + "MaDM = ?, " + " MaNCC = ? ," + " GiaNhap = ?,"
				+ " GiaBan = ?," + " SoLuong = ? ," + " Mota = ?" + " where MaSP = ?";
		try {
			// lấy mã nhà cung cấp từ tên nhà cung cấp
			String maNCC = getMa("MaNCC", "NhaCungCap", "TenNCC", sanPham.getNhaCungCap());
			String maDM = getMa("MaDM", "DanhMucSanPham", "TenDM", sanPham.getDanhMuc());
			// int maDM = maDmStr.isEmpty() ? 0 : Integer.parseInt(maDmStr);

			stmt = con.prepareStatement(sql);
//			stmt.setString(1, sanPham.getMaSanPham());
			stmt.setString(1, sanPham.getTenSanPham());
			stmt.setString(2, maDM);
			stmt.setString(3, maNCC);
			stmt.setDouble(4, sanPham.getGiaNhap());
			stmt.setDouble(5, sanPham.getGiaBan());
			stmt.setInt(6, sanPham.getSoLuong());
			stmt.setString(7, sanPham.getMoTa());
			stmt.setString(8, sanPham.getMaSanPham());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void capNhatSoLuong(char phepTinh, int slSuaDoi, String maSP) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE SanPham SET SoLuong = SoLuong " + phepTinh + " ? WHERE MaSP = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, slSuaDoi);
			stmt.setString(2, maSP);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
