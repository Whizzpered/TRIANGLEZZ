package com.mygdx.game.shapes;

import com.mygdx.game.GameWorld;

/**
 * Created by Whizzpered on 28.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Shell extends Triangle {

    private int damage;

    public void setDamage(int dmg) {
        damage = dmg;
    }

    public Shell(GameWorld world, int x, int y) {
        super(world, x, y);
        setGr(Math.PI / 2);
        dead = false;
        damage = 1;
        growAcc = 10.0;
        setColor(damage);
    }

    public void enable() {
        setGr(Math.PI / 2);
        eps = 27;
        postroit();
    }

    @Override
    public void move(float delta){
        if (moveTarget != null) {
            if (!center.isClose(moveTarget,4)) {
                setAngle(Math.atan2(moveTarget.y - center.y, moveTarget.x - center.x));
                center.x += Math.cos(getAngle()) * getSpeed() * delta;
                center.y += Math.sin(getAngle()) * getSpeed() * delta;
                postroit();
            } else {
                moveTarget = null;
            }
        }
        attack();
    }

    public void attack() {
        for (Part p : world.enemy.getParts()) {
            if (center.isClose(p.center, eps)) {
                p.hit(damage);
                moveTarget = null;
                setGr(Math.PI);
            }
        }
    }
}
