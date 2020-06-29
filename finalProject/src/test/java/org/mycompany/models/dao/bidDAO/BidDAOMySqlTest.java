package org.mycompany.models.dao.bidDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.client.Customer;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class BidDAOMySqlTest {

    private BidDAO bidDAOMySql = mock(BidDAOMySql.class);
    private Bid bid;
    private List<Bid> bidList;

    @Before
    public void before(){
        bidList = new ArrayList<>();
        Client client = new Customer();
        CargoType cargoType = new CargoType();
        SendingPoint sendingPoint = new SendingPoint();
        DestinationPoint destinationPoint = new DestinationPoint();
        BidStatus bidStatus = new BidStatus();
        PaymentStatus paymentStatus = new PaymentStatus();
        bid = new Bid();
        bid.setClient(client);
        bid.setCargoType(cargoType);
        bid.setSendingPoint(sendingPoint);
        bid.setDestinationPoint(destinationPoint);
        bid.setBidStatus(bidStatus);
        bid.setPaymentStatus(paymentStatus);
    }

    @After
    public void after(){
        bidList.remove(bid);
    }

    @Test
    public void createBid(){
        doCallRealMethod().when(bidDAOMySql).create(bid);
        bidList.add(bid);
        Assert.assertTrue(bidList.size() == 1);
    }

    @Test
    public void readBid(){
        bidList.add(bid);
        doCallRealMethod().when(bidDAOMySql).read(0);
        Bid result = bidList.get(0);
        Assert.assertTrue(result != null);
    }

    @Test
    public void updateBid(){
        bidList.add(bid);
        bid = bidList.get(0);
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId(2);
        bid.setPaymentStatus(paymentStatus);
        Assert.assertEquals(2, bid.getPaymentStatus().getId());
    }

    @Test
    public void deleteBid(){
        bidList.add(bid);
        bidList.remove(bid);
        Assert.assertTrue(bidList.size() == 0);
    }

}
