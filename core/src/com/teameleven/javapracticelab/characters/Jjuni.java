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
import com.teameleven.javapracticelab.items.*;
import com.teameleven.javapracticelab.utils.Gender;


public class Jjuni extends Villager {
    final private String name;
    final private Gender gender;
    private float move_cycle = 0;
    private int move_dir = 0;
    private int speed = 5;
    Random random = new Random();
    JOptionPane talk_msg = new JOptionPane();
    private boolean first_talk = true;
    
    float positionX = (float)400;
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
    
    public Jjuni(String name, Gender gender) {
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
    		talk_msg.showMessageDialog(null, "안녕" + player.getName() +  "? \n 어떻게 너의 이름을 알고있냐고? 그건 운명이기 때문이지. 어차피~");
    		first_talk = false;
    		return;
    	}
    	if (talk_case == 0) {
    		talk_msg.showMessageDialog(null, "여어, 잘 지내? 어차피? \n 그대의 미소를 보고 있으니 마음이 따뜻해져, 어차피...");
    		return;
    	}
    	if (talk_case == 1) {
    		talk_msg.showMessageDialog(null, "안녕, " + player.getName() + " 그대구나? 어차피? \n 언제나 고민이 있으면 말해줘, 어차피!");
    		return;
    	}
    	if (talk_case == 2) {
    		talk_msg.showMessageDialog(null, player.getName() + " 그대는 참 열정적이군! \n 그대처럼 달콤한 사과를 선물로 주겠네, 어차피~");
    		player.getInventory().addItem(new Apple());
    		player.getInventory().addItem(new Apple());
    		talk_msg.showMessageDialog(null, "사과 2개 획득!");
    		return;
    	}
    	if (talk_case == 3) {
    		talk_msg.showMessageDialog(null, "후후후, 내 음악성의 원천은 소울이지... \n 그렇기에 나도 아티스트란 말이지, 어차피!");
    		return;
    	}
    	if (talk_case == 4) {
    		talk_msg.showMessageDialog(null, player.getName() + " 그대를 생각하니 정말로 그대가 나타났군... \n 우리는 정말로 운명이야, 어차피!");
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
