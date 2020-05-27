package org.mycompany.models.factory;

import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.controllers.registration.RegistrationDataController;

public interface ControllerFactory {

    AuthorizationDataController getAuthorizationDataController();
    CreditCardDataController getCreditCardController();
    DateController getDateController();
    MailController getMailController(String emailTo, String token);
    RegistrationDataController getRegistrationDataController();

}
