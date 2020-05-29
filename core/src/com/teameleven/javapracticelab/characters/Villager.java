package com.teameleven.javapracticelab.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class Villager extends Sprite {
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
    
    public Villager(String name) {
        super(new Texture("villager.png"));
        this.name = name;
        
    }

    public void action(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        this.spriteControl();
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }

    public void spriteControl() {

 
    }
    
    public float get_x() {
    	return positionX;
    }
    public float get_y() {
    	return positionY;
    }
}
