package com.bridgelabz.hotelreservation;

import static org.junit.Assert.*;

import org.junit.Test;

public class HotelReservationTest 
{

	@Test
	public void whenAddedNewHotel_ItMustBeAddedToList() 
	{
		HotelSystem hotelList = new HotelSystem();
		hotelList.addHotel("LakeWood", 1000);
				
		assertEquals(hotelList.getHotel("LakeWood"), new Hotel("LakeWood", 1000));
		
		
	}

}
