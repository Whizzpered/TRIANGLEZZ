package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.shapes.Triangle;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class GameRenderer {

    private OrthographicCamera cam;
    private GameWorld world;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    public BitmapFont font;
    public float fps = 0;

    public static final Color[] colors = {
            new Color(255 / 255f, 0, 51 / 255f, 1),
            new Color(51 / 255f, 204 / 255f, 102 / 255f, 1),
            new Color(204 / 255f, 0, 102 / 255f, 1),
            new Color(51 / 255f, 51 / 255f, 204 / 255f, 1),
            new Color(0, 204 / 255f, 51 / 255f, 1),
            new Color(204 / 255f, 255 / 255f, 0, 1),
            new Color(1, 153 / 255f, 0, 1),
            new Color(102 / 255f, 153 / 255f, 153 / 255f, 1)
    };


    public GameRenderer(GameWorld world) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);
        font = new BitmapFont(true);
        font.setColor(Color.BLACK);
    }

    public void render() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        cam.update();
        Gdx.gl.glClearColor(204 / 255f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Triangle tr : world.getTrias()) {
            //shapeRenderer.setColor(new Color(tr.color.r, tr.color.g, tr.color.b, 0.3f));
            //shapeRenderer.circle(tr.center.x, tr.center.y, tr.st);
            shapeRenderer.setColor(tr.color);
            shapeRenderer.triangle(tr.a.x, tr.a.y, tr.b.x, tr.b.y, tr.c.x, tr.c.y);
        }
        shapeRenderer.end();
        batch.begin();
        font.draw(batch, (int) fps + "", 10, 10);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
