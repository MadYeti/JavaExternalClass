package org.mycompany.controllers.creditCard;

import org.junit.Assert;
import org.junit.Test;

public class CreditCardDataControllerTest {

    @Test
    public void successValidationCreditCardData(){
        CreditCardDataController creditCardDataController = new CreditCardDataController();
        boolean result = creditCardDataController.validateCreditCardData("1234567890123456", "11/22", "583");
        Assert.assertFalse(result);
    }

    @Test
    public void failedValidationCreditCardDataWrongCreditCardNumber(){
        CreditCardDataController creditCardDataController = new CreditCardDataController();
        boolean result = creditCardDataController.validateCreditCardData("123dsf678901234%^", "10/22", "112");
        Assert.assertTrue(result);
    }

    @Test
    public void failedValidationCreditCardDataWrongCreditCardExpirationDate(){
        CreditCardDataController creditCardDataController = new CreditCardDataController();
        boolean result = creditCardDataController.validateCreditCardData("6473509926481327", "16.05.22", "848");
        Assert.assertTrue(result);
    }

    @Test
    public void failedValidationCreditCardDataWrongCreditCardCVVCode(){
        CreditCardDataController creditCardDataController = new CreditCardDataController();
        boolean result = creditCardDataController.validateCreditCardData("6789012345612345", "04/25", "er4");
        Assert.assertTrue(result);
    }

    @Test
    public void successValidationCreditCardNumber(){
        boolean result = CreditCardDataController.validateCreditCardNumber("9012313424556768");
        Assert.assertTrue(result);
    }

    @Test
    public void failedValidationCreditCardNumber(){
        boolean result = CreditCardDataController.validateCreditCardNumber("11ddd4xcv78xvxz7");
        Assert.assertFalse(result);
    }

    @Test
    public void successValidationCreditCardExpirationDate(){
        boolean result = CreditCardDataController.validateCreditCardExpirationDate("06/23");
        Assert.assertTrue(result);
    }

    @Test
    public void failedValidationCreditCardExpirationDate(){
        boolean result = CreditCardDataController.validateCreditCardExpirationDate("12/qwe");
        Assert.assertFalse(result);
    }

    @Test
    public void successValidationCreditCardCVVCode(){
        boolean result = CreditCardDataController.validateCreditCardCVVCode("062");
        Assert.assertTrue(result);
    }

    @Test
    public void failedValidationCreditCardCVVCode(){
        boolean result = CreditCardDataController.validateCreditCardCVVCode("0a1");
        Assert.assertFalse(result);
    }

}
