package org.mycompany.models.bid;


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
    @XmlElement(name = "clientId")
    private int clientId;
    @XmlElement(name = "weight")
    private double weight;
    @XmlElement(name = "volume")
    private double volume;
    @XmlElement(name = "cargoTypeId")
    private int cargoType;
    private String cargoTypeValue;
    @XmlElement(name = "cargoCost")
    private double cargoCost;
    @XmlElement(name = "sendingPointId")
    private int sendingPoint;
    private String sendingPointValue;
    @XmlElement(name = "destinationPointId")
    private int destinationPoint;
    private String destinationPointValue;
    @XmlElement(name = "arrivalDate")
    private String arrivalDate;
    @XmlElement(name = "notes")
    private String notes;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "bidStatusId")
    private int bidStatus;
    private String bidStatusValue;
    @XmlElement(name = "paymentStatusId")
    private int paymentStatus;
    private String paymentStatusValue;

    public Bid(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public int getCargoType() {
        return cargoType;
    }

    public void setCargoType(int cargoType) {
        this.cargoType = cargoType;
    }

    public double getCargoCost() {
        return cargoCost;
    }

    public void setCargoCost(double cargoCost) {
        this.cargoCost = cargoCost;
    }

    public int getSendingPoint() {
        return sendingPoint;
    }

    public void setSendingPoint(int sendingPoint) {
        this.sendingPoint = sendingPoint;
    }

    public int getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(int destinationPoint) {
        this.destinationPoint = destinationPoint;
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

    public int getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCargoTypeValue(String cargoTypeValue) {
        this.cargoTypeValue = cargoTypeValue;
    }

    public void setSendingPointValue(String sendingPointValue) {
        this.sendingPointValue = sendingPointValue;
    }

    public void setDestinationPointValue(String destinationPointValue) {
        this.destinationPointValue = destinationPointValue;
    }

    public void setBidStatusValue(String bidStatusValue) {
        this.bidStatusValue = bidStatusValue;
    }

    public void setPaymentStatusValue(String paymentStatusValue) {
        this.paymentStatusValue = paymentStatusValue;
    }

    public String getCargoTypeValue() {
        return cargoTypeValue;
    }

    public String getSendingPointValue() {
        return sendingPointValue;
    }

    public String getDestinationPointValue() {
        return destinationPointValue;
    }

    public String getBidStatusValue() {
        return bidStatusValue;
    }

    public String getPaymentStatusValue() {
        return paymentStatusValue;
    }

    public Bid addId(int id){
        this.id = id;
        return this;
    }

    public Bid addClientId(int clientId) {
        this.clientId = clientId;
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

    public Bid addCargoType(int cargoType) {
        this.cargoType = cargoType;
        return this;
    }

    public Bid addCargoCost(double cargoCost) {
        this.cargoCost = cargoCost;
        return this;
    }

    public Bid addSendingPoint(int sendingPoint) {
        this.sendingPoint = sendingPoint;
        return this;
    }

    public Bid addDestinationPoint(int destinationPoint) {
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

    public Bid addBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
        return this;
    }

    public Bid addPaymentStatus(int paymentStatus) {
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
        if (clientId != bid.clientId) return false;
        if (Double.compare(bid.weight, weight) != 0) return false;
        if (Double.compare(bid.volume, volume) != 0) return false;
        if (cargoType != bid.cargoType) return false;
        if (Double.compare(bid.cargoCost, cargoCost) != 0) return false;
        if (sendingPoint != bid.sendingPoint) return false;
        if (destinationPoint != bid.destinationPoint) return false;
        if (Double.compare(bid.price, price) != 0) return false;
        if (bidStatus != bid.bidStatus) return false;
        if (paymentStatus != bid.paymentStatus) return false;
        if (arrivalDate != null ? !arrivalDate.equals(bid.arrivalDate) : bid.arrivalDate != null) return false;
        return notes != null ? notes.equals(bid.notes) : bid.notes == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + clientId;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cargoType;
        temp = Double.doubleToLongBits(cargoCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + sendingPoint;
        result = 31 * result + destinationPoint;
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + bidStatus;
        result = 31 * result + paymentStatus;
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", clientId=" + clientId +
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
