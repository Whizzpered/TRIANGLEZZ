package com.mygdx.game;

import com.mygdx.game.shapes.Shell;
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
    public void createTriangle(int x, int y) {
        Triangle tr = new Triangle(x, y);
        Triangle tr2 = new Triangle(x + 2, y + 2);
        tr2.setParent(tr);
        trias.add(tr2);
        trias.add(tr);
    }

    public void createShell(int x, int y) {
        Shell tr = new Shell(x, y);
        Shell tr2 = new Shell(x + 2, y + 2);
        tr2.setParent(tr);
        tr.enable();
        tr2.enable();
        trias.add(tr2);
        trias.add(tr);
    }


    // Huh, empty constructor. Lame
    public GameWorld() {

    }

    public void circlin(double z) {
        int n = 4;
        for(int i = 0; i < n;i ++) {
            double c, s;
            double r = GameRenderer.HEIGHT / 2;
            c = Math.cos(z);
            s = Math.sin(z);
            int x = GameRenderer.WIDTH / 2 + (int) Math.round((c) * r);
            int y = GameRenderer.HEIGHT / 2 - (int) Math.round((s) * r);
            createShell(x, y);
            z+=Math.PI*2/n;
        }
    }

    // Funcction to create triangle from smaller TRIANGLEZZZ
    /*public void doTringle(int n, int x, int y) {
        int sr = 300;
        int is = n;
        int starty = y;
        int startx = x;
        for (int i = 0; i < n; i++) {
            Triangle tr = new Triangle(startx + (int) (3 * (sr) / Math.sqrt(3)) * (i + 1), starty);
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
    }*/
    double z = 0;  //TEMP

    //Here's cycle of our logic and processing all object's
    public void update(float delta) {
        for (Triangle tr : getTrias()) {
            if (!tr.dead) tr.update(delta);
            else trias.remove(tr);
        }
        circlin(z);
        z += (Math.PI * 2) / 190;
    }

}
