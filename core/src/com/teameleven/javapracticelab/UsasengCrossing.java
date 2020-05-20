package com.teameleven.javapracticelab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teameleven.javapracticelab.screens.StartMenuScreen;
import com.teameleven.javapracticelab.screens.StartScreen;


public class UsasengCrossing extends Game {
	SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new StartMenuScreen(this));
	}

	public void gotoStartScreen(){
		StartScreen startScreen = new StartScreen(this);
		setScreen(startScreen);
	}


	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}