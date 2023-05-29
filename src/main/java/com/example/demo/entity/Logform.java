package com.example.demo.entity;

public class Logform {
	private int id;
	private String shopname;
	private String foodname;
	private int price;
	private String genre;
	private int score;
	private String comment;
	private String shopaddress;

	public Logform() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getShopaddress() {
		return shopaddress;
	}

	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}

}
