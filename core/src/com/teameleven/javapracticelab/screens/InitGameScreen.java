package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.characters.Player;
import com.teameleven.javapracticelab.characters.Villager;
import com.teameleven.javapracticelab.utils.Skins;

public class InitGameScreen implements Screen {
    final UsasengCrossing game;
    private Stage stage;
    private Label lblPlayer;
    private Label lblIsland;

    Player player;
    Villager villager;
    SpriteBatch batch;

    OrthographicCamera camera1;

    public InitGameScreen(final UsasengCrossing game, String playerName, String islandName) {
        this.game = game;

        System.out.println(playerName + "  " + islandName);

        int row_height = Gdx.graphics.getWidth() / 12;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        lblPlayer = new Label(playerName, Skins.korean, "black");
        lblPlayer.setSize(Gdx.graphics.getWidth(),row_height);
        lblPlayer.setPosition(+10,650);
        lblPlayer.setAlignment(Align.left);
        stage.addActor(lblPlayer);
        lblIsland = new Label(islandName, Skins.korean, "black");
        lblIsland.setSize(Gdx.graphics.getWidth(),row_height);
        lblIsland.setPosition(+10,700);
        lblIsland.setAlignment(Align.left);
        stage.addActor(lblIsland);

        player = new Player(playerName);
        villager = new Villager(playerName);
        
        camera1 = new OrthographicCamera(1024,768);
        camera1.position.set(player.get_x(),player.get_y(),0);
        camera1.update();
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();


        batch.begin();
        villager.action(batch);
        player.action(batch);
        batch.end();
        
        camera1.position.set(player.get_x()+100,player.get_y()+100,0);
        camera1.update();
        batch.setProjectionMatrix(camera1.combined);
        
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