package com.vik24rus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class TestForTable implements Screen {

    final gdxGame mygame;
    OrthographicCamera camera;
    Skin skin;
    Stage stage;

    public TestForTable (final gdxGame game) {
        this.mygame = game;
        skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 700);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Label nameLabel = new Label("Name:", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);

        Label nameLabel2 = new Label("1111:", skin);
        TextField nameText2 = new TextField("", skin);
        Label addressLabel2 = new Label("Address:", skin);
        TextField addressText2 = new TextField("", skin);

        Table table = new Table();
        Table table2 = new Table();

        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        //table.defaults().width(100);
        //table.right().bottom(); //ориаентация виджетов
        table.add(nameLabel);              // Row 0,  column 0. // .expandX(); расширени ячейки
        table.add(nameText);    // Row 0, column 1.   // padLeft внутри ячейки сдвиги и изменения .padLeft(100).width(1000)
        table.row();                       // Move to next row.
        //table.add().expand().fill(); // пустая ячейка
        //table.row();
        table.add(addressLabel);           // Row 1, column 0.
        table.add(addressText); // Row 1, column 1.
        table.row();
        //table.add(table2);  //можно делать вложенные таблиы
        //table.setFillParent(true);
        table.setDebug(true);

        //table2.setWidth(stage.getWidth()); //можно и несколько Table делать
        //table2.setHeight(stage.getHeight());
        //table2.left().bottom(); //ориаентация виджетов
//        table2.add(nameLabel2);              // Row 0, column 0.
//        table2.add(nameText2);    // Row 0, column 1.   // padLeft внутри ячейки сдвиги и изменения .padLeft(100).width(1000)
//        table2.row();                       // Move to next row.
//        table2.add(addressLabel2);           // Row 1, column 0.
//        table2.add(addressText2); // Row 1, column 1.
//        //table.setFillParent(true);
        table2.setDebug(true);

        stage.addActor(table);
        //stage.addActor(table2);
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
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

    }
}
