package org.mycompany.models.dao.bidStatusDAO;

import org.mycompany.models.bidStatus.BidStatus;

/**
 * Basic interface that contains method to get bid status object
 */
public interface BidStatusDAO {

    BidStatus read(int id);

}
