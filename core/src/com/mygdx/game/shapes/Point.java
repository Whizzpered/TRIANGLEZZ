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

    public boolean isClose(Point p2) {
        if (Math.abs(p2.x - x) < 4 && Math.abs(p2.y - y) < 4)
            return true;
        return false;
    }
}
