package org.mycompany.models.cargoType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Component
@Scope("prototype")
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
