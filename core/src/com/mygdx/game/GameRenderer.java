package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gui.Button;
import com.mygdx.game.shapes.Triangle;

/**
 * Created by Taster on 01.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public class GameRenderer {

    public static final String FONT_NAME = "astron boy.ttf";
    private OrthographicCamera cam;     // Camera for projection all objects
    private GameWorld world;            // Pointer on our Game Logic class
    private ShapeRenderer shapeRenderer;// Renderer for triangles
    private SpriteBatch batch;          // Batch for rendering sprites
    public BitmapFont font;             // Font for any text
    public static int WIDTH, HEIGHT;
    public static GlyphLayout layout = new GlyphLayout();

    private BitmapFont generateFont(String fontName, String characters) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = characters;
        parameter.size = 20;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.DARK_GRAY;
        parameter.flip = true;

        // Generate font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontName));
        BitmapFont font = generator.generateFont(parameter);
        // Dispose resources
        generator.dispose();
        return font;
    }

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
            new Color(255 / 255f, 204 / 255f, 0, 1),            //8 оранжевый2
            new Color(255 / 255f, 153 / 255f, 0, 1),            //9 оранжевый3
            new Color(255 / 255f, 102 / 255f, 0, 1),            //10 оранжевый4
            new Color(255 / 255f, 51 / 255f, 0, 1),             //11 оранжевый5
            new Color(255 / 255f, 0, 0, 1),                     //12 красный1
            new Color(255 / 255f, 0, 51 / 255f, 1),             //13 красный2
            new Color(255 / 255f, 102 / 255f, 102 / 255f, 1),   //14 красный3
            new Color(255 / 255f, 153 / 255f, 153 / 255f, 1),   //15 красный4
            new Color(255 / 255f, 204 / 255f, 204 / 255f, 1),   //16 красный5
            new Color(204 / 255f, 153 / 255f, 153 / 255f, 1),   //17 розовый1
            new Color(204 / 255f, 102 / 255f, 102 / 255f, 1),   //18 розовый2
            new Color(153 / 255f, 0, 51 / 255f, 1),             //19 розовый3
            new Color(204 / 255f, 0, 102 / 255f, 1),            //20 розовый4
            new Color(255 / 255f, 0, 204 / 255f, 1),            //21 розовый5
            new Color(204 / 255f, 0, 153 / 255f, 1),            //22 фиолетовый1
            new Color(204 / 255f, 0, 204 / 255f, 1),            //23 фиолетовый2
            new Color(153 / 255f, 51 / 255f, 204 / 255f, 1),    //24 фиолетовый3
            new Color(153 / 255f, 0, 153 / 255f, 1),            //25 фиолетовый4
            new Color(102 / 255f, 0, 102 / 255f, 1),            //26 фиолетовый5
            new Color(102 / 255f, 0, 153 / 255f, 1),            //27 синий1
            new Color(102 / 255f, 0, 204 / 255f, 1),            //28 синий2
            new Color(51 / 255f, 0, 153 / 255f, 1),             //29 синий3
            new Color(0, 0, 204 / 255f, 1),                     //30 синий4
            new Color(0, 51 / 255f, 255 / 255f, 1),             //31 синий5
            new Color(0, 102 / 255f, 255 / 255f, 1),            //32 голубой1
            new Color(0, 153 / 255f, 255 / 255f, 1),            //33 голубой2
            new Color(153 / 255f, 204 / 255f, 255 / 255f, 1),   //34 голубой3
            new Color(102 / 255f, 204 / 255f, 204 / 255f, 1),   //35 голубой4
            new Color(51 / 255f, 204 / 255f, 204 / 255f, 1),    //36 голубой5
            new Color(0, 153 / 255f, 153 / 255f, 1),            //37 бирюза1
            new Color(0, 153 / 255f, 102 / 255f, 1),            //38 бирюза2
            new Color(0, 204 / 255f, 153 / 255f, 1),            //39 бирюза3
            new Color(0, 255 / 255f, 204 / 255f, 1),            //40 бирюза4
            new Color(102 / 255f, 255 / 255f, 153 / 255f, 1),   //41 бирюза5
            new Color(102 / 255f, 204 / 255f, 102 / 255f, 1),   //42 зеленый1
            new Color(0, 204 / 255f, 0, 1),                     //43 зеленый2
            new Color(51 / 255f, 255 / 255f, 0, 1),             //44 зеленый3
            new Color(153 / 255f, 255 / 255f, 0, 1),            //45 зеленый4
            new Color(204 / 255f, 255 / 255f, 0, 1),            //46 зеленый5
            new Color(204 / 255f, 255 / 255f, 102 / 255f, 1),   //47 1
            new Color(153 / 255f, 153 / 255f, 102 / 255f, 1),   //48 2
            new Color(102 / 255f, 102 / 255f, 102 / 255f, 1),   //49 3
            new Color(153 / 255f, 153 / 255f, 153 / 255f, 1),   //50 4
            new Color(204 / 255f, 204 / 255f, 204 / 255f, 1)    //51 5
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
        //font.setColor(Color.DARK_GRAY);
        font = generateFont(FONT_NAME, FreeTypeFontGenerator.DEFAULT_CHARS);
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
