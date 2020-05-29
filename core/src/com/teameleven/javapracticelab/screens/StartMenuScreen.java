package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.utils.Skins;

public class StartMenuScreen implements Screen {
    final UsasengCrossing game;
    private Stage stage;

    final boolean[] flg = {false};
    final String[] playerName = new String[1];
    final String[] islandName = new String[1];
    final String[] gender = new String[1];

    public StartMenuScreen(final UsasengCrossing game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int buttonWidth = Gdx.graphics.getWidth() / 3;
        int buttonHeight = Gdx.graphics.getHeight() / 6;

        System.out.println(buttonWidth + "  " + buttonHeight);

        // Button
        Button btnNewGame = new TextButton("New game", Skins.craftacular);
        btnNewGame.setSize(buttonWidth,buttonHeight);
        btnNewGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),(3 * Gdx.graphics.getHeight() / 4) - (buttonHeight / 2));
        btnNewGame.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                final Input.TextInputListener genderInputListener = new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        gender[0] = text;
                        flg[0] = true;

                    }

                    @Override
                    public void canceled() {
                        Gdx.input.getTextInput(this, "성별 (m, f)", "", "");
                    }
                };
                final Input.TextInputListener islandNameInputListener = new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        islandName[0] = text;
                        Gdx.input.getTextInput(genderInputListener, "성별 (m, f)", "", "");
                    }

                    @Override
                    public void canceled() {
                        Gdx.input.getTextInput(this, "섬 이름", "", "");
                    }
                };
                Input.TextInputListener playerNameInputListener = new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        playerName[0] = text;
                        Gdx.input.getTextInput(islandNameInputListener, "섬 이름", "", "");
                    }

                    @Override
                    public void canceled() {
                        Gdx.input.getTextInput(this, "플레이어 이름", "", "");
                    }
                };

                Gdx.input.getTextInput(playerNameInputListener, "플레이어 이름", "", "");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnNewGame);

        Button btnLoadGame = new TextButton("Load game", Skins.craftacular);
        btnLoadGame.setSize(buttonWidth,buttonHeight);
        btnLoadGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),(2 * Gdx.graphics.getHeight() / 4) - (buttonHeight / 2));
        btnLoadGame.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnLoadGame);

        Button btnExitGame = new TextButton("Exit game", Skins.craftacular);
        btnExitGame.setSize(buttonWidth,buttonHeight);
        btnExitGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),(1 * Gdx.graphics.getHeight() / 4) - (buttonHeight / 2));
        btnExitGame.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnExitGame);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)(151/255.0), (float)(108/255.0), (float)(39/255.0), 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        if (flg[0]) {
            game.setScreen(new InitGameScreen(game, playerName[0], islandName[0], gender[0]));
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
}
