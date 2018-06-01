package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;
import com.mygdx.game.shapes.Spawner;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 01.06.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameShop {

    public ArrayList<Button> buttons = new ArrayList<Button>();
    public ArrayList<Spawner> spawners = new ArrayList<Spawner>();
    private boolean show;
    private GameWorld world;
    public int damage = 1;
    GlyphLayout layout = new GlyphLayout();

    public GameShop(GameWorld world) {
        this.world = world;
    }

    public boolean isShowing() {
        return show;
    }

    public void setVisibility(boolean show) {
        this.show = show;
    }

    public void initialize() {
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT - 50, "Confirm") {
            @Override
            public void action() {
                setVisibility(false);
            }
        });
        ShopButton tmp1 = new ShopButton(200, 150, "Upgrade attack") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.damage += coef;
                    world.money -= price;
                    price *= 1.35;
                }
            }
        };
        tmp1.price = 10;
        tmp1.coef = 1;
        tmp1.setDesc(new String[]{"Everybody wants more powerfull TRIANGLEZZ, right?",
                " more point to damage!",
                "dead triangles for it"});
        buttons.add(tmp1);

        ShopButton tmp2 = new ShopButton(200, 250, "Buy automatic") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.createSpawner();
                    world.money -= price;
                    price *= 1.35;
                }
            }
        };
        tmp2.price = 20;
        tmp2.setDesc(new String[]{"Everybody wants more TRIANGLEZZ, right?",
                "You will get one more automatic TRIANGLE",
                "dead triangles for it"});
        buttons.add(tmp2);
        ShopButton tmp3 = new ShopButton(200, 350, "Upgrade automatic") {
            @Override
            public void action() {
                if (world.money >= price) {
                    damage += coef;
                    update();
                    world.money -= price;
                    price *= 1.35;
                }
            }
        };
        tmp3.price = 15;
        tmp3.coef = 1;
        tmp3.setDesc(new String[]{"Everybody wants more powerfull  automatic TRIANGLE, right?",
                " more point to damage!",
                " dead triangles for it"});
        buttons.add(tmp3);
    }

    public void update() {
        for (Spawner sp : spawners) {
            sp.damage = damage;
        }
    }

    public void checkAvialables() {
        for (Button b : buttons) {
            if (b instanceof ShopButton && (((ShopButton) b).price >= world.money)) {
                b.active = false;
            } else {
                b.active = true;
            }
        }
    }

    public void render(ShapeRenderer sr, BitmapFont font, SpriteBatch batch) {
        checkAvialables();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(new Color(37 / 255f, 40 / 255f, 80 / 255f, 1));
        sr.rect(50, 50, GameRenderer.WIDTH - 100, GameRenderer.HEIGHT - 100);
        sr.end();
        for (Button b : buttons) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(b.getColor());
            sr.rect(b.getCenter().x - b.getWidth() / 2, b.getCenter().y - b.getHeight() / 2, b.getWidth(), b.getHeight());
            sr.end();
            batch.begin();
            font.setColor(Color.WHITE);
            layout.setText(font, b.getName());
            font.draw(batch, b.getName(), b.getCenter().x - (layout.width / 2), b.getCenter().y - font.getLineHeight() / 2);
            batch.end();
            if (b instanceof ShopButton) ((ShopButton) b).getDesc().render(font, batch);
        }
    }
}
