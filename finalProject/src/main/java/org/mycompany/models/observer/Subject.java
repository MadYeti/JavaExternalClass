package org.mycompany.models.observer;

import java.util.Observable;

public class Subject extends Observable{

    private int id;

    public Subject(){

    }

    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(int id){
        this.id = id;
        measurementsChanged();
    }

    public int getId() {
        return id;
    }
}
