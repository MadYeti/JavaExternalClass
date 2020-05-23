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
public class BidDAO implements DAO{

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
            preparedStatement.setString(4, bid.getCargoType());
            preparedStatement.setDouble(5, bid.getCargoCost());
            preparedStatement.setString(6, bid.getSendingPoint());
            preparedStatement.setString(7, bid.getDestinationPoint());
            preparedStatement.setString(8, bid.getArrivalDate());
            preparedStatement.setString(9, bid.getNotes());
            preparedStatement.setDouble(10, bid.getPrice());
            preparedStatement.setString(11, bid.getBidStatus());
            preparedStatement.setString(12, bid.getPaymentStatus());
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
                        .addCargoType(resultSet.getString("cargo_type"))
                        .addCargoCost(resultSet.getDouble("cargo_cost"))
                        .addSendingPoint(resultSet.getString("sending_point"))
                        .addDestinationPoint(resultSet.getString("destination_point"))
                        .addArrivalDate(resultSet.getString("arrival_date"))
                        .addNotes(resultSet.getString("notes"))
                        .addPrice(resultSet.getDouble("price"))
                        .addBidStatus(resultSet.getString("bid_status"))
                        .addPaymentStatus(resultSet.getString("payment_status"))
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
            preparedStatement.setString(4, bid.getCargoType());
            preparedStatement.setDouble(5, bid.getCargoCost());
            preparedStatement.setString(6, bid.getSendingPoint());
            preparedStatement.setString(7, bid.getDestinationPoint());
            preparedStatement.setString(8, bid.getArrivalDate());
            preparedStatement.setString(9, bid.getNotes());
            preparedStatement.setDouble(10, bid.getPrice());
            preparedStatement.setString(11, bid.getBidStatus());
            preparedStatement.setString(12, bid.getPaymentStatus());
            preparedStatement.setInt(13, bid.getId());
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
    public void delete(Bid bid) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.DELETEBID.QUERY)){
            preparedStatement.setInt(1, bid.getId());
            preparedStatement.setInt(2, bid.getClientId());
            preparedStatement.setDouble(3, bid.getWeight());
            preparedStatement.setDouble(4, bid.getVolume());
            preparedStatement.setString(5, bid.getCargoType());
            preparedStatement.setDouble(6, bid.getCargoCost());
            preparedStatement.setString(7, bid.getSendingPoint());
            preparedStatement.setString(8, bid.getDestinationPoint());
            preparedStatement.setString(9, bid.getArrivalDate());
            preparedStatement.setString(10, bid.getNotes());
            preparedStatement.setDouble(11, bid.getPrice());
            preparedStatement.setString(12, bid.getBidStatus());
            preparedStatement.setString(13, bid.getPaymentStatus());
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

    enum SQLQuery{
        INSERTBID("INSERT INTO bids (client_id, " +
                                    "weight, " +
                                    "volume, " +
                                    "cargo_type, " +
                                    "cargo_cost, " +
                                    "sending_point," +
                                    "destination_point," +
                                    "arrival_date," +
                                    "notes," +
                                    "price," +
                                    "bid_status," +
                                    "payment_status) " +
                                    "VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?))"),
        SELECTBID("SELECT * FROM bids WHERE id = (?)"),
        UPDATEBID("UPDATE bids SET client_id = (?)," +
                                    "weight = (?)," +
                                    "volume = (?)," +
                                    "cargo_type = (?)," +
                                    "cargo_cost = (?)," +
                                    "sending_point = (?)," +
                                    "destination_point = (?)," +
                                    "arrival_date = (?)," +
                                    "notes = (?)," +
                                    "price = (?)," +
                                    "bid_status = (?)," +
                                    "payment_status = (?)" +
                                    "WHERE id = (?)"),
        DELETEBID("DELETE FROM bids WHERE id = (?) AND " +
                                    "client_id = (?) AND " +
                                    "weight = (?) AND " +
                                    "volume = (?) AND " +
                                    "cargo_type = (?) AND " +
                                    "cargo_cost = (?) AND " +
                                    "sending_point = (?) AND " +
                                    "destination_point = (?) AND " +
                                    "arrival_date = (?) AND " +
                                    "notes = (?) AND " +
                                    "price = (?) AND " +
                                    "bid_status = (?) AND " +
                                    "payment_status = (?)"),
        UPDATEBIDPAYMENTSTATUS("UPDATE bids SET payment_status = 'paid'" +
                                    "WHERE id = (?)"),
        GETLASTINSERTEDID("SELECT MAX(id) FROM bids");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
