package com.teameleven.javapracticelab.items;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    ArrayList<Item> itemList = new ArrayList<>();

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public void showInventory() throws IOException {
    	File file = new File("temp_itemlist.txt");
    	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    	
        HashMap<String, Integer> map = new HashMap<>();

        for(Item item : itemList) {
            if (map.containsKey(item.getName())) {
                map.put(item.getName(), map.get(item.getName()) + 1);
            }
            else {
                map.put(item.getName(), 1);
            }
        }

        for(Map.Entry<String, Integer> elem : map.entrySet())  {
            System.out.println(elem.getKey() + ":  " + elem.getValue());
            pw.println(elem.getKey() + ":  " + elem.getValue());
        }
        pw.close();
    }


}
