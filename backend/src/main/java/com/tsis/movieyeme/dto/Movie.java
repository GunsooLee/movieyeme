package com.tsis.movieyeme.dto;

// 영화
public class Movie {
	private int id; // 영화 id
	private String name; // 영화 이름
	private int price; // 영화 가격
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
