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
		hotelList.addHotel("LakeWood", 110,90,3);
		hotelList.addHotel("BridgeWood", 150,50,4);
		hotelList.addHotel("RidgeWood", 220,150,5);

		Hotel hotel=hotelList.getHotel("LakeWood");
		assertEquals(hotelList.getHotel("LakeWood"), new Hotel("LakeWood", 110,90,3));
		assertEquals(hotelList.getHotel("BridgeWood"), new Hotel("BridgeWood", 150,50,4));
		assertEquals(hotelList.getHotel("RidgeWood"), new Hotel("RidgeWood", 220,150,5));


	}

	@Test
	public void whenGivenDateRange_ShouldReturnListOfCheapestHotel()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,3);
		hotelList.addHotel("BridgeWood", 150,50,4);
		hotelList.addHotel("RidgeWood", 220,150,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		List<Hotel> cheapHotels=hotelList.findCheapestHotelsList(initialDate,finalDate);

		//expected list of cheap hotels
		HotelReservationSystemImpl cheapHotelExpected = new HotelReservationSystemImpl();
		cheapHotelExpected.addHotel("LakeWood", 110,90,3);
		cheapHotelExpected.addHotel("BridgeWood", 150,50,4);

		assertEquals(cheapHotels, cheapHotelExpected.hotelList);
		assertEquals(hotelList.findCheapestHotelsList(initialDate,finalDate).get(0).getRegularCustomerCost(initialDate,finalDate),200);
		
		hotelList.findCheapHotelWithBestRating(initialDate, finalDate);
	}
	
	@Test
	public void whenAddedRating_ShouldbeAddedToRespectiveHotels()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,3);
		
		Hotel hotel=hotelList.getHotel("LakeWood");
		assertEquals(3, hotel.getRating());
		
	}
	
	@Test
	public void whenGivenDateRange_ShouldReturnCheapestHotelWithBestRating()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,3);
		hotelList.addHotel("BridgeWood", 150,50,4);
		hotelList.addHotel("RidgeWood", 220,150,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		Hotel cheapestHotel=hotelList.findCheapHotelWithBestRating(initialDate, finalDate);

		assertEquals("BridgeWood",cheapestHotel.getName());
		assertEquals(4,cheapestHotel.getRating());
		assertEquals(200,cheapestHotel.getRegularCustomerCost(initialDate, finalDate));
	}



}
