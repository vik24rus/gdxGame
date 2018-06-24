package com.vik24rus;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen, InputProcessor {
    final gdxGame mygame;
    private OrthographicCamera camera;

    private Table uiTable;
    private Skin glassySkin;
    private Stage uiStage;
    private Stage gameStage;
    private InputMultiplexer multiplexer;
    private Group planets;
    private Group ships;

    private boolean camMoveRight , camMoveLeft , camMoveUp,camMoveDown  = false;


    private TextButton addShip;
    private TextButton addPlanet;
    private TextButton delShip;
    private TextButton delPlanet;
    private Label FPSLabel;
    private String FPSString;
    private Label ZoomLabel;


    public GameScreen(final gdxGame game) {
        this.mygame = game;
        Gdx.app.log("INFO" , "GAME START");
        glassySkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        gameStage = new Stage(new ScreenViewport());
        uiStage = new Stage(new ScreenViewport());

        multiplexer = new InputMultiplexer(uiStage, gameStage, this);

        uiTable = new Table();
        //uiTable.setFillParent(true); //???
        uiTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //uiTable.setWidth(uiStage.getWidth());
        //uiTable.setHeight(uiStage.getHeight());

        addShip = new TextButton("Add Ship" , glassySkin , "small");
        delShip = new TextButton("Del Ship" , glassySkin , "small");
        addPlanet = new TextButton("Add Planet" , glassySkin , "small");
        delPlanet = new TextButton("Del Planet" , glassySkin , "small");

        ZoomLabel = new Label( "", glassySkin , "black");
        FPSLabel = new Label( "", glassySkin , "black");



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

        addPlanet.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("YEEEEEEES", "WOW");
            }
        });

        delPlanet.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("YEEEEEEES", "WOW");
            }
        });

        uiTable.add().expand().fill().colspan(2).left().right();
        //uiTable.add(FPSLabel).left().prefWidth(150);
        uiTable.row();
        uiTable.add(addShip).left().prefWidth(150);
        uiTable.add(addPlanet).left().prefWidth(150);
        uiTable.row();
        uiTable.add(delShip).left().prefWidth(150);
        uiTable.add(delPlanet).left().prefWidth(150);
        uiTable.row();
        uiTable.add(FPSLabel).left().prefWidth(150);
        uiTable.add(ZoomLabel).left().prefWidth(150);
        //uiTable.setDebug(true);

        Group planets =  new Group(); //TODO Groups Actors
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
        gameStage.addActor(map); //TODO map on gameSage is correct?

        camera = (OrthographicCamera) gameStage.getViewport().getCamera();
        camera.viewportWidth = 1280;
        camera.viewportHeight = 700;
        camera.translate(0,0);
        camera.zoom = 1.0f;
        Gdx.input.setInputProcessor(multiplexer);
    }


    @Override
    public void show() {
        //GameMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        uiStage.act(Gdx.graphics.getDeltaTime());

        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();
        uiStage.draw();

        cameraMooving();

        FPSLabel.setText("FPS: " + Float.toString (Gdx.graphics.getFramesPerSecond()));
        ZoomLabel.setText("Zoom: " + Float.toString(camera.zoom));
    }

    public void cameraMooving(){
        if (camMoveLeft == true){
            camera.translate(-5, 0 ,0);
        }
        if (camMoveRight == true){
            camera.translate(+5, 0 ,0);
        }
        if (camMoveUp == true){
            camera.translate(0, +5 ,0);
        }
        if (camMoveDown == true){
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
        glassySkin.dispose();
        uiStage.dispose();
        gameStage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode==Input.Keys.LEFT)
            camMoveLeft = true;
        if (keycode==Input.Keys.RIGHT)
            camMoveRight = true;
        if (keycode==Input.Keys.UP)
            camMoveUp = true;
        if (keycode==Input.Keys.DOWN)
            camMoveDown = true;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode==Input.Keys.LEFT)
            camMoveLeft = false;
        if (keycode==Input.Keys.RIGHT)
            camMoveRight = false;
        if (keycode==Input.Keys.UP)
            camMoveUp = false;
        if (keycode==Input.Keys.DOWN)
            camMoveDown = false;
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
        if ( (camera.zoom != 1.0f && amount < 0) || (camera.zoom != 5.0f && amount > 0)) {
            camera.zoom += amount;
        }
        return true;
    }
}
