package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.List;


public class Hotel 
{
	private String name;
	private int regularCustomerWeekDayRate;
	private int regularCustomerWeekEndRate;
	private int rating;


	private List<Customer> customers;

	public Hotel(String name,int regularCustomerWeekDayRate,int regularCustomerWeekEndRate,int rating ) 
	{
		this.name=name;
		this.regularCustomerWeekDayRate=regularCustomerWeekDayRate;
		this.regularCustomerWeekEndRate=regularCustomerWeekEndRate;
		this.rating=rating;

	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getRegularCustomerWeekDayRate() 
	{
		return regularCustomerWeekDayRate;
	}

	public void setRegularCustomerWeekDayRate(int regularCustomerWeekDayRate) {
		this.regularCustomerWeekDayRate = regularCustomerWeekDayRate;
	}

	public int getRegularCustomerWeekEndRate() 
	{
		return regularCustomerWeekEndRate;
	}

	public void setRegularCustomerWeekEndRate(int regularCustomerWeekEndRate) 
	{
		this.regularCustomerWeekEndRate = regularCustomerWeekEndRate;
	}

	public List<Customer> getCustomers() 
	{
		return customers;
	}

	public void setCustomers(List<Customer> customers) 
	{
		this.customers = customers;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}



	public int getRegularCustomerCost(LocalDate initialDate,LocalDate finalDate)
	{

		Calender calender = new Calender(initialDate, finalDate);
		int numberOfWeekDays=calender.getNumberOfWeekDays();
		int numberOfWeekends=calender.getNumberOfWeekends();

		return (this.regularCustomerWeekDayRate*numberOfWeekDays)+(this.regularCustomerWeekEndRate*numberOfWeekends);
	}


	@Override
	public boolean equals(Object obj) 

	{

		if (obj == null) 
		{
			return false;
		}

		if (obj.getClass() != this.getClass()) 
		{
			return false;
		}

		final Hotel other = (Hotel) obj;

		if(this.name.equals(other.name)==false||(this.regularCustomerWeekDayRate!=other.regularCustomerWeekDayRate)
				||this.regularCustomerWeekEndRate!=other.regularCustomerWeekEndRate)

		{
			return false;

		}

		return true;
	}



}
