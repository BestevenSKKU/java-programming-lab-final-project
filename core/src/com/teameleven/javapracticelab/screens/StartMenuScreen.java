package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.utils.Skins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;


public class StartMenuScreen implements Screen {
    final UsasengCrossing game;
    private Stage stage;

    final boolean[] flg = {false};
    final boolean[] load_flg = {false};
    final String[] playerName = new String[1];
    final String[] islandName = new String[1];
    final String[] gender = new String[1];
    final String[] hostname = new String[1];
    JOptionPane err_msg = new JOptionPane();

    public StartMenuScreen(final UsasengCrossing game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int buttonWidth = Gdx.graphics.getWidth() / 3;
        int buttonHeight = Gdx.graphics.getHeight() / 8;

        System.out.println(buttonWidth + "  " + buttonHeight);

        // Button
        Button btnSingleGame = new TextButton("Single Play", Skins.craftacular);
        btnSingleGame.setSize(buttonWidth,buttonHeight);
        btnSingleGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),500);
        btnSingleGame.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                hostname[0] = null;
                gamePlay();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnSingleGame);
        
        Button btnMultiGame = new TextButton("Multi Play", Skins.craftacular);
        btnMultiGame.setSize(buttonWidth,buttonHeight);
        btnMultiGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),380);
        btnMultiGame.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                final Input.TextInputListener hostnameInputListener = new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        hostname[0] = text;
                        gamePlay();
                    }

                    @Override
                    public void canceled() {
                        Gdx.input.getTextInput(this, "성별 (m, f)", "", "");
                    }
                };
                Gdx.input.getTextInput(hostnameInputListener, "서버 주소", "", "");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnMultiGame);

        //로드 게임
        Button loadGame = new TextButton("Load game", Skins.craftacular);
        loadGame.setSize(buttonWidth,buttonHeight);
        loadGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),260);
        loadGame.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	
                try {
                	File file = new File("save.txt");
                	FileReader file_reader = new FileReader(file);
                	BufferedReader bufReader = new BufferedReader(file_reader);
                	
                	load_flg[0] = true;
                	
                    playerName[0] = bufReader.readLine();
                    islandName[0] = bufReader.readLine();
                    gender[0] = bufReader.readLine();
                     
                }
                catch (Exception e) {
                	err_msg.showMessageDialog(null, "저장된 파일이 없습니다");
                	return;
                }
                
                

                
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(loadGame);
        
        
        Button btnExitGame = new TextButton("Exit game", Skins.craftacular);
        btnExitGame.setSize(buttonWidth,buttonHeight);
        btnExitGame.setPosition((Gdx.graphics.getWidth() /2 ) - (buttonWidth / 2),140);
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
            game.setScreen(new InitGameScreen(game, playerName[0], islandName[0], gender[0], hostname[0]));
        }
        if (load_flg[0]) {
            game.setScreen(new InitGameScreen(game, playerName[0], islandName[0], gender[0], hostname[0]));
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

    public void gamePlay() {
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
}

