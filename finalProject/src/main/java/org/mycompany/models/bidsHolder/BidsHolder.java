package org.mycompany.models.bidsHolder;

import org.mycompany.models.bid.Bid;

import java.util.ArrayList;
import java.util.List;

public class BidsHolder {

    private List<Bid> bidsHolder = null;

    public BidsHolder(){
        bidsHolder = new ArrayList<>();
    }

    public List<Bid> getBidsHolder(){
        return bidsHolder;
    }

    public void setBidsHolder(List<Bid> bidsHolder) {
        this.bidsHolder = bidsHolder;
    }

    public void addBid(Bid bid){
        bidsHolder.add(0, bid);
    }

}
