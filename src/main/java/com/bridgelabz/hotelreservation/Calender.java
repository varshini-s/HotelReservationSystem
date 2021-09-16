package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Objects;
import java.util.*;


public class Calender 
{
	private LocalDate initialDate ;
	private LocalDate finalDate ;
	private List<LocalDate> listOfDatesInGivenRange ;

	public Calender(LocalDate initialDate,LocalDate finalDate) 
	{
		this.initialDate=initialDate;
		this.finalDate=finalDate;
	}
	
	public LocalDate getInitialDate() 
	{
		return initialDate;
	}
	public void setInitialDate(LocalDate initialDate) {
		this.initialDate = initialDate;
	}
	public LocalDate getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	public void getListOfDaysInRange()
	{

		listOfDatesInGivenRange	= Stream.iterate(this.initialDate, date -> date.plusDays(1))
								.limit(this.getNumberOfDaysWithinRangeOfDates())
								.collect(Collectors.toList());
	}

	public int getNumberOfDaysWithinRangeOfDates()
	{
		
		return (int)Period.between(this.initialDate, this.finalDate).getDays()+1;

	}

	public int getNumberOfWeekDays()
	{
		
		this.getListOfDaysInRange();
		
		int numberOfWeekDays=(int)listOfDatesInGivenRange.stream()
							 .filter(object->object.getDayOfWeek()
							 .equals(DayOfWeek.valueOf("SUNDAY"))==false)
							 .count();

		 return numberOfWeekDays;
	}
	public int getNumberOfWeekends()
	{
		int numberOfWeekends=(int) listOfDatesInGivenRange.stream()
							 .filter(object->object.getDayOfWeek()
							 .equals(DayOfWeek.valueOf("SUNDAY")))
							 .count();
		return numberOfWeekends;

	}


}
