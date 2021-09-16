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
		hotelList.addHotel("LakeWood", 1000);

		assertEquals(hotelList.getHotel("LakeWood"), new Hotel("LakeWood", 1000));

	}

	@Test
	public void whenGivenHotelsList_ShouldReturnCheapestHotel()
	{
		HotelReservationSystemImpl hotelist = new HotelReservationSystemImpl();
		hotelist.addHotel("LakeWood", 110);
		hotelist.addHotel("BridgeWood", 160);
		hotelist.addHotel("RidgeWood", 220);

		LocalDate initialDate = LocalDate.parse("2020-09-10");
		LocalDate finalDate = LocalDate.parse("2020-09-11");
		int numberOfDays = Period.between(initialDate, finalDate).getDays()+1;

		assertEquals(hotelist.findCheapestHotel(numberOfDays).getName(),"LakeWood");
		assertEquals(hotelist.findCheapestHotel(numberOfDays).getCost(numberOfDays),220);
	}

}
