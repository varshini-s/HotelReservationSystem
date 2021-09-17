package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public interface HotelReservationSystem 
{
	public void addHotel(Hotel hotel);
	public Hotel getHotel(String name);
	public List<Hotel> findCheapestHotelsList(String initialDate,String finalDate,Customer.CustomerType customer) ;
	public Hotel findCheapHotelWithBestRating(String initialDate,String finalDate,Customer.CustomerType customer);
	public Hotel findBestRatedHotel(String initialDate,String finalDate);

}
