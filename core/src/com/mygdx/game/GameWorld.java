package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.gui.Button;
import com.mygdx.game.gui.GameShop;
import com.mygdx.game.shapes.*;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameWorld {

    public ArrayList<Triangle> trias = new ArrayList<Triangle>();      // List of our TRIANGLEZZ

    public ArrayList<Button> buttons = new ArrayList<Button>();     // Still crap and not using
    public Boss enemy;
    public int money;
    public GameShop shop;
    Preferences prefs = Gdx.app.getPreferences("Game Preferences");
    public boolean rot = true;
    public int damage;
    GameScreen main;

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

    public void createShell(int x, int y, int damage) {
        Shell tr = new Shell(this, x, y);
        tr.st = 15;
        tr.setMoveTarget(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        tr.setDamage(damage);
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
        tr.rotate(0);
        shop.spawners.add(tr);
        trias.add(tr);
    }

    public void createTest(int x, int y) {
        Test tr = new Test(this, x, y);
        tr.st = 30;
        trias.add(tr);
    }

    // Huh, empty constructor. Lame
    public GameWorld(GameScreen screen) {
        main = screen;

    }

    public void save() {

    }

    public void load() {
        //if(prefs)
    }

    public void initialize(GameShop shop) {
        enemy = new Boss(this, GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        enemy.generate();
        this.shop = shop;
        damage = 1;
        buttons.add(new Button(100, GameRenderer.HEIGHT - 50, "Shop") {
            @Override
            public void action() {
                GameScreen.shop.setVisibility(true);
            }
        });
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT - 50, "Rotating") {
            @Override
            public void action() {
                rot = !rot;
                active = rot;
            }
        });
        buttons.add(new Button(GameRenderer.WIDTH - 100, GameRenderer.HEIGHT - 50, "Exit") {
            @Override
            public void action() {
                main.dispose();
                System.exit(0);
            }
        });
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
