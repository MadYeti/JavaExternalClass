package org.mycompany.models.dao.paymentStatusDAO;

import org.mycompany.models.paymentStatus.PaymentStatus;

/**
 * Basic interface that contains method to get payment status object
 */
public interface PaymentStatusDAO {

    PaymentStatus read(int id);

}
