package org.mycompany.models.dao.bidDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAO;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAO;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAO;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidDAOMySql implements BidDAO, BidDAOHelper{

    private Connection connection;
    private static Logger logger = Logger.getLogger(BidDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public BidDAOMySql(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Bid bid) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERTBID.QUERY)){
            preparedStatement.setInt(1, bid.getClient().getId());
            preparedStatement.setDouble(2, bid.getWeight());
            preparedStatement.setDouble(3, bid.getVolume());
            preparedStatement.setInt(4, bid.getCargoType().getId());
            preparedStatement.setDouble(5, bid.getCargoCost());
            preparedStatement.setInt(6, bid.getSendingPoint().getId());
            preparedStatement.setInt(7, bid.getDestinationPoint().getId());
            preparedStatement.setString(8, bid.getArrivalDate());
            preparedStatement.setString(9, bid.getNotes());
            preparedStatement.setDouble(10, bid.getPrice());
            preparedStatement.setInt(11, bid.getBidStatus().getId());
            preparedStatement.setInt(12, bid.getPaymentStatus().getId());
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
                bid = new Bid();
                ClientDAO clientDAOMySql = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
                Client client = clientDAOMySql.read(resultSet.getInt("client_id"));
                CargoTypeDAO cargoTypeDAOMySql = new MySqlDAOFactory().createCargoTypeDAO(DBCPDataSource.getConnection());
                CargoType cargoType = cargoTypeDAOMySql.read(resultSet.getInt("cargo_type_id"));
                SendingPointDAO sendingPointDAOMySql = new MySqlDAOFactory().createSendingPointDAO(DBCPDataSource.getConnection());
                SendingPoint sendingPoint = sendingPointDAOMySql.read(resultSet.getInt("sending_point_id"));
                DestinationPointDAO destinationPointDAOMySql = new MySqlDAOFactory().createDestinationPointDAO(DBCPDataSource.getConnection());
                DestinationPoint destinationPoint = destinationPointDAOMySql.read(resultSet.getInt("destination_point_id"));
                BidStatusDAO bidStatusDAOMySql = new MySqlDAOFactory().createBidStatusDAO(DBCPDataSource.getConnection());
                BidStatus bidStatus = bidStatusDAOMySql.read(resultSet.getInt("bid_status_id"));
                PaymentStatusDAO paymentStatusDAOMySql = new MySqlDAOFactory().createPaymentStatusDAO(DBCPDataSource.getConnection());
                PaymentStatus paymentStatus = paymentStatusDAOMySql.read(resultSet.getInt("payment_status_id"));
                bid.addId(id)
                        .addClient(client)
                        .addWeight(resultSet.getDouble("weight"))
                        .addVolume(resultSet.getDouble("volume"))
                        .addCargoType(cargoType)
                        .addCargoCost(resultSet.getDouble("cargo_cost"))
                        .addSendingPoint(sendingPoint)
                        .addDestinationPoint(destinationPoint)
                        .addArrivalDate(resultSet.getString("arrival_date"))
                        .addNotes(resultSet.getString("notes"))
                        .addPrice(resultSet.getDouble("price"))
                        .addBidStatus(bidStatus)
                        .addPaymentStatus(paymentStatus)
                        .build();
            }
            resultSet.close();
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
            preparedStatement.setInt(1, bid.getClient().getId());
            preparedStatement.setDouble(2, bid.getWeight());
            preparedStatement.setDouble(3, bid.getVolume());
            preparedStatement.setInt(4, bid.getCargoType().getId());
            preparedStatement.setDouble(5, bid.getCargoCost());
            preparedStatement.setInt(6, bid.getSendingPoint().getId());
            preparedStatement.setInt(7, bid.getDestinationPoint().getId());
            preparedStatement.setString(8, bid.getArrivalDate());
            preparedStatement.setString(9, bid.getNotes());
            preparedStatement.setDouble(10, bid.getPrice());
            preparedStatement.setInt(11, bid.getBidStatus().getId());
            preparedStatement.setInt(12, bid.getPaymentStatus().getId());
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
            preparedStatement.setInt(2, bid.getClient().getId());
            preparedStatement.setDouble(3, bid.getWeight());
            preparedStatement.setDouble(4, bid.getVolume());
            preparedStatement.setInt(5, bid.getCargoType().getId());
            preparedStatement.setDouble(6, bid.getCargoCost());
            preparedStatement.setInt(7, bid.getSendingPoint().getId());
            preparedStatement.setInt(8, bid.getDestinationPoint().getId());
            preparedStatement.setString(9, bid.getArrivalDate());
            preparedStatement.setString(10, bid.getNotes());
            preparedStatement.setDouble(11, bid.getPrice());
            preparedStatement.setInt(12, bid.getBidStatus().getId());
            preparedStatement.setInt(13, bid.getPaymentStatus().getId());
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
            resultSet.close();
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTPRISE.QUERY)){
            preparedStatement.setInt(1, sendingCityId);
            preparedStatement.setInt(2, destinationCityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getDouble("price");
            }
            resultSet.close();
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTCOEFFICIENT.QUERY)){
            preparedStatement.setInt(1, cargoTypeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getDouble("coefficient");
            }
            resultSet.close();
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
        if(lang == null){
            lang = "en_EN";
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTCARGOTYPEVALUE.QUERY)){
            preparedStatement.setString(1, "type_" + lang);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("type_" + lang);
            }
            resultSet.close();
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
        if(lang == null){
            lang = "en_EN";
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTSENDINGPOINTVALUE.QUERY)){
            preparedStatement.setString(1, "city_name_" + lang);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("city_name_" + lang);
            }
            resultSet.close();
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
        if(lang == null){
            lang = "en_EN";
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTDESTINATIONPOINTVALUE.QUERY)){
            preparedStatement.setString(1, "city_name_" + lang);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("city_name_" + lang);
            }
            resultSet.close();
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
        SELECTPRISE("SELECT price FROM city_distance WHERE sending_point_id = (?) AND " +
                                    "destination_point_id = (?)"),
        SELECTCOEFFICIENT("SELECT coefficient FROM cargo_type WHERE id = (?)"),
        SELECTCARGOTYPEVALUE("SELECT (?) FROM cargo_type WHERE id = (?)"),
        SELECTSENDINGPOINTVALUE("SELECT (?) FROM sending_points WHERE id = (?)"),
        SELECTDESTINATIONPOINTVALUE("SELECT (?) FROM destination_points WHERE id = (?)"),
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
        UPDATEBIDPAYMENTSTATUS("UPDATE bids SET payment_status_id = '2'" +
                                    "WHERE id = (?)"),
        GETLASTINSERTEDID("SELECT MAX(id) FROM bids");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
