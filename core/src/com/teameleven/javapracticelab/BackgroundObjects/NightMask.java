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

public class NightMask extends Sprite {
	float positionX = -2000;
	float positionY = -2000;

	public NightMask() {
		super(new Texture("night_mask.png"));
    }
	
	public void action(Batch batch) {
        this.setPosition(positionX, positionY);
        this.draw(batch);
    }
	
}
