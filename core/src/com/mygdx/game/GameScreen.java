package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class GameScreen implements Screen {

    GameWorld world;
    GameRenderer renderer;
    InputHandler handler;
    float kk = 0;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
        handler = new InputHandler(world);
        Gdx.input.setInputProcessor(handler);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        if (kk >= 0.5f || renderer.fps == 0) {
            renderer.fps = 1 / delta;
            kk = 0;
        } else {
            kk += delta;
        }
        renderer.render();
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

    }
}
