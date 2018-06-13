package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Whizzpered on 12.06.2018.
 * Only for uncommercial and learnin use <3;
 */


public class AssetLoader {

    public static Texture texture;


    public static Sprite getSprite(){
        return new Sprite(texture);
    }
    public void load() {
        texture = new Texture(Gdx.files.internal("Barrier.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        texture.dispose();
    }
}

