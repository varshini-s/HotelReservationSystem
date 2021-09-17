package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bridgelabz.hotelreservation.UserEntryException.ExceptionType;


public class DateServiceProvider 
{
	private LocalDate initialDate ;
	private LocalDate finalDate ;
	private List<LocalDate> listOfDatesInGivenRange ;
	private static final String DATE_PATERN="^([0-2][0-9]|(3)[0-1])(Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\d{4}$";	

	enum DayType
	{
		WEEKDAY,WEEKEND;
	}

	public LocalDate dateParser(String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		return LocalDate.parse(date, formatter);

	}

	public LocalDate getInitialDate() 
	{
		return initialDate;
	}
	public void setInitialDate(String initialDate) 
	{
		this.initialDate = dateParser(initialDate);

	}
	public LocalDate getFinalDate() 
	{
		return finalDate;
	}
	public void setFinalDate(String finalDate) 
	{
		this.finalDate = dateParser(finalDate);

	}


	public void getListOfDaysInRange()
	{


		listOfDatesInGivenRange	= Stream.iterate(this.initialDate, date -> date.plusDays(1))
				.limit(this.getNumberOfDaysWithinRangeOfDates())
				.collect(Collectors.toList());
	}

	public int getNumberOfDaysWithinRangeOfDates()
	{
		if(this.finalDate.compareTo(this.initialDate)<=0)
		{
			throw new UserEntryException(ExceptionType.INVALID_DATES_ORDER, "Enter valid date order");

		}

		return (int)Period.between(this.initialDate, this.finalDate).getDays()+1;

	}

	public int getNumberOfWeekDays()
	{

		return this.getNumberOfDaysWithinRangeOfDates()-this.getNumberOfWeekends();
	}

	public int getNumberOfWeekends()
	{
		this.getListOfDaysInRange();

		int numberOfWeekends=(int) listOfDatesInGivenRange.stream()
				.filter(object->object.getDayOfWeek()
						.equals(DayOfWeek.valueOf("SUNDAY")))
				.count();
		return numberOfWeekends;

	}

	public boolean validateFormatOfDate(String date) 
	{

		Pattern pattern = Pattern.compile(DATE_PATERN);
		return pattern.matcher(date).matches();

	}

	public void dateValidation(String initialDate,String finalDate)
	{
		try
		{
			if(initialDate.isEmpty()||finalDate.isEmpty())
			{
				throw new UserEntryException(ExceptionType.ENTERED_EMPTY,"Please enter valid date");
			}

			else if(validateFormatOfDate(finalDate)==false || validateFormatOfDate(initialDate)==false)
			{
				throw new UserEntryException(ExceptionType.INVALID_DATE_FORMAT, "Enter valid date order");

			}
			else if(dateParser(finalDate).compareTo(dateParser(initialDate))<=0)
			{
				throw new UserEntryException(ExceptionType.INVALID_DATES_ORDER, "Enter valid date order");

			}

		}
		catch (NullPointerException e) 
		{
			throw new UserEntryException(ExceptionType.ENTERED_NULL,"Please enter valid date format");
		}

	}

}

