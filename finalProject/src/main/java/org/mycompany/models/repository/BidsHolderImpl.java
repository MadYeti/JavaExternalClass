package org.mycompany.models.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidsHolder.BidsHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Scope("prototype")
public class BidsHolderImpl implements Repository{

    private Connection connection;
    private BeanFactory beanFactory;
    private String lang;
    private static Logger logger = Logger.getLogger(BidsHolderImpl.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidsHolderImpl(Connection connection,
                          BeanFactory beanFactory){
        this.connection = connection;
        this.beanFactory = beanFactory;
    }

    @Override
    public List<Bid> getWholeBidHistory(int id) {
        Bid bid;
        BidsHolder bidsHolder = beanFactory.getBean(BidsHolder.class);
        if(lang == null){
            lang = "en_EN";
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETWHOLEBIDHISTORY.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bid = beanFactory.getBean(Bid.class);
                bid.setId(resultSet.getInt("id"));
                bid.setClientId(resultSet.getInt("client_id"));
                bid.setWeight(resultSet.getDouble("weight"));
                bid.setVolume(resultSet.getDouble("volume"));
                bid.setCargoType(resultSet.getInt("cargo_type_id"));
                bid.setCargoCost(resultSet.getDouble("cargo_cost"));
                bid.setSendingPoint(resultSet.getInt("sending_point_id"));
                bid.setDestinationPoint(resultSet.getInt("destination_point_id"));
                bid.setArrivalDate(resultSet.getString("arrival_date"));
                bid.setNotes(resultSet.getString("notes"));
                bid.setPrice(resultSet.getDouble("price"));
                bid.setBidStatus(resultSet.getInt("bid_status_id"));
                bid.setPaymentStatus(resultSet.getInt("payment_status_id"));
                getForeignKeyValues(bid,
                                    resultSet.getInt("cargo_type_id"),
                                    resultSet.getInt("sending_point_id"),
                                    resultSet.getInt("destination_point_id"),
                                    resultSet.getInt("bid_status_id"),
                                    resultSet.getInt("payment_status_id"));
                bidsHolder.addBid(bid);
            }
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
        return bidsHolder.getBidsHolder();
    }

    public void getForeignKeyValues(Bid bid,
                                    int cargoTypeId,
                                    int sendingPointId,
                                    int destinationPointId,
                                    int bidStatusId,
                                    int paymentStatusId) throws SQLException {
        try(PreparedStatement cargoTypePreparedStatement = connection.prepareStatement(SQLQuery.GETCARGOTYPEVALUE.QUERY)){
            cargoTypePreparedStatement.setInt(1, cargoTypeId);
            ResultSet cargoTypeResultSet = cargoTypePreparedStatement.executeQuery();
            if(cargoTypeResultSet.next()){
                bid.setCargoTypeValue(cargoTypeResultSet.getString("type_" + lang));
            }
        }
        try(PreparedStatement sendingPointPreparedStatement = connection.prepareStatement(SQLQuery.GETSENDINGPOINTVALUE.QUERY)){
            sendingPointPreparedStatement.setInt(1, sendingPointId);
            ResultSet sendingPointResultSet = sendingPointPreparedStatement.executeQuery();
            if(sendingPointResultSet.next()){
                bid.setSendingPointValue(sendingPointResultSet.getString("city_name_" + lang));
            }
        }
        try(PreparedStatement destinationPointPreparedStatement = connection.prepareStatement(SQLQuery.GETDESTINATIONPOINTVALUE.QUERY)){
            destinationPointPreparedStatement.setInt(1, destinationPointId);
            ResultSet destinationPointResultSet = destinationPointPreparedStatement.executeQuery();
            if(destinationPointResultSet.next()){
                bid.setDestinationPointValue(destinationPointResultSet.getString("city_name_" + lang));
            }
        }
        try(PreparedStatement bidStatusPreparedStatement = connection.prepareStatement(SQLQuery.GETBIDSTATUSVALUE.QUERY)){
            bidStatusPreparedStatement.setInt(1, bidStatusId);
            ResultSet bidStatusResultSet = bidStatusPreparedStatement.executeQuery();
            if(bidStatusResultSet.next()){
                bid.setBidStatusValue(bidStatusResultSet.getString("bid_status_" + lang));
            }
        }
        try(PreparedStatement paymentStatusPreparedStatement = connection.prepareStatement(SQLQuery.GETPAYMENTSTATUSVALUE.QUERY)){
            paymentStatusPreparedStatement.setInt(1, paymentStatusId);
            ResultSet paymentStatusResultSet = paymentStatusPreparedStatement.executeQuery();
            if(paymentStatusResultSet.next()){
                bid.setPaymentStatusValue(paymentStatusResultSet.getString("payment_status_" + lang));
            }
        }
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    enum SQLQuery{
        GETWHOLEBIDHISTORY("SELECT * FROM bids WHERE client_id = (?)"),
        GETCARGOTYPEVALUE("SELECT * FROM cargo_type WHERE id = (?)"),
        GETSENDINGPOINTVALUE("SELECT * FROM sending_points WHERE id = (?)"),
        GETDESTINATIONPOINTVALUE("SELECT * FROM destination_points WHERE id = (?)"),
        GETBIDSTATUSVALUE("SELECT * FROM bid_status WHERE id = (?)"),
        GETPAYMENTSTATUSVALUE("SELECT * FROM payment_status WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
