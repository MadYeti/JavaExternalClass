package org.mycompany.models.dao.sendingPointDAO;

import org.mycompany.models.sendingPoint.SendingPoint;

/**
 * Basic interface that contains method to get sending point object
 */
public interface SendingPointDAO {

    SendingPoint read(int id);

}
