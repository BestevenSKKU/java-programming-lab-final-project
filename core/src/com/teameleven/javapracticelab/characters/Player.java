package com.teameleven.javapracticelab.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class Player extends Sprite {
    final private String name;

    private int speed = 5;

    float positionX = 0.0f;
    float positionY = 0.0f;

    Texture img;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> animation;
    
    Texture img2;
    TextureRegion[] animationFrames2;
    Animation<TextureRegion> animation2;
    
    float elapsedTime;
    
    public Player(String name) {
        super(new Texture("player_m.png"));
        this.name = name;
        
        img = new Texture("player_walk_l.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(img,192,192);

        animationFrames = new TextureRegion[4];
        int index = 0;

        for (int i = 0; i < 2; i++){
           for(int j = 0; j < 2; j++) {
              animationFrames[index++] = tmpFrames[j][i];
           }
        }

        animation = new Animation<TextureRegion>(1f/4f,animationFrames);
        
        img2 = new Texture("player_walk_r.png");

        TextureRegion[][] tmpFrames2 = TextureRegion.split(img2,192,192);

        animationFrames2 = new TextureRegion[4];
        index = 0;
        for (int i = 0; i < 2; i++){
           for(int j = 0; j < 2; j++) {
              animationFrames2[index++] = tmpFrames2[j][i];
           }
        }

        animation2 = new Animation<TextureRegion>(1f/4f,animationFrames2);
        
    }

    public void action(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        this.spriteControl();
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }

    public void spriteControl() {

        int speed = 4;
        int changed = 0;
        
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed += 2;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            speed -= 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
         	elapsedTime += Gdx.graphics.getDeltaTime();
            this.setRegion(animation.getKeyFrame(elapsedTime,true));
            positionY +=speed;
            changed = 1;
        }

        
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
         	elapsedTime += Gdx.graphics.getDeltaTime();
            this.setRegion(animation.getKeyFrame(elapsedTime,true));
            positionY -=speed;
            changed = 1;
        }
 

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
        	elapsedTime += Gdx.graphics.getDeltaTime();
            this.setRegion(animation.getKeyFrame(elapsedTime,true));
            positionX -=speed;
            changed = 1;
        }
 

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
         	elapsedTime += Gdx.graphics.getDeltaTime();
            this.setRegion(animation2.getKeyFrame(elapsedTime,true));
            positionX +=speed;
            changed = 1;
        }
        
        if (changed == 0) {this.setRegion(animationFrames[0]);}
    }
    
    public float get_x() {
    	return positionX;
    }
    public float get_y() {
    	return positionY;
    }
}
