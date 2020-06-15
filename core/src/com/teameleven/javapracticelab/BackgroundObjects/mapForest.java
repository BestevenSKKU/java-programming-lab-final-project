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

public class mapForest extends Sprite {
	float positionX = -1200;
	float positionY = -800;
	float Width = 2549;
	float Height = 2477;
	
	public mapForest() {
		super(new Texture("forest_map.png"));
    }
	
	@Override
	public float getX() {
		return positionX+100;
		
	}
	
	@Override
	public float getY() {
		return positionY+100;
		
	}
	
	@Override
	public float getWidth() {
		return Width-200;
		
	}
	
	@Override
	public float getHeight() {
		return Height-130;
		
	}
	
	public void action(Batch batch) {
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }
	
}
