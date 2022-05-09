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
import org.springframework.security.core.userdetails.UserDetails;
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
import com.prospace.spring.entity.Experience;
import com.prospace.spring.entity.Skill;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.RoleRepository;
import com.prospace.spring.service.IServiceUser;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	@PostMapping("/saveEx")
	public Long saveExp(@RequestBody Experience e) {
		return serviceUser.saveExpe(e);
		
	}
	@PostMapping("/saveSkk")
	public Long saveSkk(@RequestBody Skill s) {
		return serviceUser.saveSkill(s);
		
	}
	@PutMapping("/affecterSkillToUser/{ids}/{idu}")
	public void affecterSkillToUser(@PathVariable("ids") Long ids,@PathVariable("idu") Long idu) {
		serviceUser.affecterSkillToUser(ids, idu);
	}
	@PutMapping("/affecterExpToSkill/{ids}/{ide}")
	public void affecterSkillEtEx(@PathVariable("ids") Long ids,@PathVariable("ide") Long ide) {
		serviceUser.affecterExpToSkill(ids, ide);
	}
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
	@GetMapping("/getConnectedUser")
	public User getTheConnectedUser(){
		User user=null;
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		username  = ((UserDetails) principal).getUsername();
		user = serviceUser.getOne(username);
		return user;
	}

	@GetMapping("/getCurrentUserName")
	public String getCurrentUserName() {
		log.info("in method get current userName--------");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	@GetMapping("/getCurrentUserId")
	public Long getCurrentUserId() {
		log.info("in method get current userId--------");
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		username  = ((UserDetails) principal).getUsername();
		return serviceUser.getIdUSer(username);
	}
}
