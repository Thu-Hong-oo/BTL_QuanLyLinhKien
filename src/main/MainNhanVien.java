package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bean.DanhMucBean;
import controller.ChuyenManHinhController;
import dao.NhanVien_DAO;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class MainNhanVien extends JFrame {
	private JPanel panelView;
	// private JPanel jpnSanPham, jpnHoaDon,jpnKhuyenMai, jpnNhanVien, jpnKhachHang,
	// jpnThongKe, jpnThoat;
	private JLabel jlbBanHang, jlbSanPham, jlbHoaDon, jlbKhachHang, jlbThoat;
	private JPanel jpnSanPham;
	private JPanel jpnHoaDon;
	private JPanel jpnKhachHang;
	private JPanel jpnThoat;
	private JPanel jpnBanHang;
	private JPanel jpnTrangChu;
	private JLabel jlbTrangChu;
	private JLabel lbTenNV;
	private JLabel lblNewLabel;

	private NhanVien_DAO nhanVien;
	private String tenNV;
	Login lg = Login.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Set look and feel
			String str = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
			UIManager.setLookAndFeel(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainNhanVien frame = new MainNhanVien();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainNhanVien() {
		initialize();
		ChuyenManHinhController control = new ChuyenManHinhController(panelView);
		control.setView(jpnTrangChu, jlbTrangChu);
		List<DanhMucBean> listItems = new ArrayList<>();
		listItems.add(new DanhMucBean("TrangChu", jpnTrangChu, jlbTrangChu));
		listItems.add(new DanhMucBean("BanHang", jpnBanHang, jlbBanHang));
		listItems.add(new DanhMucBean("SanPham", jpnSanPham, jlbSanPham));
		listItems.add(new DanhMucBean("HoaDon", jpnHoaDon, jlbHoaDon));
		listItems.add(new DanhMucBean("KhachHang", jpnKhachHang, jlbKhachHang));
		listItems.add(new DanhMucBean("Thoat", jpnThoat, jlbThoat));
		control.setEvent(listItems);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MainNhanVien.class.getResource("/Images/icons8-workstation-50.png")));
		setSize(1187, 700);
		setTitle("PHẦN MÊM QUẢN LÝ LINH KIỆN ĐIỆN TỬ");
		// frame.setBounds(100, 100, 1064, 723);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1181, 663);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(new Color(95, 158, 160));
		panelLeft.setBounds(-11, 0, 226, 663);
		panel.add(panelLeft);
		panelLeft.setLayout(null);

		JLabel lbLeftSP_4 = new JLabel("");
		lbLeftSP_4.setBounds(57, 443, 28, 38);
		panelLeft.add(lbLeftSP_4);

		jpnSanPham = new JPanel();
		jpnSanPham.setBackground(new Color(95, 158, 160));
		jpnSanPham.setBounds(12, 340, 214, 38);
		panelLeft.add(jpnSanPham);
		jpnSanPham.setLayout(null);

		jlbSanPham = new JLabel("Sản phẩm");
		jlbSanPham.setBackground(new Color(95, 158, 160));
		jlbSanPham.setBounds(37, 6, 125, 25);
		jpnSanPham.add(jlbSanPham);
		jlbSanPham.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/sanPham.png")));
		jlbSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnHoaDon = new JPanel();
		jpnHoaDon.setBackground(new Color(95, 158, 160));
		jpnHoaDon.setLayout(null);
		jpnHoaDon.setBounds(12, 400, 214, 38);
		panelLeft.add(jpnHoaDon);

		jlbHoaDon = new JLabel("Hóa đơn");
		jlbHoaDon.setBounds(37, 6, 125, 25);
		jpnHoaDon.add(jlbHoaDon);
		jlbHoaDon.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/hoaDon.png")));
		jlbHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnKhachHang = new JPanel();
		jpnKhachHang.setBackground(new Color(95, 158, 160));
		jpnKhachHang.setLayout(null);
		jpnKhachHang.setBounds(12, 460, 214, 38);
		panelLeft.add(jpnKhachHang);

		jlbKhachHang = new JLabel("Khách hàng");
		jlbKhachHang.setBounds(37, 6, 125, 25);
		jpnKhachHang.add(jlbKhachHang);
		jlbKhachHang.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/KhachHang.png")));
		jlbKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnThoat = new JPanel();
		jpnThoat.setBackground(new Color(95, 158, 160));
		jpnThoat.setLayout(null);
		jpnThoat.setBounds(12, 520, 214, 38);
		panelLeft.add(jpnThoat);

		jlbThoat = new JLabel("Đăng xuất");
		jlbThoat.setBounds(37, 6, 125, 25);
		jpnThoat.add(jlbThoat);
		jlbThoat.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/thoat.png")));
		jlbThoat.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnBanHang = new JPanel();
		jpnBanHang.setLayout(null);
		jpnBanHang.setBackground(new Color(95, 158, 160));
		jpnBanHang.setBounds(12, 280, 214, 38);
		panelLeft.add(jpnBanHang);

		jlbBanHang = new JLabel("Bán hàng");
		jlbBanHang.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/banHang.png")));
		jlbBanHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlbBanHang.setBounds(37, 6, 125, 25);
		jpnBanHang.add(jlbBanHang);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/staff.png")));
		lblNewLabel.setBounds(40, 17, 155, 127);
		panelLeft.add(lblNewLabel);

		lbTenNV = new JLabel("");
		lbTenNV.setForeground(new Color(47, 79, 79));
		lbTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		lbTenNV.setFont(new Font("UTM Eremitage", Font.BOLD, 18));
		lbTenNV.setBounds(12, 156, 214, 43);
		panelLeft.add(lbTenNV);

		jpnTrangChu = new JPanel();
		jpnTrangChu.setBounds(12, 220, 214, 38);
		panelLeft.add(jpnTrangChu);
		jpnTrangChu.setLayout(null);
		jpnTrangChu.setBackground(new Color(95, 158, 160));

		jlbTrangChu = new JLabel("Trang chủ");
		jlbTrangChu.setIcon(new ImageIcon(MainNhanVien.class.getResource("/Images/icons8-home-28.png")));
		jlbTrangChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlbTrangChu.setBounds(37, 6, 125, 25);
		jpnTrangChu.add(jlbTrangChu);

		panelView = new JPanel();
		panelView.setBackground(Color.WHITE);
		panelView.setBounds(215, 0, 960, 660);
		panel.add(panelView);
		tenNV = lg.getTenNV();
	//	System.out.println(tenNV);
		lbTenNV.setText(tenNV);

	}

	public String getMaNV() {
		nhanVien = new NhanVien_DAO();
		String maNV = nhanVien.getMaNVByHoTen(tenNV);
		return maNV;
	}

}
