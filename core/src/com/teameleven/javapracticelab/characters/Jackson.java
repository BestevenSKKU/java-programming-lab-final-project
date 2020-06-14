package com.teameleven.javapracticelab.characters;

import com.badlogic.gdx.graphics.Texture;
import com.teameleven.javapracticelab.items.Tuna;
import com.teameleven.javapracticelab.utils.Gender;


public class Jackson extends Villager {
    public Jackson(String name, Gender gender) {
    	super(name, gender);
        this.setTexture(new Texture("villager_" + name + ".png"));
        this.name = name;
        this.gender = gender;
		positionX = (float)300;
		positionY = (float)500;
    }
    
    @Override
    public void talk(Player player) {
    	
    	int talkCase = random.nextInt(5);
    	
    	if (firstTalk) {
    		talkMsg.showMessageDialog(null, "여어! 난 잭슨\n" + "그대의 이름은" + player.getName() +  "이군? \n 앞으로 잘 지내보지 냐하-!!");
    		firstTalk = false;
    		return;
    	}
    	if (talkCase == 0) {
    		talkMsg.showMessageDialog(null, "미안하지만 지금은 좀 바빠. \n 다음에 이야기 해도될까, 우쭐?");
    		return;
    	}
    	if (talkCase == 1) {
    		talkMsg.showMessageDialog(null, "안녕, " + player.getName() + "? \n 봄은 이별의 계절이기도 하지..\n 하지만 그대와 나같은 진정한 관계는 항상 굳건하지! 우쭐~ ");
    		return;
    	}
    	if (talkCase == 2) {
    		talkMsg.showMessageDialog(null, player.getName() + " 그대에게 주고싶은 것이 있다네.. \n 받아주겠나, 우쭐?");
    		player.getInventory().addItem(new Tuna());
    		player.getInventory().addItem(new Tuna());
    		talkMsg.showMessageDialog(null, "참치 획득!");
    		return;
    	}
    	if (talkCase == 3) {
    		talkMsg.showMessageDialog(null, "날 보러 와준건가" + player.getName() + "? \n 나는 항상 그대의 곁에 있다네, 우쭐!");
    		return;
    	}
    	if (talkCase == 4) {
    		talkMsg.showMessageDialog(null, player.getName() + " 그대를 생각하니 정말로 그대가 나타났군... \n 우리는 정말로 운명이야, 우쭐!");
    		return;
    	}
    }
}
