package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 3;
	static {
		users.add(new User(1,"Adam",new Date()));
		users.add(new User(2,"Adam2",new Date()));
		users.add(new User(3,"Adam3",new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		return users.stream().filter(userIn -> userIn.getId() == id).findFirst().orElse(null);
	}

}
