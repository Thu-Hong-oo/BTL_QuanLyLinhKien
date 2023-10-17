package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import bean.DanhMucBean;
import dao.SanPham_DAO;
import entity.SanPham;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class SanPhamJPanel extends JPanel implements ActionListener, MouseListener {
	private DefaultTableModel modelSanPham;
	private JTable tableSanPham;
	private JButton btThem, btSua, btXoa, btLamMoi;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtSoLuong;
	private JTextField txtGiaNhap;
	private JTextField txtGiaBan;
	private JTextArea textAreaMoTa;
	private SanPham_DAO sanPham;
	private JComboBox<String> cbbTenDanhMuc;
	private JComboBox<String> cbbTenNCC;
	
//	private DefaultComboBoxModel<String> cbbmodelNCC;
//	private DefaultComboBoxModel<String> cbbmodelDM;

	/**
	 * Create the panel.
	 */
	public SanPhamJPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 960, 660);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(20, 41, 930, 291);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMSnPhm = new JLabel("Mã sản phẩm:");
		lblMSnPhm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMSnPhm.setBounds(10, 34, 100, 21);
		panel_1.add(lblMSnPhm);

		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnSnPhm.setBounds(10, 74, 100, 21);
		panel_1.add(lblTnSnPhm);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSLng.setBounds(10, 114, 100, 21);
		panel_1.add(lblSLng);

		JLabel lblGiNhp = new JLabel("Giá nhập:");
		lblGiNhp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiNhp.setBounds(10, 154, 100, 21);
		panel_1.add(lblGiNhp);

		JLabel lblGiBn = new JLabel("Giá bán:");
		lblGiBn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiBn.setBounds(10, 194, 100, 21);
		panel_1.add(lblGiBn);

		txtMaSP = new JTextField();
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(147, 34, 203, 25);
		panel_1.add(txtMaSP);

		txtTenSP = new JTextField();
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(147, 74, 203, 25);
		panel_1.add(txtTenSP);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(147, 114, 203, 25);
		panel_1.add(txtSoLuong);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(147, 154, 203, 25);
		panel_1.add(txtGiaNhap);

		txtGiaBan = new JTextField();
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(147, 194, 203, 25);
		panel_1.add(txtGiaBan);

		JLabel lblDanhMc = new JLabel("Nhà cung cấp:");
		lblDanhMc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDanhMc.setBounds(440, 32, 96, 21);
		panel_1.add(lblDanhMc);

		JLabel lblMT = new JLabel("Mô tả:");
		lblMT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMT.setBounds(440, 141, 80, 21);
		panel_1.add(lblMT);

		textAreaMoTa = new JTextArea();
		textAreaMoTa.setLineWrap(true);
		textAreaMoTa.setWrapStyleWord(true);
		textAreaMoTa.setBounds(546, 140, 232, 106);
		panel_1.add(textAreaMoTa);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(797, 11, 123, 269);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		btThem = new JButton("Thêm ");
		btThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btThem.setBounds(20, 30, 81, 23);
		btThem.setBackground(new Color(95, 158, 160));
		panel_2.add(btThem);

		btSua = new JButton("Sửa");
		btSua.setBackground(new Color(95, 158, 160));
		btSua.setBounds(20, 80, 81, 23);
		panel_2.add(btSua);

		btLamMoi = new JButton("Làm mới");
		btLamMoi.setBackground(new Color(95, 158, 160));
		btLamMoi.setBounds(20, 180, 81, 23);
		panel_2.add(btLamMoi);

		btXoa = new JButton("Xóa");
		btXoa.setBackground(new Color(95, 158, 160));
		btXoa.setBounds(20, 130, 81, 23);
		panel_2.add(btXoa);

		JLabel lblDanhMc_1 = new JLabel("Danh mục:");
		lblDanhMc_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDanhMc_1.setBounds(440, 87, 80, 21);
		panel_1.add(lblDanhMc_1);

		JLabel lblNewLabel = new JLabel("Thông tin sản phẩm");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(20, 11, 160, 21);
		panel.add(lblNewLabel);

		// tạo bảng sản phẩm
		String[] headerSanPham = { "Mã sản phẩm", "Tên sản phẩm", "Tên danh mục", "Tên nhà cung cấp", "Giá nhập",
				"Giá bán", "Số lương", "Mô tả" };

		modelSanPham = new DefaultTableModel(headerSanPham, 0);
		tableSanPham = new JTable(modelSanPham);
		tableSanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(tableSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 397, 930, 252);
		panel.add(scrollPane);

		JLabel lblDanhSachSP = new JLabel("Danh sách sản phẩm");
		lblDanhSachSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDanhSachSP.setBounds(10, 365, 160, 21);
		panel.add(lblDanhSachSP);

		// thiết lập dữ liệu cho table
		try {
			sanPham = new SanPham_DAO();
			for (SanPham sp : sanPham.getAllSanPham()) {
				Object[] row = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getDanhMuc(), sp.getNhaCungCap(),
						sp.getGiaNhap(), sp.getGiaBan(), sp.getSoLuong(), sp.getMoTa() };
				modelSanPham.addRow(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// Thiết lập comboBox
		cbbTenNCC = new JComboBox<>();
		// cbbTenNCC.setModel(setDuLieuComboBox(tableSanPham, "Tên nhà cung cấp"));
		cbbTenNCC.setModel(sanPham.loadComBoBox("TenNCC", "NhaCungCap"));
		cbbTenNCC.setBounds(546, 32, 232, 25);
		cbbTenNCC.setSelectedItem(null);
		panel_1.add(cbbTenNCC);

		cbbTenDanhMuc = new JComboBox<>();
		cbbTenDanhMuc.setModel(sanPham.loadComBoBox("TenDM", "DanhMucSanPham"));
		cbbTenDanhMuc.setBounds(546, 87, 232, 25);
		cbbTenDanhMuc.setSelectedItem(null);
		panel_1.add(cbbTenDanhMuc);
		
		JLabel lblDanhMc_2 = new JLabel("Danh mục");
		lblDanhMc_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDanhMc_2.setBounds(708, 343, 73, 25);
		panel.add(lblDanhMc_2);
		
	

		btThem.addActionListener(this);
		btXoa.addActionListener(this);
		btSua.addActionListener(this);
		btLamMoi.addActionListener(this);
		tableSanPham.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btThem)) {
			if (validDataSanPham()) 
				them();
			}
		else if (o.equals(btXoa))
			xoa();
		else if (o.equals(btSua))
			sua();
		else if (o.equals(btLamMoi))
			lamMoi();

	}

//	private DefaultComboBoxModel<String> setDuLieuComboBox(JTable table, String tenCot) {
//	    Set<String> data = new HashSet<String>();
//	    // Lấy chỉ số cột muốn lấy dữ liệu
//	    int columIndex = table.getColumnModel().getColumnIndex(tenCot);
//	    // thêm dữ liệu vào Set
//	    for (int i = 0; i < table.getRowCount(); i++) {
//	        String item = table.getValueAt(i, columIndex).toString();
//	        data.add(item);
//	    }
//	    return new DefaultComboBoxModel<String>(data.toArray(new String[0]));
//	}

	private void showMessage(String message, JTextField txt) {
		JOptionPane.showMessageDialog(null, message);
		txt.requestFocus();
	}

	private boolean validDataSanPham() {
		String maSP = txtMaSP.getText().trim();
		String tenSP = txtTenSP.getText().trim();
		String giaBan = txtGiaBan.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String soLuong = txtSoLuong.getText().trim();

		if (maSP.length() < 0 || !maSP.matches("^SP\\d{3}")) {
			showMessage("Cú pháp mã SP phải là SP\\d{3} ", txtMaSP);
			return false;
		}
		if(tenSP.length()<0) {
			showMessage("Vui lòng nhập tên SP ", txtTenSP);
			return false;
		}
		if(soLuong.length()>0) {
			try {
				int x = Integer.parseInt(soLuong);
				if (x < 0) {
					showMessage("Eror: Số lượng phải là số nguyên dương", txtSoLuong);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessage("Eror: Số lượng phải là số ", txtSoLuong);
				return false;
			}
		}else {
			showMessage("Eror: Bạn chưa nhập Số lượng ", txtSoLuong);
			return false;
		}
		
		if(giaNhap.length()>0) {
			try {
				long x = Long.parseLong(giaNhap);
				if (x < 0) {
					showMessage("Error: Giá nhập phải là số dương", txtGiaNhap);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessage("Error: Giá nhập phải là số ", txtGiaNhap);
				return false;
			}
		}else {
			showMessage("Error: Bạn chưa nhập giá nhập ", txtGiaNhap);
			return false;
		}
		
		if(giaBan.length()>0) {
			try {
				long x = Long.parseLong(giaBan);
				if (x < 0) {
					showMessage("Eror: Giá bán phải là số dương", txtGiaBan);
					return false;
				}
			} catch (NumberFormatException e) {
				showMessage("Eror: Giá bán phải là số ", txtGiaBan);
				return false;
			}
		}else {
			showMessage("Eror: Bạn chưa nhập giá bán ", txtGiaBan);
			return false;
		}
		
		
		return true;
	}

	private SanPham revertSanPhamFromtxt() {
		SanPham sanPham = null;
		String maSP, tenSP;
		String tenDM;
		String tenNCC;
		double giaNhap, giaBan;
		int soLuong;
		String moTa;
		maSP = txtMaSP.getText();
		tenSP = txtTenSP.getText();
		giaNhap = Double.parseDouble(txtGiaNhap.getText());
		giaBan = Double.parseDouble(txtGiaBan.getText());
		moTa = textAreaMoTa.getText();
		soLuong = Integer.parseInt(txtSoLuong.getText());
		tenDM = cbbTenDanhMuc.getSelectedItem().toString();
		tenNCC = cbbTenNCC.getSelectedItem().toString();

		sanPham = new SanPham(maSP, tenSP, tenDM, tenNCC, soLuong, soLuong, soLuong, moTa);
		return sanPham;

	}

	private void them() {
		if (validDataSanPham()) {
			SanPham sp = revertSanPhamFromtxt();
			if(sanPham.themSanPham(sp)) {
				Object row[] = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getDanhMuc(), sp.getNhaCungCap(),
						sp.getGiaNhap(), sp.getGiaBan(), sp.getSoLuong(), sp.getMoTa() };
				modelSanPham.addRow(row);
				JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
			}else {
				JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại");
			}
			
		}

	}

	private void xoa() {
		int i = tableSanPham.getSelectedRow();
		String maSP = txtMaSP.getText();
		if (i != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Ban co chac muon xoa", "DELETE", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				sanPham.xoaSanPham(maSP);
				modelSanPham.removeRow(i);
				lamMoi();
			}
		} else
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm muốn xóa!");
	}

	private void sua() {
		int row = tableSanPham.getSelectedRow();
		SanPham sp = revertSanPhamFromtxt();
		if (validDataSanPham()) {
			String selectedmaSanPham = tableSanPham.getValueAt(row, 0).toString();
			if (!sp.getMaSanPham().equals(selectedmaSanPham)) {
				JOptionPane.showMessageDialog(null, "Không được thay đổi mã sản phẩm!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}

			else {
				modelSanPham.setValueAt(sp.getTenSanPham(), row, 1);
				modelSanPham.setValueAt(sp.getDanhMuc(), row, 2);
				modelSanPham.setValueAt(sp.getNhaCungCap(), row, 3);
				modelSanPham.setValueAt(sp.getGiaNhap(), row, 4);
				modelSanPham.setValueAt(sp.getGiaBan(), row, 5);
				modelSanPham.setValueAt(sp.getSoLuong(), row, 6);
				modelSanPham.setValueAt(sp.getMoTa(), row, 7);
				modelSanPham.fireTableRowsUpdated(row, row);
				sanPham.suaSanPham(sp);
				JOptionPane.showMessageDialog(null,
						"Thực hiện sửa đổi thành công thông tin sản phẩm có mã: " + sp.getMaSanPham());
			}
		}
	}

	private void lamMoi() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtSoLuong.setText("");
		textAreaMoTa.setText("");
		cbbTenDanhMuc.setSelectedItem(null);
		cbbTenNCC.setSelectedItem(null);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableSanPham.getSelectedRow();
		txtMaSP.setText(tableSanPham.getValueAt(row, 0).toString());
		txtTenSP.setText(tableSanPham.getValueAt(row, 1).toString());
		txtGiaNhap.setText(tableSanPham.getValueAt(row, 4).toString());
		txtGiaBan.setText(tableSanPham.getValueAt(row, 5).toString());
		txtSoLuong.setText(tableSanPham.getValueAt(row, 6).toString());
		textAreaMoTa.setText(tableSanPham.getValueAt(row, 7).toString());
		cbbTenDanhMuc.setSelectedItem(tableSanPham.getValueAt(row, 2));
		cbbTenNCC.setSelectedItem(tableSanPham.getValueAt(row, 3));

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
