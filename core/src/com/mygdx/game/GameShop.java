package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gui.Button;
import com.mygdx.game.gui.ShopButton;
import com.mygdx.game.shapes.Spawner;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameShop {

    public ArrayList<Button> buttons = new ArrayList<Button>();
    public ArrayList<Spawner> spawners = new ArrayList<Spawner>();
    private boolean show;
    private GameWorld world;
    public double cd, damage;
    GlyphLayout layout = new GlyphLayout();
    private int x, y, width, height;

    public ShopButton get(String name) {
        for (Button b : buttons) {
            if (b instanceof ShopButton && b.getName().equals(name)) {
                return (ShopButton) b;
            }
        }
        return null;
    }

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
        x = 50;
        y = 50;
        width = GameRenderer.WIDTH - 100;
        height = GameRenderer.HEIGHT - 100;
        cd = 1;
        damage = 1;
        buttons.clear();
        spawners.clear();
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT - 100, "Confirm") {
            @Override
            public void action() {
                setVisibility(false);
                world.paused = false;
            }
        });

        ShopButton tmp1 = new ShopButton(x + 85, 150, "DAMAGE") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.damage += coef;
                    ch = world.damage;
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp1.price = 10;
        tmp1.coef = 1;
        tmp1.ch = world.damage;
        tmp1.setDesc(new String[]{"Up dmg: ",
                "Dmg by now: ",
                "Cost: "});
        buttons.add(tmp1);

        ShopButton tmp2 = new ShopButton(x + 85, 250, "Buy automatic") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.createSpawner();
                    ch = spawners.size();
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp2.price = 20;
        tmp2.limit = 40;
        tmp2.ch = spawners.size();
        tmp2.setDesc(new String[]{"Add one auto ",
                "Amount by now: ",
                "Cost: "});
        buttons.add(tmp2);
        ShopButton tmp3 = new ShopButton(x + 85, 350, "Auto SPEED") {
            @Override
            public void action() {
                if (world.money >= price) {
                    cd -= coef;
                    update();
                    ch = (float) cd;
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp3.price = 15;
        tmp3.coef = 0.07f;
        tmp3.limit = 10;
        tmp3.ch = (float) cd;
        tmp3.setDesc(new String[]{"Cd reduction: ",
                "Cd by now: ",
                "Cost: "});
        buttons.add(tmp3);
        ShopButton tmp4 = new ShopButton(x + 85, 450, "Auto DAMAGE") {
            @Override
            public void action() {
                if (world.money >= price) {
                    damage += coef;
                    update();
                    ch = (float) damage;
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp4.price = 15;
        tmp4.coef = 1f;
        tmp4.ch = (float) damage;
        tmp4.setDesc(new String[]{"Upgrade DMG: ",
                "Dmg by now: ",
                "Cost: "});
        buttons.add(tmp4);
        ShopButton tmp5 = new ShopButton(x + 85, 550, "Barrier HP") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.barrier.upMaxHp(coef);
                    update();
                    ch = world.barrier.maxhp;
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp5.price = 20;
        tmp5.coef = 50f;
        tmp5.ch = world.barrier.maxhp;
        tmp5.setDesc(new String[]{"Upgrade HP: ",
                "HP now: ",
                "Cost: "});
        buttons.add(tmp5);
    }

    public void update() {
        for (Spawner sp : spawners) {
            sp.setCd(cd);
            sp.damage = (int) damage;
        }
    }

    public void checkAvialables() {
        for (Button b : buttons) {
            if (b instanceof ShopButton && (((ShopButton) b).price > world.money)) {
                b.active = false;
            } else if (b instanceof ShopButton && (((ShopButton) b).clicked >= ((ShopButton) b).limit)) {
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
        sr.rect(x, y, width, height);
        sr.end();
        for (Button b : buttons) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(b.getColor());
            sr.rect(b.getCenter().x - b.getWidth() / 2, b.getCenter().y - b.getHeight() / 2, b.getWidth(), b.getHeight());
            sr.end();
            batch.begin();
            font.setColor(Color.WHITE);
            layout.setText(font, b.getName());
            font.draw(batch, b.getName(), b.getCenter().x - (layout.width / 2), b.getCenter().y - font.getLineHeight() / 2 + 4);
            batch.end();
            if (b instanceof ShopButton) ((ShopButton) b).getDesc().render(font, batch);
        }
    }
}
