package com.bridgelabz.hotelreservation;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

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

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		List<Hotel> cheapHotels=hotelList.findCheapestHotel(initialDate,finalDate);

		//expected list of cheap hotels
		HotelReservationSystemImpl cheapHotelExpected = new HotelReservationSystemImpl();
		cheapHotelExpected.addHotel("LakeWood", 110,90);
		cheapHotelExpected.addHotel("BridgeWood", 150,50);

		assertEquals(cheapHotels, cheapHotelExpected.hotelList);
		assertEquals(hotelList.findCheapestHotel(initialDate,finalDate).get(0).getRegularCustomerCost(initialDate,finalDate),200);
	}



}
