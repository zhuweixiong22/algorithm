package homework;

/**
 * @author novo
 * @date 2022/3/5-16:01
 */

import java.awt.*;
import javax.swing.*;


public class Main extends JPanel {

    private int xLeft;
    private int xRight;
    private int yBottom;
    private int yTop;

    private LineClipper clipper;


    /**
     * Constructor
     *
     * @param xMin Bottom side of rectangle
     * @param yMin Left side of rectangle
     * @param xMax Top side of rectangle
     * @param yMax Right side of rectangle
     */
    public Main(int xMin, int yMin, int xMax, int yMax) {
        this.xLeft = xMin;
        this.yBottom = yMin;
        this.xRight = xMax;
        this.yTop = yMax;
        clipper = new CohenSutherlandLineClipper(xMin, xMax, yMin, yMax);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 800, 600);

        g2d.setColor(Color.blue);
        drawLine(g2d, xLeft, 0, xLeft, 600); // 左边界
        drawLine(g2d, xRight, 0, xRight, 600); // 右边界
        drawLine(g2d, 0, yBottom, 800, yBottom); // 下边界
        drawLine(g2d, 0, yTop, 800, yTop); // 上边界


        int x0, y0, x1, y1;
        Line line, clippedLine;
        for (int i = 0; i < 5; i++) {
            x0 = (int) (Math.random() * 800);
            x1 = (int) (Math.random() * 800);
            y0 = (int) (Math.random() * 600);
            y1 = (int) (Math.random() * 600);
            line = new Line(x0, y0, x1, y1);
            clippedLine = clipper.clip(line);

            System.out.println("Original: " + line);
            System.out.println("Clipped: " + clippedLine);

            if (clippedLine == null) {
                g2d.setColor(Color.red);
                drawLine(g2d, line.x0, line.y0, line.x1, line.y1);
            } else {
                g2d.setColor(Color.red);
                drawLine(g2d, line.x0, line.y0, clippedLine.x0, clippedLine.y0);
                drawLine(g2d, clippedLine.x1, clippedLine.y1, line.x1, line.y1);
                g2d.setColor(Color.green);
                drawLine(g2d, clippedLine.x0, clippedLine.y0, clippedLine.x1, clippedLine.y1);
            }
        }
    }


    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, getHeight() - y1, x2, getHeight() - y2);
    }

    public static void main(String[] args) {


        JFrame mainFrame = new JFrame("直线切割");
        mainFrame.setSize(1000, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        int xLeft, yBottom, xRight, yTop;
        xLeft = 100;
        xRight = 700;
        yBottom = 100;
        yTop = 480;

        mainFrame.add(new Main(xLeft, yBottom, xRight, yTop));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
