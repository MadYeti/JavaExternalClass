package org.mycompany.controllers.creditCard;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardDataController {

    private String errorCreditCardNumber;
    private String errorCreditCardExpirationDate;
    private String errorCreditCardCVVCode;
    private boolean error = false;

    public CreditCardDataController(){

    }

    public boolean validateCreditCardData(String creditCardNumber, String creditCardExpirationDate, String creditCardCVVCode){
        if(creditCardNumber.length() != 16){
            error = true;
            errorCreditCardNumber = "Wrong credit card number input";
        }else if(!validateCreditCardNumber(creditCardNumber)){
            error = true;
            errorCreditCardNumber = "Wrong credit card number input";
        }
        if(creditCardExpirationDate.length() != 5){
            error = true;
            errorCreditCardExpirationDate = "Wrong credit card expiration date input";
        }else if(!validateCreditCardExpirationDate(creditCardExpirationDate)){
            error = true;
            errorCreditCardExpirationDate = "Wrong credit card expiration date input";
        }
        if(creditCardCVVCode.length() != 3){
            error = true;
            errorCreditCardCVVCode = "Wrong credit card CVV code input";
        }else if(!validateCreditCardCVVCode(creditCardCVVCode)){
            error = true;
            errorCreditCardCVVCode = "Wrong credit card CVV code input";
        }
        return error;
    }

    public static boolean validateCreditCardNumber(String creditCardNumber) {
        String creditCardNumberRegex = "[0-9]{16}";
        Pattern pattern = Pattern.compile(creditCardNumberRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(creditCardNumber);
        return matcher.find();
    }

    public static boolean validateCreditCardExpirationDate(String creditCardExpirationDate) {
        String creditCardExpirationDateRegex = "[0-9]{2}+/+[0-9]{2}";
        Pattern pattern = Pattern.compile(creditCardExpirationDateRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(creditCardExpirationDate);
        return matcher.find();
    }

    public static boolean validateCreditCardCVVCode(String creditCardCVVCode) {
        String creditCardCVVCodeRegex = "[0-9]{3}";
        Pattern pattern = Pattern.compile(creditCardCVVCodeRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(creditCardCVVCode);
        return matcher.find();
    }

    public String getErrorCreditCardNumer() {
        return errorCreditCardNumber;
    }

    public String getErrorCreditCardExpirationDate() {
        return errorCreditCardExpirationDate;
    }

    public String getErrorCreditCardCVVCode() {
        return errorCreditCardCVVCode;
    }
}
