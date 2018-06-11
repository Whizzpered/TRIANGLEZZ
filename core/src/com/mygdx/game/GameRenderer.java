package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gui.Button;
import com.mygdx.game.shapes.Triangle;

/**
 * Created by Taster on 01.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameRenderer {

    private OrthographicCamera cam;     // Camera for projection all objects
    private GameWorld world;            // Pointer on our Game Logic class
    private ShapeRenderer shapeRenderer;// Renderer for triangles
    private SpriteBatch batch;          // Batch for rendering sprites
    public BitmapFont font;             // Font for FPS and any text
    public float fps = 0;               // FPS
    public static int WIDTH, HEIGHT;
    GlyphLayout layout = new GlyphLayout();

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public BitmapFont getFont() {
        return font;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public static final Color[] colors = {                      //Color's Array: contain's all colors in the game
            new Color(255 / 255f, 0, 51 / 255f, 1),             //die = красно-розовый (255.0.51)
            new Color(255 / 255f, 255 / 255f, 204 / 255f, 1),   //1 hp = зеленый бледный (51.204.102) => желтый1
            new Color(255 / 255f, 255 / 255f, 153 / 255f, 1),   //2 hp = розово-фиолетовый (204.0.102) => желтый2
            new Color(255 / 255f, 255 / 255f, 102 / 255f, 1),   //3 hp = сине-голубой (51.51.204) => желтый3
            new Color(255 / 255f, 255 / 255f, 51 / 255f, 1),    //4 hp = зеленый яркий (0.204.51) => желтый4
            new Color(255 / 255f, 255 / 255f, 0, 1),            //6 hp = желтый лайм (204.255.0) => желтый5
            new Color(255 / 255f, 204 / 255f, 51 / 255f, 1),    //7 hp = оранжевый (255.153.0) => оранжевый1
            new Color(255 / 255f, 204 / 255f, 0, 1),            //оранжевый2
            new Color(255 / 255f, 153 / 255f, 0, 1),            //оранжевый3
            new Color(255 / 255f, 102 / 255f, 0, 1),            //оранжевый4
            new Color(255 / 255f, 51 / 255f, 0, 1),             //оранжевый5
            new Color(255 / 255f, 0, 0, 1),                     //красный1
            new Color(255 / 255f, 0, 51 / 255f, 1),             //красный2
            new Color(255 / 255f, 102 / 255f, 102 / 255f, 1),   //красный3
            new Color(255 / 255f, 153 / 255f, 153 / 255f, 1),   //красный4
            new Color(255 / 255f, 204 / 255f, 204 / 255f, 1),   //красный5
            new Color(204 / 255f, 153 / 255f, 153 / 255f, 1),   //розовый1
            new Color(204 / 255f, 102 / 255f, 102 / 255f, 1),   //розовый2
            new Color(153 / 255f, 0, 51 / 255f, 1),             //розовый3
            new Color(204 / 255f, 0, 102 / 255f, 1),            //розовый4
            new Color(255 / 255f, 0, 204 / 255f, 1),            //розовый5
            new Color(204 / 255f, 0, 153 / 255f, 1),            //фиолетовый1
            new Color(204 / 255f, 0, 204 / 255f, 1),            //фиолетовый2
            new Color(153 / 255f, 51 / 255f, 204 / 255f, 1),    //фиолетовый3
            new Color(153 / 255f, 0, 153 / 255f, 1),            //фиолетовый4
            new Color(102 / 255f, 0, 102 / 255f, 1),            //фиолетовый5
            new Color(102 / 255f, 0, 153 / 255f, 1),            //синий1
            new Color(102 / 255f, 0, 204 / 255f, 1),            //синий2
            new Color(51 / 255f, 0, 153 / 255f, 1),             //синий3
            new Color(0, 0, 204 / 255f, 1),                     //синий4
            new Color(0, 51 / 255f, 255 / 255f, 1),             //синий5
            new Color(0, 102 / 255f, 255 / 255f, 1),            //голубой1
            new Color(0, 153 / 255f, 255 / 255f, 1),            //голубой2
            new Color(153 / 255f, 204 / 255f, 255 / 255f, 1),   //голубой3
            new Color(102 / 255f, 204 / 255f, 204 / 255f, 1),   //голубой4
            new Color(51 / 255f, 204 / 255f, 204 / 255f, 1),    //голубой5
            new Color(0, 153 / 255f, 153 / 255f, 1),            //бирюза1
            new Color(0, 153 / 255f, 102 / 255f, 1),            //бирюза2
            new Color(0, 204 / 255f, 153 / 255f, 1),            //бирюза3
            new Color(0, 255 / 255f, 204 / 255f, 1),            //бирюза4
            new Color(102 / 255f, 255 / 255f, 153 / 255f, 1),   //бирюза5
            new Color(102 / 255f, 204 / 255f, 102 / 255f, 1),   //зеленый1
            new Color(0, 204 / 255f, 0, 1),                     //зеленый2
            new Color(51 / 255f, 255 / 255f, 0, 1),             //зеленый3
            new Color(153 / 255f, 255 / 255f, 0, 1),            //зеленый4
            new Color(204 / 255f, 255 / 255f, 0, 1),            //зеленый5
            new Color(204 / 255f, 255 / 255f, 102 / 255f, 1),   //1
            new Color(153 / 255f, 153 / 255f, 102 / 255f, 1),   //2
            new Color(102 / 255f, 102 / 255f, 102 / 255f, 1),   //3
            new Color(153 / 255f, 153 / 255f, 153 / 255f, 1),   //4
            new Color(204 / 255f, 204 / 255f, 204 / 255f, 1)    //5
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
        Gdx.gl.glClearColor(29 / 255f, 30 / 255f, 51 / 255f, 1);      //setting color of background in 2 strokes
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);          // Starting renderer of shapes
        for (Triangle tr : world.getTrias()) {                        // Rendering all trinaglezz
            shapeRenderer.setColor(tr.color);
            shapeRenderer.triangle(tr.vertex[0].x, tr.vertex[0].y, tr.vertex[1].x, tr.vertex[1].y, tr.vertex[2].x, tr.vertex[2].y);
        }
        shapeRenderer.end();
        world.barrier.render(shapeRenderer, font, batch);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Button b : world.buttons) {                              // Rendering all buttons
            shapeRenderer.setColor(b.getColor());
            shapeRenderer.rect(b.getCenter().x - b.getWidth() / 2, b.getCenter().y - b.getHeight() / 2, b.getWidth(), b.getHeight());
        }
        shapeRenderer.end();                                           // Ending renderer of shapes and gui
        batch.begin();                                                 // Starting renderer of font
        font.setColor(Color.GREEN);                                    // money
        layout.setText(font, world.money + "");
        font.draw(batch, world.money + "", WIDTH / 2 - layout.width / 2, 10);
        font.setColor(Color.WHITE);                                    // level
        font.draw(batch, world.enemy.lvl + "", WIDTH - 28, 10);
        font.setColor(Color.WHITE);

        for (Button b : world.buttons) {                                // Button's description
            layout.setText(font, b.getName());
            font.draw(batch, b.getName(), b.getCenter().x - (layout.width / 2),
                    b.getCenter().y - font.getLineHeight() / 2);
        }
        batch.end();
        if (GameScreen.shop.isShowing())
            GameScreen.shop.render(getShapeRenderer(), getFont(), getBatch());
        Gdx.gl.glDisable(GL20.GL_BLEND);                                // Ending renderer of font
    }

    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }

}
