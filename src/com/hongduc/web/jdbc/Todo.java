package com.hongduc.web.jdbc;

import java.time.LocalDate;

public class Todo {
	private int id;
	private String description;
	private LocalDate date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Todo(int id, String description, LocalDate date) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
	}
	public Todo(String description, LocalDate date) {
		super();
		this.description = description;
		this.date = date;
	}
	
	
	
}
