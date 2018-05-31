package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;

import java.util.Random;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for commercial and learnin use <3;
 */
public class Part extends Triangle {

    private Boss parent;
    private boolean initiate;
    private int hp;

    public int getHP() {
        return hp;
    }

    public void hit(int dmg) {
        hp -= dmg;
        setColor(hp);
        if (hp <= 0) {
            growAcc = 10.0;
            parent.removePart(this);
        }
    }

    public Part(Boss boss, int x, int y) {
        super(boss.world, x, y);
        parent = boss;
        setMoveTarget((int) parent.center.x, (int) parent.center.y);
        initiate = boss.init;
        setSpeed(100f);
        hp = Math.min(boss.lvl, 6) - 1;
        hp = new Random().nextInt(hp) + 1;
        setColor(hp);
        eps = 15;
        boss.init = false;
    }


    public void move(float delta) {
        if (hp > 0) {
            if (parent.generating) {
                super.move(delta);
                if (initiate && getMoveTarget() == null) {
                    parent.generating = false;
                }
            } else {
                double r = center.calculateDistance(parent.center);
                center.x = parent.center.x + (int) Math.round((Math.cos(getAngle())) * r);
                center.y = parent.center.y - (int) Math.round((Math.sin(getAngle())) * r);
                setAngle(getAngle() + delta);
                postroit();
            }
            if (getGr() < Math.PI / 2) grow(delta);
        }
    }

    @Override
    public void update(float delta) {
        if (hp > 0) {
            move(delta);
        }
        if (moveTarget == null || hp <= 0) {
            move(delta);
            grow(delta);
        }
    }


}
