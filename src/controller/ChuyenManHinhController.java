package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bean.DanhMucBean;
import main.Login;
import main.MainAdmin;
import view.HoaDonJPanel;
import view.KhachHangJPanel;
//import view.KhuyenMaiJPanel;
import view.NhanVienJPanel;
import view.SanPhamJPanel;
import view.BanHangJPanel;
import view.ThongKeJPanel;
import view.TrangChuJPanel;

public class ChuyenManHinhController {
	private JPanel root; // chính là panelView dùng để hiển thị các jpanel khác nhau
	private String kindSelect = "";// lưu trữ kiểu danh mục được chọn
	private List<DanhMucBean> listItem = null;// danh sách các mục được hiển thị trên giao diện

	public ChuyenManHinhController(JPanel root) {
		this.root = root;
	}

	/**
	 * @param hiển    thị màn hình đầu tiên khi chạy trương trình, có thể xem là
	 *                trang chủ
	 * @param jlbItem
	 */
	public void setView(JPanel jpnItem, JLabel jlbItem) {
		kindSelect = "TrangChu";
		jpnItem.setBackground(new Color(224, 224, 224));
		jlbItem.setBackground(new Color(224, 224, 224));
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new TrangChuJPanel());
		root.validate();
		root.repaint();
	}

	/**
	 * phương thức này được sử dụng để gán các sự kiện click chuột vào các danh mục
	 * trên giao diện. Nó lấy danh sách các DanhMucBean làm tham số.
	 * 
	 * @param listItem
	 */
	public void setEvent(List<DanhMucBean> listItem) {
		this.listItem = listItem;

		for (DanhMucBean item : listItem) {
			item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
		}

	}

	class LabelEvent implements MouseListener {
		private JPanel node;//đây là màn hình được hiển thị khi người dùng click vào danh mục.
		private String kind;//kiểu của danh mục được chọn.

		private JPanel jpnItem;//panel chứa danh mục được chọn.
		private JLabel jlbItem;// label chứa danh mục được chọn.

		public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
			this.kind = kind;
			this.jpnItem = jpnItem;
			this.jlbItem = jlbItem;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// Lưu panel được chọn trước đó
			kindSelect = kind;
			switch (kind) {
			case "TrangChu":
				node = new TrangChuJPanel();
				break;
			case "SanPham":
				node = new SanPhamJPanel();
				break;
			case "HoaDon":
				node = new HoaDonJPanel();
				break;
//			case "KhuyenMai":
//				node = new KhuyenMaiJPanel();
//				break;
			case "NhanVien":
				node = new NhanVienJPanel();
				break;
			case "KhachHang":
				node = new KhachHangJPanel();
				break;
			case "ThongKe":
				node = new ThongKeJPanel();
				break;
			case "BanHang":
				node = new BanHangJPanel();
				break;
			default:    
				// Hiển thị giao diện đăng nhập khi người dùng chọn một mục không hợp lệ
                Login login = new Login();
                login.setVisible(true);

                // Tắt giao diện hiện tại
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(root);
                frame.dispose();
                return;
				

			}

			setChangebackGround(kind);
			root.removeAll();
			root.setLayout(new BorderLayout());
			root.add(node);
			root.validate();
			root.repaint();

		}

		@Override
		public void mousePressed(MouseEvent e) {
			kindSelect = kind;
			jpnItem.setBackground(new Color(224, 224, 224));
			jlbItem.setBackground(new Color(224, 224, 224));

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			jpnItem.setBackground(new Color(224, 224, 224));
			jlbItem.setBackground(new Color(224, 224, 224));

		}

		@Override
		public void mouseExited(MouseEvent e) {

			// nếu panel hiện tại không được chọn thì trở về màu cũ
			if (!kindSelect.equalsIgnoreCase(kind)) {
				jpnItem.setBackground(new Color(95, 158, 160));
				jlbItem.setBackground(new Color(95, 158, 160));
			}

		}

		private void setChangebackGround(String kind) {
			for (DanhMucBean item : listItem) {
				if (!item.getKind().equalsIgnoreCase(kind)) {
					item.getJpn().setBackground(new Color(95, 158, 160));
					item.getJlb().setBackground(new Color(95, 158, 160));
				} else {
					item.getJlb().setBackground(new Color(224, 224, 224));
					item.getJpn().setBackground(new Color(224, 224, 224));
				}
			}
		}
	}
}
