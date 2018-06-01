package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

import java.util.Random;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Spawner extends Triangle {

    private double ang;
    private double cd, energy;
    public int damage;

    public Spawner(GameWorld world, double startang) {
        super(world, GameRenderer.WIDTH, GameRenderer.HEIGHT);
        Random r = new Random();
        moveTarget = new Point(-9800, -9800);
        ang = startang;
        color = GameRenderer.colors[r.nextInt(GameRenderer.colors.length)];
        cd = 1.0;                                            //cooldown in seconds
        energy = 0.0;
        damage = 1;
    }

    public void enable() {
        setGr(Math.PI / 2);
        eps = 27;
        postroit();
    }

    @Override
    public void move(float delta) {
        if(world.rot) {
            double r = GameRenderer.WIDTH / 2;
            center.x = GameRenderer.WIDTH / 2 + (int) Math.round((Math.cos(ang)) * r);
            center.y = GameRenderer.HEIGHT / 2 - (int) Math.round((Math.sin(ang)) * r);
            ang += delta / 3;
        }
            postroit();
            if (energy >= cd) {
                world.createShell((int) center.x, (int) center.y, damage);
                System.out.println(damage);
                energy -= cd;
            }

            energy += delta;

    }

}
