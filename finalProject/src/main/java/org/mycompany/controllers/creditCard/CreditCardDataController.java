package org.mycompany.controllers.creditCard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller used for credit card input data validation
 */
public class CreditCardDataController {

    private String errorCreditCardNumber;
    private String errorCreditCardExpirationDate;
    private String errorCreditCardCVVCode;
    private boolean error = false;

    public CreditCardDataController(){

    }

    /**
     * Validate credit card input data
     * @param creditCardNumber credit card number
     * @param creditCardExpirationDate credit card expiration date
     * @param creditCardCVVCode credit card CVV code
     * @return true if data is correct, false otherwise
     */
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

    /**
     * Checks if credit card number matches with regex
     * @param creditCardNumber credit card number
     * @return true if credit card matches with regex false, otherwise
     */
    public static boolean validateCreditCardNumber(String creditCardNumber) {
        String creditCardNumberRegex = "[0-9]{16}";
        Pattern pattern = Pattern.compile(creditCardNumberRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(creditCardNumber);
        return matcher.find();
    }

    /**
     * Checks if credit card expiration date matches with regex
     * @param creditCardExpirationDate credit card expiration date
     * @return true if credit card expiration date matches with regex, false otherwise
     */
    public static boolean validateCreditCardExpirationDate(String creditCardExpirationDate) {
        String creditCardExpirationDateRegex = "[0-9]{2}+/+[0-9]{2}";
        String[] expirationDateParams = creditCardExpirationDate.split("/");
        try {
            if (Integer.parseInt(expirationDateParams[0]) < 1 ||
                    Integer.parseInt(expirationDateParams[0]) > 12 ||
                    Integer.parseInt(expirationDateParams[1]) < 20){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        Pattern pattern = Pattern.compile(creditCardExpirationDateRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(creditCardExpirationDate);
        return matcher.find();
    }

    /**
     * Checks if credit card CVV code matches with regex
     * @param creditCardCVVCode credit card CVV code
     * @return true if credit card CVV code matches with regex, false otherwise
     */
    public static boolean validateCreditCardCVVCode(String creditCardCVVCode) {
        String creditCardCVVCodeRegex = "[0-9]{3}";
        Pattern pattern = Pattern.compile(creditCardCVVCodeRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(creditCardCVVCode);
        return matcher.find();
    }

    public String getErrorCreditCardNumber() {
        return errorCreditCardNumber;
    }

    public String getErrorCreditCardExpirationDate() {
        return errorCreditCardExpirationDate;
    }

    public String getErrorCreditCardCVVCode() {
        return errorCreditCardCVVCode;
    }
}
