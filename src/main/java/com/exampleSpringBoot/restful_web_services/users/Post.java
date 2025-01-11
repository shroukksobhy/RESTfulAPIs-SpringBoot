package com.exampleSpringBoot.restful_web_services.users;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity(name="posts")
public class Post {

	@Id
	@GeneratedValue
	private int ID;
	
	@Size(min=10)
	private String Description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users user;

	
	@Override
	public String toString() {
		return "Post [ID=" + ID + ", Description=" + Description + "]";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	

}
