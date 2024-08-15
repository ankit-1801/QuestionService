package com.micro.question.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.micro.question.entity.SomeBean;
import com.micro.question.entity.User;
import com.micro.question.exception.UserNotFoundException;
import com.micro.question.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserService service;
	
	MessageSource messageSource;

	public UserController(UserService service, MessageSource messageSource) {
		this.service = service;
		this.messageSource =messageSource;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// GET /users
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("User not found!");
		}
		return user;
	}
	
	@PostMapping("/save")
	public User save(@RequestBody @Valid User u) {
		return service.save(u);
	}
	
	@GetMapping("/hello")
	public String getHello() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.text", null, "default message", locale);
	}

	
	//Example of dynamic filterring
	@GetMapping("/filtering") //field2
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("value1","value2", "value3");

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		
		return mappingJacksonValue;
	}

	@GetMapping("/filtering-list") //field2, field3
	public MappingJacksonValue filteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2", "value3"),
				new SomeBean("value4","value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		
		return mappingJacksonValue;
	}

}