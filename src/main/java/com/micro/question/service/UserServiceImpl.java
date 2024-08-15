package com.micro.question.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.micro.question.entity.User;

@Component
public class UserServiceImpl implements UserService {

	private static List<User> users = new ArrayList<>();

	private static int usersCount = 0;

	static {
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
	}

	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public  List<User> findAll() {
		return users;
	}
	
	public User findOne(Integer id) {
//		return users.stream().filter((user)->user.getId()==id).toList().get(0);
		return users.stream().filter((user)->user.getId()==id).findFirst().orElse(null);
	}

	
}
