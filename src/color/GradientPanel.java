package color;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        Color color1 = Color.getHSBColor(99	,95,140);
        Color color2 = Color.blue;

        // Thiết lập GradientPaint
        GradientPaint gp = new GradientPaint(
            0, 0, color1,
            panelWidth, panelHeight, color2
        );

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, panelWidth, panelHeight);
        g2d.dispose();
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Gradient Panel");
//        GradientPanel panel = new GradientPanel();
//        frame.add(panel);
//        frame.setSize(400, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
}
