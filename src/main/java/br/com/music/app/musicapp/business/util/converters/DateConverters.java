package br.com.music.app.musicapp.business.util.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
public class DateConverters {
    public Date convertToDate(String date){
        int year = Integer.parseInt(date.substring(6,10));
        int mounth = Integer.parseInt(date.substring(3,5));
        int day = Integer.parseInt(date.substring(0,2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,mounth,day);
        return new Date(calendar.getTime().getTime());
    }

    public String convertToFormat(Date date){
        var formated = new SimpleDateFormat("dd/MM/yyyy");
        var formatConvert = formated.format(date);
        return formatConvert;
    }
}
