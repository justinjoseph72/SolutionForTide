package app.labEscape;

import java.util.List;

/**
 * Created by Justin on 20/05/2017.
 * This class will store the x and y values
 */
public class Point {

    private int x = -1;
    private int y = -1;
    private List<Point> freeHorizontalPaths;
    private List<Point> freeVerticalPaths;

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Point> getFreeHorizontalPaths() {
        return freeHorizontalPaths;
    }

    public void setFreeHorizontalPaths(List<Point> freeHorizontalPaths) {
        this.freeHorizontalPaths = freeHorizontalPaths;
    }

    public List<Point> getFreeVerticalPaths() {
        return freeVerticalPaths;
    }

    public void setFreeVerticalPaths(List<Point> freeVerticalPaths) {
        this.freeVerticalPaths = freeVerticalPaths;
    }
}
