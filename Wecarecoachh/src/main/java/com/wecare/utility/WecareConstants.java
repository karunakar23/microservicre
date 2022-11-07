package com.wecare.utility;

public enum WecareConstants {
	COACH_NOT_FOUND("coach.not.found"),
	USER_NOT_FOUND("user.not.found"),
	BOOKING_ALREADY_EXISTS_EXCEPTION("booking.already.exists"),
	BOOKING_NOT_FOUND("book.not.found");
	private String type;
	WecareConstants(String type){
		this.type=type;
	}
	public String toString(){
		return this.type;
	}
}
