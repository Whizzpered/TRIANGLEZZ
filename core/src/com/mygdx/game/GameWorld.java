package com.mygdx.game;

import com.mygdx.game.shapes.Triangle;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class GameWorld {

    public ArrayList<Triangle> trias = new ArrayList<Triangle>();      // List of our TRIANGLEZZ
    float kk = 0;                                               // Still crap and not using

    // Casting our ArrayList to Array to prevent some exceptions
    public Triangle[] getTrias() {
        Triangle[] tmp = new Triangle[trias.size()];
        int i = 0;
        for (Triangle tr : trias) {
            tmp[i++] = tr;
        }
        return tmp;
    }

    // Huh, empty constructor. Lame
    public GameWorld() {

    }

    // Funcction to create triangle from smaller TRIANGLEZZZ
    public void doTringle(int n, int x, int y) {
        int sr = 300;
        int is = n;
        int starty = y;
        int startx = x;
        for (int i = 0; i < n; i++) {
            Triangle tr = new Triangle(startx + (int)(3 * (sr) / Math.sqrt(3)) * (i + 1), starty);
            tr.st = sr;
            tr.angle = 0;
            tr.reset(true);
            trias.add(tr);
        }
        while (n-- > 0) {
            for (int i = 0; i < n; i++) {
                Triangle tr = new Triangle((int) Math.round(startx + (sr * 10.227 / 12) * (is - n) + (3 * (sr) / Math.sqrt(3)) * (i + 1)), starty - (sr * 1 / 2) * (is - n) - sr * (is - n - 1));
                tr.st = sr;
                tr.angle = Math.PI;
                tr.reset(true);
                trias.add(tr);
                tr = new Triangle((int) Math.round(startx + sr * 10 / 12 * (is - n) + (3 * (sr) / Math.sqrt(3)) * (i + 1)), starty - (sr * 3 / 2) * (is - n));
                tr.st = sr;
                tr.angle = 0;
                tr.reset(true);
                trias.add(tr);
            }
        }
    }


    //Here's cycle of our logic and processing all object's
    public void update(float delta) {
        for (Triangle tr : getTrias()) {
            if (!tr.dead) tr.update(delta);
            else trias.remove(tr);
        }
    }

}
