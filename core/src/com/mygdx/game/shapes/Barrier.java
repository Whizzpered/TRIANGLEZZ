package com.mygdx.game.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

/**
 * Created by Whizzpered on 08.06.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Barrier {

    public float maxhp, hp, r;
    public Point center;
    private float cd, enrg;
    GameWorld world;

    public Barrier(GameWorld world) {
        this.world = world;
        maxhp = 50f;
        hp = maxhp;
        center = new Point(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        r = GameRenderer.WIDTH / 4 + 15;
    }

    public void update(float delta) {
        if (!world.enemy.generating) {
            if (enrg >= cd) {
                hp -= (0.01f * world.enemy.getParts().length);
                enrg = 0;
            } else {
                enrg += delta;
            }
        } else {
            if (hp <= maxhp) {
                hp += (maxhp / 100f);
            } else {
                hp = maxhp;
            }
        }
    }

    public void render(ShapeRenderer sr, BitmapFont font, SpriteBatch batch) {
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.WHITE);
        sr.circle(center.x, center.y, r);
        sr.setColor(Color.CYAN);
        sr.circle(center.x, center.y, r + 1);
        sr.rect(GameRenderer.WIDTH / 4, GameRenderer.HEIGHT / 12, GameRenderer.WIDTH / 2, 15);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(GameRenderer.colors[38]);
        sr.rect(GameRenderer.WIDTH / 4 + 1, GameRenderer.HEIGHT / 12 + 1, (GameRenderer.WIDTH / 2) * (hp / maxhp) - 2, 13);
        sr.end();
    }

}
