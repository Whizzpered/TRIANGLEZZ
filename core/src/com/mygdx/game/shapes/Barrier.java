package com.mygdx.game.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AssetLoader;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;

/**
 * Created by Whizzpered on 08.06.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Barrier {

    public float maxhp, hp, r;
    public Point center;
    public Sprite sprite;
    Color color;
    private float cd, enrg;
    GlyphLayout layout = new GlyphLayout();
    GameWorld world;

    public Barrier(GameWorld world) {
        this.world = world;
        maxhp = 50f;
        hp = maxhp;
        cd = 0f;
        center = new Point(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        r = GameRenderer.WIDTH / 4 + 15;
        sprite = AssetLoader.getSprite();
        //sprite.setScale(GameRenderer.WIDTH / 2 / 1080f);
        sprite.setSize(GameRenderer.WIDTH / 2 + 30, GameRenderer.WIDTH / 2 + 30);
        System.out.println(sprite);
        sprite.setCenter(center.x, center.y);
        sprite.setColor(Color.WHITE);
    }

    public void update(float delta) {
        if (!world.enemy.generating) {
            if (enrg >= cd) {
                hp -= (0.01f * world.enemy.lvl);
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

    public void upMaxHp(float delta) {
        float c = hp / maxhp;
        maxhp += delta;
        hp = maxhp * c;
        int x = (int) (maxhp / 100);
        if (x > 0 && x < 51) {
            sprite.setColor(GameRenderer.colors[x]);
        } else {
            sprite.setColor(Color.WHITE);
        }
    }

    public void render(ShapeRenderer sr, BitmapFont font, SpriteBatch batch) {
        sr.begin(ShapeRenderer.ShapeType.Line);
        font.setColor(Color.WHITE);
        sr.rect(GameRenderer.WIDTH / 4, GameRenderer.HEIGHT / 12, GameRenderer.WIDTH / 2, 15);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(GameRenderer.colors[13]);
        sr.rect(GameRenderer.WIDTH / 4 + 1, GameRenderer.HEIGHT / 12 + 1, (GameRenderer.WIDTH / 2) * (hp / maxhp) - 2, 13);
        sr.end();
        batch.begin();
        sprite.draw(batch);
        font.setColor(Color.WHITE);
        String s = (int) (hp) + " / " + (int) maxhp;
        font.getData().setScale(0.9f);
        layout.setText(font, s);
        font.draw(batch, s, GameRenderer.WIDTH / 2 - (layout.width / 2), GameRenderer.HEIGHT / 12 + 1);
        font.getData().setScale(1f);
        batch.end();
    }

}
