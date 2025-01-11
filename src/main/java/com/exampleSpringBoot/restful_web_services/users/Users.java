package com.exampleSpringBoot.restful_web_services.users;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")

public class Users {

	protected Users(){
		
	}
	@Id
	@GeneratedValue
	private int ID;
	
	
	@Size(min=2,message ="Name should have atleast 2 characters")
	private String Name;

	@Past(message="Birth date should be in the past")
	private LocalDate BirthDate;
	
	@OneToMany(mappedBy = "user") // mappedBy refers to the field in Post
	@JsonIgnore
	private List<Post> posts;
	 
	public Users(int iD, String name, LocalDate birthDate) {
		super();
		ID = iD;
		Name = name;
		BirthDate = birthDate;
	}
//	@Getter
//	@Setter
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	//@JsonProperty("user_name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	//@JsonProperty("birth_date")
	public LocalDate getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		BirthDate = birthDate;
	}
	
	@Override
	public String toString() {
	    return "Users{" +
	            "user_name='" + Name + '\'' + 
	            ", birth_date=" + BirthDate + 
	            ", id=" + ID + 
	            '}';
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
