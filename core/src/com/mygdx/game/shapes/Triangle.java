package com.mygdx.game.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameRenderer;

import java.util.Random;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Triangle {

    public Point a, b, c, center;       // Points of Triangle
    public int st;                      // Radius of circle with our triangle
    public double angle;                // Angle of rotation
    double gr;                          // variable for growing. Go on, fellow stalker
    public boolean dead;                // for prevent some exceptions
    public Color color;                 // Color of triangle

    // Constructor for full random
    public Triangle() {
        this(new Random().nextInt(Gdx.graphics.getWidth() - 80) + 80,
                new Random().nextInt(Gdx.graphics.getHeight() - 80) + 80);
    }

    // Constructor for known koords
    public Triangle(int x, int y) {
        Random r = new Random();
        center = new Point(x, y);
        color = GameRenderer.colors[r.nextInt(GameRenderer.colors.length)];
        st = r.nextInt(80) + 20;
        angle = (double) (r.nextInt(360)) / 180f * Math.PI;
        gr = 0;
        dead = false;
        reset(true);
    }

    public void setAngle(double angle){
        this.angle = angle;
    }


    // reseting all rotating and if @rotate == true rotate again
    public void reset(boolean rotate) {
        a = new Point(center.x, center.y -(int)gr);
        b = new Point(center.x + (int) Math.round(gr * Math.sin(Math.PI / 3)), center.y + (int) Math.round(gr * Math.cos(Math.PI / 3)));
        c = new Point(center.x - (int) Math.round(gr * Math.sin(Math.PI / 3)), center.y + (int) Math.round(gr * Math.cos(Math.PI / 3)));
        if(rotate)rotate(angle);
    }

    // Boring mathematical formules
    public void rotate(double angle) {
        int x = (int) Math.round(((a.x - center.x) * Math.cos(angle)) - ((a.y - center.y) * Math.sin(angle)) + center.x);
        int y = (int) Math.round(((a.x - center.x) * Math.sin(angle)) + ((a.y - center.y) * Math.cos(angle)) + center.y);
        a.x = x;
        a.y = y;
        x = (int) Math.round(((b.x - center.x) * Math.cos(angle)) - ((b.y - center.y) * Math.sin(angle)) + center.x);
        y = (int) Math.round(((b.x - center.x) * Math.sin(angle)) + ((b.y - center.y) * Math.cos(angle)) + center.y);
        b.x = x;
        b.y = y;
        x = (int) Math.round(((c.x - center.x) * Math.cos(angle)) - ((c.y - center.y) * Math.sin(angle)) + center.x);
        y = (int) Math.round(((c.x - center.x) * Math.sin(angle)) + ((c.y - center.y) * Math.cos(angle)) + center.y);
        c.x = x;
        c.y = y;
    }

    //fucntrion of growing our triangle
    public void grow(float delta) {
        if (gr < Math.PI) {
            gr += delta * 4 / 3;
            double r = st * Math.sin(gr);
            a = new Point(center.x, center.y - (int) r);
            b = new Point(center.x + (int) Math.round(r * Math.sin(Math.PI / 3)), center.y + (int) Math.round(r * Math.cos(Math.PI / 3)));
            c = new Point(center.x - (int) Math.round(r * Math.sin(Math.PI / 3)), center.y + (int) Math.round(r * Math.cos(Math.PI / 3)));
            rotate(angle);
        } else {
            dead = true;
        }
    }

    public void update(float delta) {
        grow(delta);
    }
}
