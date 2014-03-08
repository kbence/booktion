package com.booktion.log;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil
{
    public String getTimestamp() {
        Calendar calendar = new GregorianCalendar();

        return String.format(
                "%04d-%02d-%02d %02d:%02d:%02d",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
    }
}
