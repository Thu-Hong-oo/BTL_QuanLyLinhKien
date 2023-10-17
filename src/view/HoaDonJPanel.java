
package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;

import javax.swing.UIManager;
import javax.swing.RowFilter.ComparisonType;

import java.awt.Canvas;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class HoaDonJPanel extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaHD;
	private JTextField txtMaKH;
	private JTextField txtMaNV;
	private JTable table;
	private JComboBox cbbHTGH;
	private JComboBox cbbHTTT;
	private JComboBox cbbTT;
	private JButton btnXoaRong;
	private JTable tableHoaDon;
	private JTable tableChiTiet;
	private DefaultTableModel modelHoaDon;
	private DefaultTableModel modelChiTiet;
	private JDateChooser dateChooser;
	
	private HoaDon_DAO hoaDon;
	private ChiTietHoaDon_DAO ctHD;
	private SanPham_DAO sp;

	/**
	 * Create the panel.
	 */
	public HoaDonJPanel() {
		setBackground(SystemColor.control);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Lọc theo mã HĐ:");
		lblNewLabel.setBounds(82, 30, 105, 16);
		add(lblNewLabel);

		txtMaHD = new JTextField();
		txtMaHD.setBounds(185, 25, 140, 26);
		add(txtMaHD);
		txtMaHD.setColumns(10);

		
		JLabel lblMaKH = new JLabel("Lọc theo mã KH:");
		lblMaKH.setBounds(355, 30, 105, 16);
		add(lblMaKH);
		
		JLabel lblMaNV = new JLabel("Lọc theo mã NV:");
		lblMaNV.setBounds(630, 30, 105, 16);
		add(lblMaNV);
		
		txtMaKH = new JTextField();
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(456, 25, 140, 26);
		add(txtMaKH);
		
		txtMaNV = new JTextField();
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(734, 25, 140, 26);
		add(txtMaNV);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(96, 58, 162, 58);
		add(panel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Hình thức giao hàng:");
		lblNewLabel_1_1.setBounds(6, 6, 150, 16);
		panel_1.add(lblNewLabel_1_1);

		cbbHTGH = new JComboBox();
		cbbHTGH.setModel(new DefaultComboBoxModel(new String[] {"" ,"Giao hàng tận nơi", "Lấy tại cửa hàng" }));
		cbbHTGH.setBounds(6, 26, 150, 27);
		panel_1.add(cbbHTGH);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(298, 58, 162, 58);
		add(panel_2);

		JLabel lblNewLabel_1_2 = new JLabel("Hình thức thanh toán:");
		lblNewLabel_1_2.setBounds(6, 6, 150, 16);
		panel_2.add(lblNewLabel_1_2);

		cbbHTTT = new JComboBox();
		cbbHTTT.setModel(new DefaultComboBoxModel(new String[] {"" , "Tiền mặt", "Ví điện tử" }));
		cbbHTTT.setBounds(6, 26, 150, 27);
		panel_2.add(cbbHTTT);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_3.setBackground(SystemColor.control);
		panel_3.setBounds(500, 58, 162, 58);
		add(panel_3);

		JLabel lblNewLabel_1_3 = new JLabel("Tổng tiền:");
		lblNewLabel_1_3.setBounds(6, 6, 150, 16);
		panel_3.add(lblNewLabel_1_3);

		cbbTT = new JComboBox();
		cbbTT.setModel(new DefaultComboBoxModel(
				new String[] {"" , "0 - 500.000", "500.000 - 2.000.000", "2.000.000 - 5.000.000", "5.000.000 - 10.000.000" }));
		cbbTT.setBounds(6, 26, 150, 27);
		panel_3.add(cbbTT);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(702, 60, 162, 58);
		add(panel_4);


		
		JLabel lblNewLabel_1_4 = new JLabel("Lọc theo ngày tháng");
		lblNewLabel_1_4.setBounds(16, 6, 132, 16);
		dateChooser = new JDateChooser();
		dateChooser.setBounds(6, 26, 150, 27);
		panel_4.add(lblNewLabel_1_4);
		panel_4.add(dateChooser);


		btnXoaRong = new JButton("Xoá rỗng");
		btnXoaRong.setBounds(430, 120, 100, 29);
		add(btnXoaRong);
		

		// tạo bảng hóa đơn
		String[] headerHoaDon = { "Mã HD", "Mã KH", "Mã NV", "Tổng tiền", "Tiền đưa", "Tiền trả", "HTTT", "HTGH",
				"Ngày lập HD", "Ghi chú" };
		modelHoaDon = new DefaultTableModel(headerHoaDon, 0);
		tableHoaDon = new JTable(modelHoaDon);
		tableHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scpHoaDon = new JScrollPane(tableHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpHoaDon.setBounds(10, 154, 940, 200);
		add(scpHoaDon);
		
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.control);
		panel_5.setBorder(new TitledBorder(null, "Chi tiết hoá đơn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_5.setBounds(6, 371, 948, 283);
		add(panel_5);
		panel_5.setLayout(null);
		
		//tạo bảng chi tiết hóa đơn
		String[] headerChiTiet = { "Mã hoá đơn", "Mã linh kiện", "Tên linh kiện", "Đơn giá", "Số lượng", "Thành tiền" };
		modelChiTiet = new DefaultTableModel(headerChiTiet, 0);
		tableChiTiet = new JTable(modelChiTiet);
		tableChiTiet.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scpChiTiet = new JScrollPane(tableChiTiet,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpChiTiet.setBounds(10, 24, 927, 250);
		panel_5.add(scpChiTiet);
		
		
		btnXoaRong.addActionListener(this);
		
		try {
			hoaDon = new HoaDon_DAO();
			for (entity.HoaDon hd : hoaDon.getAllHoaDon()) {
				Object[] row = { hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getTongTien(), hd.getTienDua(),
						hd.getTienTra(), hd.gethTTT(), hd.gethTGH(), hd.getNgayLap(), hd.getGhiChu() };
				modelHoaDon.addRow(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			ctHD = new ChiTietHoaDon_DAO();
			sp = new SanPham_DAO();
			String tenSP;
			for (ChiTietHoaDon cthd : ctHD.getAllChiTietHoaDon()) {
				tenSP = sp.getTenByMaSP(cthd.getMaSP());
				Object[] row = { cthd.getMaHD(), cthd.getMaSP(), tenSP, cthd.getDonGia(), cthd.getSoLuong(),
						cthd.getThanhTien() };
				modelChiTiet.addRow(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		tableHoaDon.addMouseListener(this);
		txtMaHD.getDocument().addDocumentListener(new DocumentListener() {

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
				String locMaHD = txtMaHD.getText().trim();
				DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(locMaHD, 0);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				tableHoaDon.setRowSorter(sorter);
			}
		});
		txtMaKH.getDocument().addDocumentListener(new DocumentListener() {

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
				String maKH = txtMaKH.getText().trim();
				DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(maKH, 1);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				tableHoaDon.setRowSorter(sorter);
			}
		});
		
		txtMaNV.getDocument().addDocumentListener(new DocumentListener() {

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
				String maNV = txtMaNV.getText().trim();
				DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(maNV, 2);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				tableHoaDon.setRowSorter(sorter);
			}
		});
		
		// Xử lý cbb HTGH:	
		cbbHTGH.setSelectedIndex(0);
		cbbHTGH.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      String hTTT = (String) cbbHTGH.getSelectedItem();
			      // Xử lý giá trị được chọn
			      DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
					// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
					RowFilter<Object, Object> filter = RowFilter.regexFilter(hTTT, 7);

					// Tạo một sorter để sắp xếp lại các dòng
					TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(filter);

					// Đặt sorter cho bảng
					tableHoaDon.setRowSorter(sorter);
			      
			   }
			});
		
		// Xử lý cbb HTTT:	
		cbbHTTT.setSelectedIndex(0);
		cbbHTTT.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      String hTTT = (String) cbbHTTT.getSelectedItem();
			      // Xử lý giá trị được chọn
			      DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
					// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
					RowFilter<Object, Object> filter = RowFilter.regexFilter(hTTT, 6);

					// Tạo một sorter để sắp xếp lại các dòng
					TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(filter);

					// Đặt sorter cho bảng
					tableHoaDon.setRowSorter(sorter);
			      
			   }
			});
		
		// Xử lý cbb Tổng tiền:	
		cbbTT.setSelectedIndex(0);
		cbbTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tongTien = (String) cbbTT.getSelectedItem();
				if(!cbbTT.getSelectedItem().equals("")) {
					String[] range = tongTien.split("-");
					long min = Long.parseLong(range[0].trim().replace(".", ""));
					long max = Long.parseLong(range[1].trim().replace(".", ""));

					DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
					TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);

					ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					//So sánh khoảng giá trị ở item đã chọn với các giá trị ở cột Tổng tiền trong table Hoá đơn
					filters.add( RowFilter.numberFilter(ComparisonType.AFTER, min, 3) );
					filters.add( RowFilter.numberFilter(ComparisonType.BEFORE, max, 3) );

					RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
					sorter.setRowFilter(rf);

					// Đặt bộ lọc cho sorter
					tableHoaDon.setRowSorter(sorter);
				}
			}
		});


			
		// Xử lý lọc bằng ngày tháng:
		
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu ngày được chọn thay đổi
		        	java.util.Date date = dateChooser.getDate();
		        	Date selectedDate = new Date((long) date.getTime()); // Lấy ngày được chọn trên JDateChooser
		            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate); // Định dạng lại ngày
		            DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
		            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

		            // Tạo một bộ lọc để lấy các dòng có giá trị ngày trùng với ngày được chọn trên JDateChooser
		            RowFilter<Object, Object> filter = RowFilter.regexFilter(formattedDate, 8);
		            sorter.setRowFilter(filter);

		            // Đặt sorter cho bảng
		            tableHoaDon.setRowSorter(sorter);
		        }
		    }
		});

	}
	private void filterCTHDByMaHD(String maHD) {
		DefaultTableModel model = (DefaultTableModel) tableChiTiet.getModel();
		model.setRowCount(0); // Xóa các dòng hiện tại trong bảng

		ArrayList<ChiTietHoaDon> dsCTHD = ctHD.getAllChiTietHoaDon();
		for (ChiTietHoaDon cthd : dsCTHD) {
			if (cthd.getMaHD().equals(maHD)) {
				String tenSP = sp.getTenByMaSP(cthd.getMaSP());
				Object[] row = { cthd.getMaHD(), cthd.getMaSP(), tenSP, cthd.getDonGia(), cthd.getSoLuong(),
						cthd.getThanhTien() };
				model.addRow(row);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXoaRong)) {
			txtMaHD.setText("");
			txtMaKH.setText("");
			txtMaNV.setText("");
			cbbHTGH.setSelectedItem("");
			cbbHTTT.setSelectedItem("");
			cbbTT.setSelectedItem("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tableHoaDon)) {
			int row = tableHoaDon.getSelectedRow();
			if (row >= 0) {
				String maHD = tableHoaDon.getValueAt(row, 0).toString();
//				System.out.println(maHD);
				filterCTHDByMaHD(maHD);
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
