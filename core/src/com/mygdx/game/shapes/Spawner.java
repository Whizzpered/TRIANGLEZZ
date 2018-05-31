package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Spawner extends Shell {

    private double ang;
    private double cd, energy;

    public Spawner(GameWorld world, double startang) {
        super(world, GameRenderer.WIDTH, GameRenderer.HEIGHT);
        moveTarget = new Point(-9800, -9800);
        ang = startang;
        cd = 1.0;                                            //cooldown in seconds
        energy = 0.0;
    }

    @Override
    public void move(float delta) {
        double r = GameRenderer.HEIGHT / 2;
        center.x = GameRenderer.WIDTH / 2 + (int) Math.round((Math.cos(ang)) * r);
        center.y = GameRenderer.HEIGHT / 2 - (int) Math.round((Math.sin(ang)) * r);
        postroit();
        if (energy >= cd) {
            world.createShell((int) center.x, (int) center.y);
            energy -= cd;
        }
        ang += delta / 3;
        energy += delta;
    }

}
