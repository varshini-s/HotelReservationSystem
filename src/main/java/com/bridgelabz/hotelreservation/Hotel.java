package com.bridgelabz.hotelreservation;

import java.util.HashMap;
import java.util.Map;


public class Hotel 
{
	private String name;
	private int rating;
	private Map<Customer.CustomerType,Map<DateServiceProvider.DayType,Long>> CustomerRates;
	Map<DateServiceProvider.DayType,Long> regularCustomerRates =new HashMap<DateServiceProvider.DayType, Long>();
	Map<DateServiceProvider.DayType,Long> rewardCustomerRates=new HashMap<DateServiceProvider.DayType, Long>();
	DateServiceProvider calender = new DateServiceProvider();


	public Hotel(String name,long regularCustomerWeekDayRate,long regularCustomerWeekEndRate,long rewardCustomerWeekDayRate,long rewardCustomerWeekEndRate, int rating ) 
	{
		this.name=name;
		this.CustomerRates=new HashMap<Customer.CustomerType, Map<DateServiceProvider.DayType,Long>>();

		regularCustomerRates.put(DateServiceProvider.DayType.WEEKDAY, regularCustomerWeekDayRate);
		regularCustomerRates.put(DateServiceProvider.DayType.WEEKEND, regularCustomerWeekEndRate);
		this.CustomerRates.put(Customer.CustomerType.REGULAR_CUSTOMER,regularCustomerRates);
		rewardCustomerRates.put(DateServiceProvider.DayType.WEEKDAY, rewardCustomerWeekDayRate);
		rewardCustomerRates.put(DateServiceProvider.DayType.WEEKEND, rewardCustomerWeekEndRate);
		this.CustomerRates.put(Customer.CustomerType.REWARD_CUSTOMER,rewardCustomerRates);

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
	public int getRating() 
	{
		return rating;
	}

	public void setRating(int rating) 
	{
		this.rating = rating;
	}

	public long getRewardCustomerWeekDayRate() 
	{
		return CustomerRates.get(Customer.CustomerType.REWARD_CUSTOMER).get(DateServiceProvider.DayType.WEEKDAY);
	}

	public void setRewardCustomerWeekDayRate(long rewardCustomerWeekDay) 
	{
		rewardCustomerRates.put(DateServiceProvider.DayType.WEEKDAY, rewardCustomerWeekDay);
		this.CustomerRates.put(Customer.CustomerType.REWARD_CUSTOMER,rewardCustomerRates);
	}

	public long getRewardCustomerWeekEndrate() 
	{
		return CustomerRates.get(Customer.CustomerType.REWARD_CUSTOMER).get(DateServiceProvider.DayType.WEEKEND);
	}

	public void setRewardCustomerWeekEndrate(long rewardCustomerWeekEndrate)
	{
		rewardCustomerRates.put(DateServiceProvider.DayType.WEEKEND, rewardCustomerWeekEndrate);
		this.CustomerRates.put(Customer.CustomerType.REWARD_CUSTOMER,rewardCustomerRates);
	}

	public long getRegularCustomerWeekDayRate() 
	{
		return CustomerRates.get(Customer.CustomerType.REGULAR_CUSTOMER).get(DateServiceProvider.DayType.WEEKDAY);
	}

	public void setRegularCustomerWeekDayRate(long regularCustomerWeekDayRate) 
	{
		rewardCustomerRates.put(DateServiceProvider.DayType.WEEKDAY, regularCustomerWeekDayRate);
		this.CustomerRates.put(Customer.CustomerType.REGULAR_CUSTOMER,rewardCustomerRates);
	}

	public long getRegularCustomerWeekEndRate() 
	{
		return CustomerRates.get(Customer.CustomerType.REGULAR_CUSTOMER).get(DateServiceProvider.DayType.WEEKEND);
	}

	public void setRegularCustomerWeekEndRate(long regularCustomerWeekEndRate) 
	{
		rewardCustomerRates.put(DateServiceProvider.DayType.WEEKEND, regularCustomerWeekEndRate);
		this.CustomerRates.put(Customer.CustomerType.REGULAR_CUSTOMER,rewardCustomerRates);
	}

	public long getRegularCustomerCost(String initialDate,String finalDate)
	{
		calender.setInitialDate(initialDate);
		calender.setFinalDate(finalDate);
		int numberOfWeekDays=calender.getNumberOfWeekDays();
		int numberOfWeekends=calender.getNumberOfWeekends();

		return (Long)(this.getRegularCustomerWeekDayRate()*numberOfWeekDays)+(this.getRegularCustomerWeekEndRate()*numberOfWeekends);
	}

	public long getRewardCustomerCost(String initialDate,String finalDate)
	{

		calender.setInitialDate(initialDate);
		calender.setFinalDate(finalDate);

		int numberOfWeekDays=calender.getNumberOfWeekDays();
		int numberOfWeekends=calender.getNumberOfWeekends();

		return (this.getRewardCustomerWeekDayRate()*numberOfWeekDays)+(this.getRewardCustomerWeekEndrate()*numberOfWeekends);
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


		if(this.name.equals(other.name)==false||(this.CustomerRates.equals(other.CustomerRates)==false)
				||this.rating!=other.rating)

		{
			return false;

		}

		return true;
	}


}
