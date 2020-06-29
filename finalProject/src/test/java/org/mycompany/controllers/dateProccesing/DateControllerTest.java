package org.mycompany.controllers.dateProccesing;

import org.junit.Assert;
import org.junit.Test;

public class DateControllerTest {

    @Test
    public void failedGettingArrivalDate(){
        DateController dateController = new DateController();
        String result = dateController.getArrivalDate(462);
        Assert.assertNotEquals("15-12-2020", result);
    }

}
