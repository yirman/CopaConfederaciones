package com.example.german.copaconfederaciones.utils;

import android.content.Context;

import com.example.german.copaconfederaciones.R;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by German on 9/6/2018.
 */

public class Utilities {

    public static int getTranslationID(String value, Context context) {

        try {

            int id =  context.getResources().getIdentifier(value, "string", context.getPackageName());

            if(id != 0)
                return id;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.string.not_found;
    }

    public static String toLocalDateTime(String date){

        try{

            DateTime dateTime = new DateTime(date);
            LocalDateTime localTime = dateTime.toLocalDateTime();
            return  localTime.toString();

        }catch (Exception e){
            return date;
        }
    }

    public static String getRegularTime(String date){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate;

        date = date.replace('T',' ');

        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }

        dateFormat = new SimpleDateFormat("h:mma");

        String formatedTime = dateFormat.format(parsedDate).toLowerCase();
        String time = formatedTime.substring(0, formatedTime.length() - 2);
        String period = " " + formatedTime.substring(formatedTime.length() - 2).toUpperCase();

        return  time + period;
    }

    public static String getRegularDate(String date){

        DateTime dateTime = new DateTime(date);

        String day = String.valueOf(dateTime.getDayOfMonth());
        String month = String.valueOf(dateTime.getMonthOfYear());
        String year = String.valueOf(dateTime.getYear());

        return day + "-" + month + "-" + year;

    }
}
