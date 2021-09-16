package com.bridgelabz.hotelreservation;

import java.util.LinkedList;
import java.util.List;

public class HotelSystem 
{

	List<Hotel> hotelList = new LinkedList<Hotel>();

	public List<Hotel> getHotelList() 
	{
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) 
	{
		this.hotelList = hotelList;
	}

	public void addHotel(String name,int regularCustomerRate) 
	{

		hotelList.add(new Hotel(name,regularCustomerRate));		

	}
	public Hotel getHotel(String name)
	{
		for(int index=0;index<hotelList.size();index++)
		{
			if(hotelList.get(index).getName().equals(name))
			{
				return hotelList.get(index);
			}
		}

		return null;
	}


}
