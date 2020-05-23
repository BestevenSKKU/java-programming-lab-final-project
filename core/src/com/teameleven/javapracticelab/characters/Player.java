package com.teameleven.javapracticelab.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
    final private String name;

    private int speed = 5;

    float positionX = 0.0f;
    float positionY = 0.0f;

    public Player(String name) {
        super(new Texture("player_m.png"));
        this.name = name;
    }

    public void action(Batch batch) {
        this.spriteControl();
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }

    public void spriteControl() {
        int speed = 4;

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed += 2;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            speed -= 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            positionY +=speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            positionY -=speed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            positionX -=speed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            positionX +=speed;
        }
    }
}
