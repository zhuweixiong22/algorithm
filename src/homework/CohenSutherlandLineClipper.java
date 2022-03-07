package homework;

/**
 * @author novo
 * @date 2022/3/5-16:29
 */
public class CohenSutherlandLineClipper implements LineClipper {

    public static final int INSIDE = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 4;
    public static final int TOP = 8;


    private int xLeft;
    private int xRight;
    private int yBottom;
    private int yTop;

    public CohenSutherlandLineClipper(int xLeft, int xRight, int yBottom, int yTop) {
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yBottom = yBottom;
        this.yTop = yTop;
    }

    /**
     * 编码
     *
     * @param x
     * @param y
     * @return
     */
    private int encode(double x, double y) {
        int code = INSIDE;

        if (x < xLeft) {
            code |= LEFT;
        } else if (x > xRight) {
            code |= RIGHT;
        }
        if (y < yBottom) {
            code |= BOTTOM;
        } else if (y > yTop) {
            code |= TOP;
        }
        return code;
    }


    /**
     * 裁剪
     *
     * @param line
     * @return
     */
    public Line clip(Line line) {
        System.out.println("正在裁剪...");
        int x0 = line.x0, x1 = line.x1, y0 = line.y0, y1 = line.y1;
        int code0 = encode(x0, y0);
        int code1 = encode(x1, y1);
        System.out.println("P1编码：" + intToBinary(code0) + ", P2编码：" + intToBinary(code1));
        boolean fullyVisible = false;

        while (true) {
            // 显然可见
            if ((code0 | code1) == 0) {
                fullyVisible = true;
                break;
            } else if ((code0 & code1) != 0) {
                // 在同一侧 显然不可见
                break;
            } else {
                int newX, newY;
                // 取一个窗外点
                int outPointCode = (code0 != 0) ? code0 : code1;

                // 四个方向求交
                if ((outPointCode & TOP) != 0) {
                    newX = x0 + (x1 - x0) * (yTop - y0) / (y1 - y0);
                    newY = yTop;
                } else if ((outPointCode & BOTTOM) != 0) {
                    newX = x0 + (x1 - x0) * (yBottom - y0) / (y1 - y0);
                    newY = yBottom;
                } else if ((outPointCode & RIGHT) != 0) {
                    newY = y0 + (y1 - y0) * (xRight - x0) / (x1 - x0);
                    newX = xRight;
                } else {
                    newY = y0 + (y1 - y0) * (xLeft - x0) / (x1 - x0);
                    newX = xLeft;
                }

                // 用交点替换窗外点 完成一次裁剪
                if (outPointCode == code0) {
                    x0 = newX;
                    y0 = newY;
                    code0 = encode(x0, y0);
                } else {
                    x1 = newX;
                    y1 = newY;
                    code1 = encode(x1, y1);
                }
            }
        }

        if (fullyVisible) {
            return new Line(x0, y0, x1, y1);
        }
        return null;
    }

    /**
     * int -> binary 补全0
     *
     * @param x
     * @return
     */
    private String intToBinary(int x) {
        System.out.println("x " + x);
        StringBuilder str = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            str.append(x >>> i & 1);
        }
        return str.toString();
    }
}
