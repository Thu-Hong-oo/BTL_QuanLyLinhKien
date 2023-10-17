package view;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import dao.TaiKhoan_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import main.MainAdmin;
import main.MainNhanVien;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class BanHangJPanel extends JPanel implements ActionListener, MouseListener, PropertyChangeListener {
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtTienKhachDua;
	private JTextField txtTimKiem;
	private JComboBox<String> cbbHinhThucGiaoHang;
	private JComboBox<String> cbbHinhThucThanhToan;
	private JTextArea textAreaGhiChu;
	private JTable tableGioHang;
	private JTable tableDanhSach;
	private DefaultTableModel modelGioHang;
	private DefaultTableModel modelDanhSach;
	private SanPham_DAO sanPham;
	private KhachHang_DAO khachHang;
	private SanPham selectedSanPham;
	private JComboBox cbbDanhMuc;
	private HoaDon_DAO hoaDon = new HoaDon_DAO();
	private ChiTietHoaDon_DAO ctHD = new ChiTietHoaDon_DAO();
	private ArrayList<SanPham> dsSP = new ArrayList<SanPham>();

	private DefaultTableModel modelKH;
	private JTable tableKH;
	private JButton btXoaSanPham, btXoaTatCa, btTao, btHuyDonHang, btLamMoi, btThanhToan;
	private JDateChooser dateChooserNgaytao;
	private JLabel lbHienThiMaHD, lbTongTien, lbTienThua;
	private int soLuongNhap;

	private JTextField txtTenKHThem;
	private JTextField txtMaKHThem;
	private JTextField txtEmail;
	private JTextField txtSoDienThoai;
	private JTextArea txtDiaChi;
	private JButton btThem;
	private JButton btChon;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	private ThemKHFrame themKHFrame;

	/**
	 * Create the panel.
	 */
	public BanHangJPanel() {

		try {
			sanPham = new SanPham_DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 960, 660);
		add(panel);
		panel.setLayout(null);

		JLabel lbCenterGioHang = new JLabel("Giỏ hàng");
		lbCenterGioHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbCenterGioHang.setBounds(10, 11, 125, 25);
		panel.add(lbCenterGioHang);

		JPanel panelGioHang = new JPanel();
		panelGioHang.setLayout(null);
		panelGioHang.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true),

				new LineBorder(new Color(192, 192, 192), 2, true)));
		panelGioHang.setBackground(Color.WHITE);
		panelGioHang.setBounds(10, 35, 571, 189);
		panel.add(panelGioHang);

		btXoaSanPham = new JButton("Xóa sản phẩm");
		btXoaSanPham.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-remove-20.png")));
		btXoaSanPham.setFont(new Font("SansSerif", Font.BOLD, 12));
		btXoaSanPham.setBackground(new Color(95, 158, 160));
		btXoaSanPham.setBounds(410, 27, 138, 28);
		panelGioHang.add(btXoaSanPham);

		btXoaTatCa = new JButton("Xóa tất cả");
		btXoaTatCa.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-remove-20.png")));
		btXoaTatCa.setFont(new Font("SansSerif", Font.BOLD, 12));
		btXoaTatCa.setBackground(new Color(95, 158, 160));
		btXoaTatCa.setBounds(410, 75, 138, 28);
		panelGioHang.add(btXoaTatCa);

		JLabel lbDonHang = new JLabel("Hóa đơn");
		lbDonHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDonHang.setBounds(610, 11, 85, 25);
		panel.add(lbDonHang);

		JPanel panelDonHang = new JPanel();
		panelDonHang.setLayout(null);
		panelDonHang.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true),

				new LineBorder(new Color(192, 192, 192), 2, true)));
		panelDonHang.setBackground(Color.WHITE);
		panelDonHang.setBounds(610, 35, 345, 614);
		panel.add(panelDonHang);

		JPanel panelDonHang_1 = new JPanel();
		panelDonHang_1.setLayout(null);
		panelDonHang_1.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true),

				new LineBorder(new Color(192, 192, 192), 2, true)));
		panelDonHang_1.setBackground(Color.WHITE);
		panelDonHang_1.setBounds(6, 12, 333, 77);
		panelDonHang.add(panelDonHang_1);

		JLabel lbMaKH = new JLabel("Mã KH:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbMaKH.setBounds(6, 6, 50, 25);
		panelDonHang_1.add(lbMaKH);

		JLabel lbTenKH = new JLabel("Tên KH:");
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTenKH.setBounds(6, 43, 50, 25);
		panelDonHang_1.add(lbTenKH);

		JButton btThayDoi = new JButton("Thay đổi");
		btThayDoi.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-change-20.png")));
		btThayDoi.setFont(new Font("SansSerif", Font.BOLD, 12));
		btThayDoi.setBackground(new Color(95, 158, 160));
		btThayDoi.setBounds(220, 25, 105, 28);
		panelDonHang_1.add(btThayDoi);

		txtMaKH = new JTextField("KH000");
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(56, 4, 154, 28);
		panelDonHang_1.add(txtMaKH);

		txtTenKH = new JTextField("Khách bán lẻ");
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(56, 41, 154, 28);
		panelDonHang_1.add(txtTenKH);

		JLabel lbma = new JLabel("Mã hóa đơn:");
		lbma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbma.setBounds(15, 170, 97, 25);
		panelDonHang.add(lbma);

		lbHienThiMaHD = new JLabel("");
		lbHienThiMaHD.setBounds(122, 170, 144, 25);
		panelDonHang.add(lbHienThiMaHD);

		JLabel lbtogtin = new JLabel("Tổng tiền:");
		lbtogtin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbtogtin.setBounds(15, 200, 97, 25);
		panelDonHang.add(lbtogtin);

		JLabel lbVND = new JLabel("VND");
		lbVND.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbVND.setBounds(270, 200, 55, 16);
		panelDonHang.add(lbVND);

		JLabel lblTinKhcha = new JLabel("Tiền khách đưa:");
		lblTinKhcha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTinKhcha.setBounds(15, 230, 97, 25);
		panelDonHang.add(lblTinKhcha);

		JLabel lbVND_3 = new JLabel("VND");
		lbVND_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbVND_3.setBounds(270, 230, 55, 16);
		panelDonHang.add(lbVND_3);

		JLabel lblTinTha = new JLabel("Tiền thừa:");
		lblTinTha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTinTha.setBounds(15, 260, 97, 25);
		panelDonHang.add(lblTinTha);

		JLabel lblHnhThcThanh = new JLabel("Hình thức thanh toán:");
		lblHnhThcThanh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHnhThcThanh.setBounds(15, 290, 152, 25);
		panelDonHang.add(lblHnhThcThanh);

		JLabel lblHnhThcGiao = new JLabel("Hình thức giao hàng");
		lblHnhThcGiao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHnhThcGiao.setBounds(15, 320, 126, 25);
		panelDonHang.add(lblHnhThcGiao);

		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGhiCh.setBounds(15, 350, 97, 25);
		panelDonHang.add(lblGhiCh);

		JLabel lbVND_5 = new JLabel("VND");
		lbVND_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbVND_5.setBounds(270, 260, 55, 16);
		panelDonHang.add(lbVND_5);

		btTao = new JButton("Tạo");
		btTao.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-save-20 (1).png")));
		btTao.setFont(new Font("SansSerif", Font.BOLD, 12));
		btTao.setBackground(new Color(95, 158, 160));
		btTao.setBounds(252, 100, 83, 28);
		panelDonHang.add(btTao);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setHorizontalAlignment(SwingConstants.LEFT);
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(122, 230, 134, 25);
		panelDonHang.add(txtTienKhachDua);

		lbTongTien = new JLabel("");
		lbTongTien.setBounds(122, 200, 134, 25);
		panelDonHang.add(lbTongTien);

		lbTienThua = new JLabel("");
		lbTienThua.setBounds(122, 260, 136, 25);
		panelDonHang.add(lbTienThua);

		cbbHinhThucThanhToan = new JComboBox();
		cbbHinhThucThanhToan.setBounds(166, 290, 173, 25);
		cbbHinhThucThanhToan
				.setModel(new DefaultComboBoxModel<>(new String[] { "Thẻ", "Ví điện tử", "Chuyển khoản", "Tiền mặt" }));
		cbbHinhThucThanhToan.setSelectedItem(null);
		panelDonHang.add(cbbHinhThucThanhToan);

		cbbHinhThucGiaoHang = new JComboBox();
		cbbHinhThucGiaoHang.setBounds(166, 320, 173, 25);
		cbbHinhThucGiaoHang
				.setModel(new DefaultComboBoxModel<>(new String[] { "Lấy tại cửa hàng", "Giao hàng tận nơi" }));
		cbbHinhThucGiaoHang.setSelectedItem(null);
		panelDonHang.add(cbbHinhThucGiaoHang);

		textAreaGhiChu = new JTextArea();
		textAreaGhiChu.setLineWrap(true);
		textAreaGhiChu.setBackground(SystemColor.window);
		textAreaGhiChu.setBounds(33, 386, 302, 60);
		panelDonHang.add(textAreaGhiChu);

		btHuyDonHang = new JButton("Hủy đơn hàng");
		btHuyDonHang.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-cancel-order-20.png")));
		btHuyDonHang.setFont(new Font("SansSerif", Font.BOLD, 12));
		btHuyDonHang.setBackground(new Color(95, 158, 160));
		btHuyDonHang.setBounds(15, 476, 134, 28);
		panelDonHang.add(btHuyDonHang);

		btLamMoi = new JButton("Làm mới");
		btLamMoi.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-available-updates-20.png")));
		btLamMoi.setFont(new Font("SansSerif", Font.BOLD, 12));
		btLamMoi.setBackground(new Color(95, 158, 160));
		btLamMoi.setBounds(205, 476, 134, 28);
		panelDonHang.add(btLamMoi);

		btThanhToan = new JButton("THANH TOÁN");
		btThanhToan.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-salary-male-30.png")));
		btThanhToan.setFont(new Font("SansSerif", Font.BOLD, 20));
		btThanhToan.setBackground(new Color(95, 158, 160));
		btThanhToan.setBounds(23, 525, 302, 51);
		panelDonHang.add(btThanhToan);

		JLabel lblNgyTo = new JLabel("Ngày tạo:");
		lblNgyTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyTo.setBounds(16, 140, 97, 25);
		panelDonHang.add(lblNgyTo);

		JLabel lbThanhToan_1 = new JLabel("");
		lbThanhToan_1.setBounds(132, 284, 97, 16);
		panelDonHang.add(lbThanhToan_1);

		dateChooserNgaytao = new JDateChooser();
		dateChooserNgaytao.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooserNgaytao.setBounds(122, 140, 167, 25);
		panelDonHang.add(dateChooserNgaytao);
		MainNhanVien mainnv = new MainNhanVien();

		JPanel panelDS = new JPanel();
		panelDS.setLayout(null);
		panelDS.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true),

				new LineBorder(new Color(192, 192, 192), 2, true)));
		panelDS.setBackground(Color.WHITE);
		panelDS.setBounds(10, 291, 571, 356);
		panel.add(panelDS);

		JLabel lbTimKiemSanPham = new JLabel("Tìm kiếm sản phẩm");
		lbTimKiemSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTimKiemSanPham.setBounds(16, 6, 168, 25);
		panelDS.add(lbTimKiemSanPham);

		JLabel lblDanhMc = new JLabel("Danh mục");
		lblDanhMc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDanhMc.setBounds(401, 6, 73, 25);
		panelDS.add(lblDanhMc);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(16, 42, 154, 23);
		panelDS.add(txtTimKiem);

		cbbDanhMuc = new JComboBox();
		cbbDanhMuc.setModel(sanPham.loadComBoBox("TenDM", "DanhMucSanPham"));
		cbbDanhMuc.setBounds(401, 42, 143, 23);
		cbbDanhMuc.setSelectedItem(null);
		panelDS.add(cbbDanhMuc);

		JButton btThemSP = new JButton("Thêm sản phẩm");
		btThemSP.setIcon(new ImageIcon(BanHangJPanel.class.getResource("/Images/icons8-macos-maximize-20.png")));
		btThemSP.setFont(new Font("SansSerif", Font.BOLD, 12));
		btThemSP.setBackground(new Color(95, 158, 160));
		btThemSP.setBounds(217, 41, 149, 28);
		panelDS.add(btThemSP);

		JLabel lblDanhSchSn = new JLabel("Danh sách sản phẩm");
		lblDanhSchSn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDanhSchSn.setBounds(10, 255, 164, 25);
		panel.add(lblDanhSchSn);

		// tạo bảng giỏ hàng
		String[] headergioHang = { "Mã sản phẩm", "Đơn giá", "Số lượng", "Thành tiền" };
		modelGioHang = new DefaultTableModel(headergioHang, 0);
		tableGioHang = new JTable(modelGioHang);
		tableGioHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scpGioHang = new JScrollPane(tableGioHang, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpGioHang.setBounds(10, 11, 390, 165);
		panelGioHang.add(scpGioHang);

		// tạo bảng danh sách
		String[] headerDanhSach = { "Mã sản phẩm", "Tên sản phẩm ", "Tên danh mục", "Đơn Giá", "Số lượng trong kho" };
		modelDanhSach = new DefaultTableModel(headerDanhSach, 0);
		tableDanhSach = new JTable(modelDanhSach);
		JScrollPane scpDS = new JScrollPane(tableDanhSach, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpDS.setBounds(10, 80, 551, 265);
		panelDS.add(scpDS);

		// thiết lập dữ liệu cho table
		loadDataTable();
		// xư lý sự kiện thêm sản phẩm
		tableDanhSach.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = tableDanhSach.getSelectedRow();
				if (selectedRow >= 0) {
					selectedSanPham = sanPham.getByMa((String) tableDanhSach.getValueAt(selectedRow, 0));
				}
			}
		});
		btThemSP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedSanPham == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm từ danh sách để thêm vào giỏ hàng");
					return;
				}
				boolean soLuongNhapHopLe = false;
				soLuongNhap = 0;
				while (!soLuongNhapHopLe) {
					String inputsoLuongNhap = JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm cần thêm:");
					if (inputsoLuongNhap == null) {
						// Người dùng bấm Cancel
						return;
					}

					try {
						soLuongNhap = Integer.parseInt(inputsoLuongNhap);
						if (soLuongNhap > 0 && soLuongNhap <= selectedSanPham.getSoLuong()) {
							soLuongNhapHopLe = true;
						} else if (soLuongNhap <= 0) {
							JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0.");
						} else {
							JOptionPane.showMessageDialog(null, "Số lượng sản phẩm trong kho không đủ.");
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là một số nguyên dương.");
					}
				}

				// Thêm sản phẩm vào giỏ hàng
				Object[] row = { selectedSanPham.getMaSanPham(), selectedSanPham.getGiaBan(), soLuongNhap,
						soLuongNhap * selectedSanPham.getGiaBan() };
				modelGioHang.addRow(row);
			}
		});

		// xử lý sự kiện thya đổi khách hàng
		btThayDoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame fr = new JFrame();
				fr.setVisible(true);
				// fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fr.setSize(600, 400);
				fr.setLocationRelativeTo(null);

				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(10, 11, 564, 339);
				fr.add(tabbedPane);

				JPanel panelDS = new JPanel();
				tabbedPane.addTab("Danh sách khách hàng", null, panelDS, null);
				panelDS.setLayout(null);

				JPanel panelThemKH = new JPanel();
				tabbedPane.addTab("Thêm khách hàng", null, panelThemKH, null);
				panelThemKH.setLayout(null);

				JLabel lblNewLabel = new JLabel("Mã khách hàng:");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel.setBounds(27, 11, 139, 33);
				panelThemKH.add(lblNewLabel);

				JLabel lblNewLabel_1 = new JLabel("Tên khách hàng:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_1.setBounds(27, 58, 139, 33);
				panelThemKH.add(lblNewLabel_1);

				JLabel lblNewLabel_3 = new JLabel("Giới tính:");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_3.setBounds(27, 105, 139, 33);
				panelThemKH.add(lblNewLabel_3);

				rbNam = new JRadioButton("Nam", false);
				rbNam.setFont(new Font("Tahoma", Font.BOLD, 12));
				rbNam.setBackground(new Color(240, 240, 240));
				rbNam.setBounds(157, 107, 57, 30);
				panelThemKH.add(rbNam);

				rbNu = new JRadioButton("Nữ");
				rbNu.setFont(new Font("Tahoma", Font.BOLD, 12));
				rbNu.setBackground(new Color(240, 240, 240));
				rbNu.setBounds(224, 106, 57, 30);
				panelThemKH.add(rbNu);
				ButtonGroup group = new ButtonGroup();
				group.add(rbNu);
				group.add(rbNam);

				txtTenKHThem = new JTextField();
				txtTenKHThem.setColumns(10);
				txtTenKHThem.setBounds(157, 67, 212, 25);
				panelThemKH.add(txtTenKHThem);

				txtMaKHThem = new JTextField();
				txtMaKHThem.setColumns(10);
				txtMaKHThem.setBounds(157, 20, 212, 25);
				panelThemKH.add(txtMaKHThem);

				JLabel lblNewLabel_4_2 = new JLabel("Email:");
				lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_4_2.setBounds(27, 144, 139, 33);
				panelThemKH.add(lblNewLabel_4_2);

				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(157, 149, 212, 25);
				panelThemKH.add(txtEmail);

				JLabel lblNewLabel_4_1 = new JLabel("Địa chỉ:");
				lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_4_1.setBounds(27, 188, 139, 33);
				panelThemKH.add(lblNewLabel_4_1);

				txtDiaChi = new JTextArea();
				txtDiaChi.setBounds(159, 188, 210, 65);
				panelThemKH.add(txtDiaChi);

				JLabel lblNewLabel_2 = new JLabel("Số điện thoại:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_2.setBounds(307, 105, 99, 33);
				panelThemKH.add(lblNewLabel_2);

				txtSoDienThoai = new JTextField();
				txtSoDienThoai.setColumns(10);
				txtSoDienThoai.setBounds(395, 110, 154, 25);
				panelThemKH.add(txtSoDienThoai);

				btThem = new JButton("Thêm");
				btThem.setFont(new Font("Tahoma", Font.BOLD, 11));
				btThem.setBounds(227, 277, 89, 23);
				panelThemKH.add(btThem);

				String[] headerKH = { "Mã KH", "Tên KH", "Giới tính", "Địa chỉ", "SDT", "Email" };
				modelKH = new DefaultTableModel(headerKH, 0);
				tableKH = new JTable(modelKH);
				tableKH.setBorder(new LineBorder(new Color(0, 0, 0)));
				JScrollPane scpKH = new JScrollPane(tableKH, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scpKH.setBounds(10, 11, 539, 259);
				panelDS.add(scpKH);

				btChon = new JButton("Chọn");
				btChon.setFont(new Font("Tahoma", Font.BOLD, 11));
				btChon.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btChon.setBounds(234, 277, 89, 23);
				panelDS.add(btChon);
// thiết lập dữ liệu cho Jframe Khach hàng
				try {
					khachHang = new KhachHang_DAO();
					for (KhachHang kh : khachHang.getAllKhachHang()) {
						Object[] rowData = { kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getDiaChi(), kh.getSDT(),
								kh.getEmail() };
						modelKH.addRow(rowData);
					}
				} catch (Exception eKH) {
					// TODO: handle exception
					eKH.printStackTrace();
				}
				btThem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						themKH();

					}

				});
				btChon.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						int selectedRowKH = tableKH.getSelectedRow();
						if (selectedRowKH >= 0) {
							txtMaKH.setText(tableKH.getValueAt(selectedRowKH, 0).toString());
							txtTenKH.setText(tableKH.getValueAt(selectedRowKH, 1).toString());

						}

					}
				});

			}
		});

		btXoaTatCa.addActionListener(this);
		btXoaSanPham.addActionListener(this);
		btTao.addActionListener(this);
		btHuyDonHang.addActionListener(this);
		btLamMoi.addActionListener(this);
		btThanhToan.addActionListener(this);
		cbbDanhMuc.addActionListener(this);
		cbbHinhThucGiaoHang.addActionListener(this);
		tableDanhSach.addMouseListener(this);
		txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {

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
				//loadDataTable();
				
				String text = txtTimKiem.getText().trim();

				DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();

				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(text, 0);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				tableDanhSach.setRowSorter(sorter);
			}
		});
		txtTimKiem.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        // Xử lý khi TextField nhận focus
		        cbbDanhMuc.setSelectedIndex(-1); // Đặt ComboBox về trạng thái null
		        loadDataTable();
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        // Xử lý khi TextField mất focus (không nhận focus nữa)
		    }
		});
		lbTienThua.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("text")) {
					// Lấy giá trị của lbTienThua
					String tienThuaText = lbTienThua.getText();
					if (!tienThuaText.isEmpty()) {
						long tienThua = Long.parseLong(tienThuaText);

						// Kiểm tra nếu giá trị của lbTienThua âm thì khóa button thanh toán
						if (tienThua < 0) {
							btThanhToan.setEnabled(false);
						} else {
							btThanhToan.setEnabled(true);
						}
					}

				}
			}
		});

	}

	private void loadDataTable() {
		try {

			for (SanPham sp : sanPham.getAllSanPham()) {
				Object[] row = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getDanhMuc(), sp.getGiaBan(),
						sp.getSoLuong() };
				modelDanhSach.addRow(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btTao))
			taoHoaDon();
		else if (o.equals(btXoaSanPham))
			xoaSanPham();
		else if (o.equals(btXoaTatCa))
			xoaTatCa();
		else if (o.equals(btThanhToan))
			thanhToan();
		else if (o.equals(btLamMoi))
			lamMoi();
		else if (o.equals(btHuyDonHang))
			huyDonHang();
		else if (o.equals(cbbDanhMuc)) {
			String tenDM = (String) cbbDanhMuc.getSelectedItem();

			filterSanPhamByDanhMuc(tenDM);
		} else if (o.equals(cbbHinhThucGiaoHang)) {
			String giaoHang = (String) cbbHinhThucGiaoHang.getSelectedItem();
			tinhPhiShip(giaoHang);
		}

	}

	private void xoaSanPham() {
		int i = tableGioHang.getSelectedRow();
		if (i != -1) {
			modelGioHang.removeRow(i);
		}
	}

	private void xoaTatCa() {
		modelGioHang.setRowCount(0);
	}

	private void updateTable(ArrayList<SanPham> dsSanPhamLoc) {
		DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
		model.setRowCount(0); // Xóa các dòng hiện tại trong bảng

		for (SanPham sp : dsSanPhamLoc) {
			Object[] row = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getDanhMuc(), sp.getGiaBan(), sp.getSoLuong() };
			model.addRow(row);
		}
	}

	private void filterSanPhamByDanhMuc(String danhMuc) {
		ArrayList<SanPham> dsSP = sanPham.getAllSanPham();
		ArrayList<SanPham> dsSPLoc = new ArrayList<>();

		for (SanPham sp : dsSP) {
			if (sp.getDanhMuc().equals(danhMuc)) {
//				System.out.println(sp.getDanhMuc());
				dsSPLoc.add(sp);
			}
		}

		updateTable(dsSPLoc);
		lamMoi();
	}

	private void lamMoi() {
		lbHienThiMaHD.setText("");
		lbTongTien.setText("");
		lbTongTien.setText("");
		txtTienKhachDua.setText("");
		lbTienThua.setText("");
		cbbHinhThucGiaoHang.setSelectedItem(null);
		cbbHinhThucThanhToan.setSelectedItem(null);
		dateChooserNgaytao.setDate(null);
		textAreaGhiChu.setText("");
		modelGioHang.setRowCount(0);

	}

	private void taoHoaDon() {
		// Thiết lập ngày giờ hiện tại cho dateChooserNgaytao
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date currentDate = new Date();
		String formattedDate = format.format(currentDate);
		dateChooserNgaytao.setDate(currentDate);

		hoaDon = new HoaDon_DAO();
		lbHienThiMaHD.setText(hoaDon.taoMaHoaDon());
		long tongTien = 0;
		for (int i = 0; i < tableGioHang.getRowCount(); i++) {
			int soLuongNhap = (int) tableGioHang.getValueAt(i, 2);
			long donGia = (long) tableGioHang.getValueAt(i, 1);
			tongTien += soLuongNhap * donGia;
		}
		// Đặt giá trị của tongTien vào ô lbTongTien
		lbTongTien.setText(Long.toString(tongTien));

		String tienKhachDuaText = txtTienKhachDua.getText();
		// Thêm sự kiện focus lost cho txtTienKhachDua
		txtTienKhachDua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// Tính toán tiền thừa và hiển thị lên lbTienThua
				try {

					long tienKhachDua = Long.parseLong(txtTienKhachDua.getText());
					long tongTien = Long.parseLong(lbTongTien.getText());
					long tienThua = tienKhachDua - tongTien;
					lbTienThua.setText(Long.toString(tienThua));
				} catch (NumberFormatException ex) {
					// Nếu nhập không đúng định dạng số, không làm gì cả
				}
			}
		});

		if (!tienKhachDuaText.isEmpty()) {
			// Kiểm tra nếu tiền thừa âm thì hiển thị thông báo và không cho phép thanh toán
			if (Long.parseLong(lbTienThua.getText()) < 0) {
				JOptionPane.showMessageDialog(null, "Tiền khách đưa không đủ.");
				btThanhToan.setEnabled(false);
				return;
			}
		}
	}

	private void taoDSChiTietHD() {
		String maHD = lbHienThiMaHD.getText();
		for (int i = 0; i < tableGioHang.getRowCount(); i++) {
			String maSP = tableGioHang.getValueAt(i, 0).toString();
			String donGia = tableGioHang.getValueAt(i, 1).toString();
			String soLuong = tableGioHang.getValueAt(i, 2).toString();
			String thanhTien = tableGioHang.getValueAt(i, 3).toString();
			ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, Long.parseLong(donGia), Integer.parseInt(soLuong),
					Long.parseLong(thanhTien));
			ctHD.themChiTietHD(cthd);

		}
	}

	private HoaDon revertHoaDonFromTXT() {
		HoaDon hd = null;
		String maHD = lbHienThiMaHD.getText();
		String maKH = txtMaKH.getText();
		MainNhanVien mainNhanVien = new MainNhanVien();
		String maNV = mainNhanVien.getMaNV();
		Long tongTien = Long.parseLong(lbTongTien.getText());
		Long tienDua = Long.parseLong(txtTienKhachDua.getText());
		Long tienThua = Long.parseLong(lbTienThua.getText());
		String hTTT = cbbHinhThucThanhToan.getSelectedItem().toString();
		String hTGH = cbbHinhThucGiaoHang.getSelectedItem().toString();
		Date ngayLap = dateChooserNgaytao.getDate();
		String ghiChu = textAreaGhiChu.getText();
		hd = new HoaDon(maHD, maKH, maNV, tongTien, tienDua, tienThua, hTTT, hTGH, new java.sql.Date(ngayLap.getTime()),
				ghiChu);
		return hd;

	}

	private void thanhToan() {

		if (lbHienThiMaHD.getText().equals("") || txtTienKhachDua.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn trước khi thanh toán");
			return;
		} else {
			HoaDon hd = revertHoaDonFromTXT();
			sanPham.capNhatSoLuong('-', soLuongNhap, selectedSanPham.getMaSanPham());
			loadDataTable();
			hoaDon.themHoaDon(hd);
			taoDSChiTietHD();
			JOptionPane.showMessageDialog(null, "Đã tạo một hóa đơn mới thành công");
		}
	}

	private void tinhPhiShip(String giaoHang) {
		String tongTienText = lbTongTien.getText();
		String tienKhachDuaText = txtTienKhachDua.getText();
		if (tongTienText.isEmpty() || tienKhachDuaText.isEmpty())
			return;
		else {
			long tongTien = Long.parseLong(tongTienText);
			long tienKhachDua = Long.parseLong(tienKhachDuaText);
			long tienThua = 0;
			String ghiChu = "";
			long phiShip = 0;
			if (giaoHang.equals("Giao hàng tận nơi")) {
				if (tongTien < 300000) {
					phiShip = 30000;
					ghiChu = "Ship 30k";
					tienThua = tienKhachDua - (tongTien + phiShip);
				} else if (tongTien < 500000) {
					phiShip = 20000;
					ghiChu = "Ship 20k";
					tienThua = tienKhachDua - (tongTien + phiShip);
				} else if (tongTien < 1000000) {
					phiShip = 10000;
					ghiChu = "Ship 10k";
					tienThua = tienKhachDua - (tongTien + phiShip);
				} else {
					ghiChu = "Miễn phí ship";
					tienThua = tienKhachDua - tongTien;
				}
				if (tongTien >= 2000000) {
					ghiChu += ", Tặng mã giảm 50k";
					tienThua += 50000;
				}

			}

			else if (giaoHang.equals("Lấy tại cửa hàng")) {
				ghiChu = "";
				tienThua = tienKhachDua - tongTien;
			}
			lbTienThua.setText(Long.toString(tienThua));
			textAreaGhiChu.setText(ghiChu);

		}

	}

	private void huyDonHang() {
		if (lbHienThiMaHD.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Chỉ hủy đơn hàng vừa được tạo", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			hoaDon.xoaHoaDon(lbHienThiMaHD.getText());
			lamMoi();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(txtTimKiem))
			cbbDanhMuc.setSelectedItem(null);

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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	private void showMess(String mess, JTextField txt) {
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, mess);

	}

	private boolean kiemTra() {
		String maKH = txtMaKHThem.getText().trim();
		String tenKH = txtTenKHThem.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (maKH.length() < 0 || !maKH.matches("^KH\\d{3}")) {
			showMess("Cú pháp mã KH phải là KH\\d{3} ", txtMaKHThem);
			return false;
		}
		if (tenKH.length() < 0 || !tenKH.matches("^[A-Z]\\p{L}+(\\s[A-Z]\\p{L}*)+")) {
			showMess("Tên khách hàng không hợp lệ ", txtTenKHThem);
			return false;
		}
		if (soDienThoai.length() < 0 || !soDienThoai.matches("^0\\d{9,10}")) {
			showMess("Số điện thoại không hợp lệ ", txtSoDienThoai);
			return false;
		}
		if (email.length() < 0 || !email.matches("[a-z]\\w+@(gmail|yahoo|iuh)\\.com")) {
			showMess(" Email không hợp lệ ", txtEmail);
			return false;
		}
		if (diaChi.isEmpty()) {
			JOptionPane.showMessageDialog(txtDiaChi, "Chưa nhập địa chỉ");
			return false;
		}
		return true;
	}
	private boolean checkExist() {
		String maKH = txtMaKHThem.getText().trim();
		if (true) {
			DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
			boolean isMaKHTrung = false;
			for (int i = 0; i < model.getRowCount(); i++) {
				String maKHTable = model.getValueAt(i, 0).toString();
				if (maKHTable.equals(maKH)) {
					isMaKHTrung = true;
					break;
				}
			}
			if (isMaKHTrung) {
				showMess("Mã khách hàng đã tồn tại trong danh sách", txtMaKHThem);
				return false;
			}
		}
		return true;
	}

	private boolean themKH() {
		KhachHang k = layKH();
		if ( checkExist() && kiemTra()) {
			String[] row = { k.getMaKH(), k.getTenKH(), k.getGioiTinh(), k.getDiaChi(), k.getSDT(), k.getEmail() };
			modelKH.addRow(row);
			khachHang.themKH(k);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
			return true;
		} else {
			return false;
		}
	}

	private KhachHang layKH() {
		String maKH = txtMaKHThem.getText().trim();
		String tenKH = txtTenKHThem.getText();
		String gioiTinh = "";
		if (rbNam.isSelected()) {
			gioiTinh = "Nam";
		} else {
			gioiTinh = "Nữ";
		}
		String soDienThoai = txtSoDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText();

		KhachHang k = new KhachHang(maKH, tenKH, gioiTinh, diaChi, soDienThoai, email);
		return k;
	}
}
