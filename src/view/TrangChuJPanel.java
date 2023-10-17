package view;

import javax.swing.JPanel;
import AppPackage.AnimationClass;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class TrangChuJPanel extends JPanel {

	AnimationClass ac = new AnimationClass();
	int x = 0;
	int y = 80;
	private JPanel panelImage;
	private JLabel img1;
	private JLabel img2;
	private JLabel img3;

	public TrangChuJPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 960, 660);
		add(panel);
		panel.setLayout(null);
		

		panelImage = new JPanel();
		panelImage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelImage.setBackground(Color.WHITE);
		panelImage.setBounds(110, 140, 720, 320);
		panel.add(panelImage);
		panelImage.setLayout(null);

		img1 = new JLabel("");
		img1.setIcon(new ImageIcon(TrangChuJPanel.class.getResource("/Images/img1.jpg")));
		img1.setBounds(0, 0, 720, 320);
		panelImage.add(img1);

		img2 = new JLabel("");
		img2.setIcon(new ImageIcon(TrangChuJPanel.class.getResource("/Images/img2.jpg")));
		img2.setBounds(0, 0, 720, 320);
		panelImage.add(img2);

		img3 = new JLabel("");
		img3.setIcon(new ImageIcon(TrangChuJPanel.class.getResource("/Images/img3.jpg")));
		img3.setBounds(0, 0, 720, 320);
		panelImage.add(img3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		panel_1.setBounds(105, 134, 730, 334);
		panel.add(panel_1);

		slideShow();

		Timer timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x += 10;
				if (x > getWidth()) {
					x = -800;
				}
				repaint();
			}
		});
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gd = (Graphics2D) g;
		gd.setFont(new Font("UTM Atlas", Font.BOLD, 50));
		gd.setColor(new Color(95, 158, 160));
		gd.drawString("CỬA HÀNG LINH KIỆN ĐIỆN TỬ TECHZONE", x, y);
	}

	public void slideShow() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				int nb = 0;
				try {
					while (true) {
						switch (nb) {

						case 0:
							Thread.sleep(2500);
							ac.jLabelXLeft(0, -720, 12, 10, img1);
							ac.jLabelXLeft(720, 0, 12, 10, img2);
							ac.jLabelXLeft(1440, 720, 12, 10, img3);
							nb++;
							break;
						case 1:
							Thread.sleep(3000);
							ac.jLabelXLeft(-720, -1440, 12, 10, img1);
							ac.jLabelXLeft(0, -720, 12, 10, img2);
							ac.jLabelXLeft(720, 0, 12, 10, img3);
							nb++;
							break;
						case 2:
							Thread.sleep(3500);
							ac.jLabelXRight(-1440, -720, 12, 10, img1);
							ac.jLabelXRight(-720, 0, 12, 10, img2);
							ac.jLabelXRight(0, 720, 12, 10, img3);
							nb++;
							break;
						case 3:
							Thread.sleep(3000);
							ac.jLabelXRight(-720, 0, 12, 10, img1);
							ac.jLabelXRight(0, 720, 12, 10, img2);
							ac.jLabelXRight(720, 1440, 12, 10, img3);
							nb = 0;
							break;

						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}).start();
	}
}
