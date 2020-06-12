package com.teameleven.javapracticelab.BackgroundObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.teameleven.javapracticelab.texture.Textures;
import com.teameleven.javapracticelab.utils.Gender;

public class House extends Sprite {
	boolean isplayer_house;
	float positionX = 0.0f;
	float positionY = 0.0f;
	
	
	public House(boolean isplayer_house,float positionX, float positionY) {
		super(isplayer_house == true ? new Texture("house_player.png") : new Texture("house_villager.png"));
		this.isplayer_house = isplayer_house;
		this.positionX = positionX;
		this.positionY = positionY;
    }
	
	public void action(Batch batch) {
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }
	
}
