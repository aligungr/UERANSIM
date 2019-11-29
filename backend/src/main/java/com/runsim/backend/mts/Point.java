package com.runsim.backend.mts;

class Yazı {
    public Yazı(Point p) {

    }
}

public class Point {
    private int x, y;

    public Point(Point point) {
        this(point.x * point.y);
    }

    public Point(double d) {
        x = (int) d;
        y = (int) d;
    }

    public Point(Yazı d) {
        x = (int) 2;
        y = (int) 2;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
