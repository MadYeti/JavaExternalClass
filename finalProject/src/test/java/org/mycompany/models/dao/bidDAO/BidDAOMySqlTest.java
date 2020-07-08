package org.mycompany.models.dao.bidDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;

import java.sql.SQLException;

public class BidDAOMySqlTest {

    private BidDAO bidDAOMySql;
    private Bid bid;

    @Before
    public void before(){
        bidDAOMySql = new BidDAOMySql(DBCPDataSource.getConnection());
        bid = new Bid();
        Client client = new Client();
        CargoType cargoType = new CargoType();
        SendingPoint sendingPoint = new SendingPoint();
        DestinationPoint destinationPoint = new DestinationPoint();
        BidStatus bidStatus = new BidStatus();
        PaymentStatus paymentStatus = new PaymentStatus();
        client.setId(1);
        client.setEmail("ivancov13@bigmir.net");
        client.setPassword("-5540a5d6a714897ee70ccea486193fbb");
        cargoType.setId(1);
        sendingPoint.setId(1);
        destinationPoint.setId(12);
        bidStatus.setId(1);
        paymentStatus.setId(2);
        bid.addClient(client)
                .addBidStatus(bidStatus)
                .addCargoType(cargoType)
                .addDestinationPoint(destinationPoint)
                .addSendingPoint(sendingPoint)
                .addArrivalDate("26-06-2020")
                .addPaymentStatus(paymentStatus)
                .addNotes("Khreschatyk street 1")
                .build();
        bid.setId(58);
    }

    @After
    public void after(){
        try {
            DBCPDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createBid(){
        bidDAOMySql.create(bid);
        Bid result = new BidDAOMySql(DBCPDataSource.getConnection()).read(bid.getId());
        Assert.assertEquals(bid.getClient().getEmail(), result.getClient().getEmail());
        Assert.assertEquals(bid.getClient().getPassword(), result.getClient().getPassword());
    }

    @Test
    public void readBid(){
        Assert.assertNotNull(bidDAOMySql.read(53));
    }

    @Test
    public void updateBid(){
        bidDAOMySql.create(bid);
        bid.setWeight(192.80);
        new BidDAOMySql(DBCPDataSource.getConnection()).update(bid);
        Bid result = new BidDAOMySql(DBCPDataSource.getConnection()).read(bid.getId());
        Assert.assertTrue(result.getWeight() == 192.80);
    }

    @Test
    public void deleteBid(){
        bidDAOMySql.create(bid);
        Bid before = new BidDAOMySql(DBCPDataSource.getConnection()).read(bid.getId());
        new BidDAOMySql(DBCPDataSource.getConnection()).delete(bid);
        Bid after = new BidDAOMySql(DBCPDataSource.getConnection()).read(bid.getId());
        Assert.assertNotNull(before);
        Assert.assertNull(after);
    }

}
