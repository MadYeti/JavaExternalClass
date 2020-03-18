package org.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class Subject extends Observable{

    private double temp;
    private double humidity;
    private double pressure;
    private List<Double> tempList = new ArrayList<>();
    private List<Double> humididtyList = new ArrayList<>();
    private List<Double> pressureList = new ArrayList<>();

    public Subject(){

    }

    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(double temp, double humidity, double pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public double getTemperature() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public List<Double> getTempList() {
        return tempList;
    }

    public List<Double> getHumididtyList() {
        return humididtyList;
    }

    public List<Double> getPressureList() {
        return pressureList;
    }
}
