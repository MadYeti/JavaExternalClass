package org.mycompany.controllers.dateProccesing;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateController {

    public DateController(){

    }

    public static String getArrivalDate(int distance){
        Calendar calendar = Calendar.getInstance();
        int arrivalDays = distance / 100;
        calendar.add(Calendar.DAY_OF_MONTH, arrivalDays);
        Date date = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
