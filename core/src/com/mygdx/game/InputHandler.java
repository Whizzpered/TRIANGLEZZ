package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.shapes.Spawner;

/**
 * Created by Whizzpered on 02.05.2018.
 * Only for commercial and learnin use <3;
 */
public class InputHandler implements InputProcessor {

    GameWorld world;
    int price = 10;

    public InputHandler(GameWorld world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (!world.enemy.generating) {
            //world.createShell(screenX,screenY);
            //world.createTriangle(screenX,screenY);
            //world.createTest(screenX + 120, screenY);
            world.createSpawner();
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.R) {
            world.rot = !world.rot;
        }
        if (keycode == Input.Keys.U) {
            if (world.money >= price) {
                for (Spawner sp : world.spawners) {
                    sp.damage += 1;
                }
                world.money -= price;
                price *= 2;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //world.createShell(screenX,screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
