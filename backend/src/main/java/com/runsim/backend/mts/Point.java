package com.runsim.backend.mts;

class Deee {
    public Deee(byte b) {

    }
}

class Yazı {
    public Yazı(byte b) {

    }

    public Yazı(Point p) {

    }

    public Yazı(Deee p) {

    }
}

public class Point {
    private int x, y;

    public Point(Point point) {
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
