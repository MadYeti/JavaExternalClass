package org.mycompany.models.cargoType;

import javax.xml.bind.annotation.*;

/**
 * Model class for bids cargo type object. Represents by cargo_type table in DB
 */
@XmlAccessorType(XmlAccessType.NONE)
public class CargoType {

    @XmlElement(name = "id")
    private int id;
    private String cargoTypeEN;
    private String cargoTypeUA;
    private double coefficient;

    public CargoType() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargoTypeEN() {
        return cargoTypeEN;
    }

    public String getCargoTypeUA() {
        return cargoTypeUA;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCargoTypeEN(String cargoTypeEN) {
        this.cargoTypeEN = cargoTypeEN;
    }

    public void setCargoTypeUA(String cargoTypeUA) {
        this.cargoTypeUA = cargoTypeUA;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "CargoType{" +
                "id=" + id +
                ", cargoTypeEN='" + cargoTypeEN + '\'' +
                ", cargoTypeUA='" + cargoTypeUA + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}
