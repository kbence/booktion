package com.booktion.client.gui;

import com.michaelbaranov.microba.calendar.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JDateTimePicker extends JPanel
{
    private DatePicker datePicker;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;

    public JDateTimePicker(Date date)
    {
        datePicker = new DatePicker();
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            datePicker.setDate(cal.getTime());
            hourSpinner.setValue(cal.get(Calendar.HOUR_OF_DAY));
            minuteSpinner.setValue(cal.get(Calendar.MINUTE));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(datePicker);
        add(hourSpinner);
        add(new JLabel(":"));
        add(minuteSpinner);
    }

    public Date getDateTime()
    {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(datePicker.getDate());
        cal.set(Calendar.HOUR_OF_DAY, (Integer)hourSpinner.getValue());
        cal.set(Calendar.MINUTE, (Integer)minuteSpinner.getValue());
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }
}
