package com.teameleven.javapracticelab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.teameleven.javapracticelab.screens.StartMenuScreen;
import com.teameleven.javapracticelab.screens.StartScreen;


public class UsasengCrossing extends Game {
    Sprite sprite;
	SpriteBatch batch;

    float spriteXposition;
    float spriteYposition;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new StartScreen(this));
	    sprite = new Sprite(new Texture("us.png"));
	    batch = new SpriteBatch();
	}

	public void gotoStartScreen(){
		StartScreen startScreen = new StartScreen(this);
		setScreen(startScreen);
	}


	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.setPosition(spriteXposition, spriteYposition);
        sprite.draw(batch);
        batch.end();
        spriteControl();

	}
	
	 public void spriteControl() {

	        if(Gdx.input.isKeyPressed(Keys.UP)) {
	            spriteYposition+=4;
	        }
	        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
	            spriteYposition-=4;
	        }

	        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	            spriteXposition-=4;
	        }

	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	            spriteXposition+=4;
	        }
	    }

	
	@Override
	public void dispose () {
		super.dispose();
	}
}