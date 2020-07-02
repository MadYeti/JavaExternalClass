package org.mycompany.models.repository;

import org.mycompany.models.bid.Bid;

import java.util.List;

/**
 * Interface for returning DAO object that works with
 * not a single entities
 */
public interface Repository {

    List<Bid> getWholeBidHistory(int id);

}
