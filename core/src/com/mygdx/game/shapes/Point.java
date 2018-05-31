package com.mygdx.game.shapes;

/**
 * Created by Whizzpered on 02.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Point {

    public float x, y;

    public Point() {
        this(0, 0);
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isClose(Point p2, float dist) {
        if (Math.abs(p2.x - x) < dist && Math.abs(p2.y - y) < dist)
            return true;
        return false;
    }

    public double calculateDistance(Point p2) {
        double distx = p2.x - x,
                disty = p2.y - y,
                dist = Math.sqrt(Math.pow(distx, 2) + Math.pow(disty, 2));
        return dist;
    }
}
