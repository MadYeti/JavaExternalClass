package org.mycompany.models.dao.paymentStatusDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Implementation of PaymentStatusDAO interface
 */
@Component
@Scope("prototype")
public class PaymentStatusDAOMySql implements PaymentStatusDAO {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(PaymentStatusDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public PaymentStatusDAOMySql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get payment status object from payment_status table in DB
     * @param id payment status id that needs to be received
     * @return payment status objects from DB
     */
    @Override
    public PaymentStatus read(int id) {
        PaymentStatus paymentStatus = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            paymentStatus = session.get(PaymentStatus.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return paymentStatus;
    }
}
