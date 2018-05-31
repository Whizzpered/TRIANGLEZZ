package com.mygdx.game;

import com.mygdx.game.shapes.*;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class GameWorld {

    public ArrayList<Triangle> trias = new ArrayList<Triangle>();      // List of our TRIANGLEZZ
    float kk = 0;                                               // Still crap and not using
    public Boss enemy;

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
        Triangle tr = new Triangle(this, x, y);
        trias.add(tr);
    }

    public void createShell(int x, int y) {
        Shell tr = new Shell(this, x, y);
        tr.st = 30;
        tr.setMoveTarget(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        tr.enable();
        trias.add(tr);
    }

    public void createPart(Boss boss, int x, int y) {
        Part tr = new Part(boss, x, y);
        tr.st = 30;
        boss.addpart(tr);
        trias.add(tr);
    }

    public void createSpawner() {
        Spawner tr = new Spawner(this, 0);
        tr.st = 30;
        tr.setMoveTarget(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        tr.enable();
        trias.add(tr);
    }

    public void createTest(int x, int y) {
        Test tr = new Test(this, x, y);
        tr.st = 30;
        trias.add(tr);
    }

    // Huh, empty constructor. Lame
    public GameWorld() {

    }

    public void initialize() {
        enemy = new Boss(this, GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        enemy.generate();
    }

    public void circlin(double z) {
        int n = 8;
        for (int i = 0; i < n; i++) {
            double c, s;
            double r = GameRenderer.HEIGHT / 2;
            c = Math.cos(z);
            s = Math.sin(z);
            int x = GameRenderer.WIDTH / 2 + (int) Math.round((c) * r);
            int y = GameRenderer.HEIGHT / 2 - (int) Math.round((s) * r);
            createShell(x, y);
            z += Math.PI * 2 / n;
        }
    }

    double z = 0;  //TEMP

    //Here's cycle of our logic and processing all object's
    public void update(float delta) {
        for (Triangle tr : getTrias()) {
            if (!tr.dead) tr.update(delta);
            else {
                trias.remove(tr);
            }
        }
        enemy.update(delta);
        //circlin(z);
        z += (Math.PI * 2) / 190;
    }

}
