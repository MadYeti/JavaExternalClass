package org.mycompany.controllers.dateProccesing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Controller used for creating string representation of date according
 * to distance between cities
 */
public class DateController {

    public DateController(){

    }

    /**
     * Create string representation of date according to distance between cities
     * @param distance distance between cities, gets from DB
     * @return string representation of date in format "dd-MM-yyyy"
     */
    public String getArrivalDate(int distance){
        Calendar calendar = Calendar.getInstance();
        int arrivalDays = distance / 100;
        calendar.add(Calendar.DAY_OF_MONTH, arrivalDays);
        Date date = calendar.getTime();
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

}
