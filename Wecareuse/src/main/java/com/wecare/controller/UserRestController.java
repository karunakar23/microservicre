package com.wecare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecare.LoadBalancerConfig;
import com.wecare.DTO.BookDTO;
import com.wecare.DTO.LoginDTO;
import com.wecare.DTO.UserDTO;
import com.wecare.entity.Bookingtable;
import com.wecare.entity.Usertable;
import com.wecare.exception.UserNotFoundException;
import com.wecare.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	private UserService userv;
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserDTO dto) {
		String str=userv.createUser(dto);
		return ResponseEntity.ok(str);
		
	}
	@PostMapping("users/login")
	public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO logindto)throws UserNotFoundException {
		Boolean opt= userv.loginUser(logindto);
		return ResponseEntity.ok(opt);
	}
	@GetMapping("users/{id}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable("id") String id){
		UserDTO ud=userv.getUserProfile(id);
		return ResponseEntity.ok(ud);
	}
	
	@GetMapping("users/booking/{userid}")
	public List<BookDTO> showMyAppointments(@PathVariable("userid") String userid){
		return userv.showMyAppointments(userid);
	}
	@GetMapping("users/findbyid/{userid}")
	public Optional<Usertable> findbyid(@PathVariable("userid") String id){
		return userv.findbyid(id);
	}
}
