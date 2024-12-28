package com.exampleSpringBoot.restful_web_services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.exampleSpringBoot.restful_web_services.users.Users;

@Component
public class UserDAOservice {
	//DAO is Data Access Object 
	
	private static List<Users> users = new ArrayList<>();
	private static int usersCount=0; 
	
	static {
		users.add(new Users(++usersCount,"Shimaa",LocalDate.now()));
		users.add(new Users(++usersCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new Users(++usersCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new Users(++usersCount,"Jim",LocalDate.now().minusYears(20)));
	}
	
	public List<Users> findAll(){
		return users;
	}
	

	public Users findOne(int id){
		Predicate<? super Users> predicate = user -> user.getID() == id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public Users save(Users user) {
		user.setID(++usersCount);
		users.add(user);
		return user;
	}
	public void deleteUser(int id) {
		Predicate<? super Users> predicate = user -> user.getID() == id;
		users.removeIf(predicate);
	}
}
