package org.mycompany.models.dao.bidStatusDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.bidStatus.BidStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class BidStatusDAOMySqlTest {

    private BidStatusDAO bidStatusDAOMySql = mock(BidStatusDAOMySql.class);
    private BidStatus bidStatus;
    private List<BidStatus> bidStatusList;

    @Before
    public void before(){
        bidStatusList = new ArrayList<>();
        bidStatus = new BidStatus();
    }

    @After
    public void after(){
        bidStatusList.remove(bidStatus);
    }

    @Test
    public void readBidStatus(){
        bidStatusList.add(bidStatus);
        doCallRealMethod().when(bidStatusDAOMySql).read(0);
        BidStatus result = bidStatusList.get(0);
        Assert.assertTrue(result != null);
    }

}
