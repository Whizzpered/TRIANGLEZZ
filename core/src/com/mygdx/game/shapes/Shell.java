package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;

/**
 * Created by Whizzpered on 28.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Shell extends Triangle {

    public Shell(int x, int y) {
        super(x, y);
        //setAngle(Math.atan2(y - GameRenderer.HEIGHT / 2, x - GameRenderer.WIDTH / 2));
        setMoveTarget(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        setGr(Math.PI/2);
        dead = false;
    }

    public void enable() {
        setGr(Math.PI/2);
        postroit();
    }

    @Override
    public void update(float delta) {
        move();
    }
}
