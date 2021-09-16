package com.bridgelabz.hotelreservation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;


public class HotelReservationSystemImpl implements HotelReservationSystemIF
{
	List<Hotel> hotelList; 

	public HotelReservationSystemImpl() 
	{
		hotelList= new ArrayList<Hotel>();
	}

	@Override
	public void addHotel(String name, int regularCustomerWeekDayRate,int regularCustomerWeekEndRate) 
	{
		hotelList.add(new Hotel(name,regularCustomerWeekDayRate,regularCustomerWeekEndRate));

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
	public Hotel findCheapestHotel(int numberOfDays) 

	{
		Hotel cheapHotel=hotelList.stream().min((firstHotel, secondHotel) -> Integer.compare(firstHotel.getCost(numberOfDays), secondHotel.getCost(numberOfDays))).get();
		return cheapHotel;
	}





}
