package com.teameleven.javapracticelab.screens;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.characters.Player;
import com.teameleven.javapracticelab.items.*;
import com.teameleven.javapracticelab.utils.Skins;

public class InventoryScreen implements Screen {
	
    final UsasengCrossing game;
    final Player player;
    private Stage stage;
    
    InitGameScreen initGameScreen;
    SpriteBatch batch;
    
    private Texture texture;
    private Label title;
    int row_height = Gdx.graphics.getWidth() / 10;
    ArrayList<Label> itemList = new ArrayList<Label>();
    ArrayList<Button> buttonList = new ArrayList<Button>();

    
    public InventoryScreen(final UsasengCrossing game, final InitGameScreen initGameScreen, final Player player) {
    	
        this.game = game;
        this.initGameScreen = initGameScreen;
        this.player = player;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("inventory_img.png"));

        title = new Label("아이템 목록   (나가기 Esc)", Skins.korean, "black");
        title.setSize(Gdx.graphics.getWidth(),row_height);
        title.setPosition(0,600);
        title.setAlignment(Align.center);
        stage.addActor(title);

        int buttonWidth = Gdx.graphics.getWidth() / 5;
        int buttonHeight = Gdx.graphics.getHeight() / 12;

        Button back = new TextButton("Back", Skins.craftacular);
        back.setSize(buttonWidth,buttonHeight);
        back.setPosition(600,0);

        back.addListener(new InputListener() {

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //back to initscreen
                game.setScreen(initGameScreen);
            }
    ;
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


        });
        //stage.addActor(back);

        setList();
    }
    
    @Override
    public void show() {
    		
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)(151/255.0), (float)(108/255.0), (float)(39/255.0), 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(texture, 160, 30);
        batch.end();

        stage.act();
        stage.draw();


        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(this.initGameScreen);
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
    
    public void setList() {
        HashMap<String, Integer> map = this.player.getInventory().getItemList();

        int i = 0;
        int j = 0;
        int xPos = 250;
        
        for(final Map.Entry<String, Integer> elem : map.entrySet())  {
            itemList.add(new Label(elem.getKey() + " : " + elem.getValue(),Skins.korean, "black"));
            itemList.get(i).setAlignment(Align.left);
            itemList.get(i).setPosition(xPos,550-j*50);
            stage.addActor(itemList.get(i));
            
            if(j>=9) {
            	xPos = 550;
            	j = -1;
            }
            i++;
            j++;
        }

    }
}
