package com.prospace.spring.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.JwtAndAuthConf.JwtResponse;
import com.prospace.spring.JwtAndAuthConf.JwtUtils;
import com.prospace.spring.JwtAndAuthConf.LoginRequest;
import com.prospace.spring.JwtAndAuthConf.UserDetailsImpl;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.RoleRepository;
import com.prospace.spring.service.IServiceUser;

@RestController
@RequestMapping("/User")
public class UserRestController {
	@Autowired 
	IServiceUser serviceUser;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	@PostMapping("/adduser")
	public String registration(@RequestBody User u) {
		return serviceUser.addUser(u);
	}
	@GetMapping("/confirm/{token}")
	public String confirm(@PathVariable("token") String token) {
		return serviceUser.confirmToken(token);
	}
	@PostMapping("/forgot/{username}")
	public String forgotPassworProcess(@PathVariable("username") String username,HttpServletRequest request) {
		return serviceUser.forgotPassword(username, request);
	}
	@PostMapping("/reset/{token}/{newpassword}")
	public String resetPassword(@PathVariable("token") String token,@PathVariable("newpassword") String newpassword ) {
		return serviceUser.fogetPasswordSetting(token, newpassword);
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
