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
    public Jjuni(String name, Gender gender) {
    	super(name, gender);
        this.setTexture(new Texture("villager_" + name + ".png"));
        this.name = name;
        this.gender = gender;
		positionX = (float)400;
		positionY = (float)500;
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
}
