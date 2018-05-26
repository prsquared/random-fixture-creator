package com.prsquared.fixtures.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	private SimpleDateFormat dateFormat;
	private String dateStr;
	private Integer offset; 
	
	public DateUtil(SimpleDateFormat dateFormat, String dateStr, Integer offset) {
		this.dateFormat = dateFormat;
		this.dateStr = dateStr;
		if(offset !=null)
			this.offset = offset;
		else
			this.offset = 7;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public String getDateStr() {
		return dateStr;
	}

	public Integer getOffset() {
		return offset;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getFixtureDate(int round) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(dateStr));
		c.add(Calendar.DATE, round * offset); // Adding offset
		return dateFormat.format(c.getTime());
	}
}
