package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.Objects;
import java.util.stream.Collectors;

import com.bridgelabz.hotelreservation.UserEntryException.ExceptionType;




public class HotelReservationSystemImpl implements HotelReservationSystemIF
{


	List<Hotel> hotelList; 

	public HotelReservationSystemImpl() 
	{
		hotelList= new ArrayList<Hotel>();
	}

	@Override
	public void addHotel(String name, int regularCustomerWeekDayRate,int regularCustomerWeekEndRate,int rewardCustomerWeekDayRate,int rewardCustomerWeekEndrate,int rating) 
	{
		hotelList.add(new Hotel(name,regularCustomerWeekDayRate,regularCustomerWeekEndRate,rewardCustomerWeekDayRate,rewardCustomerWeekEndrate,rating));

	}

	@Override
	public Hotel getHotel(String name) 
	{

		return hotelList.stream()
				.filter(hotel -> Objects.equals(hotel.getName(),name))
				.findFirst()
				.orElse(null);	
	}

	@Override
	public List<Hotel> findCheapestHotelsList(LocalDate initialDate,LocalDate finalDate,String customerType) 

	{
		List<Hotel> cheapHotels = null;
		try
		{
			if(customerType.isEmpty())
			{
				throw new UserEntryException(ExceptionType.ENTERED_EMPTY,"Please enter valid customer type");
			}
			else
			{		
				if((customerType.toLowerCase()).equals("regular"))
				{
					Integer cost=hotelList.stream()
							.min((firstHotel, secondHotel)->Integer
									.compare(firstHotel.getRegularCustomerCost(initialDate,finalDate), secondHotel.getRegularCustomerCost(initialDate,finalDate)))
							.get()
							.getRegularCustomerCost(initialDate, finalDate);


					cheapHotels = new ArrayList<Hotel>();
					cheapHotels=hotelList.stream().
							filter(hotel->hotel.getRegularCustomerCost(initialDate, finalDate)==cost)
							.collect(Collectors.toList());

					
				}
				else if((customerType.toLowerCase()).equals("reward"))
				{
					Integer cost=hotelList.stream()
							.min((firstHotel, secondHotel)->Integer
									.compare(firstHotel.getRewardCustomerCost(initialDate,finalDate), secondHotel.getRewardCustomerCost(initialDate,finalDate)))
							.get()
							.getRewardCustomerCost(initialDate, finalDate);


					cheapHotels = new ArrayList<Hotel>();
					cheapHotels=hotelList.stream().
							filter(hotel->hotel.getRewardCustomerCost(initialDate, finalDate)==cost)
							.collect(Collectors.toList());

					
				}

				return cheapHotels;
			}



		}
		catch (NullPointerException e) 
		{
			throw new UserEntryException(ExceptionType.ENTERED_NULL,"Please enter valid customer type");
		}

	}


	@Override
	public Hotel findCheapHotelWithBestRating(LocalDate initialDate,LocalDate finalDate,String customerType)
	{
		try
		{
			if(customerType.isEmpty())
			{
				throw new UserEntryException(ExceptionType.ENTERED_EMPTY,"Please enter valid customer type");
			}
			else
			{	List<Hotel> cheapHotels=this.findCheapestHotelsList(initialDate, finalDate,customerType);

			Hotel cheapestHotel=cheapHotels.stream().max((firstHotel, secondHotel)->Integer
					.compare(firstHotel.getRating(), secondHotel.getRating()))
					.get();

			return cheapestHotel;
			}



		}
		catch (NullPointerException e) 
		{
			throw new UserEntryException(ExceptionType.ENTERED_NULL,"Please enter valid customer type");
		}

		

	}

	@Override
	public Hotel findBestRatedHotel(LocalDate initialDate,LocalDate finalDate)
	{

		Hotel bestRateHotel=hotelList.stream().max((firstHotel, secondHotel)->Integer
				.compare(firstHotel.getRating(), secondHotel.getRating()))
				.get();

		return bestRateHotel;

	}






}
