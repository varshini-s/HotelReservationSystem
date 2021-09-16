package com.bridgelabz.hotelreservation;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.junit.Test;

public class HotelReservationTest 
{

	@Test
	public void whenAddedNewHotel_ItMustBeAddedToList() 
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90);
		hotelList.addHotel("BridgeWood", 150,50);
		hotelList.addHotel("RidgeWood", 220,150);

		Hotel hotel=hotelList.getHotel("LakeWood");
		assertEquals(hotelList.getHotel("LakeWood"), new Hotel("LakeWood", 110,90));
		assertEquals(hotelList.getHotel("BridgeWood"), new Hotel("BridgeWood", 150,50));
		assertEquals(hotelList.getHotel("RidgeWood"), new Hotel("RidgeWood", 220,150));


	}

	@Test
	public void whenGivenHotelsList_ShouldReturnCheapestHotel()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90);
		hotelList.addHotel("BridgeWood", 150,50);
		hotelList.addHotel("RidgeWood", 220,150);

		LocalDate initialDate = LocalDate.parse("2020-09-10");
		LocalDate finalDate = LocalDate.parse("2020-09-11");
		int numberOfDays = Period.between(initialDate, finalDate).getDays()+1;

		assertEquals(hotelList.findCheapestHotel(numberOfDays).getName(),"LakeWood");
		assertEquals(hotelList.findCheapestHotel(numberOfDays).getCost(numberOfDays),220);
	}
	
	

}
