package org.mycompany.models.bidsHolder;

import org.mycompany.models.bid.Bid;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
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

}
