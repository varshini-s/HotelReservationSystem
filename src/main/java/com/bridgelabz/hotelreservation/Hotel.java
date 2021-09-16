package com.bridgelabz.hotelreservation;

import java.util.List;


public class Hotel 
{
	private String name;
	private int regularCustomerRate;
	private List<Customer> customers;

	public Hotel(String name,int regularCustomerRate) 
	{
		this.name=name;
		this.regularCustomerRate=regularCustomerRate;

	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getRegularCustomerRate() 
	{
		return regularCustomerRate;
	}

	public void setRegularCustomerRate(int regularCustomerRate) 
	{
		this.regularCustomerRate = regularCustomerRate;
	}

	public List<Customer> getCustomers() 
	{
		return customers;
	}

	public void setCustomers(List<Customer> customers) 
	{
		this.customers = customers;
	}


	public int getCost(int numberOfDays)
	{
		return this.regularCustomerRate*numberOfDays;
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

		if(this.name.equals(other.name)==false||(this.regularCustomerRate!=other.regularCustomerRate))

		{
			return false;

		}

		return true;
	}




}
