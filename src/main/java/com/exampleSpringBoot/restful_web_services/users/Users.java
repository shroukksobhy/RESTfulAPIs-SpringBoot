package com.exampleSpringBoot.restful_web_services.users;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Users {
	private int ID;
	
	@Size(min=2,message ="Name should have atleast 2 characters")
	private String Name;

	@Past(message="Birth date should be in the past")
	private LocalDate BirthDate;
	
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
	
	@JsonProperty("user_name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("birth_date")
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

}
