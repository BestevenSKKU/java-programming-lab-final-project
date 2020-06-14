package com.teameleven.javapracticelab.characters;

import java.util.Random;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.teameleven.javapracticelab.utils.Gender;


public class Villager extends Sprite {
    public String getName() {
        return name;
    }

    String name;
    Gender gender;
    private float moveCycle = 0;
    private int moveDir = 0;
    private int speed = 5;
    Random random = new Random();
    JOptionPane talkMsg = new JOptionPane();
    boolean firstTalk = false;

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    float positionX;
    float positionY;
    
    float tmpPositionX = 0.0f;
    float tmpPositionY = 0.0f;
    
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

    public void backPos() {
    	positionX = tmpPositionX;
    	positionY = tmpPositionY;
    }

    public void talk(Player player) {
    	
    }
    
    public void spriteControl() {
    	
    	tmpPositionX = positionX;
    	tmpPositionY = positionY;
    	
    	if (moveCycle > 100) {
    		moveDir = 4;
    	}
    	if (moveCycle > 200) {
    		moveCycle = 0;
    		moveDir = random.nextInt(4);
    	}
    	
    	
    	if(moveDir == 0) {
    		positionX = positionX - 3;
    	}
    	if(moveDir == 1) {
    		positionX = positionX + 3;
    	}
    	if(moveDir == 2) {
    		positionY = positionY - 3;
    	}
    	if(moveDir == 3) {
    		positionY = positionY + 3;
    	}
    	if(moveDir == 4) {

    	}
    	moveCycle += random.nextInt(4);
    }
}
