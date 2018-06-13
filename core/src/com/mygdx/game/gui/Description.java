package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameRenderer;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class Description {

    private String[] text = new String[3];
    private Color[] colors;
    private String[] vars = new String[3];
    private ShopButton parent;
    int n = 0;

    public Description(ShopButton button) {
        parent = button;
    }

    public void update() {
        vars[0] = String.valueOf(parent.coef);
        vars[1] = String.valueOf(parent.ch);
        vars[2] = String.valueOf(parent.price);
    }

    public String[] getText() {
        return text;
    }

    public void setText(String text, int index) {
        this.text[index] = text;
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
        for (int i = 0; i < text.length; i++) {
            float delta;
            GameRenderer.layout.setText(font, text[i] + " ");
            font.setColor(Color.WHITE);
            font.draw(batch, text[i], parent.getCenter().x + parent.getWidth() / 2 + 20,
                    parent.getCenter().y - parent.getHeight() / 2 + ((font.getLineHeight()) * i) - 4);
            delta = GameRenderer.layout.width;
            font.setColor(GameRenderer.colors[42]);
            font.draw(batch, vars[i], parent.getCenter().x + parent.getWidth() / 2 + delta + 20,
                    parent.getCenter().y - parent.getHeight() / 2 + (font.getLineHeight() * i) - 4);

        }
        batch.end();
    }
}
