package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.shapes.Part;
import com.mygdx.game.shapes.Triangle;

/**
 * Created by Whizzpered on 01.05.2018.
 * Only for commercial and learnin use <3;
 */
public class GameRenderer {

    private OrthographicCamera cam;     // Camera for projection all objects
    private GameWorld world;            // Pointer on our Game Logic class
    private ShapeRenderer shapeRenderer;// Renderer for triangles
    private SpriteBatch batch;          // Batch for rendering sprites
    public BitmapFont font;             // Font for FPS and any text
    public float fps = 0;               // FPS
    public static int WIDTH, HEIGHT;

    public static final Color[] colors = {                      //Color's Array: contain's all colors in the game
            new Color(255 / 255f, 0, 51 / 255f, 1),             //
            new Color(51 / 255f, 204 / 255f, 102 / 255f, 1),    //
            new Color(204 / 255f, 0, 102 / 255f, 1),            // idk what the color is each of this,
            new Color(51 / 255f, 51 / 255f, 204 / 255f, 1),     // u can fill by yourself
            new Color(0, 204 / 255f, 51 / 255f, 1),             //
            new Color(204 / 255f, 255 / 255f, 0, 1),            //
            new Color(1, 153 / 255f, 0, 1)                      //
    };


    public GameRenderer(GameWorld world) {
        this.world = world;                                     // fillin' the pointer
        cam = new OrthographicCamera();
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        cam.setToOrtho(true, WIDTH, HEIGHT);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);
        font = new BitmapFont(true);
        font.setColor(Color.DARK_GRAY);
    }

    public void render() {
        Gdx.gl.glEnable(GL20.GL_BLEND);                               //Boring OpenGL begin of cycle of rendering
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Setting for possibility to render transparent shapes
        cam.update();                                                 // updating the camera
        Gdx.gl.glClearColor(204 / 255f, 1, 1, 1);                     //setting color of background in 2 strokes
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);          // Starting renderer of shapes
        for (Triangle tr : world.getTrias()) {                        // Rendering all trinaglezz
            shapeRenderer.setColor(tr.color);
            shapeRenderer.triangle(tr.vertex[0].x, tr.vertex[0].y, tr.vertex[1].x, tr.vertex[1].y, tr.vertex[2].x, tr.vertex[2].y);
        }
        shapeRenderer.end();                                           // Ending renderer of shapes
        batch.begin();                                                 // Starting renderer of font
        font.setColor(Color.BLACK);
        font.draw(batch, (int) fps + "fps", 10, 10);
        font.setColor(Color.GREEN);
        font.draw(batch, world.money + "", WIDTH/2 - 28, 10);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);                                // Ending renderer of font
    }

}
