package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.shapes.Point;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public abstract class Button {
    private Point center;
    private int height;
    private int width;
    private Color color;
    private String name;
    public boolean active = true;

    public boolean pressed(int x, int y) {
        if (Math.abs(center.x - x) < (getWidth() / 2) && Math.abs(center.y - y) < (getHeight() / 2)) {
            action();
            return true;
        }
        return false;
    }

    public Button(int x, int y, String name) {
        setCenter(x, y);
        setName(name);
        setColor(new Color(100 / 255f, 149 / 255f, 237 / 255f, 1));
        setWidth(150);
        setHeight(50);
    }

    public abstract void action();

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(int x, int y) {
        center = new Point(x, y);
    }

    public void setCenter(Point target) {
        center = target;
    }

    public Color getColor() {
        if (active) return color;
        else return new Color(color.r / 1.3f, color.g / 1.3f, color.b / 1.3f, 1);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
