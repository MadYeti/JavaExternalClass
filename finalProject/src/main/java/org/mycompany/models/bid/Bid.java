package org.mycompany.models.bid;


import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Bid")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
@Scope("prototype")
public class Bid {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "client")
    private Client client;
    @XmlElement(name = "weight")
    private double weight;
    @XmlElement(name = "volume")
    private double volume;
    @XmlElement(name = "cargoType")
    private CargoType cargoType;
    @XmlElement(name = "cargoCost")
    private double cargoCost;
    @XmlElement(name = "sendingPoint")
    private SendingPoint sendingPoint;
    @XmlElement(name = "destinationPoint")
    private DestinationPoint destinationPoint;
    @XmlElement(name = "arrivalDate")
    private String arrivalDate;
    @XmlElement(name = "notes")
    private String notes;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "bidStatus")
    private BidStatus bidStatus;
    @XmlElement(name = "paymentStatus")
    private PaymentStatus paymentStatus;

    public Bid(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCargoCost() {
        return cargoCost;
    }

    public void setCargoCost(double cargoCost) {
        this.cargoCost = cargoCost;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public SendingPoint getSendingPoint() {
        return sendingPoint;
    }

    public void setSendingPoint(SendingPoint sendingPoint) {
        this.sendingPoint = sendingPoint;
    }

    public DestinationPoint getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(DestinationPoint destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Bid addId(int id){
        this.id = id;
        return this;
    }

    public Bid addClient(Client client){
        this.client = client;
        return this;
    }

    public Bid addWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public Bid addVolume(double volume) {
        this.volume = volume;
        return this;
    }

    public Bid addCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
        return this;
    }

    public Bid addCargoCost(double cargoCost) {
        this.cargoCost = cargoCost;
        return this;
    }

    public Bid addSendingPoint(SendingPoint sendingPoint) {
        this.sendingPoint = sendingPoint;
        return this;
    }

    public Bid addDestinationPoint(DestinationPoint destinationPoint) {
        this.destinationPoint = destinationPoint;
        return this;
    }

    public Bid addArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public Bid addNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Bid addPrice(double price) {
        this.price = price;
        return this;
    }

    public Bid addBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
        return this;
    }

    public Bid addPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public Bid build(){
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (id != bid.id) return false;
        if (Double.compare(bid.weight, weight) != 0) return false;
        if (Double.compare(bid.volume, volume) != 0) return false;
        if (Double.compare(bid.cargoCost, cargoCost) != 0) return false;
        if (Double.compare(bid.price, price) != 0) return false;
        if (client != null ? !client.equals(bid.client) : bid.client != null) return false;
        if (cargoType != null ? !cargoType.equals(bid.cargoType) : bid.cargoType != null) return false;
        if (sendingPoint != null ? !sendingPoint.equals(bid.sendingPoint) : bid.sendingPoint != null) return false;
        if (destinationPoint != null ? !destinationPoint.equals(bid.destinationPoint) : bid.destinationPoint != null)
            return false;
        if (arrivalDate != null ? !arrivalDate.equals(bid.arrivalDate) : bid.arrivalDate != null) return false;
        if (notes != null ? !notes.equals(bid.notes) : bid.notes != null) return false;
        if (bidStatus != null ? !bidStatus.equals(bid.bidStatus) : bid.bidStatus != null) return false;
        return paymentStatus != null ? paymentStatus.equals(bid.paymentStatus) : bid.paymentStatus == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cargoType != null ? cargoType.hashCode() : 0);
        temp = Double.doubleToLongBits(cargoCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sendingPoint != null ? sendingPoint.hashCode() : 0);
        result = 31 * result + (destinationPoint != null ? destinationPoint.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (bidStatus != null ? bidStatus.hashCode() : 0);
        result = 31 * result + (paymentStatus != null ? paymentStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", client=" + client +
                ", weight=" + weight +
                ", volume=" + volume +
                ", cargoType='" + cargoType + '\'' +
                ", cargoCost=" + cargoCost +
                ", sendingPoint='" + sendingPoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", notes='" + notes + '\'' +
                ", price=" + price +
                ", bidStatus='" + bidStatus + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
