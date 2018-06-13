package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gui.Button;

import java.util.ArrayList;

/**
 * Created by Whizzpered on 03.06.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameOver {

    public ArrayList<Button> buttons = new ArrayList<Button>();
    GlyphLayout layout = new GlyphLayout();
    String[] messages;
    String[] stats;
    double alpha;
    GameScreen main;
    BitmapFont font;

    public void initialize(GameScreen screen, BitmapFont f) {
        messages = new String[4];
        stats = new String[2];
        main = screen;
        font = f;
        messages[0] = " Congratzz, you have lose! But you're free to think you win";
        messages[1] = "You have reached";
        stats[0] = main.world.enemy.lvl + "  lvl";
        messages[2] = "And killed";
        int frags = main.world.killed;
        stats[1] = frags + "  trianglezz";
        if (frags >= 100) {
            messages[3] = "Not bad. Try again!";
        } else if (frags >= 200) {
            messages[3] = "They had children, u know?";
        } else if (frags >= 500) {
            messages[3] = "You're just like Hitler, wow!";
        } else {
            messages[3] = "You're such a pussy. Remove this game";
        }
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT - 50, "Return") {
            @Override
            public void action() {
                main.menu.setVisibility(true);
            }
        });
        buttons.add(new Button(GameRenderer.WIDTH / 2, GameRenderer.HEIGHT - 220, "Play again") {
            @Override
            public void action() {
                main.replay();
            }
        });
        main.save(true);
    }

    public void render(ShapeRenderer sr, SpriteBatch batch, double delta) {
        if (alpha < 1) alpha += delta;
        Gdx.gl.glEnable(GL20.GL_BLEND);                               //Boring OpenGL begin of cycle of rendering
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Setting for possibility to render transparent shapes         // updating the camera
        Gdx.gl.glClearColor(29 / 255f, 30 / 255f, 51 / 255f, (float) alpha);      //setting color of background in 2 strokes
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(new Color(37 / 255f, 40 / 255f, 80 / 255f, (float) alpha));
        sr.end();
        Color c;
        for (Button b : buttons) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            c = b.getColor();
            sr.setColor(c.r, c.g, c.b, (float) alpha);
            sr.rect(b.getCenter().x - b.getWidth() / 2, b.getCenter().y - b.getHeight() / 2, b.getWidth(), b.getHeight());
            sr.end();
            batch.begin();
            c = Color.WHITE;
            font.setColor(c.r, c.g, c.b, (float) alpha);
            layout.setText(font, b.getName());
            font.draw(batch, b.getName(), b.getCenter().x - (layout.width / 2), b.getCenter().y - font.getLineHeight() / 2 + 4);
            batch.end();
        }
        batch.begin();
        float height = (font.getLineHeight() + 20), d = 0;
        layout.setText(font, messages[0]);
        font.draw(batch, messages[0], GameRenderer.WIDTH / 2 - layout.width / 2,
                GameRenderer.HEIGHT / 5 + d);
        d += (height);
        for (int i = 1; i < messages.length; i++) {
            layout.setText(font, messages[i]);
            font.draw(batch, messages[i], GameRenderer.WIDTH / 2 - layout.width / 2,
                    GameRenderer.HEIGHT / 5 + d);
            d += (height);
            if (i - 1 < stats.length) {
                layout.setText(font, stats[i - 1]);
                c = Color.GREEN;
                font.setColor(c.r, c.g, c.b, (float) alpha);
                font.draw(batch, stats[i - 1], GameRenderer.WIDTH / 2 - layout.width / 2,
                        GameRenderer.HEIGHT / 5 + d);
                d += (height);
                c = Color.WHITE;
                font.setColor(c.r, c.g, c.b, (float) alpha);
            }
        }
        batch.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
