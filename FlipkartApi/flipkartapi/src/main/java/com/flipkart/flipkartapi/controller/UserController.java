package com.flipkart.flipkartapi.controller;

import java.util.List;

import com.flipkart.flipkartapi.Services.UserService;
import com.flipkart.flipkartapi.dto.UsersData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UsersData> findAll() {
		return userService.findAll();

	}

	@GetMapping("{id}")
	public UsersData findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping
	public UsersData create(@RequestBody UsersData usersData) {
		return userService.create(usersData);
	}

	@DeleteMapping("{id}")
	public boolean delete(@PathVariable Long id) {
		return userService.delete(id);
	}

	@PutMapping("{id}")
	public boolean updateUsers(@RequestBody UsersData usersData, @PathVariable("id") Long userId) {
		return userService.update(usersData, userId);
	}

}
