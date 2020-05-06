package org.mycompany.models.dao.bidDAO;

import org.mycompany.models.bid.Bid;


public interface DAO {

    void create(Bid bid);
    Bid read(int id);
    void update(Bid bid);
    void delete(Bid bid);

}
