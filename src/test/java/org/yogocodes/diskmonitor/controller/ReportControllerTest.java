package org.yogocodes.diskmonitor.controller;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.yogocodes.diskmonitor.Constants;

public class ReportControllerTest {

	ReportController reportController = new ReportController(); 
	
	@Test
	public void testParseDateYear() {
		Date parseDate = reportController.parseDate("2014", Constants.YEAR_FORMAT); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(parseDate);
		Assert.assertEquals("", 2014, cal.get(Calendar.YEAR));
	}
	
	@Test
	public void testParseDateMonth() {
		Date parseDate = reportController.parseDate("2014-01", Constants.MONTH_FORMAT); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(parseDate);
		Assert.assertEquals("", 2014, cal.get(Calendar.YEAR));
		Assert.assertEquals("", 1, cal.get(Calendar.MONTH) +1);
	}

	@Test
	public void testParseDateDay() {
		Date parseDate = reportController.parseDate("2014-01-01", Constants.DAY_FORMAT); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(parseDate);
		Assert.assertEquals("", 2014, cal.get(Calendar.YEAR));
		Assert.assertEquals("", 1, cal.get(Calendar.MONTH) +1);
		Assert.assertEquals("", 1, cal.get(Calendar.DAY_OF_MONTH));
	}
	
	
	
	@Test
	public void testParseDateHour() {
		Date parseDate = reportController.parseDate("2014-01-01-12", Constants.HOUR_FORMAT); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(parseDate);
		Assert.assertEquals("", 2014, cal.get(Calendar.YEAR));
		Assert.assertEquals("", 1, cal.get(Calendar.MONTH) +1);
		Assert.assertEquals("", 1, cal.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals("", 12, cal.get(Calendar.HOUR_OF_DAY));
	}
	
}
