package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public interface HotelReservationSystemIF 
{
	public void addHotel(String name, int regularCustomerWeekDayRate,int regularCustomerWeekEndRate);
	public Hotel getHotel(String name);
	public List<Hotel> findCheapestHotel(LocalDate initialDate,LocalDate finalDate);
	

}
