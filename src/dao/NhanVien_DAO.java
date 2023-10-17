package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {


	public ArrayList<NhanVien> getAllNhanVien() {
		ConnectDB con = ConnectDB.getInstance();
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			con.connect();
			String sql = "SELECT *FROM NhanVien";

			Statement statement = con.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsNhanVien.add(new NhanVien(rs.getString("MaNV"), rs.getString("HoTen"), rs.getString("DiaChi"),
						rs.getString("DienThoai"), rs.getString("Email"), rs.getDate("NgaySinh"),
						rs.getString("GioiTinh"), rs.getLong("Luong"), rs.getString("ChucVu")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	public String getMaNVByHoTen(String hoTen) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String maNV = "";
		String sql = "SELECT MaNV FROM NhanVien where HoTen = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hoTen);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				maNV = rs.getString("MaNV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return maNV;
	}
	public void themNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO NhanVien(MaNV,HoTen, DiaChi, DienThoai, Email,NgaySinh, GioiTinh, Luong, ChucVu)  VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getHoten());
			stmt.setString(3, nv.getDiaChi());
			stmt.setString(4, nv.getDienThoai());
			stmt.setString(5, nv.getEmail());
			stmt.setDate(6, (Date) nv.getNgaySinh());
			stmt.setString(7, nv.getGioiTinh());
			stmt.setLong(8, nv.getLuong());
			stmt.setString(9, nv.getChucVu());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	public void xoaNhanVien(String maNV) {
		TaiKhoan_DAO taiKhoan = new TaiKhoan_DAO();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "Delete from NhanVien where MaNV = ?";
		try {
			taiKhoan.xoaTaiKhoan(maNV);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void suaNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE NhanVien " + " set HoTen = ?, " + " DiaChi = ?, " + " DienThoai = ?, " + " Email = ?, "
				+ " NgaySinh = ?, " + " GioiTinh = ?, " + " Luong = ?, " + "ChucVu = ? where MaNV = ?;";
		try {
			stmt = con.prepareStatement(sql);
			// stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(1, nv.getHoten());
			stmt.setString(2, nv.getDiaChi());
			stmt.setString(3, nv.getDienThoai());
			stmt.setString(4, nv.getEmail());
			stmt.setDate(5, nv.getNgaySinh());
			stmt.setString(6, nv.getGioiTinh());
			stmt.setLong(7, nv.getLuong());
			stmt.setString(8, nv.getChucVu());
			stmt.setString(9, nv.getMaNhanVien());
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

}
