package com.mygdx.game.shapes;

import com.mygdx.game.GameWorld;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Test extends Triangle {
    public Test(GameWorld world, int x, int y) {
        super(world, x, y);
    }


    @Override
    public void build() {
        int i = 0, n = 3;
        double z = Math.PI * 7 /6, c, s, ang = (Math.PI * 2) / n;
        double r = st * Math.sin(getGr());
        while (i < n) {
            c = Math.cos(z);
            s = Math.sin(z);
            vertex[i].x = center.x + (int) Math.round((c) * r);
            vertex[i].y = center.y - (int) Math.round((s) * r);
            z += ang;
            i++;
        }
    }
}
