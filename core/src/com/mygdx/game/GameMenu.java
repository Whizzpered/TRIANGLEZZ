package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gui.Button;
import com.mygdx.game.shapes.Triangle;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 05.06.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameMenu {

    GameScreen main;
    public ArrayList<Button> buttons = new ArrayList<Button>();
    public ArrayList<Triangle> background = new ArrayList<Triangle>();
    boolean show;
    GlyphLayout layout = new GlyphLayout();
    BitmapFont font;
    float z = 0, cd = 0f, erg;

    public boolean isShowing() {
        return show;
    }

    public void setVisibility(boolean show) {
        this.show = show;
        checkAvialables();
        if (main.world.started) {
            buttons.get(1).setName("New Game");
        }
    }

    public GameMenu(GameScreen screen, BitmapFont f) {
        main = screen;
        font = f;
    }

    public void checkAvialables() {
        buttons.get(0).active = main.world.started;

    }

    public Triangle[] getTrias() {
        Triangle[] tmp = new Triangle[background.size()];
        int i = 0;
        for (Triangle tr : background) {
            tmp[i++] = tr;
        }
        return tmp;
    }

    public void initialize() {
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2 - 100, "Continue") {
            @Override
            public void action() {
                main.cont();
            }
        });
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2, "Start") {
            @Override
            public void action() {
                if (!main.world.started) main.start();
                else {
                    main.replay();
                }
            }
        });
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2 + 100, "Exit") {
            @Override
            public void action() {
                main.save(false);
                main.exit();
            }
        });
        circlin();
    }

    public void createTriangle() {
        Triangle tr = new Triangle(null) {
            @Override
            public void update(float delta) {
                move(delta);
                if (moveTarget == null) {
                    grow(delta);
                }
            }
        };
        tr.st = 70;
        tr.setSpeed(5f);
        tr.setGr(Math.PI / 2);
        tr.setMoveTarget(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT / 2);
        background.add(tr);
    }

    public void circlin() {
        int n = 664;
        //if (erg >= cd) {
            //if (isShowing()) {
                for (int i = 0; i < n; i++) {
                    createTriangle();
                    z += Math.PI * 2 / n;
                }
            //}
           // erg -= cd;
        //} else {
            //erg += delta;
        //}
    }

    public void render(ShapeRenderer sr, SpriteBatch batch, double delta) {
        for (Triangle tr : getTrias()) {
            if (!tr.dead) tr.update((float) delta);
            else background.remove(tr);
        }
        Gdx.gl.glEnable(GL20.GL_BLEND);                               //Boring OpenGL begin of cycle of rendering
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Setting for possibility to render transparent shapes         // updating the camera
        Gdx.gl.glClearColor(29 / 255f, 30 / 255f, 51 / 255f, 1f);      //setting color of background in 2 strokes
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (Triangle tr : background) {                        // Rendering all trinaglezz
            sr.setColor(tr.color.r, tr.color.g, tr.color.g, 0.1f);
            sr.triangle(tr.vertex[0].x, tr.vertex[0].y, tr.vertex[1].x, tr.vertex[1].y, tr.vertex[2].x, tr.vertex[2].y);
        }
        sr.end();
        int l;
        if (main.world.started && !main.world.lose) {
            l = 0;
        } else {
            l = 1;
        }
        for (int i = l; i < buttons.size(); i++) {
            Button b = buttons.get(i);
            sr.begin(ShapeRenderer.ShapeType.Filled);
            Color c = b.getColor();
            sr.setColor(c.r, c.g, c.b, 1f);
            sr.rect(b.getCenter().x - b.getWidth() / 2, b.getCenter().y - b.getHeight() / 2, b.getWidth(), b.getHeight());
            sr.end();
            batch.begin();
            c = Color.WHITE;
            font.setColor(c.r, c.g, c.b, 1f);
            layout.setText(font, b.getName());
            font.draw(batch, b.getName(), b.getCenter().x - (layout.width / 2), b.getCenter().y - font.getLineHeight() / 2);
            //float height = (font.getLineHeight() + 20), d = 0;
            batch.end();
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
