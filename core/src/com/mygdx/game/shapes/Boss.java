package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Boss extends Triangle {

    private ArrayList<Part> parts = new ArrayList<Part>();
    public boolean generating, init;
    double z = 0;  //TEMP
    private double cd, energy;
    public int lvl, wave;

    public Boss(GameWorld world, int x, int y) {
        super(world, x, y);
        lvl = 1;
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

    public void regenerate(int lvl) {
        this.lvl = lvl;
        parts.clear();
        generate();
    }

    public void generate() {
        System.out.println(lvl);
        wave = 0;
        z = 0;
        if (world != null) {
            for (Triangle s : world.getTrias()) {
                if (s instanceof Shell) {
                    s.setMoveTarget(null);
                }
            }
        }
        generating = true;
        init = true;
        energy = 0.0;
        cd = 2 / (double) (Math.min(lvl, 40));
        if (lvl < 5) {
            generateEarly();
            //generating = false;
        }
    }

    public void circlin(double z) {
        int n = Math.min(Math.max(lvl / 3, 1), 16);
        if (!init) wave++;
        for (int i = 0; i < n; i++) {
            double c, s;
            double r = GameRenderer.WIDTH / 4;
            c = Math.cos(z);
            s = Math.sin(z);
            int x = (int) center.x + (int) Math.round((c) * r);
            int y = (int) center.y - (int) Math.round((s) * r);
            world.createPart(this, x, y);
            z += Math.PI * 2 / n;
        }
    }

    public void generateEarly() {
        if (lvl == 1)
            world.createPart(this, (int) center.x, (int) center.y);
        else {
            int n = lvl;
            double r = GameRenderer.WIDTH / 4;
            for (int i = 0; i < n; i++) {
                double c, s;
                c = Math.cos(z);
                s = Math.sin(z);
                int x = (int) (center.x + Math.round((c) * r));
                int y = (int) (center.y - Math.round((s) * r));
                world.createPart(this, x, y);
                z += Math.PI * 2 / n;
            }
        }
    }

    public void die() {
        lvl++;
        world.main.save(false);
        generate();
    }

    @Override
    public void update(float delta) {
        if (generating) {
            if (energy >= cd) {
                if (lvl >= 5) circlin(z);
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
