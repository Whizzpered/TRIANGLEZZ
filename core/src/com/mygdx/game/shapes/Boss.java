package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Boss extends Triangle {

    private ArrayList<Part> parts;
    public boolean generating, init;
    double z = 0;  //TEMP
    private double cd, energy, coef;
    public int lvl;

    public Boss(GameWorld world, int x, int y) {
        super(world, x, y);
        parts = new ArrayList<Part>();
        lvl = 6;
    }

    public Part[] getParts() {
        Part[] tmp = new Part[parts.size()];
        int i = 0;
        for (Part tr : parts) {
            tmp[i++] = tr;
        }
        return tmp;
    }

    public void addpart(Part t) {
        parts.add(t);
    }

    public void removePart(Part t) {
        parts.remove(t);
    }

    public void generate() {
        generating = true;
        init = true;
        energy = 0.0;
        cd = 2 / (double) (lvl);
        //coef = 1.0;

    }

    public void circlin(double z) {
        int n = 16;
        for (int i = 0; i < n; i++) {
            double c, s;
            double r = GameRenderer.WIDTH/3;
            c = Math.cos(z);
            s = Math.sin(z);
            int x = (int) center.x + (int) Math.round((c) * r);
            int y = (int) center.y - (int) Math.round((s) * r);
            world.createPart(this, x, y);
            z += Math.PI * 2 / n;
        }
    }

    // Funcction to create triangle from smaller TRIANGLEZZZ
    public void doTringle(int n, int x, int y) {
        int sr = 30;
        int is = n;
        for (int i = 0; i < n; i++) {
            world.createPart(this, x, y);
        }
        while (n-- > 0) {
            for (int i = 0; i < n; i++) {
                int nx = (int) Math.round(x + (sr * 10.227 / 12) * (is - n) + (3 * (sr) / Math.sqrt(3)) * (i + 1)),
                        ny = y - (sr / 2) * (is - n) - sr * (is - n - 1);
                world.createPart(this, nx, ny);
                nx = (int) Math.round(x + sr * 10 / 12 * (is - n) + (3 * (sr) / Math.sqrt(3)) * (i + 1));
                ny = y - (sr * 3 / 2) * (is - n);
                world.createPart(this, nx, ny);
            }
        }
        //for (int i = 0; i < n; i++) world.createPart(this, x, y);
    }

    public void die() {
        lvl++;
        generate();
    }

    @Override
    public void update(float delta) {
        if (generating) {
            if (energy >= cd) {
                circlin(z);
                energy -= cd;
            }
            energy += delta;
        }
        if (getParts().length == 0 && !generating) {
            die();
        }
        z += (Math.PI * 2) / 190;
    }
}
