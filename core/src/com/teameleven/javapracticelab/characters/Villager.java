package com.teameleven.javapracticelab.characters;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.teameleven.javapracticelab.utils.Gender;


public class Villager extends Sprite {
    final private String name;
    final private Gender gender;
    private float move_cycle = 0;
    private int move_dir = 0;
    private int speed = 5;
    Random random = new Random();
    
    float positionX = 0.0f;
    float positionY = 0.0f;

    Texture img;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> animation;
    
    Texture img2;
    TextureRegion[] animationFrames2;
    Animation<TextureRegion> animation2;
    
    float elapsedTime;
    
    public Villager(String name, Gender gender) {
        super(new Texture("villager_" + name + ".png"));
        this.name = name;
        this.gender = gender;
    }

    public void action(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        this.spriteControl();
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }

    public void spriteControl() {
    	if (move_cycle > 100) {
    		move_dir = 4;
    	}
    	if (move_cycle > 200) {
    		move_cycle = 0;
    		move_dir = random.nextInt(4);
    	}
    	
    	
    	if(move_dir == 0) {
    		positionX = positionX - 3;
    	}
    	if(move_dir == 1) {
    		positionX = positionX + 3;
    	}
    	if(move_dir == 2) {
    		positionY = positionY - 3;
    	}
    	if(move_dir == 3) {
    		positionY = positionY + 3;
    	}
    	if(move_dir == 4) {

    	}
    	move_cycle++;
    }
    
    public float get_x() {
    	return positionX;
    }
    public float get_y() {
    	return positionY;
    }
}
