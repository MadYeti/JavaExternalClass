package org.mycompany.models.dao.bidDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;

public class BidDAOMySqlTest {

    private SessionFactory sessionFactory;
    private BidDAO bidDAOMySql;
    private Bid bid;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        bidDAOMySql = new BidDAOMySql(sessionFactory);
        bid = new Bid();
        Client client = new Client();
        BidStatus bidStatus = new BidStatus();
        CargoType cargoType = new CargoType();
        DestinationPoint destinationPoint = new DestinationPoint();
        SendingPoint sendingPoint = new SendingPoint();
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
        bid.setId(53);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    /**
     * Delete bid #53 before running test
     * SqlExceptionHelper:142 - Duplicate entry '53' for key 'PRIMARY'
     * BidDAOMySql:54 - org.hibernate.exception.ConstraintViolationException: could not execute statement
     */
    @Test
    public void createBid(){
        bidDAOMySql.create(bid);
        Bid result = bidDAOMySql.read(bid.getId());
        Assert.assertEquals(bid.getClient().getEmail(), result.getClient().getEmail());
        Assert.assertEquals(bid.getClient().getPassword(), result.getClient().getPassword());
    }

    @Test
    public void readBid(){
        bid = bidDAOMySql.read(53);
        Assert.assertNotNull(bid);
    }

    @Test
    public void updateBid(){
        bidDAOMySql.create(bid);
        bid.setWeight(198.20);
        bidDAOMySql.update(bid);
        Bid result = bidDAOMySql.read(bid.getId());
        Assert.assertTrue(result.getWeight() == 198.20);
    }

    @Test
    public void deleteBid(){
        bidDAOMySql.create(bid);
        Bid before = bidDAOMySql.read(bid.getId());
        bidDAOMySql.delete(bid);
        Bid after = bidDAOMySql.read(bid.getId());
        Assert.assertNotNull(before);
        Assert.assertNull(after);
    }

}
