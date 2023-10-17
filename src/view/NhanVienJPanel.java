package view;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao.TaiKhoan_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class NhanVienJPanel extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMa;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtChucVu;
	private JTextField txtLuong;
	private JTextField txtTimKiem;
	private JTable tableNhanVien;
	private JTextField txtDienThoai;
	private JTextField txtMatKhau;
	private JTextField txtTenDangNhap;
	private JButton btThem, btSua, btXoa, btLamMoi;
	private JButton btTKThem, btTKSua, btTKXoa, btTKLamMoi;
	private JDateChooser dateChooserNgaySinh;
	private JRadioButton radNam, radNu;
	private JComboBox<String> cbbLocGioiTinh, cbbLocVaiTro, cbbMaNhanVien;
	private JTabbedPane tabbedPane;

	private DefaultTableModel modelNhanVien;
	private JTable tableTaiKhoan;
	private DefaultTableModel modelTaiKhoan;

	private TaiKhoan_DAO taiKhoan;
	private NhanVien_DAO nhanVien;

	/**
	 * Create the panel.
	 */
	public NhanVienJPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(10, 0, 960, 660);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_1.setBounds(20, 32, 919, 241);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã NV:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(98, 30, 46, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Họ Tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(98, 60, 46, 14);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Địa chỉ:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(98, 90, 63, 14);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Điện thoại:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(98, 120, 63, 14);
		panel_1.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Email:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4.setBounds(98, 150, 46, 14);
		panel_1.add(lblNewLabel_1_4);

		txtMa = new JTextField();
		txtMa.setBounds(166, 27, 296, 26);
		panel_1.add(txtMa);
		txtMa.setColumns(10);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(166, 57, 296, 26);
		panel_1.add(txtHoTen);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(166, 87, 296, 26);
		panel_1.add(txtDiaChi);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(166, 147, 296, 26);
		panel_1.add(txtEmail);

		JLabel lblNewLabel_1_5 = new JLabel("Ngày sinh:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_5.setBounds(528, 30, 63, 14);
		panel_1.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_1_1 = new JLabel("Chức vụ:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(528, 120, 63, 14);
		panel_1.add(lblNewLabel_1_1_1);

		txtChucVu = new JTextField();
		txtChucVu.setColumns(10);
		txtChucVu.setBounds(596, 120, 194, 26);
		panel_1.add(txtChucVu);

		JLabel lblNewLabel_1_2_1 = new JLabel("Lương:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1.setBounds(528, 90, 63, 14);
		panel_1.add(lblNewLabel_1_2_1);

		txtLuong = new JTextField();
		txtLuong.setColumns(10);
		txtLuong.setBounds(596, 90, 194, 26);
		panel_1.add(txtLuong);

		btThem = new JButton("Thêm ");
		btThem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btThem.setBackground(new Color(95, 158, 160));
		btThem.setBounds(311, 195, 69, 23);
		panel_1.add(btThem);

		btSua = new JButton("Sửa");
		btSua.setFont(new Font("Tahoma", Font.BOLD, 11));
		btSua.setBackground(new Color(95, 158, 160));
		btSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btSua.setBounds(417, 195, 69, 23);
		panel_1.add(btSua);

		btLamMoi = new JButton("Làm mới");
		btLamMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
		btLamMoi.setBackground(new Color(95, 158, 160));
		btLamMoi.setBounds(623, 195, 86, 23);
		panel_1.add(btLamMoi);

		txtDienThoai = new JTextField();
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(166, 116, 296, 26);
		panel_1.add(txtDienThoai);

		JLabel lblNewLabel_1_3_1 = new JLabel("Giới tính:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3_1.setBounds(528, 60, 53, 14);
		panel_1.add(lblNewLabel_1_3_1);

		radNam = new JRadioButton("Nam");
		radNam.setBackground(SystemColor.controlHighlight);
		radNam.setBounds(596, 60, 77, 23);
		panel_1.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setBackground(SystemColor.controlHighlight);
		radNu.setBounds(713, 60, 77, 23);
		panel_1.add(radNu);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radNam);
		buttonGroup.add(radNu);

		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBounds(599, 24, 191, 26);
		panel_1.add(dateChooserNgaySinh);

		btXoa = new JButton("Xóa");
		btXoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		btXoa.setBackground(new Color(95, 158, 160));
		btXoa.setBounds(522, 195, 69, 23);
		panel_1.add(btXoa);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192), 2, true),
				new LineBorder(new Color(227, 227, 227), 2, true)));
		panel_2.setBounds(20, 307, 570, 90);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_4_2 = new JLabel("Lọc theo giới tính:");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_4_2.setBounds(10, 35, 121, 14);
		panel_2.add(lblNewLabel_1_4_2);

		cbbLocGioiTinh = new JComboBox();
		cbbLocGioiTinh.setBounds(141, 34, 124, 25);
		panel_2.add(cbbLocGioiTinh);

		JLabel lblNewLabel_1_4_2_1 = new JLabel("Lọc theo vai trò:");
		lblNewLabel_1_4_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_4_2_1.setBounds(317, 35, 107, 14);
		panel_2.add(lblNewLabel_1_4_2_1);

		cbbLocVaiTro = new JComboBox();
		cbbLocVaiTro.setBounds(424, 33, 124, 25);
		panel_2.add(cbbLocVaiTro);

		JLabel lblLc = new JLabel("Lọc");
		lblLc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLc.setBounds(20, 284, 159, 24);
		panel.add(lblLc);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192), 2, true),
				new LineBorder(new Color(227, 227, 227), 2, true)));
		panel_2_1.setBounds(613, 307, 326, 90);
		panel.add(panel_2_1);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(24, 30, 292, 25);
		panel_2_1.add(txtTimKiem);

		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTmKim.setBounds(614, 284, 159, 24);
		panel.add(lblTmKim);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 408, 919, 241);
		panel.add(tabbedPane);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("DS Thông Tin", null, panel_3, null);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("DS Tài Khoản", null, panel_4, null);
		panel_4.setLayout(null);

		String[] headerTaiKhoan = { "Mã NV", "Tên đăng nhập", "Mật khẩu" };
		modelTaiKhoan = new DefaultTableModel(headerTaiKhoan, 0);
		tableTaiKhoan = new JTable(modelTaiKhoan);
		tableTaiKhoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scpTaiKhoan = new JScrollPane(tableTaiKhoan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpTaiKhoan.setBounds(71, 21, 423, 142);
		panel_4.add(scpTaiKhoan);

		txtMatKhau = new JTextField();
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(688, 81, 194, 23);
		panel_4.add(txtMatKhau);

		JLabel lblNewLabel_1_1_2 = new JLabel("Mật khẩu:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_2.setBounds(571, 84, 95, 14);
		panel_4.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_6 = new JLabel("Tên đăng nhập:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_6.setBounds(571, 54, 95, 14);
		panel_4.add(lblNewLabel_1_6);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setColumns(10);
		txtTenDangNhap.setBounds(688, 51, 194, 23);
		panel_4.add(txtTenDangNhap);

		JLabel lblNewLabel_1_6_1 = new JLabel("Mã NV:");
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_6_1.setBounds(571, 22, 95, 14);
		panel_4.add(lblNewLabel_1_6_1);

		cbbMaNhanVien = new JComboBox();
		cbbMaNhanVien.setEditable(false);
		cbbMaNhanVien.setBounds(687, 18, 195, 23);
		panel_4.add(cbbMaNhanVien);

		btTKThem = new JButton("Thêm ");
		btTKThem.setBackground(new Color(95, 158, 160));
		btTKThem.setBounds(592, 139, 72, 23);
		panel_4.add(btTKThem);

		btTKSua = new JButton("Sửa");
		btTKSua.setBackground(new Color(95, 158, 160));
		btTKSua.setBounds(682, 139, 61, 23);
		panel_4.add(btTKSua);

		btTKXoa = new JButton("Xóa");
		btTKXoa.setBackground(new Color(95, 158, 160));
		btTKXoa.setBounds(753, 139, 61, 23);
		panel_4.add(btTKXoa);

		btTKLamMoi = new JButton("Làm mới");
		btTKLamMoi.setBackground(new Color(95, 158, 160));
		btTKLamMoi.setBounds(824, 139, 80, 23);
		panel_4.add(btTKLamMoi);

		String[] headerDangLamViec = { "Mã NV", "Họ tên", "Địa chỉ", "Điện thoại", "Email", "Ngày sinh", "Giới tính",
				"Lương", "Chức vụ" };
		modelNhanVien = new DefaultTableModel(headerDangLamViec, 0);
		tableNhanVien = new JTable(modelNhanVien);
		tableNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scpThongTin = new JScrollPane(tableNhanVien, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpThongTin.setBounds(0, 0, 914, 202);
		panel_3.add(scpThongTin);

		JLabel lblThngTinNhn = new JLabel("Thông tin nhân viên");
		lblThngTinNhn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThngTinNhn.setBounds(20, 11, 159, 24);
		panel.add(lblThngTinNhn);

		// thiết lập dữ liệu cho table nhân viên
		try {
			nhanVien = new NhanVien_DAO();
			for (NhanVien nv : nhanVien.getAllNhanVien()) {
				Object[] rowNV = { nv.getMaNhanVien(), nv.getHoten(), nv.getDiaChi(), nv.getDienThoai(), nv.getEmail(),
						nv.getNgaySinh(), nv.getGioiTinh(), nv.getLuong(), nv.getChucVu() };
				modelNhanVien.addRow(rowNV);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// thiết lập dữ liệu cho table tài khoản
		try {
			taiKhoan = new TaiKhoan_DAO();
			for (TaiKhoan tk : taiKhoan.getAllTaiKhoan()) {
				Object[] rowNV = { tk.getMaNhanVien(), tk.getTenDangNhap(), tk.getMatKhau() };
				modelTaiKhoan.addRow(rowNV);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		btThem.addActionListener(this);
		btXoa.addActionListener(this);
		btSua.addActionListener(this);
		btLamMoi.addActionListener(this);
		btTKThem.addActionListener(this);
		btTKXoa.addActionListener(this);
		btTKSua.addActionListener(this);
		btTKLamMoi.addActionListener(this);
		btTKLamMoi.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		tableTaiKhoan.addMouseListener(this);
		cbbLocGioiTinh.addActionListener(this);
		cbbLocVaiTro.addActionListener(this);
		txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
			// Thêm một DocumentListener vào Document của JTextField txtTimKiem, để lắng
			// nghe sự kiện khi nội dung của JTextField thay đổi

			@Override
			public void removeUpdate(DocumentEvent e) {
				findData();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				findData();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				findData();

			}

			private void findData() {
				String text = txtTimKiem.getText().trim();
				cbbLocGioiTinh.setSelectedItem("Tất cả");
				cbbLocVaiTro.setSelectedItem("Tất cả");
				DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();

				// Tạo một bộ lọc để lấy các dòng có giá trị tên nhân viên trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(text, 1);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				tableNhanVien.setRowSorter(sorter);
			}
		});

		cbbMaNhanVien.setModel(taiKhoan.loadComBoBox("MaNV", "NhanVien"));
		cbbMaNhanVien.setSelectedItem(null);
		cbbLocGioiTinh.setModel(new DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ" }));
		// cbbLocGioiTinh.setSelectedItem(null);
		cbbLocVaiTro.setModel(new DefaultComboBoxModel<>(new String[] { "Tất cả", "Thanh toán", "CSKH", "Quản lý kho",
				"Quản lý", "Giao hàng", "Bán hàng", "NV bán thời gian" }));
		// cbbLocVaiTro.setSelectedItem(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btThem))
			them();
		else if (o.equals(btSua))
			sua();
		else if (o.equals(btXoa))
			xoa();
		else if (o.equals(btLamMoi))
			lamMoi();
		else if (o.equals(btTKThem))
			themTK();
		else if (o.equals(btTKSua))
			suaTK();
		else if (o.equals(btTKXoa))
			xoaTK();
		else if (o.equals(btTKLamMoi)) {
			lamMoiTK();
		} else if (o.equals(cbbLocGioiTinh)) {
			String gioiTinh = (String) cbbLocGioiTinh.getSelectedItem();
			filterNhanVienByGioiTinh(gioiTinh);
		} else if (o.equals(cbbLocVaiTro)) {
			String chucVu = (String) cbbLocVaiTro.getSelectedItem();
			String gioiTinh = (String) cbbLocGioiTinh.getSelectedItem();
			filterNhanVienByChucVu(chucVu, gioiTinh);

		}

	}

	private void showMessage(String message, JTextField txt) {
		JOptionPane.showMessageDialog(null, message);
		txt.requestFocus();
	}

	private NhanVien revertNhanVienFromtxt() {
		NhanVien nv = null;
		String maNV = txtMa.getText();
		String hoTen = txtHoTen.getText();
		String diaChi = txtDiaChi.getText();
		String dienThoai = txtDienThoai.getText();
		String email = txtEmail.getText();
		java.util.Date ngaySinh = dateChooserNgaySinh.getDate();
		String gioiTinh = null;
		if (radNam.isSelected())
			gioiTinh = radNam.getText();
		else if (radNu.isSelected())
			gioiTinh = radNu.getText();
		String chucVu = txtChucVu.getText();
		long luong = Long.parseLong(txtLuong.getText());
		nv = new NhanVien(maNV, hoTen, diaChi, dienThoai, email, new java.sql.Date(ngaySinh.getTime()), gioiTinh, luong,
				chucVu);
		return nv;
	}

	private boolean validDataNhanVien() {
		String maNV = txtMa.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String dienThoai = txtDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		String chucVu = txtChucVu.getText();
		String luong = txtLuong.getText().trim();
		if (!(maNV.length() > 0)) {
			showMessage("Mã nhân viên không được rỗng", txtMa);
			return false;
		} else if (!maNV.matches("NV\\d{3}")) {
			showMessage("Mã nhân viên phải có dạng NV\\d{3}", txtMa);
			return false;
		}
		if (hoTen.length() < 0 || !hoTen.matches("^[A-Z]\\p{L}+(\\s[A-Z]\\p{L}*)+")) {
			showMessage("Tên Nhân viên không hợp lệ ", txtHoTen);
			return false;
		}
		if (diaChi.length() < 0) {
			showMessage(" Địa chỉ không hợp lệ ", txtDiaChi);
			return false;
		}
		if (dienThoai.length() < 0 || !dienThoai.matches("^0[1-9]{9}")) {
			showMessage("Số điện thoại không hợp lệ ", txtDienThoai);
			return false;
		}
		if (email.length() < 0 || !email.matches("[a-z]\\w+@(gmail|yahoo|iuh)\\.com")) {
			showMessage(" Email không hợp lệ ", txtEmail);
			return false;
		}
		if (luong.length() > 0) {
			try {
				long x = Long.parseLong(luong);
				if (x < 0) {
					showMessage("Error: Lương phải là số dương", txtLuong);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessage("Error: Lương phải là số ", txtLuong);
				return false;
			}
		} else {
			showMessage("Eror: Bạn chưa nhập lương ", txtLuong);
			return false;
		}
		if (chucVu.length() < 0) {
			showMessage(" Chưa nhập chức vụ  ", txtChucVu);
			return false;
		}

		return true;
	}

	private boolean checkExist() {
		String maNV = txtMa.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		if (true) {
			DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
			boolean isMaNVTrung = false;
			for (int i = 0; i < model.getRowCount(); i++) {
				String maNVTable = model.getValueAt(i, 0).toString();
				if (maNVTable.equals(maNV)) {
					isMaNVTrung = true;
					break;
				}
			}
			if (isMaNVTrung) {
				showMessage("Mã nhân viên đã tồn tại trong danh sách", txtMa);
				return false;
			}
		}
		return true;
	}

	private void updateTable(ArrayList<NhanVien> dsNVLoc) {
		DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
		model.setRowCount(0); // Xóa các dòng hiện tại trong bảng

		for (NhanVien nv : dsNVLoc) {
			Object[] rowNV = { nv.getMaNhanVien(), nv.getHoten(), nv.getDiaChi(), nv.getDienThoai(), nv.getEmail(),
					nv.getNgaySinh(), nv.getGioiTinh(), nv.getLuong(), nv.getChucVu() };
			modelNhanVien.addRow(rowNV);
		}
	}

	private void filterNhanVienByGioiTinh(String dieuKien) {
		ArrayList<NhanVien> dsNV = nhanVien.getAllNhanVien();
		ArrayList<NhanVien> dsNVLoc = new ArrayList<>();
		if (dieuKien.equals("Tất cả")) {
			dsNVLoc.addAll(dsNV);
		} else {
			for (NhanVien nv : dsNV) {
				if (nv.getGioiTinh().equals(dieuKien)) {
					dsNVLoc.add(nv);
				}
			}
		}
		updateTable(dsNVLoc);
		lamMoi();
	}

	private void filterNhanVienByChucVu(String dieuKien, String gioiTinh) {
		ArrayList<NhanVien> dsNV = nhanVien.getAllNhanVien();
		ArrayList<NhanVien> dsNVLoc = new ArrayList<>();
		if (dieuKien.equals("Tất cả") && gioiTinh.equals("Tất cả")) {
			dsNVLoc.addAll(dsNV);
		} else {
			for (NhanVien nv : dsNV) {
				if (nv.getChucVu().equals(dieuKien) && nv.getGioiTinh().equals(gioiTinh)) {
					dsNVLoc.add(nv);
				}
				if (nv.getChucVu().equals(dieuKien) && gioiTinh.equals("Tất cả")) {
					dsNVLoc.add(nv);
				}

			}
		}
		updateTable(dsNVLoc);
		lamMoi();
	}

	private void them() {
		if (validDataNhanVien() && checkExist()) {
			NhanVien nv = revertNhanVienFromtxt();
			Object[] rowNV = { nv.getMaNhanVien(), nv.getHoten(), nv.getDiaChi(), nv.getDienThoai(), nv.getEmail(),
					nv.getNgaySinh(), nv.getGioiTinh(), nv.getLuong(), nv.getChucVu() };
			modelNhanVien.addRow(rowNV);
			nhanVien.themNhanVien(nv);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
			int tb = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo tài khoản ngay", "Tạo Tài Khoản",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				tabbedPane.setSelectedIndex(1);
				tableTaiKhoan.setRowSelectionAllowed(false);
				cbbMaNhanVien.setModel(taiKhoan.loadComBoBox("MaNV", "NhanVien"));
				cbbMaNhanVien.setSelectedItem(nv.getMaNhanVien());
				txtTenDangNhap.setText("");
				txtMatKhau.setText("");
				showMessage("Vui lòng nhập tên đăng nhập và mật khẩu", txtTenDangNhap);
			}

		}
	}

	private void sua() {
		int row = tableNhanVien.getSelectedRow();
		NhanVien nv = revertNhanVienFromtxt();
		String selectedmaNhanVien = tableNhanVien.getValueAt(row, 0).toString();
		if (!nv.getMaNhanVien().equals(selectedmaNhanVien)) {
			JOptionPane.showMessageDialog(null, "Không được thay đổi mã nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

		else {
			modelNhanVien.setValueAt(nv.getHoten(), row, 1);
			modelNhanVien.setValueAt(nv.getDiaChi(), row, 2);
			modelNhanVien.setValueAt(nv.getDienThoai(), row, 3);
			modelNhanVien.setValueAt(nv.getEmail(), row, 4);
			modelNhanVien.setValueAt(nv.getNgaySinh(), row, 5);
			modelNhanVien.setValueAt(nv.getGioiTinh(), row, 6);
			modelNhanVien.setValueAt(nv.getLuong(), row, 7);
			modelNhanVien.setValueAt(nv.getChucVu(), row, 7);
			modelNhanVien.fireTableRowsUpdated(row, row);
			nhanVien.suaNhanVien(nv);
			JOptionPane.showMessageDialog(null,
					"Thực hiện sửa đổi thành công thông tin nhân viên có mã: " + nv.getMaNhanVien());
		}

	}

	private void xoa() {
		int i = tableNhanVien.getSelectedRow();
		String maNV = txtMa.getText();
		if (i != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Ban co chac chan muon xoa", "DELETE",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				nhanVien.xoaNhanVien(maNV);
				modelNhanVien.removeRow(i);
				int iTK = tableTaiKhoan.getSelectedRow();
				if (iTK != -1) {
					taiKhoan.xoaTaiKhoan(maNV);
					modelTaiKhoan.removeRow(iTK);
				}
				JOptionPane.showMessageDialog(null, "Đã xóa thành công");

				lamMoi();
			}
		} else
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần xóa");
	}

	private void lamMoi() {
		tableNhanVien.clearSelection();
		txtMa.setText("");
		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtDienThoai.setText("");
		txtEmail.setText("");
		dateChooserNgaySinh.setDate(null);
		txtLuong.setText("");
		txtChucVu.setText("");

	}

	private boolean validDataTaiKhoan() {
		String maNV = cbbMaNhanVien.getSelectedItem().toString();
		String tenDangNhap = txtTenDangNhap.getText().trim();
		String matKhau = txtMatKhau.getText().trim();
		if (!(tenDangNhap.length() > 0)) {
			showMessage("Chưa nhập tên đăng nhập", txtTenDangNhap);
			return false;
		} else if (!(matKhau.length() > 0)) {
			showMessage("Chưa nhập mật khẩu ", txtTenDangNhap);
			return false;
		} else if (!matKhau.matches("^nv\\d{3}.*")) {
			showMessage("Mật khẩu phải có dạng nv\\\\d{3}.* ", txtTenDangNhap);
			return false;
		}
		return true;
	}

	private boolean checkExistTK() {
		String maNV = cbbMaNhanVien.getSelectedItem().toString();
		String tenDangNhap = txtTenDangNhap.getText().trim();
		String matKhau = txtMatKhau.getText().trim();
		if (true) {
			DefaultTableModel model = (DefaultTableModel) tableTaiKhoan.getModel();
			boolean isMaNVTrung = false;
			boolean isTenDNTrung = false;
			for (int i = 0; i < model.getRowCount(); i++) {
				String maNVTable = model.getValueAt(i, 0).toString();
				String tenDNtable = model.getValueAt(i, 1).toString();
				if (maNVTable.equals(maNV)) {
					isMaNVTrung = true;
					break;
				}
				if (tenDNtable.equals(tenDangNhap)) {
					isTenDNTrung = true;
					break;
				}
			}
			if (isMaNVTrung) {
				JOptionPane.showMessageDialog(null, "Nhân viên mã " + maNV + " đã có tài khoản");
				cbbMaNhanVien.setSelectedItem(null);
				return false;
			}
			if (isTenDNTrung) {
				showMessage("Bị trùng tên đăng nhập", txtTenDangNhap);
				return false;
			}
		}
		return true;
	}

	private void themTK() {
		String maNV = cbbMaNhanVien.getSelectedItem().toString();
		String tenDangNhap = txtTenDangNhap.getText();
		String matKhau = txtMatKhau.getText();
		if (validDataTaiKhoan() && checkExistTK()) {
			TaiKhoan tk = new TaiKhoan(maNV, tenDangNhap, matKhau);
			Object row[] = { tk.getMaNhanVien(), tk.getTenDangNhap(), tk.getMatKhau() };
			modelTaiKhoan.addRow(row);
			taiKhoan.themTaiKhoan(tk);
			JOptionPane.showMessageDialog(null, "Tạo tài khoản mới thành công");
		}

	}

	private void xoaTK() {
		int i = tableTaiKhoan.getSelectedRow();
		String tenDangNhap = txtTenDangNhap.getText();
		if (i != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Ban co chac muon xoa", "DELETE", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				taiKhoan.xoaTaiKhoan(tenDangNhap);
				modelTaiKhoan.removeRow(i);
				JOptionPane.showInternalMessageDialog(null, "Đã xóa thành công", "Xóa",
						JOptionPane.INFORMATION_MESSAGE);
				lamMoiTK();
			}
		} else
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn tài khảon cần xóa");
	}

	private void suaTK() {
		int row = tableTaiKhoan.getSelectedRow();
		String maNV = cbbMaNhanVien.getSelectedItem().toString();
		String tenDangNhap = txtTenDangNhap.getText();
		String matKhau = txtMatKhau.getText();
		if (validDataTaiKhoan()) {
			TaiKhoan tk = new TaiKhoan(maNV, tenDangNhap, matKhau);
			modelTaiKhoan.setValueAt(tk.getTenDangNhap(), row, 1);
			modelTaiKhoan.setValueAt(tk.getMatKhau(), row, 2);
			taiKhoan.suaTaiKhoan(tk);
			JOptionPane.showMessageDialog(null,
					"Thay đổi thông tin tài khoản thành công cho mã nhân viên" + tk.getMaNhanVien());
		}
	}

	private void lamMoiTK() {
		txtTenDangNhap.setText("");
		txtMatKhau.setText("");
		cbbMaNhanVien.setSelectedItem(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tableTaiKhoan)) {
			int rowTK = tableTaiKhoan.getSelectedRow();
			if (rowTK >= 0) {
				tableTaiKhoan.setRowSelectionAllowed(true);
				cbbMaNhanVien.setSelectedItem(tableTaiKhoan.getValueAt(rowTK, 0).toString());
				txtTenDangNhap.setText(tableTaiKhoan.getValueAt(rowTK, 1).toString());
				txtMatKhau.setText(tableTaiKhoan.getValueAt(rowTK, 2).toString());
				cbbMaNhanVien.setEditable(false);
			}

		}
		if (o.equals(tableNhanVien)) {
			int rowNV = tableNhanVien.getSelectedRow();
			if (rowNV >= 0) {

				String maNV = tableNhanVien.getValueAt(rowNV, 0).toString();
				txtMa.setText(tableNhanVien.getValueAt(rowNV, 0).toString());
				txtHoTen.setText(tableNhanVien.getValueAt(rowNV, 1).toString());
				txtDiaChi.setText(tableNhanVien.getValueAt(rowNV, 2).toString());
				txtDienThoai.setText(tableNhanVien.getValueAt(rowNV, 3).toString());
				txtEmail.setText(tableNhanVien.getValueAt(rowNV, 4).toString());
				if (modelNhanVien.getValueAt(rowNV, 6).toString().equalsIgnoreCase("Nam")) {
					radNam.setSelected(true);
					radNu.setSelected(false);
				} else {
					radNu.setSelected(true);
					radNam.setSelected(false);
				}
				txtLuong.setText(tableNhanVien.getValueAt(rowNV, 7).toString());
				txtChucVu.setText(tableNhanVien.getValueAt(rowNV, 8).toString());

				// Lấy giá trị ngày tháng từ cột 5 của hàng có chỉ số rowNV
				String ngaySinhStr = tableNhanVien.getValueAt(rowNV, 5).toString();
				// Chuyển đổi giá trị ngày sinh từ kiểu chuỗi sang kiểu Date
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date ngaySinh = dateFormat.parse(ngaySinhStr);
					dateChooserNgaySinh.setDate(ngaySinh);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < tableTaiKhoan.getRowCount(); i++) {
					if (tableTaiKhoan.getValueAt(i, 0).equals(maNV)) {
						tableTaiKhoan.setRowSelectionAllowed(true);
						tableTaiKhoan.setRowSelectionInterval(i, i);
						break;
					} else
						tableTaiKhoan.setRowSelectionAllowed(false);

				}
				int rowTK = tableTaiKhoan.getSelectedRow();
				if (rowTK != -1) {
					cbbMaNhanVien.setSelectedItem(tableTaiKhoan.getValueAt(rowTK, 0).toString());
					txtTenDangNhap.setText(tableTaiKhoan.getValueAt(rowTK, 1).toString());
					txtMatKhau.setText(tableTaiKhoan.getValueAt(rowTK, 2).toString());
				} else {
					cbbMaNhanVien.setSelectedItem(null);
					txtTenDangNhap.setText("");
					txtMatKhau.setText("");
				}

			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
