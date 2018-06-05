package com.mygdx.game.shapes;

import com.mygdx.game.GameRenderer;

import java.util.Random;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Part extends Triangle {

    private Boss parent;
    private boolean initiate;
    private int hp, maxhp, grdelta = 10;
    private double cooldown, gracc;
    boolean growing, wt;

    public int getHP() {
        return hp;
    }

    public void hit(int dmg) {
        hp -= dmg;
        if (hp >= 0) setColor(hp);
        if (hp <= 0) {
            growAcc = 10.0;
            world.money += maxhp;
            world.killed++;
            parent.removePart(this);
        }
    }

    public Part(Boss boss, int x, int y) {
        //super(boss.world, (int) boss.center.x, (int) boss.center.y);
        super(boss.world, (int) x, (int) y);
        parent = boss;
        //setMoveTarget(x, y);
        setMoveTarget((int) boss.center.x, (int) boss.center.y);
        initiate = boss.init;
        setSpeed(100f);
        if (boss.lvl <= 52) {
            hp = Math.min(boss.lvl, 50);
        } else {
            hp = (int) (boss.lvl * 1.3);
        }
        if (hp != 1) {
            hp = new Random().nextInt(hp - 1) + 1;
        }
        if (!initiate && boss.wave != 0) hp = boss.wave;
        maxhp = hp;
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
                setAngle(getAngle() + delta / 2);
                build();
            }
            if (getGr() < Math.PI / 2) grow(delta);
        }
    }

    public void up(float delta) {
        if (!growing && cooldown >= 2f) {
            cooldown = 0f;
            growing = true;
            wt = true;
            growAcc = 0;
        }
        if (growing) {
            if (wt) {
                growAcc -= delta * 4.0 / 3.0;
                st += (Math.sin(growAcc));
                if (growAcc <= (Math.PI / 14) * (-1)) wt = false;
            } else {
                growAcc += delta * 4.0 / 3.0;
                st += (Math.sin(growAcc));
                if (growAcc >= (Math.PI / 5)) growing = false;
            }
        } else {
            cooldown += delta;
        }
        if (center.x + st >= GameRenderer.WIDTH) {
            parent.world.lose();
        }
    }


    @Override
    public void update(float delta) {
        if (hp > 0) {
            move(delta);
            up(delta);
        }
        if (hp <= 0) {
            move(delta);
            grow(delta);
        }
    }
}
