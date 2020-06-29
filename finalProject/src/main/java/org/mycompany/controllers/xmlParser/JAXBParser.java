package org.mycompany.controllers.xmlParser;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.bid.Bid;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class responsible for marshalling adn unmarshalling bids.
 */
public class JAXBParser {

    private static Logger logger = Logger.getLogger(JAXBParser.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public JAXBParser(){

    }

    /**
     * Method create XML file based on bid
     * @param bid that needs to be marshalling
     */
    public void creteXMLBasedOnObject(Bid bid){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bid.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(bid, new File("src/main/resources/bid".concat(String.valueOf(bid.getId())).concat(".xml")));
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Method create bid by reading XML file
     * @param id suffix of XML file that needs to be unmarshalling
     * @return bid created by unmarshaller
     */
    public Bid createObjectBasedOnXML(int id){
        Bid bid = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bid.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            bid = (Bid) unmarshaller.unmarshal(new File("src/main/resources/bid".concat(String.valueOf(id)).concat(".xml")));
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        return bid;
    }

}
