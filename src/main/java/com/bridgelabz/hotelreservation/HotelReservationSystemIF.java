package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public interface HotelReservationSystemIF 
{
	public void addHotel(String name, int regularCustomerWeekDayRate,int regularCustomerWeekEndRate,int rating);
	public Hotel getHotel(String name);
	public List<Hotel> findCheapestHotelsList(LocalDate initialDate,LocalDate finalDate);
	public Hotel findCheapHotelWithBestRating(LocalDate initialDate,LocalDate finalDate);
	public Hotel findBestRatedHotel(LocalDate initialDate,LocalDate finalDate);




}
