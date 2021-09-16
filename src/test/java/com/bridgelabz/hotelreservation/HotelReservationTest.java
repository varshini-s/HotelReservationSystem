package com.bridgelabz.hotelreservation;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bridgelabz.hotelreservation.UserEntryException.ExceptionType;

import org.junit.Test;

public class HotelReservationTest 
{

	@Test
	public void whenAddedNewHotel_ItMustBeAddedToList() 
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		Hotel hotel=hotelList.getHotel("LakeWood");
		Assert.assertEquals(hotelList.getHotel("LakeWood"), new Hotel("LakeWood", 110,90,80,80,3));
		Assert.assertEquals(hotelList.getHotel("BridgeWood"),new Hotel ("BridgeWood", 150,50,110,50,4));
		Assert.assertEquals(hotelList.getHotel("RidgeWood"), new Hotel("RidgeWood", 220,150,100,40,5));


	}

	@Test
	public void whenGivenRegularCustomerRates_ShouldBeAddedToGivenHotel()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		Hotel hotel=hotelList.getHotel("LakeWood");
		Assert.assertEquals(hotel.getRegularCustomerWeekDayRate(), 110);
		Assert.assertEquals(hotel.getRegularCustomerWeekEndRate(), 90);
	}
	@Test
	public void whenGivenDateRange_ShouldReturnListOfCheapestHotel()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		List<Hotel> cheapHotels=hotelList.findCheapestHotelsList(initialDate,finalDate,"regular");

		//expected list of cheap hotels
		HotelReservationSystemImpl cheapHotelExpected = new HotelReservationSystemImpl();
		cheapHotelExpected.addHotel("LakeWood", 110,90,80,80,3);
		cheapHotelExpected.addHotel("BridgeWood", 150,50,110,50,4);

		Assert.assertEquals(cheapHotels, cheapHotelExpected.hotelList);
		Assert.assertEquals(hotelList.findCheapestHotelsList(initialDate,finalDate,"regular").get(0).getRegularCustomerCost(initialDate,finalDate),200);

		hotelList.findCheapHotelWithBestRating(initialDate, finalDate,"regular");
	}

	@Test
	public void whenGivenRating_ShouldbeAddedToRespectiveHotels()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);

		Hotel hotel=hotelList.getHotel("LakeWood");
		Assert.assertEquals(3, hotel.getRating());

	}

	@Test
	public void whenGivenDateRangeForRegularCustomer_ShouldReturnCheapestHotelWithBestRating()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		Hotel cheapestHotel=hotelList.findCheapHotelWithBestRating(initialDate, finalDate,"regular");

		assertEquals("BridgeWood",cheapestHotel.getName());
		Assert.assertEquals(4,cheapestHotel.getRating());
		Assert.assertEquals(200,cheapestHotel.getRegularCustomerCost(initialDate, finalDate));
	}

	@Test
	public void whenGivenDateRange_ShouldReturnBestRatedHotel()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		Hotel cheapestHotel=hotelList.findBestRatedHotel(initialDate, finalDate);

		assertEquals("RidgeWood",cheapestHotel.getName());
		Assert.assertEquals(5,cheapestHotel.getRating());
		Assert.assertEquals(370,cheapestHotel.getRegularCustomerCost(initialDate, finalDate));


	}
	
	@Test
	public void whenGivenRewardCustomerRates_ShouldBeAddedToGivenHotel()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		Hotel hotel=hotelList.getHotel("LakeWood");
		Assert.assertEquals(hotel.getRewardCustomerWeekDayRate(),80);
		Assert.assertEquals(hotel.getRewardCustomerWeekEndrate(),80);
	}


	@Test
	public void whenGivenDateRangeForRewardCustomer_ShouldReturnCheapestHotelWithBestRating()
	{
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");

		Hotel cheapestHotel=hotelList.findCheapHotelWithBestRating(initialDate, finalDate,"reward");

		Assert.assertEquals("RidgeWood",cheapestHotel.getName());
		Assert.assertEquals(5,cheapestHotel.getRating());
		Assert.assertEquals(140,cheapestHotel.getRewardCustomerCost(initialDate, finalDate));
	}
	@Test
	public void whenEnteredNullCustomerType_ShouldThrowException()
	{
		
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");
		
		try 
		{
			hotelList.findCheapHotelWithBestRating(initialDate, finalDate,null);

		} catch (UserEntryException e) 
		{
			Assert.assertEquals(ExceptionType.ENTERED_NULL ,e.type);
		}
		
	}
	
	@Test
	public void whenEnteredEmptyCustomerType_ShouldThrowException()
	{
		
		HotelReservationSystemImpl hotelList = new HotelReservationSystemImpl();
		hotelList.addHotel("LakeWood", 110,90,80,80,3);
		hotelList.addHotel("BridgeWood", 150,50,110,50,4);
		hotelList.addHotel("RidgeWood", 220,150,100,40,5);

		LocalDate initialDate = LocalDate.parse("2021-09-11");
		LocalDate finalDate = LocalDate.parse("2021-09-12");
		
		try 
		{
			hotelList.findCheapHotelWithBestRating(initialDate, finalDate,"");

		} catch (UserEntryException e) 
		{
			Assert.assertEquals(ExceptionType.ENTERED_EMPTY ,e.type);
		}
		
	}
	
	
	
	

}
