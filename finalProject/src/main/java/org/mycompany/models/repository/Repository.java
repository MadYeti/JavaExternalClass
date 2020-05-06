package org.mycompany.models.repository;

import org.mycompany.models.bid.Bid;

import java.util.List;


public interface Repository {

    List<Bid> getWholeBidHistory(int id);

}
