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
    
    public void delItem(Item item) {
        for (Item item2 : this.itemList) {
           if (item2.getName() == item.getName()) {
              this.itemList.remove(item2);
              break;
           }
        }
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


}
