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
import com.teameleven.javapracticelab.items.Mango;
import com.teameleven.javapracticelab.utils.Gender;


public class Neogul extends Villager {
    final private String name;
    final private Gender gender;
    private float move_cycle = 0;
    private int move_dir = 0;
    private int speed = 5;
    Random random = new Random();
    JOptionPane talk_msg = new JOptionPane();
    private boolean first_talk = true;
    
    
    float positionX = (float)500;
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
    
    public Neogul(String name, Gender gender) {
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
    		talk_msg.showMessageDialog(null, "이 섬에온걸 환영하구리 " + player.getName() +  "!");
    		first_talk = false;
    		return;
    	}
    	if (talk_case == 0) {
    		talk_msg.showMessageDialog(null, "혹시 고민이 있어구리? \n 언제나 말해줘도 괜찮구리!");
    		return;
    	}
    	if (talk_case == 1) {
    		talk_msg.showMessageDialog(null, "" + player.getName() + ", 섬 생활은 좀 어떠구리? \n 여러 주민들에게도 말을 걸어보면 좋은 일이 있을수도 있구리!");
    		return;
    	}
    	if (talk_case == 2) {
    		talk_msg.showMessageDialog(null, "미안하구리 " + player.getName() + "... 집은 아직 공사 중이여서 들어갈수 없구리...");
    		return;
    	}
    	if (talk_case == 3) {
    		talk_msg.showMessageDialog(null, "내 선물이구리! 아무한테도 말하지 말어!");
    		player.getInventory().addItem(new Mango());
    		player.getInventory().addItem(new Mango());
    		talk_msg.showMessageDialog(null, "망고 2개 획득!");
    		return;
    	}
    	if (talk_case == 4) {
    		talk_msg.showMessageDialog(null, player.getName() + ", 시간이 되면 다른 사람들의 섬으로도 놀러가 보구리!");
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
