package org.mycompany.models.dao.bidDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.bid.Bid;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("prototype")
public class BidDAO implements DAO, DAOHelper{

    private Connection connection;
    private BeanFactory beanFactory;
    private static Logger logger = Logger.getLogger(BidDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidDAO(Connection connection,
                  BeanFactory beanFactory){
        this.connection = connection;
        this.beanFactory = beanFactory;
    }

    @Override
    public void create(Bid bid) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERTBID.QUERY)){
            preparedStatement.setInt(1, bid.getClientId());
            preparedStatement.setDouble(2, bid.getWeight());
            preparedStatement.setDouble(3, bid.getVolume());
            preparedStatement.setInt(4, bid.getCargoType());
            preparedStatement.setDouble(5, bid.getCargoCost());
            preparedStatement.setInt(6, bid.getSendingPoint());
            preparedStatement.setInt(7, bid.getDestinationPoint());
            preparedStatement.setString(8, bid.getArrivalDate());
            preparedStatement.setString(9, bid.getNotes());
            preparedStatement.setDouble(10, bid.getPrice());
            preparedStatement.setInt(11, bid.getBidStatus());
            preparedStatement.setInt(12, bid.getPaymentStatus());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public Bid read(int id) {
        Bid bid = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTBID.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                bid = beanFactory.getBean(Bid.class);
                bid.addId(id)
                        .addClientId(resultSet.getInt("client_id"))
                        .addWeight(resultSet.getDouble("weight"))
                        .addVolume(resultSet.getDouble("volume"))
                        .addCargoType(resultSet.getInt("cargo_type_id"))
                        .addCargoCost(resultSet.getDouble("cargo_cost"))
                        .addSendingPoint(resultSet.getInt("sending_point_id"))
                        .addDestinationPoint(resultSet.getInt("destination_point_id"))
                        .addArrivalDate(resultSet.getString("arrival_date"))
                        .addNotes(resultSet.getString("notes"))
                        .addPrice(resultSet.getDouble("price"))
                        .addBidStatus(resultSet.getInt("bid_status_id"))
                        .addPaymentStatus(resultSet.getInt("payment_status_id"))
                        .build();
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return bid;
    }

    @Override
    public void update(Bid bid) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.UPDATEBID.QUERY)){
            preparedStatement.setInt(1, bid.getClientId());
            preparedStatement.setDouble(2, bid.getWeight());
            preparedStatement.setDouble(3, bid.getVolume());
            preparedStatement.setInt(4, bid.getCargoType());
            preparedStatement.setDouble(5, bid.getCargoCost());
            preparedStatement.setInt(6, bid.getSendingPoint());
            preparedStatement.setInt(7, bid.getDestinationPoint());
            preparedStatement.setString(8, bid.getArrivalDate());
            preparedStatement.setString(9, bid.getNotes());
            preparedStatement.setDouble(10, bid.getPrice());
            preparedStatement.setInt(11, bid.getBidStatus());
            preparedStatement.setInt(12, bid.getPaymentStatus());
            preparedStatement.setInt(13, bid.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void delete(Bid bid) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.DELETEBID.QUERY)){
            preparedStatement.setInt(1, bid.getId());
            preparedStatement.setInt(2, bid.getClientId());
            preparedStatement.setDouble(3, bid.getWeight());
            preparedStatement.setDouble(4, bid.getVolume());
            preparedStatement.setInt(5, bid.getCargoType());
            preparedStatement.setDouble(6, bid.getCargoCost());
            preparedStatement.setInt(7, bid.getSendingPoint());
            preparedStatement.setInt(8, bid.getDestinationPoint());
            preparedStatement.setString(9, bid.getArrivalDate());
            preparedStatement.setString(10, bid.getNotes());
            preparedStatement.setDouble(11, bid.getPrice());
            preparedStatement.setInt(12, bid.getBidStatus());
            preparedStatement.setInt(13, bid.getPaymentStatus());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public int getLastInsertedId(){
        int result = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETLASTINSERTEDID.QUERY)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt("MAX(id)");
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public void updateBidPaymentStatus(int id){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.UPDATEBIDPAYMENTSTATUS.QUERY)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public double getPriceAccordingToCityDistance(int sendingCityId, int destinationCityId) {
        double result = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETPRICE.QUERY)){
            preparedStatement.setInt(1, sendingCityId);
            preparedStatement.setInt(2, destinationCityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getDouble("price");
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public double getCargoTypeCoefficient(int cargoTypeId) {
        double result = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETCOEFFICIENT.QUERY)){
            preparedStatement.setInt(1, cargoTypeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getDouble("coefficient");
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public String getCargoTypeValue(int id, String lang) {
        String result = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETCARGOTYPEVALUE.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("type_" + lang);
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public String getSendingPointValue(int id, String lang) {
        String result = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETSENDINGPOINTVALUE.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("city_name_" + lang);
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public String getDestinationPointValue(int id, String lang) {
        String result = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETDESTINATIONPOINTVALUE.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("city_name_" + lang);
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    enum SQLQuery{
        INSERTBID("INSERT INTO bids (client_id, " +
                                    "weight, " +
                                    "volume, " +
                                    "cargo_type_id, " +
                                    "cargo_cost, " +
                                    "sending_point_id," +
                                    "destination_point_id," +
                                    "arrival_date," +
                                    "notes," +
                                    "price," +
                                    "bid_status_id," +
                                    "payment_status_id) " +
                                    "VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?))"),
        SELECTBID("SELECT * FROM bids WHERE id = (?)"),
        UPDATEBID("UPDATE bids SET client_id = (?)," +
                                    "weight = (?)," +
                                    "volume = (?)," +
                                    "cargo_type_id = (?)," +
                                    "cargo_cost = (?)," +
                                    "sending_point_id = (?)," +
                                    "destination_point_id = (?)," +
                                    "arrival_date = (?)," +
                                    "notes = (?)," +
                                    "price = (?)," +
                                    "bid_status_id = (?)," +
                                    "payment_status_id = (?)" +
                                    "WHERE id = (?)"),
        DELETEBID("DELETE FROM bids WHERE id = (?) AND " +
                                    "client_id = (?) AND " +
                                    "weight = (?) AND " +
                                    "volume = (?) AND " +
                                    "cargo_type_id = (?) AND " +
                                    "cargo_cost = (?) AND " +
                                    "sending_point_id = (?) AND " +
                                    "destination_point_id = (?) AND " +
                                    "arrival_date = (?) AND " +
                                    "notes = (?) AND " +
                                    "price = (?) AND " +
                                    "bid_status_id = (?) AND " +
                                    "payment_status_id = (?)"),
        UPDATEBIDPAYMENTSTATUS("UPDATE bids SET payment_status_id = '2' WHERE id = (?)"),
        GETLASTINSERTEDID("SELECT MAX(id) FROM bids"),
        GETPRICE("SELECT price FROM city_distance WHERE sending_point_id = (?) AND destination_point_id = (?)"),
        GETCOEFFICIENT("SELECT coefficient FROM cargo_type WHERE id = (?)"),
        GETCARGOTYPEVALUE("SELECT * FROM cargo_type WHERE id = (?)"),
        GETSENDINGPOINTVALUE("SELECT * FROM sending_points WHERE id = (?)"),
        GETDESTINATIONPOINTVALUE("SELECT * FROM destination_points WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
