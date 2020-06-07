package com.teameleven.javapracticelab.items;

public class Branch extends Wood{
	
	static int count = 0;
	static int cost;
	static String en_name = "Branch";
	static String ko_name = "나뭇가지";

	public Branch(){
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
