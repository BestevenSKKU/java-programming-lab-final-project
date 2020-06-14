package com.teameleven.javapracticelab.characters;

import java.util.Random;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private float move_cycle = 0;
    private int move_dir = 0;
    private int speed = 5;
    Random random = new Random();
    JOptionPane talk_msg = new JOptionPane();
    boolean first_talk = false;

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    float positionX;
    float positionY;
    
    float tmp_positionX = 0.0f;
    float tmp_positionY = 0.0f;
    
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

    public void back_pos() {
    	positionX = tmp_positionX;
    	positionY = tmp_positionY;
    }

    public void talk(Player player) {
    	
    }
    
    public void spriteControl() {
    	
    	tmp_positionX = positionX;
    	tmp_positionY = positionY;
    	
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
    	move_cycle += random.nextInt(4);
    }
}
