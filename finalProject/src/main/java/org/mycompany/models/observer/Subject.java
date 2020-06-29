package org.mycompany.models.observer;

import java.util.Observable;

/**
 * Utility class that uses in pattern Observable
 */
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
