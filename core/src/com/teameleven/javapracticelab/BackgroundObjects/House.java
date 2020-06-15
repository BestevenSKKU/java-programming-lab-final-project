package com.teameleven.javapracticelab.BackgroundObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class House extends Sprite {
	boolean isPlayerHouse;
	float positionX = 0.0f;
	float positionY = 0.0f;
	float Height = 275;
	
	public House(boolean isPlayerHouse, float positionX, float positionY) {
		super(isPlayerHouse == true ? new Texture("house_player.png") : new Texture("house_villager.png"));
		this.isPlayerHouse = isPlayerHouse;
		this.positionX = positionX;
		this.positionY = positionY;
    }
	
	public void action(Batch batch) {
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }
	
	public float getHeight() {
		return Height;
		
	}
	
}
