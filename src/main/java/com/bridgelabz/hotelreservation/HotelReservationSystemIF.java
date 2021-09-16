package com.bridgelabz.hotelreservation;

import java.util.LinkedList;
import java.util.List;

public interface HotelReservationSystemIF 
{
	public void addHotel(String name,int regularCustomerRate) ;
	public Hotel getHotel(String name);
	public Hotel findCheapestHotel(int numberOfDays);
	

}
