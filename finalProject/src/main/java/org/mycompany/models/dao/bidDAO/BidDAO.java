package org.mycompany.models.dao.bidDAO;

import org.mycompany.models.bid.Bid;

/**
 * Basic interface presents CRUD operation for bids
 */
public interface BidDAO {

    void create(Bid bid);
    Bid read(int id);
    void update(Bid bid);
    void delete(Bid bid);

}
