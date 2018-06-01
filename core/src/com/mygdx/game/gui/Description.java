package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Whizzpered on 01.06.2018.
 * Only for commercial and learnin use <3;
 */
public class Description {

    private String[] text = new String[3];
    private Color[] colors;
    private String[] vars = new String[2];
    private ShopButton parent;
    int n = 0;

    public Description(ShopButton button) {
        parent = button;
    }

    public void update() {
        vars[0] = String.valueOf((int) parent.coef);
        vars[1] = String.valueOf(parent.price);
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        for (int i = 0; i < this.text.length; i++) {
            this.text[i] = text[i];
        }
    }

    public Color[] getColors() {
        return colors;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    public ShopButton getParent() {
        return parent;
    }

    public void setParent(ShopButton parent) {
        this.parent = parent;
    }

    public void render(BitmapFont font, SpriteBatch batch) {
        batch.begin();
        font.draw(batch, text[0], parent.getCenter().x + parent.getWidth() / 2 + 20,
                parent.getCenter().y - parent.getHeight() / 2);
        for (int i = 1; i < text.length; i++) {
            int delta = 0;
            if (Integer.parseInt(vars[i - 1]) != 0) {
                font.draw(batch, vars[i - 1], parent.getCenter().x + parent.getWidth() / 2 + 20,
                        parent.getCenter().y - parent.getHeight() / 2 + ((font.getLineHeight()) * i));
                delta = vars[i - 1].toCharArray().length * 9;
            }
            font.draw(batch, text[i], parent.getCenter().x + parent.getWidth() / 2 + delta + 20,
                    parent.getCenter().y - parent.getHeight() / 2 + (font.getLineHeight() * i));

        }
        batch.end();
    }
}
