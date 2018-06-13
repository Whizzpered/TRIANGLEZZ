package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

import java.util.Random;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
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
        setCd(world.shop.cd);
        damage = (int)world.shop.damage;
        build();
    }

    public void rotate(float delta) {
        build(ang);
        ang -= delta / 3;
    }

    public void build(double ang) {
        this.ang = ang;
        double r = GameRenderer.WIDTH / 2 - 20;
        center.x = GameRenderer.WIDTH / 2 + (int) Math.round((Math.cos(ang)) * r);
        center.y = GameRenderer.HEIGHT / 2 - (int) Math.round((Math.sin(ang)) * r * 1.4);
    }

    @Override
    public void move(float delta) {
        if (world.rot) {
            rotate(delta);
        }
        build();
        if (energy >= cd && world.killin) {
            world.createShell((int) center.x, (int) center.y, damage);
            energy -= cd;
        }
        if (energy >= cd) {
            energy -= cd;
        }
        energy += delta;

    }

    public void setCd(double cd) {
        this.cd = cd;
    }
}
