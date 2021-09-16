package com.bridgelabz.hotelreservation;


public class UserEntryException extends RuntimeException
{

	enum ExceptionType 
	{
		ENTERED_NULL, ENTERED_EMPTY
	}

	ExceptionType type;

	public UserEntryException(ExceptionType type, String message) 
	{

		super(message);
		this.type = type;

	}

}
