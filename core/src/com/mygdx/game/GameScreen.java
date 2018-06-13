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
    public static GameMenu menu;
    public static GameOver over;
    public static AssetLoader loader;
    public Preferences prefs;
    boolean starting;

    public GameScreen() {
        prefs = Gdx.app.getPreferences("Save");
        starting = true;
        world = new GameWorld(this);
        renderer = new GameRenderer(world);
        menu = new GameMenu(this, renderer.font);
        handler = new InputHandler(world);
        over = new GameOver();
        shop = new GameShop(world);
        loader = new AssetLoader();
        loader.load();
        Gdx.input.setInputProcessor(handler);  // Setting input of application to our Handler
        menu.initialize();
        menu.setVisibility(true);
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
            int autodmg = prefs.getInteger("AutomatsDMG", 0);
            int barrier = prefs.getInteger("Barrier", 0);
            int kills = prefs.getInteger("Kills", 0);
            for (int i = 0; i < damage; i++) {
                shop.get("DAMAGE").action();
                shop.get("DAMAGE").getDesc().update();
                shop.get("DAMAGE").clicked++;
            }
            for (int i = 0; i < autos; i++) {
                shop.get("Buy automatic").action();
                shop.get("Buy automatic").getDesc().update();
                shop.get("Buy automatic").clicked++;
            }
            for (int i = 0; i < cd; i++) {
                shop.get("Auto SPEED").action();
                shop.get("Auto SPEED").getDesc().update();
                shop.get("Auto SPEED").clicked++;
            }
            for (int i = 0; i < autodmg; i++) {
                shop.get("Auto DAMAGE").action();
                shop.get("Auto DAMAGE").getDesc().update();
                shop.get("Auto DAMAGE").clicked++;
            }
            for (int i = 0; i < barrier; i++) {
                shop.get("Barrier HP").action();
                shop.get("Barrier HP").getDesc().update();
                shop.get("Barrier HP").clicked++;
            }
            world.money = money;
            world.enemy.lvl = lvl;
        }
    }

    public void save(boolean reset) {
        if (reset) {
            prefs.clear();
            prefs.putInteger("Level", 0);
            prefs.flush();
        } else if (world.started) {
            prefs.putInteger("Level", world.enemy.lvl);
            prefs.putInteger("Damage", shop.get("DAMAGE").clicked);
            prefs.putInteger("Automats", shop.spawners.size());
            prefs.putInteger("AutomatsCD", shop.get("Auto SPEED").clicked);
            prefs.putInteger("AutomatsDMG", shop.get("Auto DAMAGE").clicked);
            prefs.putInteger("Barrier", shop.get("Barrier HP").clicked);
            prefs.putInteger("Money", world.money);
            prefs.putInteger("Kills", world.killed);
            prefs.flush();
        }
    }

    public void start() {
        world.initialize(shop);
        shop.initialize();
        load();
        world.enemy.generate();
        menu.setVisibility(false);
    }

    public void cont() {
        if (world.started) {
            menu.setVisibility(false);
            world.paused = false;
        }
    }

    public void replay() {
        starting = true;
        save(true);
        world.initialize(shop);
        shop.initialize();
        world.enemy.generate();
        menu.setVisibility(false);
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
        System.out.println(1f / delta);
        if (menu.isShowing()) {
            menu.render(renderer.getShapeRenderer(), renderer.getBatch(), delta);
            return;
        }
        if (!starting && !world.lose) {
            world.update(delta);
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
        loader.dispose();
    }
}
