package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.shapes.Shell;
import com.mygdx.game.shapes.Triangle;

/**
 * Created by Whizzpered on 02.05.2018.
 * Only for commercial and learnin use <3;
 */
public class InputHandler implements InputProcessor {

    GameWorld world;

    public InputHandler(GameWorld world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Shell tr = new Shell(screenX, screenY);
        Shell tr2 = new Shell(screenX + 2, screenY + 2);
        tr2.setParent(tr);
        tr.enable();
        tr2.enable();
        world.trias.add(tr2);
        world.trias.add(tr);
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
        Shell tr = new Shell(screenX, screenY);
        Shell tr2 = new Shell(screenX + 2, screenY + 2);
        tr2.setParent(tr);
        tr.enable();
        tr2.enable();
        world.trias.add(tr2);
        world.trias.add(tr);
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
