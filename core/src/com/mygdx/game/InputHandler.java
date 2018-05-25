package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
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
        Triangle tr = new Triangle(screenX, screenY);
        Triangle tr2 = new Triangle(screenX + 2, screenY + 2);
        tr2.st = tr.st;
        tr2.angle = tr.angle;
        tr2.color = new Color(tr.color.r / 2, tr.color.g / 2, tr.color.b / 2, 0.3f);
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
        Triangle tr = new Triangle(screenX, screenY);
        Triangle tr2 = new Triangle(screenX + 2, screenY + 2);
        tr2.st = tr.st;
        tr2.angle = tr.angle;
        tr2.color = new Color(tr.color.r / 2, tr.color.g / 2, tr.color.b / 2, 0.3f);
        world.trias.add(tr2);
        world.trias.add(tr);
        return false;
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
