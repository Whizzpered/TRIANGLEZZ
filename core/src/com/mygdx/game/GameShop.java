package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.GameWorld;
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
    public double cd;
    GlyphLayout layout = new GlyphLayout();
    private int x, y, width, height;

    public ShopButton get(String name){
        for (Button b : buttons) {
            if(b instanceof ShopButton && b.getName().equals(name)){
                return (ShopButton)b;
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
        buttons.clear();
        spawners.clear();
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT - 50, "Confirm") {
            @Override
            public void action() {
                setVisibility(false);
            }
        });

        ShopButton tmp1 = new ShopButton(x + 85, 150, "Upgrade attack") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.damage += coef;
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp1.price = 10;
        tmp1.coef = 1;
        tmp1.setDesc(new String[]{"more powerfull tap TRIANGLEZZ",
                " more point to damage!",
                "dead triangles for it"});
        buttons.add(tmp1);

        ShopButton tmp2 = new ShopButton(x + 85, 250, "Buy automatic") {
            @Override
            public void action() {
                if (world.money >= price) {
                    world.createSpawner();
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp2.price = 20;
        tmp2.limit = 40;
        tmp2.setDesc(new String[]{"Will get more TRIANGLEZZ, huh",
                "You will get one more automatic TRIANGLE",
                "dead triangles for it"});
        buttons.add(tmp2);
        ShopButton tmp3 = new ShopButton(x + 85, 350, "Upgrade automatic") {
            @Override
            public void action() {
                if (world.money >= price) {
                    cd -= coef;
                    update();
                    world.money -= price;
                    price *= 1.5;
                }
            }
        };
        tmp3.price = 15;
        tmp3.coef = 0.07f;
        tmp3.setDesc(new String[]{"More powerfull automatic TRIANGLE",
                " less seconds cooldown of automatic!",
                " dead triangles for it"});
        buttons.add(tmp3);
    }

    public void update() {
        for (Spawner sp : spawners) {
            sp.setCd(cd);
        }
    }

    public void checkAvialables() {
        for (Button b : buttons) {
            if (b instanceof ShopButton && (((ShopButton) b).price >= world.money)) {
                b.active = false;
            } else {
                b.active = true;
            }
            if (b instanceof ShopButton && ((ShopButton) b).clicked >= ((ShopButton) b).limit)
                b.active = false;
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
            font.draw(batch, b.getName(), b.getCenter().x - (layout.width / 2), b.getCenter().y - font.getLineHeight() / 2);
            batch.end();
            if (b instanceof ShopButton) ((ShopButton) b).getDesc().render(font, batch);
        }
    }
}
