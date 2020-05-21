package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.CrossingUsaseng;

public class InitGameScreen implements Screen {
    final CrossingUsaseng game;
    private Stage stage;
    private Label lblPlayer;
    private Label lblIsland;

    public InitGameScreen(final CrossingUsaseng game, String playerName, String islandName) {
        this.game = game;

        System.out.println(playerName + "  " + islandName);

        int row_height = Gdx.graphics.getWidth() / 12;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin glassy = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        lblPlayer = new Label(playerName, glassy, "black");
        lblPlayer.setSize(Gdx.graphics.getWidth(),row_height);
        lblPlayer.setPosition(0,row_height+250);
        lblPlayer.setAlignment(Align.center);
        stage.addActor(lblPlayer);
        lblIsland = new Label(islandName, glassy, "black");
        lblIsland.setSize(Gdx.graphics.getWidth(),row_height);
        lblIsland.setPosition(0,row_height+250-50);
        lblIsland.setAlignment(Align.center);
        stage.addActor(lblIsland);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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