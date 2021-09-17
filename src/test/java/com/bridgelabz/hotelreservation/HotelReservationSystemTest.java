package com.bridgelabz.hotelreservation;


import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bridgelabz.hotelreservation.UserEntryException.ExceptionType;


public class HotelReservationSystemTest 
{
	public HotelReservationSystemImpl hotelReservationSystem;
	Hotel firstHotel;
	Hotel secondHotel;
	Hotel thirdHotel;
	DateServiceProvider calenderOperations;
	String startDate;
	String endDate;

	@Before 
	public  void initialSetup()
	{
		hotelReservationSystem = new HotelReservationSystemImpl();
		firstHotel = new Hotel("LakeWood",110,90,80,80,3);
		hotelReservationSystem.addHotel(firstHotel);

		secondHotel= new Hotel("BridgeWood",150,50,110,50,4);
		hotelReservationSystem.addHotel(secondHotel);

		thirdHotel= new Hotel("RidgeWood",220,150,100,40,5);
		hotelReservationSystem.addHotel(thirdHotel);

		calenderOperations = new DateServiceProvider();
		calenderOperations.setInitialDate("11Sep2021");
		calenderOperations.setFinalDate("12Sep2021");
		startDate="11Sep2021";
		endDate = "12Sep2021";
	}


	@Test
	public void givenHotel_WhenAddedProperly_ShouldBeAddedToList() 
	{
		List<Hotel> hotelsAdded = new ArrayList<Hotel>();
		Hotel firstHotel = new Hotel("LakeWood", 110,90,80,80,3);
		hotelsAdded.add(firstHotel);

		Hotel secondHotel= new Hotel("BridgeWood", 150,50,110,50,4);
		hotelsAdded.add(secondHotel);

		Hotel thirdHotel= new Hotel("RidgeWood", 220,150,100,40,5);
		hotelsAdded.add(thirdHotel);

		Assert.assertEquals(hotelsAdded, hotelReservationSystem.hotelList);
	}


	@Test
	public void whenGivenRegularCustomerRates_ShouldBeAddedToGivenHotel()
	{
		Hotel hotel=hotelReservationSystem.getHotel("LakeWood");
		Assert.assertEquals(hotel.getRegularCustomerWeekDayRate(), 110);
		Assert.assertEquals(hotel.getRegularCustomerWeekEndRate(), 90);
	}

	@Test
	public void whenGivenDateRange_ShouldReturnListOfCheapestHotel()
	{
		if(calenderOperations.validateFormatOfDate(startDate)&&calenderOperations.validateFormatOfDate(endDate))
		{
			List<Hotel> cheapHotelsList=hotelReservationSystem.findCheapestHotelsList(startDate,endDate,Customer.CustomerType.REGULAR_CUSTOMER);

			//expected list of cheap hotels
			List<Hotel> cheapHotelExpected = new ArrayList<Hotel>();

			cheapHotelExpected.add(firstHotel);
			cheapHotelExpected.add(secondHotel);

			Assert.assertEquals(cheapHotelsList, cheapHotelExpected);
			Assert.assertEquals(hotelReservationSystem.findCheapestHotelsList(startDate,endDate,Customer.CustomerType.REGULAR_CUSTOMER).get(0)
					.getRegularCustomerCost(startDate,endDate),200);

			hotelReservationSystem.findCheapHotelWithBestRating(startDate,endDate,Customer.CustomerType.REGULAR_CUSTOMER);
		}
	}

	@Test
	public void whenGivenRating_ShouldbeAddedToRespectiveHotels()
	{	
		Hotel hotel=hotelReservationSystem.getHotel("LakeWood");
		Assert.assertEquals(3, hotel.getRating());
	}

	@Test
	public void whenGivenDateRangeForRegularCustomer_ShouldReturnCheapestHotelWithBestRating()
	{

		Hotel cheapestHotel=hotelReservationSystem.findCheapHotelWithBestRating(startDate,endDate,Customer.CustomerType.REGULAR_CUSTOMER);

		Assert.assertEquals("BridgeWood",cheapestHotel.getName());
		Assert.assertEquals(4,cheapestHotel.getRating());
		Assert.assertEquals(200,cheapestHotel.getRegularCustomerCost(startDate, endDate));
	}

	@Test
	public void whenGivenDateRange_ShouldReturnBestRatedHotel()
	{

		Hotel cheapestHotel=hotelReservationSystem.findBestRatedHotel(startDate,endDate);

		Assert.assertEquals("RidgeWood",cheapestHotel.getName());
		Assert.assertEquals(5,cheapestHotel.getRating());
		Assert.assertEquals(370,cheapestHotel.getRegularCustomerCost(startDate,endDate));
	}

	@Test
	public void whenGivenDateRangeForRewardCustomer_ShouldReturnCheapestHotelWithBestRating()
	{
		Hotel cheapestHotel=hotelReservationSystem.findCheapHotelWithBestRating(startDate,endDate,Customer.CustomerType.REWARD_CUSTOMER);

		Assert.assertEquals("RidgeWood",cheapestHotel.getName());
		Assert.assertEquals(5,cheapestHotel.getRating());
		Assert.assertEquals(140,cheapestHotel.getRewardCustomerCost(startDate, endDate));
	}

	@Test
	public void whenGivenvalidDateFormat_ShouldReturnTrue()
	{
		String date="10Jan2020";
		DateServiceProvider calenderOperation = new DateServiceProvider();
		boolean isValid=calenderOperation.validateFormatOfDate(date);
		Assert.assertTrue(isValid);
	}
	@Test
	public void whenEnteredNullDates_ShouldThrowException()
	{
		try 
		{
			hotelReservationSystem.findCheapestHotelsList(null,null,Customer.CustomerType.REGULAR_CUSTOMER);
		}
		catch (UserEntryException e) 
		{
			Assert.assertEquals(ExceptionType.ENTERED_NULL ,e.type);
		}
	}


}
