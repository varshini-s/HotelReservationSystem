package com.bridgelabz.hotelreservation;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

		String initialDate="11Sep2021";
		String finalDate="12Sep2021";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate starteDate = LocalDate.parse(initialDate, formatter);
		LocalDate endDate = LocalDate.parse(finalDate, formatter);
		

		Calender calenderOperations = new Calender();
		if(calenderOperations.validateFormatOfDate(finalDate)&&calenderOperations.validateFormatOfDate(initialDate))
		{
			
			List<Hotel> cheapHotels=hotelList.findCheapestHotelsList(starteDate,endDate,"regular");

			//expected list of cheap hotels
			HotelReservationSystemImpl cheapHotelExpected = new HotelReservationSystemImpl();
			cheapHotelExpected.addHotel("LakeWood", 110,90,80,80,3);
			cheapHotelExpected.addHotel("BridgeWood", 150,50,110,50,4);

			Assert.assertEquals(cheapHotels, cheapHotelExpected.hotelList);
			Assert.assertEquals(hotelList.findCheapestHotelsList(starteDate,endDate,"regular").get(0).getRegularCustomerCost(starteDate,endDate),200);

			hotelList.findCheapHotelWithBestRating(starteDate,endDate,"regular");
			
		}
		
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

		String initialDate="11Sep2021";
		String finalDate="12Sep2021";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate starteDate = LocalDate.parse(initialDate, formatter);
		LocalDate endDate = LocalDate.parse(finalDate, formatter);
		

		Calender calenderOperations = new Calender();
		if(calenderOperations.validateFormatOfDate(finalDate)&&calenderOperations.validateFormatOfDate(initialDate))
		{

		Hotel cheapestHotel=hotelList.findCheapHotelWithBestRating(starteDate, endDate,"reward");

		Assert.assertEquals("RidgeWood",cheapestHotel.getName());
		Assert.assertEquals(5,cheapestHotel.getRating());
		Assert.assertEquals(140,cheapestHotel.getRewardCustomerCost(starteDate, endDate));
		}
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
	
	@Test
	public void whenGivenInvalidDateFormat_ShouldReturnFalse()
	{
		String date="10-Augu-2020";
		Calender calenderOperation = new Calender();
		boolean isValid=calenderOperation.validateFormatOfDate(date);
		Assert.assertFalse(isValid);
		
	}
	
	@Test
	public void whenGivenvalidDateFormat_ShouldReturnTrue()
	{
		String date="10Jan2020";
		Calender calenderOperation = new Calender();
		boolean isValid=calenderOperation.validateFormatOfDate(date);
		Assert.assertTrue(isValid);
		
	}
	
	
	
	

}
