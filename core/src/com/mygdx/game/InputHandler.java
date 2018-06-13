package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gui.Button;

/**
 * Created by Whizzpered on 02.05.2018.
 * Only for uncommercial and learning use :)
 */
public class InputHandler implements InputProcessor {

    GameWorld world;
    //int price = 10;

    public InputHandler(GameWorld world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (GameScreen.menu.isShowing()) {
            for (Button b : GameScreen.menu.buttons) {
                if (b.pressed(screenX, screenY)) {
                    return true;
                }
            }
        } else if (world.lose) {
            for (Button b : GameScreen.over.buttons) {
                if (b.pressed(screenX, screenY)) {
                    return true;
                }
            }
            return false;
        } else if (GameScreen.shop.isShowing()) {
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
            if (!world.enemy.generating && !world.paused) {
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
