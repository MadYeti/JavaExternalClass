package org.mycompany.models.dao.cargoTypeDAO;

import org.mycompany.models.cargoType.CargoType;

/**
 * Basic interface that contains method to get cargo type object
 */
public interface CargoTypeDAO {

    CargoType read(int id);

}
