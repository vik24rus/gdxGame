package com.vik24rus;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen, InputProcessor {
    final gdxGame mygame;
    private OrthographicCamera camera;

    private Table uiTable;
    private Stage uiStage;
    private Stage gameStage;
    private InputMultiplexer multiplexer;
    private Group planets;
    private Group ships;

    private boolean camMooveRigt = false;
    private boolean camMooveLeft = false;
    private boolean camMooveUp = false;
    private boolean camMooveDown = false;

    private TextButton addShip;
    private TextButton addPlanet;
    private TextButton delShip;
    private TextButton delPlanet;


    public GameScreen(final gdxGame mygame) {
        this.mygame = mygame;

//        PARALLAX
//        stage = new Stage(new ScreenViewport());
//        camera = (OrthographicCamera) stage.getViewport().getCamera();
//
//        Array<Texture> textures = new Array<Texture>();
//        for(int i = 1; i <=6;i++){
//            textures.add(new Texture(Gdx.files.internal("parallax/img"+i+".png")));
//            textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
//        }
//
//        ParallaxBackground parallaxBackground = new ParallaxBackground(textures);
//        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//        parallaxBackground.setSpeed(1);
//        stage.addActor(parallaxBackground);
        Skin glassySkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        gameStage = new Stage(new ScreenViewport());
        uiStage = new Stage(new ScreenViewport());

        multiplexer = new InputMultiplexer(uiStage, gameStage, this);

        uiTable = new Table();
        uiTable.setWidth(uiStage.getWidth());
        uiTable.align(Align.center|Align.top);
        uiTable.setPosition(0, Gdx.graphics.getHeight());

        addShip = new TextButton("Add Ship" , glassySkin);
        delShip = new TextButton("Del Ship" , glassySkin);
        addShip.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("YEEEEEEES", "WOW");
            }
        });

        delShip.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("YEEEEEEES", "WOW");
            }
        });

        uiTable.padTop(30);
        uiTable.add(addShip).padBottom(30);
        uiTable.row();
        uiTable.add(delShip);
        uiTable.setDebug(true);

        Group planets =  new Group();
        Group ships = new Group();

        Image map = new Image(new Texture("res/map.png"));
        map.addListener(new ActorGestureListener(){
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                super.pan(event, x, y, deltaX, deltaY);
                camera.position.x -= (deltaX*Gdx.graphics.getDensity());
                camera.position.y -= (deltaY*Gdx.graphics.getDensity());

            }
        });

        uiStage.addActor(uiTable);
        gameStage.addActor(map);

        camera = (OrthographicCamera) gameStage.getViewport().getCamera();
        camera.viewportWidth = 1280;
        camera.viewportHeight = 700;
        camera.translate(0,0);
        camera.zoom = 1.0f;
        Gdx.input.setInputProcessor(multiplexer);
    }


    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
        //example
        // PARALLAX
        //Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        // PARALLAX
        //stage.act();
        //stage.draw();
        uiStage.act();
        gameStage.act();
        gameStage.draw();
        uiStage.draw();
        cameraMooving();
    }

    public void cameraMooving(){
        if (camMooveLeft == true){
            camera.translate(-5, 0 ,0);
        }
        if (camMooveRigt == true){
            camera.translate(+5, 0 ,0);
        }
        if (camMooveUp == true){
            camera.translate(0, +5 ,0);
        }
        if (camMooveDown == true){
            camera.translate(0, -5 ,0);
        }
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

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode==Input.Keys.LEFT)
            camMooveLeft = true;
        if (keycode==Input.Keys.RIGHT)
            camMooveRigt = true;
        if (keycode==Input.Keys.UP)
            camMooveUp = true;
        if (keycode==Input.Keys.DOWN)
            camMooveDown = true;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode==Input.Keys.LEFT)
            camMooveLeft = false;
        if (keycode==Input.Keys.RIGHT)
            camMooveRigt = false;
        if (keycode==Input.Keys.UP)
            camMooveUp = false;
        if (keycode==Input.Keys.DOWN)
            camMooveDown = false;
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        Gdx.app.log( "INFO" , "ZOOM" + camera.zoom);
        Gdx.app.log( "INFO" , "amount" + amount);
        if ( (camera.zoom != 1.0f && amount < 0) || (camera.zoom != 5.0f && amount > 0)) {
            camera.zoom += amount;
        }
        return true;
    }
}
