package com.teameleven.javapracticelab.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Player extends Sprite {
    public enum Direction {
        LEFT, RIGHT
    }
    final private String name;

    private int speed = 5;

    float positionX = 0.0f;
    float positionY = 0.0f;

    Texture[] img = new Texture[2];
    TextureRegion[][] animationFrame = new TextureRegion[2][];
    Animation<TextureRegion>[] animation = new Animation[2];

    Direction previousDirection = Direction.LEFT;
    
    float elapsedTime;
    
    public Player(String name) {
        super(new Texture("player_m.png"));
        this.name = name;
        
        img[Direction.LEFT.ordinal()] = new Texture("player_walk_l.png");
        img[Direction.RIGHT.ordinal()] = new Texture("player_walk_r.png");
        TextureRegion[][][] tmpFrames = new TextureRegion[2][][];
        tmpFrames[Direction.LEFT.ordinal()] = TextureRegion.split(
                img[Direction.LEFT.ordinal()],
                img[Direction.LEFT.ordinal()].getWidth() /2 ,
                img[Direction.LEFT.ordinal()].getHeight() / 2
        );
        tmpFrames[Direction.RIGHT.ordinal()] = TextureRegion.split(
                img[Direction.RIGHT.ordinal()],
                img[Direction.RIGHT.ordinal()].getWidth() /2 ,
                img[Direction.RIGHT.ordinal()].getHeight() / 2
        );

        animationFrame[Direction.LEFT.ordinal()] = new TextureRegion[4];
        animationFrame[Direction.RIGHT.ordinal()] = new TextureRegion[4];

        int index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++) {
                animationFrame[Direction.LEFT.ordinal()][index] = tmpFrames[Direction.LEFT.ordinal()][j][i];
                animationFrame[Direction.RIGHT.ordinal()][index++] = tmpFrames[Direction.RIGHT.ordinal()][j][i];
           }
        }

        animation[Direction.LEFT.ordinal()] = new Animation<TextureRegion>(1f/4f,animationFrame[Direction.LEFT.ordinal()]);
        animation[Direction.RIGHT.ordinal()] = new Animation<TextureRegion>(1f/4f,animationFrame[Direction.RIGHT.ordinal()]);
    }

    public void action(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        this.spriteControl();
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }

    public void spriteControl() {

        int speed = 4;
        boolean directionChanged = false;
        
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed += 2;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            speed -= 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            this.setRegion(animation[previousDirection.ordinal()].getKeyFrame(elapsedTime,true));
            positionY +=speed;
            directionChanged = true;
        }

        
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
         	elapsedTime += Gdx.graphics.getDeltaTime();
            this.setRegion(animation[previousDirection.ordinal()].getKeyFrame(elapsedTime,true));
            positionY -=speed;
            directionChanged = true;
        }
 

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
        	elapsedTime += Gdx.graphics.getDeltaTime();
        	previousDirection = Direction.LEFT;
            this.setRegion(animation[previousDirection.ordinal()].getKeyFrame(elapsedTime,true));
            positionX -=speed;
            directionChanged = true;
        }
 

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
         	elapsedTime += Gdx.graphics.getDeltaTime();
         	previousDirection = Direction.RIGHT;
            this.setRegion(animation[previousDirection.ordinal()].getKeyFrame(elapsedTime,true));
            positionX +=speed;
            directionChanged = true;
        }
        
        if (directionChanged == false) {
            this.setRegion(animationFrame[previousDirection.ordinal()][0]);
        }
    }
    
    public float get_x() {
    	return positionX;
    }
    public float get_y() {
    	return positionY;
    }
}
