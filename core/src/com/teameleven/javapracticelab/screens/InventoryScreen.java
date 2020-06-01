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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.utils.Skins;

public class InventoryScreen implements Screen {
	
    final UsasengCrossing game;
    private Stage stage;

    public InventoryScreen(final UsasengCrossing game, final InitGameScreen initscreen) {
    	
    this.game = game;

    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
    int buttonWidth = Gdx.graphics.getWidth() / 5;
    int buttonHeight = Gdx.graphics.getHeight() / 12;
    
    Button back = new TextButton("Back", Skins.craftacular);
    back.setSize(buttonWidth,buttonHeight);
    back.setPosition(600,0);
    
    back.addListener(new InputListener() {

		@Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			//back to initscreen
        	game.setScreen(initscreen);
        }
        
        @Override
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return true;
        }

           
    });
    stage.addActor(back);
    
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
