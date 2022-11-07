package com.wecare.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class CoachDTO {
	private String coach_id;
	private String name;
	private String gender;
	private LocalDate date_of_birth;
	private String password;
	private Long mobile_number;
	private String speciality;
	
	public CoachDTO() {
		
	}
	
	public CoachDTO(String coach_id, String name, String gender, LocalDate date_of_birth, String password,
			Long mobile_number, String speciality) {
		super();
		this.coach_id = coach_id;
		this.name = name;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.password = password;
		this.mobile_number = mobile_number;
		this.speciality = speciality;
	}

	public String getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(String coach_id) {
		this.coach_id = coach_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@Override
	public String toString() {
		return "Coachtable [coach_id=" + coach_id + ", name=" + name + ", gender=" + gender + ", date_of_birth="
				+ date_of_birth + ", password=" + password + ", mobile_number=" + mobile_number + ", speciality="
				+ speciality + "]";
	}
	
}
