package com.example.jsfdemo.domain;

import java.util.Date;
import java.util.List;

public class Game {
	
	String PIN;
	String title;
	Date yop;
	double price;
	boolean requireAdult;
	
	public Game() {

	}

	public Game(String PIN, String title, Date yop, double price, boolean requireAdult) {
		super();
		this.PIN = PIN;
		this.title = title;
		this.yop = yop;
		this.price = price;
		this.requireAdult = requireAdult;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String PIN) {
		this.PIN = PIN;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getYop() {
		return yop;
	}
	public void setYop(Date yop) {
		this.yop = yop;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isRequireAdult() {
		return requireAdult;
	}
	public void setRequireAdult(boolean requireAdult) {
		this.requireAdult = requireAdult;
	}
	
	

}
