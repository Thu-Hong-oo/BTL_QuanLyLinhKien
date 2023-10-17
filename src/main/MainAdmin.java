package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import bean.DanhMucBean;

import controller.ChuyenManHinhController;


import java.awt.Color;

public class MainAdmin extends JFrame {
	private JPanel panelView;
	// private JPanel jpnSanPham, jpnHoaDon,jpnKhuyenMai, jpnNhanVien, jpnKhachHang,
	// jpnThongKe, jpnThoat;
	private JLabel jlbSanPham, jlbHoaDon, jlbNhanVien, jlbKhachHang, jlbThongKe, jlbThoat,jlbTrangChu;
	private JPanel jpnTrangChu;
	private JPanel jpnSanPham;
	private JPanel jpnHoaDon;
	private JPanel jpnNhanVien;
	private JPanel jpnKhachHang;
	private JPanel jpnThongKe;
	private JPanel jpnThoat;
	private JLabel lblNewLabel;
	private JPanel contentPane;

	


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
					MainAdmin frame = new MainAdmin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainAdmin() {
		initialize();
		ChuyenManHinhController control = new ChuyenManHinhController(panelView);
		control.setView(jpnTrangChu, jlbTrangChu);
		
		List<DanhMucBean> listItems = new ArrayList<>();
		listItems.add(new DanhMucBean("TrangChu", jpnTrangChu, jlbTrangChu));
		listItems.add(new DanhMucBean("SanPham", jpnSanPham, jlbSanPham));
		listItems.add(new DanhMucBean("HoaDon", jpnHoaDon, jlbHoaDon));
		listItems.add(new DanhMucBean("NhanVien", jpnNhanVien, jlbNhanVien));
		listItems.add(new DanhMucBean("KhachHang", jpnKhachHang, jlbKhachHang));
		listItems.add(new DanhMucBean("ThongKe", jpnThongKe, jlbThongKe));
		listItems.add(new DanhMucBean("Thoat", jpnThoat, jlbThoat));
		control.setEvent(listItems);
	}
	private void initialize() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MainAdmin.class.getResource("/Images/icons8-workstation-50.png")));
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
//		gp = new GradientPanel();
//		gp.setBackground(new Color(0, 128, 0));
//		gp.setBounds(0, 128, 53, -128);
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(new Color(95, 158, 160));
//		panelLeft.add(gp);
		panelLeft.setBounds(-11, 0, 226, 663);
		panel.add(panelLeft);
		panelLeft.setLayout(null);

		JLabel lbLeftSP_4 = new JLabel("");
		lbLeftSP_4.setBounds(57, 443, 28, 38);
		panelLeft.add(lbLeftSP_4);

		jpnSanPham = new JPanel();
		jpnSanPham.setBackground(new Color(95, 158, 160));
		jpnSanPham.setBounds(12, 280, 214, 38);
		panelLeft.add(jpnSanPham);
		jpnSanPham.setLayout(null);

		jlbSanPham = new JLabel("Sản phẩm");
		jlbSanPham.setBackground(new Color(95, 158, 160));
		jlbSanPham.setBounds(37, 6, 125, 25);
		jpnSanPham.add(jlbSanPham);
		jlbSanPham.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/sanPham.png")));
		jlbSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnHoaDon = new JPanel();
		jpnHoaDon.setBackground(new Color(95, 158, 160));
		jpnHoaDon.setLayout(null);
		jpnHoaDon.setBounds(12, 330, 214, 38);
		panelLeft.add(jpnHoaDon);

		jlbHoaDon = new JLabel("Hóa đơn");
		jlbHoaDon.setBounds(37, 6, 125, 25);
		jpnHoaDon.add(jlbHoaDon);
		jlbHoaDon.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/hoaDon.png")));
		jlbHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnNhanVien = new JPanel();
		jpnNhanVien.setBackground(new Color(95, 158, 160));
		jpnNhanVien.setLayout(null);
		jpnNhanVien.setBounds(12, 380, 214, 38);
		panelLeft.add(jpnNhanVien);

		jlbNhanVien = new JLabel("Nhân viên");
		jlbNhanVien.setBounds(37, 6, 125, 25);
		jpnNhanVien.add(jlbNhanVien);
		jlbNhanVien.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/nhanVien (1).png")));
		jlbNhanVien.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnKhachHang = new JPanel();
		jpnKhachHang.setBackground(new Color(95, 158, 160));
		jpnKhachHang.setLayout(null);
		jpnKhachHang.setBounds(12, 430, 214, 38);
		panelLeft.add(jpnKhachHang);

		jlbKhachHang = new JLabel("Khách hàng");
		jlbKhachHang.setBounds(37, 6, 125, 25);
		jpnKhachHang.add(jlbKhachHang);
		jlbKhachHang.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/KhachHang.png")));
		jlbKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnThongKe = new JPanel();
		jpnThongKe.setBackground(new Color(95, 158, 160));
		jpnThongKe.setLayout(null);
		jpnThongKe.setBounds(12, 480, 214, 38);
		panelLeft.add(jpnThongKe);

		jlbThongKe = new JLabel("Thống kê");
		jlbThongKe.setBounds(37, 6, 125, 25);
		jpnThongKe.add(jlbThongKe);
		jlbThongKe.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/thongKe.png")));
		jlbThongKe.setName("lbThongKe");
		jlbThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));

		jpnThoat = new JPanel();
		jpnThoat.setBackground(new Color(95, 158, 160));
		jpnThoat.setLayout(null);
		jpnThoat.setBounds(12, 530, 214, 38);
		panelLeft.add(jpnThoat);

		jlbThoat = new JLabel("Đăng xuất");
		jlbThoat.setBounds(37, 6, 125, 25);
		jpnThoat.add(jlbThoat);
		jlbThoat.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/thoat.png")));
		jlbThoat.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/icons8_Gmail_32px_3.png")));
		lblNewLabel.setBounds(35, 17, 150, 165);
		panelLeft.add(lblNewLabel);
		
		jpnTrangChu = new JPanel();
		jpnTrangChu.setLayout(null);
		jpnTrangChu.setBackground(new Color(95, 158, 160));
		jpnTrangChu.setBounds(12, 230, 214, 38);
		panelLeft.add(jpnTrangChu);
		
		jlbTrangChu = new JLabel("Trang chủ");
		jlbTrangChu.setIcon(new ImageIcon(MainAdmin.class.getResource("/Images/icons8-home-28.png")));
		jlbTrangChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlbTrangChu.setBackground(new Color(95, 158, 160));
		jlbTrangChu.setBounds(37, 6, 125, 25);
		jpnTrangChu.add(jlbTrangChu);

		panelView = new JPanel();
		panelView.setBackground(Color.WHITE);
		panelView.setBounds(215, 0, 960, 660);
		panel.add(panelView);
		panelView.setLayout(null);
		

	}
}
