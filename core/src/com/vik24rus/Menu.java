package com.vik24rus;


import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu implements Screen {

    final gdxGame mygame;

    OrthographicCamera camera;

    private Stage stage;
    private Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private SpriteBatch batch;
    private Sprite sprite;
    private Label outputLabel;

    public Menu(final gdxGame mygame) {
        this.mygame = mygame;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        Label.LabelStyle headerStyle = new Label.LabelStyle();
        BitmapFont headerFont = new BitmapFont(Gdx.files.internal("fonts/mySegoe.fnt"));
        headerStyle.font = headerFont;

        Skin glassySkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setWidth(stage.getWidth()); //ширина стола
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        startButton = new TextButton("New Game" , glassySkin);
        quitButton = new TextButton("Quit Game" , glassySkin);
        Label headerLabel = new Label("SUPER GAME NAME",headerStyle);


        table.padTop(10);
        table.add(headerLabel).padBottom(50);
        table.row();
        table.add(startButton).padBottom(200);
        table.row();
        table.add(quitButton);
        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mygame.setScreen(new GameScreen(mygame));
                dispose();
                //Gdx.app.log("YEEEEEEES", "WOW");
            }
        });

        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
    }



    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        // rainMusic.play();
        // example

    }

    @Override
    public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
//        mygame.setScreen(new GameScreen(mygame)); //переключение на следующий экран

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        //Gdx.app.log("SYS","HIDE");
        //dipose(); // вызывать вручную т.к. dispose() не вызвался при переключении на другой Screen
    }

    @Override
    public void dispose() {
        stage.dispose();
        mygame.dispose();
    }
}
