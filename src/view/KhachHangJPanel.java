package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class KhachHangJPanel extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField textField_4;
	private JTable table;
	private KhachHang_DAO khachHang;
	private DefaultTableModel model;
	private JButton btnNewButton_1, btnNewButton_1_1, btnNewButton_1_2, btnNewButton_1_1_1;
	private JRadioButton rbNam, rbNu;
	private JLabel lblNewLabel_7;
	private JTextArea C;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public KhachHangJPanel() {
		setBackground(SystemColor.controlHighlight);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setSize(960, 660);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thi\u1EBFt l\u1EADp th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEFT, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(20, 22, 916, 279);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 51, 139, 33);
		panel.add(lblNewLabel);

		txtMaKH = new JTextField();
		txtMaKH.setBounds(140, 60, 212, 25);
		panel.add(txtMaKH);
		txtMaKH.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 98, 139, 33);
		panel.add(lblNewLabel_1);

		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(140, 107, 212, 25);
		panel.add(txtTenKH);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 192, 139, 33);
		panel.add(lblNewLabel_2);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(140, 201, 212, 25);
		panel.add(txtSDT);

		JLabel lblNewLabel_3 = new JLabel("Giới tính:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 145, 139, 33);
		panel.add(lblNewLabel_3);

		rbNam = new JRadioButton("Nam", false);
		rbNam.setBackground(SystemColor.controlHighlight);
		rbNam.setFont(new Font("Tahoma", Font.BOLD, 12));
		rbNam.setBounds(140, 147, 140, 30);
		panel.add(rbNam);

		rbNu = new JRadioButton("Nữ");
		rbNu.setBackground(SystemColor.controlHighlight);
		rbNu.setFont(new Font("Tahoma", Font.BOLD, 12));
		rbNu.setBounds(282, 148, 140, 30);
		panel.add(rbNu);

		ButtonGroup btg = new ButtonGroup();
		btg.add(rbNu);
		btg.add(rbNam);

		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(430, 51, 139, 33);
		panel.add(lblNewLabel_4);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(528, 59, 212, 25);
		panel.add(txtEmail);

		JLabel lblNewLabel_4_1 = new JLabel("Địa chỉ:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1.setBounds(430, 98, 139, 33);
		panel.add(lblNewLabel_4_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(528, 103, 212, 89);
		panel.add(scrollPane);

		C = new JTextArea();
		scrollPane.setViewportView(C);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(766, 19, 139, 237);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnNewButton_1 = new JButton("Thêm");
		btnNewButton_1
				.setIcon(new ImageIcon(KhachHangJPanel.class.getResource("/Images/icons8-macos-maximize-20.png")));
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(10, 11, 120, 40);
		panel_1.add(btnNewButton_1);

		btnNewButton_1_1 = new JButton("Sửa");
		btnNewButton_1_1.setIcon(new ImageIcon(KhachHangJPanel.class.getResource("/Images/icons8-change-20.png")));
		btnNewButton_1_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1_1.setBounds(10, 62, 120, 40);
		panel_1.add(btnNewButton_1_1);

		btnNewButton_1_2 = new JButton("Làm mới");
		btnNewButton_1_2.setIcon(new ImageIcon(KhachHangJPanel.class.getResource("/Images/icons8-remove-20.png")));
		btnNewButton_1_2.setBackground(new Color(95, 158, 160));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1_2.setBounds(10, 171, 120, 40);
		panel_1.add(btnNewButton_1_2);

		btnNewButton_1_1_1 = new JButton("Xóa");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1_1_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1_1_1.setBounds(10, 120, 120, 40);
		panel_1.add(btnNewButton_1_1_1);

		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(191, 243, 363, 14);
		panel.add(lblNewLabel_7);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(20, 313, 916, 320);
		add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Tìm kiếm theo mã:");
		lblNewLabel_5.setBounds(21, 11, 124, 21);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(lblNewLabel_5);

		textField_4 = new JTextField();
		textField_4.setBounds(150, 11, 296, 25);
		textField_4.setColumns(10);
		panel_2.add(textField_4);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 56, 726, 209);
		panel_2.add(scrollPane_1);

		String[] column = { "Mã KH", "Tên KH", "Giới tính", "Địa chỉ", "SDT", "Email" };
		model = new DefaultTableModel(column, 0);
		table = new JTable(model);
		scrollPane_1.setViewportView(table);
		table.setRowSelectionAllowed(true);
		khachHang = new KhachHang_DAO();
		for (KhachHang k : khachHang.getAllKhachHang()) {
			Object[] rowData = { k.getMaKH(), k.getTenKH(), k.getGioiTinh(), k.getDiaChi(), k.getSDT(), k.getEmail() };
			model.addRow(rowData);
		}

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.controlHighlight);
		panel_4.setBounds(757, 38, 146, 228);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Giới tính:");
		lblNewLabel_6.setBounds(22, 72, 124, 21);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(lblNewLabel_6);

		comboBox = new JComboBox();
		comboBox.setBounds(22, 106, 114, 22);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Nam", "Nữ" }));
		panel_4.add(comboBox);

		btnNewButton_1.addActionListener(this);
		btnNewButton_1_1.addActionListener(this);
		btnNewButton_1_2.addActionListener(this);
		btnNewButton_1_1_1.addActionListener(this);
		comboBox.addActionListener(this);
		table.addMouseListener(this);
		textField_4.getDocument().addDocumentListener(new DocumentListener() {

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
				String text = textField_4.getText().trim();
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();

				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(text, 0);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model2);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				table.setRowSorter(sorter);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnNewButton_1)) {
			if (kiemTra()) {
				themKH();
			}
		} else if (e.getSource().equals(btnNewButton_1_1_1)) {
			xoaKH();
		} else if (e.getSource().equals(btnNewButton_1_1)) {
			suaKH();
		} else if (e.getSource().equals(btnNewButton_1_2)) {
			lamMoi();
		}
		String gioiTinh = (String) comboBox.getSelectedItem();
		filterKhachHangByGioiTinh(gioiTinh);

	}

	private void updateTable(ArrayList<KhachHang> danhSachKhachHangLoc) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Xóa các dòng hiện tại trong bảng

		for (KhachHang khachHang : danhSachKhachHangLoc) {
			String[] row = { khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getGioiTinh(), khachHang.getDiaChi(),
					khachHang.getSDT(), khachHang.getEmail() };
			model.addRow(row); // Thêm dòng mới vào bảng
		}
	}

	private void filterKhachHangByGioiTinh(String gioiTinh) {
		ArrayList<KhachHang> danhSachKhachHang = khachHang.getAllKhachHang();
		ArrayList<KhachHang> danhSachKhachHangLoc = new ArrayList<>();

		if (gioiTinh.equals("Tất cả")) {
			danhSachKhachHangLoc.addAll(danhSachKhachHang);
		} else {
			for (KhachHang kh : danhSachKhachHang) {
				if (kh.getGioiTinh().equals(gioiTinh)) {
					danhSachKhachHangLoc.add(kh);
				}
			}
		}
		updateTable(danhSachKhachHangLoc);
	}

	private void lamMoi() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		textField_4.setText("");
		C.setText("");
	}

	private void showMess(String mess, JTextField txt) {
		txt.requestFocus();
		lblNewLabel_7.setText(mess);

	}

	private boolean kiemTra() {
		String maKH = txtMaKH.getText().trim();
		String tenKH = txtTenKH.getText();
		String soDienThoai = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = C.getText();

		if (maKH.length() < 0 || !maKH.matches("^KH\\d{3}")) {
			showMess("Cú pháp mã KH phải là KH\\d{3} ", txtMaKH);
			return false;
		}
		if (tenKH.length() < 0 || !tenKH.matches("^[A-Z]\\p{L}+(\\s[A-Z]\\p{L}*)+")) {
			showMess("Tên khách hàng không hợp lệ ", txtTenKH);
			return false;
		}
		if (soDienThoai.length() < 0 || !soDienThoai.matches("^0[1-9]{9}")) {
			showMess("Số điện thoại không hợp lệ ", txtSDT);
			return false;
		}
		if (email.length() < 0 || !email.matches("[a-z]\\w+@(gmail|yahoo|iuh)\\.com")) {
			showMess(" Email không hợp lệ ", txtEmail);
			return false;
		}
		if (diaChi.length() < 0) {
			showMess(" Địa chỉ không hợp lệ ", textField_4);
			return false;
		}
		showMess("", txtMaKH);
		return true;
	}

	private boolean themKH() {
		KhachHang k = layKH();
		if (khachHang.themKH(k)) {
			String[] row = { k.getMaKH(), k.getTenKH(), k.getGioiTinh(), k.getDiaChi(), k.getSDT(), k.getEmail() };
			model.addRow(row);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
			return true;
		} else {
			return false;
		}
	}

	private KhachHang layKH() {
		String maKH = txtMaKH.getText().trim();
		String tenKH = txtTenKH.getText();
		String gioiTinh = "";
		if (rbNam.isSelected()) {
			gioiTinh = "Nam";
		} else {
			gioiTinh = "Nữ";
		}
		String soDienThoai = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = C.getText();

		KhachHang k = new KhachHang(maKH, tenKH, gioiTinh, diaChi, soDienThoai, email);
		return k;
	}

	private void xoaKH() {
		int i = table.getSelectedRow();
		if (i != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Ban co chac chan muon xoa", "DELETE",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				KhachHang k = layKH();
				khachHang.xoaLopHoc(k.getMaKH());
				model.removeRow(i);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần xóa");
		}
	}

	private void suaKH() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng cần sửa");
		} else {
			if (kiemTra()) {
				KhachHang k = layKH();
				khachHang.suaKH(k);
				table.setValueAt(k.getTenKH(), row, 1);
				table.setValueAt(k.getDiaChi(), row, 3);
				table.setValueAt(k.getGioiTinh(), row, 2);
				table.setValueAt(k.getSDT(), row, 4);
				table.setValueAt(k.getEmail(), row, 5);
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				txtMaKH.setEditable(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaKH.setText(table.getValueAt(row, 0).toString());
		txtTenKH.setText(table.getValueAt(row, 1).toString());
		if (model.getValueAt(row, 2).toString().equalsIgnoreCase("Nam")) {
			rbNam.setSelected(true);
			rbNu.setSelected(false);
		} else {
			rbNu.setSelected(true);
			rbNam.setSelected(false);
		}
		txtSDT.setText(table.getValueAt(row, 4).toString());
		txtEmail.setText(table.getValueAt(row, 5).toString());
		C.setText(table.getValueAt(row, 3).toString());

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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
