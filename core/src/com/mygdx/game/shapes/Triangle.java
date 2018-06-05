package com.mygdx.game.shapes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Triangle {

    public Point vertex[], center;       // Points of Triangle
    public float st;                      // Radius of circle with our triangle
    protected double angle, speed;
    protected double acc, growAcc = 4.0 / 3.0; // Angle of rotation, Speed and accelerations
    private double gr;                          // variable for growing. Go on, fellow stalker
    public boolean dead;                // for prevent some exceptions
    public Color color;                 // Color of triangle
    protected Point moveTarget;         // Target where TRIANGLE can go
    public ArrayList<Triangle> children;
    protected GameWorld world;
    protected int eps;

    // Constructor for full random
    public Triangle(GameWorld world) {
        this(world, new Random().nextInt(GameRenderer.WIDTH - 80) + 80,
                new Random().nextInt(GameRenderer.HEIGHT - 80) + 80);
    }

    public void setColor(int primaryAttribute) {
        if (primaryAttribute < GameRenderer.colors.length) {
            color = GameRenderer.colors[primaryAttribute];
        } else {
            color = GameRenderer.colors[GameRenderer.colors.length-1];
        }
    }

    // Constructor for known koords
    public Triangle(GameWorld world, int x, int y) {
        vertex = new Point[3];
        for (int i = 0; i < 3; i++) {
            vertex[i] = new Point();
        }
        Random r = new Random();
        center = new Point(x, y);
        children = new ArrayList<Triangle>();
        color = GameRenderer.colors[r.nextInt(GameRenderer.colors.length)];
        st = r.nextInt(80) + 20;
        dead = false;
        eps = 4;
        this.world = world;
        setAngle((double) (r.nextInt(360)) / 180f * Math.PI);
        setGr(0);
        setSpeed(200f);
        build();
    }

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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setMoveTarget(int x, int y) {
        Point target = new Point(x, y);
        setMoveTarget(target);
    }


    public void addChild(Triangle t) {
        children.add(t);
    }

    public void setMoveTarget(Point target) {
        moveTarget = target;
        for (Triangle t : children) {
            t.setMoveTarget((int) (target.x + 2), (int) (target.y + 2));
        }
        if(target!=null)
        setAngle(Math.atan2(moveTarget.y - center.y, moveTarget.x - center.x));
    }

    public void setGr(double gr) {
        this.gr = gr;
    }

    public void setParent(Triangle parent) {
        st = parent.st;
        setAngle(parent.getAngle());
        color = new Color(parent.color.r / 2, parent.color.g / 2, parent.color.b / 2, 0.3f);
        parent.addChild(this);
    }

    public void build() {
        int i = 0, n = 3;
        double z = getAngle(), c, s, ang = (Math.PI * 2) / n;
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

    //function of growing our triangle
    public void grow(float delta) {
        if (getGr() < Math.PI) {
            setGr(getGr() + delta * growAcc);
            build();
        } else {
            dead = true;
        }
    }

    public void move(float delta) {
        if (moveTarget != null) {
            if (!center.isClose(moveTarget, eps)) {
                setAngle(Math.atan2(moveTarget.y - center.y, moveTarget.x - center.x));
                center.x += Math.cos(getAngle()) * getSpeed() * delta;
                center.y += Math.sin(getAngle()) * getSpeed() * delta;
                build();
            } else {
                moveTarget = null;
            }
        }
    }

    public void update(float delta) {
        if(!world.enemy.generating) {
            move(delta);
            if (moveTarget == null) {
                grow(delta);
            }
        }
    }


}
