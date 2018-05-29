package com.mygdx.game.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameRenderer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Triangle {

    public Point vertex[], center;       // Points of Triangle
    public int st;                      // Radius of circle with our triangle
    protected double angle, speed;
    protected double acc; // Angle of rotation, Speed and acceleration
    private double gr;                          // variable for growing. Go on, fellow stalker
    public boolean dead;                // for prevent some exceptions
    public Color color;                 // Color of triangle
    private Point moveTarget;           // Target where TRIANGLE can go
    private Triangle parent;
    public ArrayList<Triangle> childs;

    public Point getMoveTarget() {
        return moveTarget;
    }

    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return speed;
    }

    public double getGr() {
        return gr;
    }

    public Triangle getParent() {
        return parent;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setMoveTarget(int x, int y) {
        moveTarget = new Point(x, y);
    }

    public void setMoveTarget(Point target) {
        moveTarget = target;
    }

    public void setGr(double gr) {
        this.gr = gr;
    }

    public void setParent(Triangle parent) {
        this.parent = parent;
        st = parent.st;
        setAngle(parent.getAngle());
        color = new Color(parent.color.r / 2, parent.color.g / 2, parent.color.b / 2, 0.3f);
    }

    // Constructor for full random
    public Triangle() {
        this(new Random().nextInt(Gdx.graphics.getWidth() - 80) + 80,
                new Random().nextInt(Gdx.graphics.getHeight() - 80) + 80);
    }

    // Constructor for known koords
    public Triangle(int x, int y) {
        vertex = new Point[3];
        for (int i = 0; i < 3; i++){
            vertex[i] = new Point();
        }
        Random r = new Random();
        center = new Point(x, y);
        color = GameRenderer.colors[r.nextInt(GameRenderer.colors.length)];
        st = r.nextInt(80) + 20;
        dead = false;
        setAngle((double) (r.nextInt(360)) / 180f * Math.PI);
        setGr(0);
        setSpeed(3f);
        postroit();
    }

    public void postroit() {
        int i = 0, n = 3;
        double z = getAngle(), c, s, ang = (Math.PI*2) / n;
        double r = st * Math.sin(getGr());
        while (i < n) {
            c = Math.cos(z);
            s = Math.sin(z);
            vertex[i].x = center.x + (int) Math.round((c) * r);
            vertex[i].y = center.y - (int) Math.round((s) * r);
            z += ang;
            i++;
        }
    }

    //fucntrion of growing our triangle
    public void grow(float delta) {
        if (getGr() < Math.PI) {
            setGr(getGr() + delta * 4 / 3);
            postroit();
        } else {
            dead = true;
        }
    }

    public void move() {
        if (moveTarget != null) {
            if (!center.isClose(moveTarget)) {
                setAngle(Math.atan2(moveTarget.y - center.y, moveTarget.x - center.x));
                center.x += Math.cos(getAngle()) * getSpeed();
                center.y += Math.sin(getAngle()) * getSpeed();
            }
        }
        postroit();
    }

    public void imitate() {
        if (getParent() != null) {
            setMoveTarget(getParent().getMoveTarget());
        }
    }

    public void update(float delta) {
        grow(delta);
        move();
        if (getParent() != null) imitate();
    }


}
