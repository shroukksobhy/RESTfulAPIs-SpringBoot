package com.exampleSpringBoot.restful_web_services.users;

import java.time.LocalDate;

public class Users {
	private int ID;
	private String Name;
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public LocalDate getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		BirthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Users [ID=" + ID + ", Name=" + Name + ", BirthDate=" + BirthDate + "]";
	}
	
	

}
