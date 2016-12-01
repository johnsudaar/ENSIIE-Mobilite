package com.ensiie.tp3.bo;

/**
 * Created by Adrian on 30/11/2016.
 */
public class NeighbourhoodDetail {
	private String            name;
	private String            description;
	private String            facebook;
	private String            telephone;
	private String            email;

	public NeighbourhoodDetail(String name, String description, String facebook, String telephone, String email) {
		this.name = name;
		this.description = description;
		this.facebook = facebook;
		this.telephone = telephone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
