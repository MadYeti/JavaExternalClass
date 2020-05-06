package org.mycompany.models.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidsHolder.BidsHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BidsHolderImpl implements Repository{

    private Connection connection;
    private static Logger logger = Logger.getLogger(BidsHolderImpl.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public BidsHolderImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Bid> getWholeBidHistory(int id) {
        Bid bid;
        BidsHolder bidsHolder = new BidsHolder();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETWHOLEBIDHISTORY.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bid = new Bid();
                bid.setId(resultSet.getInt("id"));
                bid.setClientId(resultSet.getInt("client_id"));
                bid.setWeight(resultSet.getDouble("weight"));
                bid.setVolume(resultSet.getDouble("volume"));
                bid.setCargoType(resultSet.getString("cargo_type"));
                bid.setCargoCost(resultSet.getDouble("cargo_cost"));
                bid.setSendingPoint(resultSet.getString("sending_point"));
                bid.setDestinationPoint(resultSet.getString("destination_point"));
                bid.setArrivalDate(resultSet.getString("arrival_date"));
                bid.setNotes(resultSet.getString("notes"));
                bid.setPrice(resultSet.getDouble("price"));
                bid.setBidStatus(resultSet.getString("bid_status"));
                bid.setPaymentStatus(resultSet.getString("payment_status"));
                bidsHolder.addBid(bid);
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
        return bidsHolder.getBidsHolder();
    }

    enum SQLQuery{
        GETWHOLEBIDHISTORY("SELECT * FROM bids WHERE client_id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
