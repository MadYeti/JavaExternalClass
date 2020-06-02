package org.mycompany.controllers.authorization;


import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.factory.FactorySession;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;


public class Main {

    public static void main(String[] args) {

        Bid bid = new Bid();
        Client client = new Client();
        client.setId(1);
        CargoType cargoType = new CargoType();
        cargoType.setId(1);
        SendingPoint sendingPoint = new SendingPoint();
        sendingPoint.setId(1);
        DestinationPoint destinationPoint = new DestinationPoint();
        destinationPoint.setId(2);
        BidStatus bidStatus = new BidStatus();
        bidStatus.setId(1);
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId(1);
        bid.addId(52).addClient(client)
                .addWeight(543)
                .addVolume(543)
                .addCargoType(cargoType)
                .addCargoCost(5434)
                .addSendingPoint(sendingPoint)
                .addDestinationPoint(destinationPoint)
                .addArrivalDate(DateController.getArrivalDate(4567))
                .addNotes("asdfaasdf")
                .addPrice(5678)
                .addBidStatus(bidStatus)
                .addPaymentStatus(paymentStatus)
                .build();

        /*
        //Return all objects form DB
        SessionFactory sessionFactory = FactorySession.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
        Root<Bid> root = criteriaQuery.from(Bid.class);
        //criteriaQuery.select(root);//select list of all objects
        criteriaQuery.select(criteriaBuilder.max(root.get("id")));
        Query query = session.createQuery(criteriaQuery);
        Object maxId = query.getSingleResult();
        System.out.println(maxId);
        //List list = query.getResultList();//select list of all objects
        //System.out.println(Arrays.toString(list.toArray()));//select list of all objects
        //*/

        //ClientDAO clientDAO = new ClientDAO(FactorySession.getSessionFactory());
        //System.out.println(clientDAO.getClient("ivancov13@bigmir.net", "Zzzz1!"));

        //BidsHolderImpl bidsHolderImpl = new BidsHolderImpl(FactorySession.getSessionFactory());
        //System.out.println(Arrays.toString(bidsHolderImpl.getWholeBidHistory(1).toArray()));

        BidDAO bidDAO = new BidDAO(FactorySession.getSessionFactory());
        System.out.println(bidDAO.getDestinationPointValue(5, "uk_UA"));


    }

}
