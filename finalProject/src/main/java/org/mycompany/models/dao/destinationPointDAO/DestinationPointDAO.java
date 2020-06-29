package org.mycompany.models.dao.destinationPointDAO;

import org.mycompany.models.destinationPoint.DestinationPoint;

/**
 * Basic interface that contains method to get destination point object
 */
public interface DestinationPointDAO {

    DestinationPoint read(int id);

}
