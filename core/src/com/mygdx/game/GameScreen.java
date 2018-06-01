package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.gui.GameShop;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class GameScreen implements Screen {

    public static GameWorld world;        // Logic class of our game
    public static GameRenderer renderer;  // Render class of out game
    public static InputHandler handler;   // Input Handler, duuh
    public static GameShop shop;
    float kk = 0;           // garbage variable for tracking FPS
    boolean starting;

    public GameScreen() {
        starting = true;
        world = new GameWorld(this);
        renderer = new GameRenderer(world);
        handler = new InputHandler(world);
        shop = new GameShop(world);
        Gdx.input.setInputProcessor(handler);  // Setting input of application to our Handler
        world.initialize(shop);
        shop.initialize();
        starting = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (!starting) {
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
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
