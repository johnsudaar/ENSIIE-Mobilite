package com.ensiie.tp4.database;

/**
 * Created by BRACIGAD on 12/12/2016.
 */

public class Todo {
	int id;
	String text;

	public Todo(String text) {
		this.text = text;
	}

	public Todo(int id, String text) {
		this.text = text;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
