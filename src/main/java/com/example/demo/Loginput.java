package com.example.demo;


import jakarta.validation.constraints.NotBlank;


public class Loginput {
	
	public Loginput() {}
	
	@NotBlank(message = "店名を入力してください")
	private String shopname;
	@NotBlank(message = "商品名を入力してください")
	private String foodname;
	@NotBlank(message = "ジャンルを入力してください")
	private String genre;
	@NotBlank(message = "コメントを入力してください")
	private String comment;
	@NotBlank(message = "メールアドレスを入力してください")
	private String shopaddress;
	
	private int score;
	
	private int price;
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
