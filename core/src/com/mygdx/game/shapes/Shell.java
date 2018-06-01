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
        dead = false;
        growAcc = 10.0;
        setGr(Math.PI / 2);
    }

    public void enable() {
        eps = (int)(st*1.5);
        setGr(Math.PI / 2);
        setColor(damage);
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
            attack();
        }
    }

    public void attack() {
        for (Part p : world.enemy.getParts()) {
            if (center.isClose(p.center, eps)) {
                p.hit(damage);
                moveTarget = null;
                setGr(Math.PI);
                return;
            }
        }
    }

    @Override
    public void update(float delta) {
        if(!world.enemy.generating) {
            move(delta);
            if (moveTarget == null) {
                grow(delta);
        }
        }
        if(moveTarget == null && world.enemy.generating){
            grow(delta);
        }
    }
}
