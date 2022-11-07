package com.wecare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecare.DTO.BookDTO;
import com.wecare.DTO.CoachDTO;
import com.wecare.DTO.LoginDTO;
import com.wecare.entity.Coachtable;
import com.wecare.exception.CoachNotFoundException;
import com.wecare.service.CoachService;

@RestController
public class CoachRestController {
	@Autowired
	CoachService service;
	@PostMapping("/coaches")
	public ResponseEntity<String>  createCoach(@RequestBody CoachDTO dto)throws CoachNotFoundException {
		String str=service.createCoach(dto);
		return ResponseEntity.accepted().body(str);
	}
	@PostMapping("coaches/login")
	public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO dto) throws CoachNotFoundException {
		Boolean b=service.loginCoach(dto);
		return ResponseEntity.accepted().body(b);
	}
	@GetMapping("coaches/{id}")
	public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable("id") String id) throws CoachNotFoundException{
		CoachDTO ct=service.getCoachProfile(id);
		return ResponseEntity.accepted().body(ct);
	}
	@GetMapping("coaches/all")
	public ResponseEntity<List<CoachDTO>> showAllCoaches() {
		List<CoachDTO> dto=service.showAllCoaches();
		return ResponseEntity.accepted().body(dto);
	}
	@GetMapping("coaches/bookings/{coachid}")
	public ResponseEntity<List<BookDTO>> showMySchedule(@PathVariable("coachid") String coachid){
		List<BookDTO> list=service.showMySchedule(coachid);
		return ResponseEntity.accepted().body(list);
	}
	@GetMapping("coaches/findbyid/{coachid}")
	public Optional<Coachtable> findbyid(@PathVariable("coachid") String coachid){
		return service.findbyid(coachid);
	}

}
