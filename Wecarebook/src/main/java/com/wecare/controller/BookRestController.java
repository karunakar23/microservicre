package com.wecare.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecare.entity.Bookingtable;
import com.wecare.exception.BookingAlreadyExistsException;
import com.wecare.exception.BookingNotFoundException;
import com.wecare.exception.CoachNotFoundException;
import com.wecare.exception.UserNotFoundException;
import com.wecare.service.BookService;
import com.wecare.utility.WecareConstants;

@RestController
public class BookRestController {
	@Autowired
	private BookService bookserv;
	@Autowired
	private Environment env;
	
	@PostMapping("book/users/{userid}/booking/{coachid}/slot/{slot}/appointmentdate/{date}")
	public ResponseEntity<String> bookAppointments(@PathVariable("userid") String userid,@PathVariable("coachid") String coachid,@PathVariable("slot") String slot,@PathVariable("date") String date)throws BookingAlreadyExistsException,
	UserNotFoundException,CoachNotFoundException, InterruptedException, ExecutionException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  LocalDate localDate = LocalDate.parse(date, formatter);
		if(bookserv.bookAppointments(userid, coachid, slot, localDate)) {
			return ResponseEntity.accepted().body("Successfully booked appointment");
		}else {
			throw new BookingAlreadyExistsException(env.getProperty(WecareConstants.BOOKING_ALREADY_EXISTS_EXCEPTION.toString()));
		}
	}
	@PutMapping("book/{bookingid}/slot/{slot}/appointmentdate/{date}")
	public ResponseEntity<String> rescheduleAppointment(@PathVariable("bookingid") int bookingid,@PathVariable("slot") String slot,@PathVariable("date") String date)throws BookingNotFoundException,BookingAlreadyExistsException,UserNotFoundException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate localDate = LocalDate.parse(date, formatter);
		boolean flag=bookserv.rescheduleAppointment(bookingid,localDate,slot);
		if(!flag) {
			throw new BookingAlreadyExistsException(env.getProperty(WecareConstants.BOOKING_ALREADY_EXISTS_EXCEPTION.toString()));
		}
		return ResponseEntity.accepted().body("Successfully resechudeuled");
	}
	@DeleteMapping("book/delete/{bookingid}")
	public ResponseEntity<String> cancelAppointment(@PathVariable("bookingid") Integer bookingid)throws BookingNotFoundException,BookingAlreadyExistsException,UserNotFoundException {
		bookserv.cancelAppointment(bookingid);
		return ResponseEntity.accepted().body("Successfully deleted");
	}
	@GetMapping("book/findall")
	public List<Bookingtable> findall(){
		return bookserv.findall();
	}
	
}
