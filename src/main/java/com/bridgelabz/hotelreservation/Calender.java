package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calender 
{
	private LocalDate initialDate ;
	private LocalDate finalDate ;
	private List<LocalDate> listOfDatesInGivenRange ;

	public Calender(LocalDate initDate,LocalDate finDate) 
	{
		this.initialDate=initialDate;
		this.finalDate=finalDate;
	}
	
	public LocalDate getInitialDate() {
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

		listOfDatesInGivenRange	= Stream.iterate(initialDate, date -> date.plusDays(1))
								.limit(getNumberOfDaysWithinRangeOfDates())
								.collect(Collectors.toList());
	}

	public int getNumberOfDaysWithinRangeOfDates()
	{
		return Period.between(initialDate, finalDate).getDays()+1;

	}

	public int getNumberOfWeekDays()
	{
		int numberOfWeekDays=(int) listOfDatesInGivenRange.stream().filter(object->object.getDayOfWeek().equals(DayOfWeek.valueOf("SUNDAY"))==false).count();
		return numberOfWeekDays;
	}
	public int getNumberOfWeekends()
	{
		int numberOfWeekends=(int) listOfDatesInGivenRange.stream().filter(object->object.getDayOfWeek().equals(DayOfWeek.valueOf("SUNDAY"))).count();
		return numberOfWeekends;

	}


}
