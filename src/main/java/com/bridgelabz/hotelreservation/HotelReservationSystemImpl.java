package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

public class HotelReservationSystemImpl implements HotelReservationSystemIF
{
	List<Hotel> hotelList; 

	public HotelReservationSystemImpl() 
	{
		hotelList= new ArrayList<Hotel>();
	}

	@Override
	public void addHotel(String name, int regularCustomerWeekDayRate,int regularCustomerWeekEndRate,int rating) 
	{
		hotelList.add(new Hotel(name,regularCustomerWeekDayRate,regularCustomerWeekEndRate,rating));

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
	public List<Hotel> findCheapestHotelsList(LocalDate initialDate,LocalDate finalDate) 

	{
		Integer cost=hotelList.stream()
					.min((firstHotel, secondHotel)->Integer
					.compare(firstHotel.getRegularCustomerCost(initialDate,finalDate), secondHotel.getRegularCustomerCost(initialDate,finalDate)))
					.get()
					.getRegularCustomerCost(initialDate, finalDate);


		List<Hotel> cheapHotels = new ArrayList<Hotel>();
		cheapHotels=hotelList.stream().
					filter(hotel->hotel.getRegularCustomerCost(initialDate, finalDate)==cost)
					.collect(Collectors.toList());
		return cheapHotels;
	}

	@Override
	public Hotel findCheapHotelWithBestRating(LocalDate initialDate,LocalDate finalDate)
	{
		List<Hotel> cheapHotels=this.findCheapestHotelsList(initialDate, finalDate);
		
		Hotel cheapestHotel=cheapHotels.stream().max((firstHotel, secondHotel)->Integer
							.compare(firstHotel.getRating(), secondHotel.getRating()))
							.get();
		
		return cheapestHotel;
		
	}

	




}
