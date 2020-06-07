package com.teameleven.javapracticelab.items;

public class FishingRod extends Tool{
	
	static int count = 0;
	static int cost;
	static String en_name = "FishingRod";
	static String ko_name = "낚싯대";

	public FishingRod(){
		//empty
	}
	
	static int getCount() {
		return count;
	}
	static int getCost() {
		return cost;
	}
	static String getEn_name() {
		return en_name;
	}
	static String getKo_name() {
		return ko_name;
	}
	

	static void setCount(int val) {
		count = val;
	}
	static void setCost(int val) {
		cost = val;
	}
	
	
	static void increaseCount(int val) {
		count += val;
	}
	static void decreaseCount(int val) {
		count -= val;
	}
	
}

