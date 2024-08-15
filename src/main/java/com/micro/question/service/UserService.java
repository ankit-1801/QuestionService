package com.micro.question.service;

import java.util.List;

import com.micro.question.entity.User;

public interface UserService {
    User save(User user);
	List<User> findAll();
    User findOne(Integer Id);
    
}
