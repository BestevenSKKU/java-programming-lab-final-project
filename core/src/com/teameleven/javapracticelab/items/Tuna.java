package com.teameleven.javapracticelab.items;

public class Tuna extends Fish{
	
	static int count = 0;
	static int cost;
	static int calorie;
	static String en_name = "Tuna";
	static String ko_name = "참치";

	public Tuna(){
		//empty
	}
	
	static int getCount() {
		return count;
	}
	static int getCost() {
		return cost;
	}
	static int getCalorie() {
		return calorie;
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
	static void setCalorie(int val) {
		calorie = val;
	}
	
	
	
	static void increaseCount(int val) {
		count += val;
	}
	static void decreaseCount(int val) {
		count -= val;
	}
	
}
