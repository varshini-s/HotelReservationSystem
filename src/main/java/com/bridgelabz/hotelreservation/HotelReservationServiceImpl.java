package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.bridgelabz.hotelreservation.UserEntryException.ExceptionType;
import java.util.*;

public class HotelReservationServiceImpl implements HotelReservationServiceIF
{

	List<Hotel> hotelList; 

	public HotelReservationServiceImpl() 
	{
		hotelList= new ArrayList<Hotel>();
	}

	@Override
	public void addHotel(Hotel hotel) 
	{
		hotelList.add(hotel);

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
	public List<Hotel> findCheapestHotelsList(String initialDate,String finalDate,Customer.CustomerType customer) 

	{
		List<Hotel> cheapHotels = new ArrayList<Hotel>();
		DateServiceProvider dateservice = new DateServiceProvider();
		dateservice.dateValidation(initialDate, finalDate);


		if(Customer.CustomerType.REGULAR_CUSTOMER.equals(customer))
		{	
			Long cost=this.hotelList.stream()
					.min((firstHotel, secondHotel)->Long
							.compare(firstHotel.getRegularCustomerCost(initialDate,finalDate), secondHotel.getRegularCustomerCost(initialDate,finalDate)))
					.get()
					.getRegularCustomerCost(initialDate, finalDate);

			cheapHotels=hotelList.stream().
					filter(hotel->hotel.getRegularCustomerCost(initialDate, finalDate)==cost)
					.collect(Collectors.toList());


		}
		else if(Customer.CustomerType.REWARD_CUSTOMER.equals(customer))
		{
			Long cost=hotelList.stream()
					.min((firstHotel, secondHotel)->Long
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

	@Override
	public Hotel findCheapHotelWithBestRating(String initialDate,String finalDate,Customer.CustomerType customer)
	{
		DateServiceProvider dateservice = new DateServiceProvider();
		dateservice.dateValidation(initialDate, finalDate);

		List<Hotel> cheapHotels=this.findCheapestHotelsList(initialDate, finalDate,customer);

		Hotel cheapestHotel=cheapHotels.stream().max((firstHotel, secondHotel)->Integer
				.compare(firstHotel.getRating(), secondHotel.getRating()))
				.get();

		return cheapestHotel;

	}

	@Override
	public Hotel findBestRatedHotel(String initialDate,String finalDate)
	{
		Hotel bestRateHotel=hotelList.stream().max((firstHotel, secondHotel)->Integer
				.compare(firstHotel.getRating(), secondHotel.getRating()))
				.get();

		return bestRateHotel;

	}
}
