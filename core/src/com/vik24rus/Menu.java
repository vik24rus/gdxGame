package com.vik24rus;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu implements Screen {

    final gdxGame mygame;

    OrthographicCamera camera;

    private Stage stage;
    private Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Skin glassySkin;

    public Menu(final gdxGame game) {
        this.mygame = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 700);
//userFonts
//        Label.LabelStyle headerStyle = new Label.LabelStyle();
//        BitmapFont headerFont = new BitmapFont(Gdx.files.internal("fonts/mySegoe.fnt"));
//        headerStyle.font = headerFont;
        glassySkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        stage = new Stage(new ScreenViewport());

        startButton = new TextButton("New Game" , glassySkin);
        quitButton = new TextButton("Quit" , glassySkin);
        Label headerLabel = new Label("SUPER GAME NAME" , glassySkin , "big");

        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        table.add(headerLabel).padBottom(100);
        table.row();
        table.add(startButton).padBottom(100);
        table.row();
        table.add(quitButton);

        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mygame.setScreen(new GameScreen(mygame));
                dispose();
            }
        });

        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });

        batch = new SpriteBatch();
        backgroundSprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        backgroundSprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
    }



    @Override
    public void show() {
        // MenuMusic.play();
    }

    @Override
    public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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
    }

    @Override
    public void dispose() {
        stage.dispose();
        glassySkin.dispose();

    }
}
