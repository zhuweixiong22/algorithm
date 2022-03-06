package homework;

/**
 * @author novo
 * @date 2022/3/5-16:27
 */
public class Line {
    public int x0;
    public int y0;
    public int x1;
    public int y1;

    public Line(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public String toString() {
        return "LineSegment(x0: " + x0 + ", y0: " + y0 + "; x1: " + x1 + ", y1: " + y1 + ")";
    }
}
