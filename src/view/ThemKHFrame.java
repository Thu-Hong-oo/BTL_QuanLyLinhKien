package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHang_DAO;
import entity.KhachHang;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class ThemKHFrame extends JFrame {

	private JPanel contentPane;
	private JTable tableKH;
	private DefaultTableModel modelKH;
	private KhachHang_DAO khachHang;
	private JTextField txtTenKHThem;
	private JTextField txtMaKHThem;
	private JTextField txtEmail;
	private JTextField txtSoDienThoai;
	private JTextArea txtDiaChi;
	public JButton btThem;
	public JButton btChon;
	private JRadioButton rbNam, rbNu;
	private String maKh = "";
	private String tenKh = "";
	private boolean isBtChonClick = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemKHFrame frame = new ThemKHFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThemKHFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 564, 339);
		contentPane.add(tabbedPane);

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
				isBtChonClick = true;
				int selectedRowKH = tableKH.getSelectedRow();
				if (selectedRowKH >= 0) {
					maKh = tableKH.getValueAt(selectedRowKH, 0).toString();
					tenKh = tableKH.getValueAt(selectedRowKH, 1).toString();
		
				}

			}
		});
		
	}
	private void showMess(String mess, JTextField txt) {
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, mess);
		
	}
	private boolean kiemTra() {
		String maKH = txtMaKHThem.getText().trim();
		String tenKH = txtTenKHThem.getText();
		String soDienThoai=txtSoDienThoai.getText().trim();
		String email=txtEmail.getText().trim();
		String diaChi=txtDiaChi.getText();
		
		if(maKH.length()<0||!maKH.matches("^KH\\d{3}")) {
			showMess("Cú pháp mã KH phải là KH\\d{3} ", txtMaKHThem);
			return false;
		}
		if(tenKH.length()<0||!tenKH.matches("^[A-Z]\\p{L}+(\\s[A-Z]\\p{L}*)+")) {
			showMess("Tên khách hàng không hợp lệ ", txtTenKHThem);
			return false;
		}
		if(soDienThoai.length()<0||!soDienThoai.matches("^0\\d{9,10}")) {
			showMess("Số điện thoại không hợp lệ ", txtSoDienThoai);
			return false;
		}
		if(email.length()<0||!email.matches("[a-z]\\w+@(gmail|yahoo|iuh)\\.com")) {
			showMess(" Email không hợp lệ ", txtEmail);
			return false;
		}
		return true;
	}
	private boolean themKH() {
		KhachHang k=layKH();
		if(khachHang.themKH(k)) {
			String[] row= {k.getMaKH(),k.getTenKH(),k.getGioiTinh(),k.getDiaChi(),k.getSDT(),k.getEmail()};
			modelKH.addRow(row);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
			return true;
		}else {
			return false;
		}
	}
	private KhachHang layKH() {
		String maKH = txtMaKHThem.getText().trim();
		String tenKH = txtTenKHThem.getText();
		String gioiTinh = "";
		if(rbNam.isSelected()) {
			gioiTinh="Nam";
		}else {
			gioiTinh="Nữ";
		}
		String soDienThoai=txtSoDienThoai.getText().trim();
		String email=txtEmail.getText().trim();
		String diaChi=txtDiaChi.getText();
		
		KhachHang k=new KhachHang(maKH, tenKH, gioiTinh, diaChi, soDienThoai, email);
		return k;
	}
	public boolean chon() {
		return isBtChonClick;
	}
	public String getMaKH() {
		
		return maKh;
	}
	public String getTenKH() {
		return tenKh;
	}
	
	
	
}
