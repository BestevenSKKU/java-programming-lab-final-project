package com.teameleven.javapracticelab.items;

public abstract class Item {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public void useItem() {

    }
}
