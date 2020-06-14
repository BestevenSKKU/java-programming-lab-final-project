package com.teameleven.javapracticelab.items;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Item> itemListAll = new ArrayList<>();
    JOptionPane getFruitsMsg = new JOptionPane();
    JOptionPane getStonesMsg = new JOptionPane();
    JOptionPane getFishsMsg = new JOptionPane();
    
    Random random = new Random();
    int fruitChoose;
    int stoneChoose;
    int fishChoose;
    
    

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
        	FileReader fileReader = new FileReader(file);
        	BufferedReader bufReader = new BufferedReader(fileReader);
        	
        	bufReader.readLine();
        	bufReader.readLine();
        	bufReader.readLine();
        	while((tmp = bufReader.readLine()) != null){

	        	for(Item item : itemListAll) {
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
    
    public void addItem2(Item item) {
        this.itemListAll.add(item);
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
    		
		int iNum = 0;
		
	    for (Item item2 : this.itemList) {
	       if (item2.getName() == item.getName()) {
	          iNum ++;
	       }
	    }
	    
	    if (iNum >= num) {
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
    
	public void addRadomItemPond(boolean haveFishingRod) {
    	
    	if (haveFishingRod == false) {
    		getFishsMsg.showMessageDialog(null, "아무것도 없었다.... 낚싯대가 있으면 어떨까?");
    	}
    	
    	if (haveFishingRod == true) {
    		fishChoose = random.nextInt(5);
	    	
	    	if (fishChoose == 0) {
	    		getFishsMsg.showMessageDialog(null, "대구 획득!");
	    		this.addItem(new Cod());
	    	}
	    	
	    	if (fishChoose == 1) {
	    		getFishsMsg.showMessageDialog(null, "참치 획득!");
	    		this.addItem(new Tuna());
	    	}
	    	
	    	if (fishChoose == 2) {
	    		getFishsMsg.showMessageDialog(null, "문어 획득!");
	    		this.addItem(new Octopus());
	    	}
	    	
	    	if (fishChoose == 3) {
	    		getFishsMsg.showMessageDialog(null, "연어 획득!");
	    		this.addItem(new Salmon());
	    	}
	    	
	    	if (fishChoose >3) {
	    		getFishsMsg.showMessageDialog(null, "아무것도 없었다...");
	    	}
	    	
    	}
    	
    }
    
	public void addRadomItemFruit(boolean haveAxe) {
	    	
	    	if (haveAxe == false) {
		    	fruitChoose = random.nextInt(9);
		    	
		    	if (fruitChoose == 0) {
		    		getFruitsMsg.showMessageDialog(null, "사과 획득!");
		    		this.addItem(new Apple());
		    	}
		    	
		    	else if (fruitChoose == 1) {
		    		getFruitsMsg.showMessageDialog(null, "바나나 획득!");
		    		this.addItem(new Banana());
		    	}
		    	
		    	else if (fruitChoose == 2) {
		    		getFruitsMsg.showMessageDialog(null, "망고 획득!");
		    		this.addItem(new Mango());
		    	}
		    	
		    	else if (fruitChoose == 3) {
		    		getFruitsMsg.showMessageDialog(null, "복숭아 획득!");
		    		this.addItem(new Peach());
		    	}
		    	
		    	else if (fruitChoose == 4 || fruitChoose == 5) {
		    		getFruitsMsg.showMessageDialog(null, "나뭇가지 획득!");
		    		this.addItem(new Branch());
		    	}
		    	
		    	else if (fruitChoose == 6 || fruitChoose == 7) {
		    		getFruitsMsg.showMessageDialog(null, "덩굴 획득!");
		    		this.addItem(new Vine());
		    	}
		    	
		    	else if (fruitChoose > 7) {
		    		getFruitsMsg.showMessageDialog(null, "아무것도 없었다....");
		    	}
	    	
	    	}
	    	if (haveAxe == true) {
		    	fruitChoose = random.nextInt(12);
		    	
		    	if (fruitChoose == 0) {
		    		getFruitsMsg.showMessageDialog(null, "사과 획득!");
		    		this.addItem(new Apple());
		    	}
		    	
		    	if (fruitChoose == 1) {
		    		getFruitsMsg.showMessageDialog(null, "바나나 획득!");
		    		this.addItem(new Banana());
		    	}
		    	
		    	if (fruitChoose == 2) {
		    		getFruitsMsg.showMessageDialog(null, "망고 획득!");
		    		this.addItem(new Mango());
		    	}
		    	
		    	if (fruitChoose == 3) {
		    		getFruitsMsg.showMessageDialog(null, "복숭아 획득!");
		    		this.addItem(new Peach());
		    	}
		    	
		    	if (fruitChoose == 4) {
		    		getFruitsMsg.showMessageDialog(null, "나뭇가지 획득!");
		    		this.addItem(new Branch());
		    	}
		    	
		    	if (fruitChoose == 5 || fruitChoose == 6) {
		    		getFruitsMsg.showMessageDialog(null, "부드러운 목재 획득!");
		    		this.addItem(new SoftWood());
		    	}
		    	
		    	if (fruitChoose == 7 || fruitChoose == 8) {
		    		getFruitsMsg.showMessageDialog(null, "단단한 목재 획득!");
		    		this.addItem(new HardWood());
		    	}
		    	
		    	if (fruitChoose == 9 || fruitChoose == 10) {
		    		getFruitsMsg.showMessageDialog(null, "덩굴 획득!");
		    		this.addItem(new Vine());
		    	}
		    	
		    	if (fruitChoose >10) {
		    		getFruitsMsg.showMessageDialog(null, "아무것도 없었다...");
		    	}
		    	
	    	}
	    	
	    }
    
    public void addRadomItemStone() {
    	stoneChoose = random.nextInt(2);
    	if (stoneChoose == 0) {
    		getStonesMsg.showMessageDialog(null, "돌멩이 획득!");
    		this.addItem(new NormalStones());
    	}
    	if (stoneChoose > 0) {
    		getStonesMsg.showMessageDialog(null, "아무것도 없었다...");
    	}
    }
    public void makeAllList(){
    	addItem2(new Apple());
    	addItem2(new Axe());
    	addItem2(new Banana());
    	addItem2(new Branch());
    	addItem2(new Cod());
    	addItem2(new FishingRod());
    	addItem2(new HardWood());
    	addItem2(new Mango());
    	addItem2(new NormalStones());
    	addItem2(new Octopus());
    	addItem2(new Salmon());
    	addItem2(new SoftWood());
    	addItem2(new Tuna());
    	addItem2(new Vine());
    	addItem2(new Peach());
    }
}
