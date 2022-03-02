package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.User;
import com.prospace.spring.service.IServiceUser;

@RestController
@RequestMapping("/User")
public class UserRestController {
	@Autowired 
	IServiceUser serviceUser;
	@PostMapping("/adduser")
	public String registration(@RequestBody User u) {
		return serviceUser.addUser(u);
	}
	@GetMapping("/confirm/{token}")
	public String confirm(@PathVariable("token") String token) {
		return serviceUser.confirmToken(token);
	}
	@GetMapping("/getusers")
	public List<User> retrieveAllUsers(){
		return serviceUser.getusers();
	}
	@GetMapping("/getusers2")
	public List<User> retrieveAllUndeletedUsers(){
		return serviceUser.getUndeletedUsers();
	}
	@PutMapping("/updateuser")
	public void updateUser(@RequestBody User u) {
		serviceUser.updateUser(u);
	}
	@PutMapping("/deleteuser/{iduser}")
	public void deleteUser(@PathVariable("iduser")Long id) {
		serviceUser.deleteUser(id);
	}
}
