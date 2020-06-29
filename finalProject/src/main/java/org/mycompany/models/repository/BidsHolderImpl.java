package org.mycompany.models.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.bidsHolder.BidsHolder;
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
import java.util.List;

/**
 * Implementation of Repository interface. Main goal of this class
 * is to return DAO objects that works with not a single entities
 */
public class BidsHolderImpl implements Repository{

    private Connection connection;
    private static Logger logger = Logger.getLogger(BidsHolderImpl.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public BidsHolderImpl(Connection connection){
        this.connection = connection;
    }

    /**
     * Gets all ordered bids of specific client
     * @param id the number of client which ordered bid list
     *           needs to be received
     * @return the list of ordered bids of specific client
     */
    @Override
    public List<Bid> getWholeBidHistory(int id) {
        Bid bid;
        BidsHolder bidsHolder = new BidsHolder();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GETWHOLEBIDHISTORY.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
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
                bid.setId(resultSet.getInt("id"));
                bid.setClient(client);
                bid.setWeight(resultSet.getDouble("weight"));
                bid.setVolume(resultSet.getDouble("volume"));
                bid.setCargoType(cargoType);
                bid.setCargoCost(resultSet.getDouble("cargo_cost"));
                bid.setSendingPoint(sendingPoint);
                bid.setDestinationPoint(destinationPoint);
                bid.setArrivalDate(resultSet.getString("arrival_date"));
                bid.setNotes(resultSet.getString("notes"));
                bid.setPrice(resultSet.getDouble("price"));
                bid.setBidStatus(bidStatus);
                bid.setPaymentStatus(paymentStatus);
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

    /**
     * Enum class that encapsulate SQL queries
     */
    enum SQLQuery{
        GETWHOLEBIDHISTORY("SELECT * FROM bids WHERE client_id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
