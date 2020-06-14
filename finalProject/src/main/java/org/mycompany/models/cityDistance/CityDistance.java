package org.mycompany.models.cityDistance;


public class CityDistance {

    private int id;
    private int sendingPointId;
    private int destinationPointId;
    private double price;

    public CityDistance() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSendingPointId() {
        return sendingPointId;
    }

    public void setSendingPointId(int sendingPointId) {
        this.sendingPointId = sendingPointId;
    }

    public int getDestinationPointId() {
        return destinationPointId;
    }

    public void setDestinationPointId(int destinationPointId) {
        this.destinationPointId = destinationPointId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
