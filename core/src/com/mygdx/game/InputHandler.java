package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.shapes.Boss;
import com.mygdx.game.shapes.Shell;

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
        //world.createTriangle(screenX,screenY);
        //world.createTest(screenX + 120, screenY);
        //world.createSpawner();
        world.createShell(screenX,screenY);
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
