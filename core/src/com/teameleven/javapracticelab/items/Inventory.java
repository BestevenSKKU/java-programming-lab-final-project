package com.teameleven.javapracticelab.items;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import com.teameleven.javapracticelab.utils.Skins;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.teameleven.javapracticelab.utils.Skins;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Item> itemList_all = new ArrayList<>();
    JOptionPane get_fruits_msg = new JOptionPane();
    JOptionPane get_stones_msg = new JOptionPane();
    JOptionPane get_fishs_msg = new JOptionPane();
    
    Random random = new Random();
    int fruit_choose;
    int stone_choose;
    int fish_choose;
    
    

    public void saveItem(String playerName, String islandName, String gender) throws IOException {
    	
    	try {
	    	File file = new File("save.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
	        if(file.isFile() && file.canWrite()){
	            bufferedWriter.write(playerName);
	            bufferedWriter.newLine();
	            bufferedWriter.write(islandName);
	            bufferedWriter.newLine();
	            bufferedWriter.write(gender);
	            bufferedWriter.newLine();
	            
	            for (Item item : itemList) {
	            	bufferedWriter.write(item.getName());
		            bufferedWriter.newLine();
	            }
	            bufferedWriter.close();
	        }
    	} catch (IOException e) {
    		System.out.println(e);
    	}
        
    }
    
    public void loadItem() {
    	
    		String tmp;
    	try {
        	File file = new File("save.txt");
        	FileReader file_reader = new FileReader(file);
        	BufferedReader bufReader = new BufferedReader(file_reader);
        	
        	bufReader.readLine();
        	bufReader.readLine();
        	bufReader.readLine();
        	while((tmp = bufReader.readLine()) != null){

	        	for(Item item : itemList_all) {
	        		if (tmp.equals(item.getName())) {
	        			addItem(item);
	        		}
	        	}
        	}
        }
        catch (Exception e) {

        }
    }
    
    public void addItem(Item item) {
        this.itemList.add(item);
    }
    
    public void addItem_2(Item item) {
        this.itemList_all.add(item);
    }
    
    public void delItem(Item item) {
        for (Item item2 : this.itemList) {
           if (item2.getName() == item.getName()) {
              this.itemList.remove(item2);
              break;
           }
        }
    }
    
    public void delItems(Item item, int num) {
    	
	    for (int i = 0; i< num; i++){
	        for (Item item2 : this.itemList) {
	           if (item2.getName() == item.getName()) {
	              this.itemList.remove(item2);
	              break;
	           }
	        }
	        
	    }
    }

    public boolean ckItems(Item item, int num) {
    		
		int i_num = 0;
		
	    for (Item item2 : this.itemList) {
	       if (item2.getName() == item.getName()) {
	          i_num ++;
	       }
	    }
	    
	    if (i_num >= num) {
	    	return true;
	    }
	    return false;
    }
    
    
    
    public HashMap<String, Integer> getItemList() {
    	
        HashMap<String, Integer> map = new HashMap<>();

        for(Item item : itemList) {
            if (map.containsKey(item.getName())) {
                map.put(item.getName(), map.get(item.getName()) + 1);
            }
            else {
                map.put(item.getName(), 1);
            }
        }

        return map;
    }
    
	public void addRadomItem_pond(boolean have_FishingRod) {
    	
    	if (have_FishingRod == false) {
    		get_fishs_msg.showMessageDialog(null, "아무것도 없었다.... 낚싯대가 있으면 어떨까?");
    	}
    	
    	if (have_FishingRod == true) {
    		fish_choose = random.nextInt(5);
	    	
	    	if (fish_choose == 0) {
	    		get_fishs_msg.showMessageDialog(null, "대구 획득!");
	    		this.addItem(new Cod());
	    	}
	    	
	    	if (fish_choose == 1) {
	    		get_fishs_msg.showMessageDialog(null, "참치 획득!");
	    		this.addItem(new Tuna());
	    	}
	    	
	    	if (fish_choose == 2) {
	    		get_fishs_msg.showMessageDialog(null, "문어 획득!");
	    		this.addItem(new Octopus());
	    	}
	    	
	    	if (fish_choose == 3) {
	    		get_fishs_msg.showMessageDialog(null, "연어 획득!");
	    		this.addItem(new Salmon());
	    	}
	    	
	    	if (fish_choose >3) {
	    		get_fishs_msg.showMessageDialog(null, "아무것도 없었다...");
	    	}
	    	
    	}
    	
    }
    
	public void addRadomItem_fruit(boolean have_axe) {
	    	
	    	if (have_axe == false) {
		    	fruit_choose = random.nextInt(9);
		    	
		    	if (fruit_choose == 0) {
		    		get_fruits_msg.showMessageDialog(null, "사과 획득!");
		    		this.addItem(new Apple());
		    	}
		    	
		    	if (fruit_choose == 1) {
		    		get_fruits_msg.showMessageDialog(null, "바나나 획득!");
		    		this.addItem(new Banana());
		    	}
		    	
		    	if (fruit_choose == 2) {
		    		get_fruits_msg.showMessageDialog(null, "망고 획득!");
		    		this.addItem(new Mango());
		    	}
		    	
		    	if (fruit_choose == 3) {
		    		get_fruits_msg.showMessageDialog(null, "복숭아 획득!");
		    		this.addItem(new Peach());
		    	}
		    	
		    	if (fruit_choose == 4 || fruit_choose == 5) {
		    		get_fruits_msg.showMessageDialog(null, "나뭇가지 획득!");
		    		this.addItem(new Branch());
		    	}
		    	
		    	if (fruit_choose == 6 || fruit_choose == 7) {
		    		get_fruits_msg.showMessageDialog(null, "덩굴 획득!");
		    		this.addItem(new Vine());
		    	}
		    	
		    	if (fruit_choose > 7) {
		    		get_fruits_msg.showMessageDialog(null, "아무것도 없었다....");
		    	}
	    	
	    	}
	    	if (have_axe == true) {
		    	fruit_choose = random.nextInt(12);
		    	
		    	if (fruit_choose == 0) {
		    		get_fruits_msg.showMessageDialog(null, "사과 획득!");
		    		this.addItem(new Apple());
		    	}
		    	
		    	if (fruit_choose == 1) {
		    		get_fruits_msg.showMessageDialog(null, "바나나 획득!");
		    		this.addItem(new Banana());
		    	}
		    	
		    	if (fruit_choose == 2) {
		    		get_fruits_msg.showMessageDialog(null, "망고 획득!");
		    		this.addItem(new Mango());
		    	}
		    	
		    	if (fruit_choose == 3) {
		    		get_fruits_msg.showMessageDialog(null, "복숭아 획득!");
		    		this.addItem(new Peach());
		    	}
		    	
		    	if (fruit_choose == 4) {
		    		get_fruits_msg.showMessageDialog(null, "나뭇가지 획득!");
		    		this.addItem(new Branch());
		    	}
		    	
		    	if (fruit_choose == 5 || fruit_choose == 6) {
		    		get_fruits_msg.showMessageDialog(null, "부드러운 목재 획득!");
		    		this.addItem(new SoftWood());
		    	}
		    	
		    	if (fruit_choose == 7 || fruit_choose == 8) {
		    		get_fruits_msg.showMessageDialog(null, "단단한 목재 획득!");
		    		this.addItem(new HardWood());
		    	}
		    	
		    	if (fruit_choose == 9 || fruit_choose == 10) {
		    		get_fruits_msg.showMessageDialog(null, "덩굴 획득!");
		    		this.addItem(new Vine());
		    	}
		    	
		    	if (fruit_choose >10) {
		    		get_fruits_msg.showMessageDialog(null, "아무것도 없었다...");
		    	}
		    	
	    	}
	    	
	    }
    
    public void addRadomItem_stone() {
    	stone_choose = random.nextInt(2);
    	if (stone_choose == 0) {
    		get_stones_msg.showMessageDialog(null, "돌멩이 획득!");
    		this.addItem(new NormalStones());
    	}
    	if (stone_choose > 0) {
    		get_stones_msg.showMessageDialog(null, "아무것도 없었다...");
    	}
    }
    public void make_all_list(){
    	addItem_2(new Apple());
    	addItem_2(new Axe());
    	addItem_2(new Banana());
    	addItem_2(new Branch());
    	addItem_2(new Cod());
    	addItem_2(new FishingRod());
    	addItem_2(new HardWood());
    	addItem_2(new Mango());
    	addItem_2(new NormalStones());
    	addItem_2(new Octopus());
    	addItem_2(new Salmon());
    	addItem_2(new SoftWood());
    	addItem_2(new Tuna());
    	addItem_2(new Vine());
    	addItem_2(new Peach());
    }
}
