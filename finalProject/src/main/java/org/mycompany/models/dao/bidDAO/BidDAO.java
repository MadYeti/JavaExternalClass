package org.mycompany.models.dao.bidDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.cityDistance.CityDistance;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.sendingPoint.SendingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.*;

@Component
@Scope("prototype")
public class BidDAO implements DAO, DAOHelper{

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(BidDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Bid bid) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bid);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Bid read(int id) {
        Bid bid = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bid = session.get(Bid.class, id);
            if(bid != null) {
                Hibernate.initialize(bid.getClient());
                Hibernate.initialize(bid.getCargoType());
                Hibernate.initialize(bid.getSendingPoint());
                Hibernate.initialize(bid.getDestinationPoint());
                Hibernate.initialize(bid.getBidStatus());
                Hibernate.initialize(bid.getPaymentStatus());
            }
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return bid;
    }

    @Override
    public void update(Bid bid) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(bid);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(Bid bid) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(bid);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public int getLastInsertedId(){
        int result = 0;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<Bid> root = criteriaQuery.from(Bid.class);
            criteriaQuery.select(criteriaBuilder.max(root.get("id")));
            Query query = session.createQuery(criteriaQuery);
            result = (Integer)query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void updateBidPaymentStatus(int id){
        try(Session session = sessionFactory.openSession()){
            /*
            session.beginTransaction();
            CriteriaBuilderImpl criteriaBuilderImpl = new CriteriaBuilderImpl();
            CriteriaUpdate<Bid> criteriaUpdate = criteriaBuilderImpl.createCriteriaUpdate(Bid.class);
            Root<Bid> root = criteriaUpdate.from(Bid.class);
            criteriaUpdate.set(root.get("payment_status_id"), 2);
            criteriaUpdate.where(criteriaBuilderImpl.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaUpdate);
            query.executeUpdate();
            session.getTransaction().commit();*/
        }
    }

    @Override
    public double getPriceAccordingToCityDistance(int sendingPoint, int destinationPoint) {
        double result = 0;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<CityDistance> root = criteriaQuery.from(CityDistance.class);
            Predicate predicateForSendingPoint = criteriaBuilder.equal(root.get("sendingPointId"), sendingPoint);
            Predicate predicateForDestinationPoint = criteriaBuilder.equal(root.get("destinationPointId"), destinationPoint);
            Predicate finalPredicate = criteriaBuilder.and(predicateForSendingPoint, predicateForDestinationPoint);
            criteriaQuery.select(root.get("price"));
            criteriaQuery.where(finalPredicate);
            Query query = session.createQuery(criteriaQuery);
            result = (Double)query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public double getCargoTypeCoefficient(int cargoTypeId) {
        double result = 0;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<CargoType> root = criteriaQuery.from(CargoType.class);
            criteriaQuery.select(root.get("coefficient"));
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), cargoTypeId));
            Query query = session.createQuery(criteriaQuery);
            result = (Double)query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String getCargoTypeValue(int id, String lang) {
        String result = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<CargoType> root = criteriaQuery.from(CargoType.class);
            if(lang == null){
                criteriaQuery.select(root.get("cargoTypeEN"));
            }else{
                String[] langArray = lang.split("_");
                criteriaQuery.select(root.get("cargoType" + langArray[1]));
            }
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaQuery);
            result = (String)query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String getSendingPointValue(int id, String lang) {
        String result = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<SendingPoint> root = criteriaQuery.from(SendingPoint.class);
            if(lang == null){
                criteriaQuery.select(root.get("sendingPointEN"));
            }else{
                String[] langArray = lang.split("_");
                criteriaQuery.select(root.get("sendingPoint" + langArray[1]));
            }
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaQuery);
            result = (String)query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String getDestinationPointValue(int id, String lang) {
        String result = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<DestinationPoint> root = criteriaQuery.from(DestinationPoint.class);
            if(lang == null){
                criteriaQuery.select(root.get("destinationPointEN"));
            }else{
                String[] langArray = lang.split("_");
                criteriaQuery.select(root.get("destinationPoint" + langArray[1]));
            }
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaQuery);
            result = (String)query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

}
