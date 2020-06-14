package com.teameleven.javapracticelab.characters;

import com.badlogic.gdx.graphics.Texture;
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
    	
    	int talkCase = random.nextInt(5);
    	
    	if (firstTalk) {
    		talkMsg.showMessageDialog(null, "안녕" + player.getName() +  "? \n 어떻게 너의 이름을 알고있냐고? 그건 운명이기 때문이지. 어차피~");
    		firstTalk = false;
    		return;
    	}
    	if (talkCase == 0) {
    		talkMsg.showMessageDialog(null, "여어, 잘 지내? 어차피? \n 그대의 미소를 보고 있으니 마음이 따뜻해져, 어차피...");
    		return;
    	}
    	if (talkCase == 1) {
    		talkMsg.showMessageDialog(null, "안녕, " + player.getName() + " 그대구나? 어차피? \n 언제나 고민이 있으면 말해줘, 어차피!");
    		return;
    	}
    	if (talkCase == 2) {
    		talkMsg.showMessageDialog(null, player.getName() + " 그대는 참 열정적이군! \n 그대처럼 달콤한 사과를 선물로 주겠네, 어차피~");
    		player.getInventory().addItem(new Apple());
    		player.getInventory().addItem(new Apple());
    		talkMsg.showMessageDialog(null, "사과 2개 획득!");
    		return;
    	}
    	if (talkCase == 3) {
    		talkMsg.showMessageDialog(null, "후후후, 내 음악성의 원천은 소울이지... \n 그렇기에 나도 아티스트란 말이지, 어차피!");
    		return;
    	}
    	if (talkCase == 4) {
    		talkMsg.showMessageDialog(null, player.getName() + " 그대를 생각하니 정말로 그대가 나타났군... \n 우리는 정말로 운명이야, 어차피!");
    		return;
    	}
    }
}
