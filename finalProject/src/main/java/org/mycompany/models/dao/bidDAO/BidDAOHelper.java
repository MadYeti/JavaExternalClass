package org.mycompany.models.dao.bidDAO;

/**
 * Utility interface that contains methods getting last inserted id,
 * price and foreign keys values
 */
public interface BidDAOHelper {

    int getLastInsertedId();
    void updateBidPaymentStatus(int id);
    double getPriceAccordingToCityDistance(int sendingCityId, int destinationCityId);
    double getCargoTypeCoefficient(int cargoTypeId);
    String getCargoTypeValue(int id, String lang);
    String getSendingPointValue(int id, String lang);
    String getDestinationPointValue(int id, String lang);

}
