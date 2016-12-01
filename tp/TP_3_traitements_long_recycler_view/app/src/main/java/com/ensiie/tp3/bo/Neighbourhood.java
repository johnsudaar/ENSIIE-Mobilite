package com.ensiie.tp3.bo;

import java.io.Serializable;

/**
 * Created by Adrian on 30/11/2016.
 */
public class Neighbourhood implements Serializable {
	private String id;
	private String name;

	public Neighbourhood(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return "Neighbourhood{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
