package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameScreen implements Screen {

    public static GameWorld world;        // Logic class of our game
    public static GameRenderer renderer;  // Render class of out game
    public static InputHandler handler;   // Input Handler, duuh
    public static GameShop shop;
    public static GameOver over;
    public Preferences prefs;
    float kk = 0;                         // garbage variable for tracking FPS
    boolean starting;

    public GameScreen() {
        prefs = Gdx.app.getPreferences("Save");
        starting = true;
        world = new GameWorld(this);
        renderer = new GameRenderer(world);
        handler = new InputHandler(world);
        over = new GameOver();
        shop = new GameShop(world);
        Gdx.input.setInputProcessor(handler);  // Setting input of application to our Handler
        world.initialize(shop);
        shop.initialize();
        //load();
        starting = false;
    }

    public void load() {
        int lvl = prefs.getInteger("Level", 0);
        if (lvl != 0) {
            world.money = Integer.MAX_VALUE;
            int money = prefs.getInteger("Money", 0);
            int damage = prefs.getInteger("Damage", 0);
            int autos = prefs.getInteger("Automats", 0);
            int cd = prefs.getInteger("AutomatsCD", 0);
            for (int i = 0; i < damage; i++) {
                shop.get("Upgrade attack").action();
                shop.get("Buy automatic").getDesc().update();
                shop.get("Upgrade attack").clicked++;
            }
            for (int i = 0; i < autos; i++) {
                shop.get("Buy automatic").action();
                shop.get("Buy automatic").getDesc().update();
                shop.get("Buy automatic").clicked++;
            }
            for (int i = 0; i < cd; i++) {
                shop.get("Upgrade automatic").action();
                shop.get("Buy automatic").getDesc().update();
                shop.get("Upgrade automatic").clicked++;
            }
            world.money = money;
            world.enemy.lvl = lvl;
            world.enemy.generate();
        }
    }

    public void save(boolean reset) {
        if (reset) {
            prefs.clear();
            prefs.putInteger("Level", 0);
        } else {
            prefs.putInteger("Level", world.enemy.lvl);
            prefs.putInteger("Damage", world.damage);
            prefs.putInteger("Automats", shop.spawners.size());
            prefs.putInteger("AutomatsCD", shop.get("Upgrade automatic").clicked);
            prefs.putInteger("Money", world.money);
        }
    }

    public void replay() {
        starting = true;
        world.initialize(shop);
        shop.initialize();
        starting = false;
    }

    public void exit() {
        prefs.flush();
        dispose();
        System.exit(0);
    }

    public void lose() {
        over.initialize(this, renderer.font);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (!starting && !world.lose) {
            world.update(delta);
            if (kk >= 0.5f || renderer.fps == 0) {
                renderer.fps = 1 / delta;
                kk = 0;
            } else {
                kk += delta;
            }
            if (shop.isShowing())
                shop.render(renderer.getShapeRenderer(), renderer.getFont(), renderer.getBatch());
            renderer.render();
        } else if (world.lose) {
            over.render(renderer.getShapeRenderer(), renderer.getBatch(), delta);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        save(false);
        prefs.flush();
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        save(false);
        prefs.flush();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
