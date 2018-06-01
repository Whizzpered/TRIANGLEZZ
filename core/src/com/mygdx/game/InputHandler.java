package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gui.Button;
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
        if (GameScreen.shop.isShowing()) {
            for (Button b : GameScreen.shop.buttons) {
                if (b.pressed(screenX, screenY)) {
                    return true;
                }
            }
        } else {
            for (Button b : world.buttons) {
                if (b.pressed(screenX, screenY)) {
                    return true;
                }
            }
            if (!world.enemy.generating) {
                world.createShell(screenX, screenY, world.damage);
            }
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
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
