package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teameleven.javapracticelab.UsasengCrossing;

public class StartScreen implements Screen {

    final private UsasengCrossing game;
    private float elapsed = 0.0f;
    final float LOADING_TIME = 1.0f;
    SpriteBatch batch;
    Texture img;

    public StartScreen(final UsasengCrossing game) {
        this.game = game;
        batch = new SpriteBatch();

        img = new Texture("Intro2.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)(151/255.0), (float)(108/255.0), (float)(39/255.0), 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, (Gdx.graphics.getWidth()-img.getWidth())/2, (Gdx.graphics.getHeight() - img.getHeight())/2);
        batch.end();

        elapsed += delta;

        if (elapsed > LOADING_TIME) {
            game.setScreen(new StartMenuScreen(game));
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
