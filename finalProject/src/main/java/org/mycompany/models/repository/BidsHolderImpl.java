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
import java.util.List;

@Component
@Scope("prototype")
public class BidsHolderImpl implements Repository{

    private String lang;
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(BidsHolderImpl.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidsHolderImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Bid> getWholeBidHistory(int id) {
        BidsHolder bidsHolder = new BidsHolder();//beanFactory.getBean(BidsHolder.class);
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
