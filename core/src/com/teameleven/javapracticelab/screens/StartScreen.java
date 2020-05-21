package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teameleven.javapracticelab.CrossingUsaseng;

public class StartScreen implements Screen {

    final private CrossingUsaseng game;
    private float elapsed = 0.0f;
    SpriteBatch batch;
    Texture img;

    public StartScreen(final CrossingUsaseng game) {
        this.game = game;
        batch = new SpriteBatch();

        img = new Texture("Intro.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)(151/255.0), (float)(108/255.0), (float)(39/255.0), 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, (1024-639)/2, (768 - 246)/2);
        batch.end();

        elapsed += delta;

        if (elapsed > 4) {
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
