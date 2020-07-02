package org.mycompany.models.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidsHolder.BidsHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Repository interface. Main goal of this class
 * is to return DAO objects that works with not a single entities
 */
@Component
@Scope("prototype")
public class BidsHolderImpl implements Repository{

    private BeanFactory beanFactory;
    private String lang;
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(BidsHolderImpl.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidsHolderImpl(SessionFactory sessionFactory,
                          BeanFactory beanFactory){
        this.sessionFactory = sessionFactory;
        this.beanFactory = beanFactory;
    }

    /**
     * Gets all ordered bids of specific client
     * @param id the number of client which ordered bid list
     *           needs to be received
     * @return the list of ordered bids of specific client
     */
    @Override
    public List<Bid> getWholeBidHistory(int id) {
        BidsHolder bidsHolder = beanFactory.getBean(BidsHolder.class);
        if(lang == null){
            lang = "en_EN";
        }
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<Bid> root = criteriaQuery.from(Bid.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("client").get("id"), id));
            Query query = session.createQuery(criteriaQuery);
            List<Bid> bidList = query.getResultList();
            for(Bid bids: bidList) {
                Hibernate.initialize(bids.getClient());
                Hibernate.initialize(bids.getCargoType());
                Hibernate.initialize(bids.getSendingPoint());
                Hibernate.initialize(bids.getDestinationPoint());
                Hibernate.initialize(bids.getBidStatus());
                Hibernate.initialize(bids.getPaymentStatus());
            }
            Collections.reverse(bidList);
            bidsHolder.setBidsHolder(bidList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return bidsHolder.getBidsHolder();
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
