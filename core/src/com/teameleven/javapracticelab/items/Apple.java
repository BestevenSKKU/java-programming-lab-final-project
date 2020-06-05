package com.teameleven.javapracticelab.items;

public class Apple extends Fruit{
	
	static int count;
	static int getCount() {
		return count;
	}
	static void increaseCount(int val) {
		count += val;
	}
	static void decreaseCount(int val) {
		count -= val;
	}
}

