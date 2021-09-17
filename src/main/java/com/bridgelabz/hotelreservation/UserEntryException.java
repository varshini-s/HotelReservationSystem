package com.bridgelabz.hotelreservation;


public class UserEntryException extends RuntimeException
{

	enum ExceptionType 
	{
		ENTERED_NULL, ENTERED_EMPTY,INVALID_DATE_FORMAT,INVALID_DATES_ORDER
	}

	ExceptionType type;

	public UserEntryException(ExceptionType type, String message) 
	{

		super(message);
		this.type = type;

	}

}
