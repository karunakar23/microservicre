package com.wecare.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class Bookingtable {
	private Integer booking_id;
	private String user_id;
	private  String coach_id;
	private LocalDate appointment_date;
	private String slot;
	
	public Bookingtable(){
		
	}
	public Bookingtable(Integer booking_id, String user_id, String coach_id, LocalDate appointment_date, String slot) {
		this.booking_id = booking_id;
		this.user_id = user_id;
		this.coach_id = coach_id;
		this.appointment_date = appointment_date;
		this.slot = slot;
	}
	public Integer getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(String coach_id) {
		this.coach_id = coach_id;
	}
	public LocalDate getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	@Override
	public String toString() {
		return "Bookingtable [booking_id=" + booking_id + ", user_id=" + user_id + ", coach_id=" + coach_id
				+ ", appointment_date=" + appointment_date + ", slot=" + slot + "]";
	}
	}
