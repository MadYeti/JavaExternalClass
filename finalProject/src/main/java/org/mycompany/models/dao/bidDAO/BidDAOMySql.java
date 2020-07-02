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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.Query;

/**
 * Implementation of CRUD and helper interface
 */
@Component
@Scope("prototype")
public class BidDAOMySql implements BidDAO, BidDAOHelper {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(BidDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidDAOMySql(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create bid record in bids table in DB
     * @param bid object that needs to be created in DB
     */
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

    /**
     * Get bid object from bids table in DB
     * @param id number of bid that needs to be received
     * @return bid object from DB
     */
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

    /**
     * Update bid record in bids table in DB
     * @param bid object that needs to be updated
     */
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

    /**
     * Delete bid record from bids table in DB
     * @param bid objects that need to be deleted
     */
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

    /**
     * Get last inserted id (later that id will be set to bid)
     * @return last inserted id in bids table in DB
     */
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

    /**
     * Update bids payment status from '1' ('not paid') to '2' 'paid'
     * @param id number of bid which payment status needs to be updated
     */
    @Override
    public void updateBidPaymentStatus(int id){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Bid> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Bid.class);
            Root<Bid> root = criteriaUpdate.from(Bid.class);
            criteriaUpdate.set(root.get("paymentStatus").get("id"), 2);
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaUpdate);
            query.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * Get price from city_distance table in DB
     * @param sendingPoint id of sending point
     * @param destinationPoint id of destination point
     * @return price record from DB
     */
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

    /**
     * Get coefficient from cargo_type table in DB
     * @param cargoTypeId cargo type id which coefficient needs to be received
     * @return coefficient of specific cargo type
     */
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

    /**
     * Get cargo type value according to lang parameter
     * @param id cargo type id
     * @param lang language locale
     * @return cargo type value of specific lang
     */
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

    /**
     * Get sending point value according to lang parameter
     * @param id sending point id
     * @param lang language locale
     * @return sending point value of specific lang
     */
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

    /**
     * Get destination point value according to lang parameter
     * @param id destination point id
     * @param lang language locale
     * @return destination point value of specific lang
     */
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
