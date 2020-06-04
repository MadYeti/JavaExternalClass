package org.mycompany.models.dao.bidStatusDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.bidStatus.BidStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BidStatusDAOMySql implements BidStatusDAO {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(BidStatusDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidStatusDAOMySql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BidStatus read(int id) {
        BidStatus bidStatus = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bidStatus = session.get(BidStatus.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return bidStatus;
    }
}
