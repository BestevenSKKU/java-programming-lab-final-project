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
import com.teameleven.javapracticelab.items.Apple;
import com.teameleven.javapracticelab.items.FishingRod;
import com.teameleven.javapracticelab.items.Tuna;
import com.teameleven.javapracticelab.utils.Gender;


public class Jackson extends Villager {
    final private String name;
    final private Gender gender;
    private float move_cycle = 0;
    private int move_dir = 0;
    private int speed = 5;
    Random random = new Random();
    JOptionPane talk_msg = new JOptionPane();
    private boolean first_talk = true;
    
    float positionX = (float)300;
    float positionY = (float)500;
    
    float tmp_positionX = 0.0f;
    float tmp_positionY = 0.0f;
    
    Texture img;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> animation;
    
    Texture img2;
    TextureRegion[] animationFrames2;
    Animation<TextureRegion> animation2;
    
    float elapsedTime;
    
    public Jackson(String name, Gender gender) {
    	super(name, gender);
        this.setTexture(new Texture("villager_" + name + ".png"));
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
    
    @Override
    public void talk(Player player) {
    	
    	int talk_case = random.nextInt(5);
    	
    	if (first_talk) {
    		talk_msg.showMessageDialog(null, "여어! 난 잭슨\n" + "그대의 이름은" + player.getName() +  "이군? \n 앞으로 잘 지내보지 냐하-!!");
    		first_talk = false;
    		return;
    	}
    	if (talk_case == 0) {
    		talk_msg.showMessageDialog(null, "미안하지만 지금은 좀 바빠. \n 다음에 이야기 해도될까, 우쭐?");
    		return;
    	}
    	if (talk_case == 1) {
    		talk_msg.showMessageDialog(null, "안녕, " + player.getName() + "? \n 봄은 이별의 계절이기도 하지..\n 하지만 그대와 나같은 진정한 관계는 항상 굳건하지! 우쭐~ ");
    		return;
    	}
    	if (talk_case == 2) {
    		talk_msg.showMessageDialog(null, player.getName() + " 그대에게 주고싶은 것이 있다네.. \n 받아주겠나, 우쭐?");
    		player.getInventory().addItem(new Tuna());
    		player.getInventory().addItem(new Tuna());
    		talk_msg.showMessageDialog(null, "참치 획득!");
    		return;
    	}
    	if (talk_case == 3) {
    		talk_msg.showMessageDialog(null, "날 보러 와준건가" + player.getName() + "? \n 나는 항상 그대의 곁에 있다네, 우쭐!");
    		return;
    	}
    	if (talk_case == 4) {
    		talk_msg.showMessageDialog(null, player.getName() + " 그대를 생각하니 정말로 그대가 나타났군... \n 우리는 정말로 운명이야, 우쭐!");
    		return;
    	}
    	
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
    
    public float get_x() {
    	return positionX;
    }
    public float get_y() {
    	return positionY;
    }
}
