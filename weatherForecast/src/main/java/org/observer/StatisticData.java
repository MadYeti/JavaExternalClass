package org.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class StatisticData implements Observer, DispalyData {

    private Observable observable;
    private double averageTemperature;
    private double averageHumidity;
    private double averagePressure;

    public StatisticData(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject subject = (Subject) o;
        double sumTemp = 0.0;
        for(double value : subject.getTempList()){
            sumTemp = sumTemp + value;
        }
        this.averageTemperature = sumTemp / subject.getTempList().size();
        double sumHumidity = 0.0;
        for(double value : subject.getHumididtyList()){
            sumHumidity = sumHumidity + value;
        }
        this.averageHumidity = sumHumidity / subject.getHumididtyList().size();
        double sumPressure = 0.0;
        for(double value : subject.getPressureList()){
            sumPressure = sumPressure + value;
        }
        this.averagePressure = sumPressure / subject.getPressureList().size();
        display();
    }

    @Override
    public void display() {
        System.out.println("Average indicators:" + " average temp " + averageTemperature +
                                                   " C average humidity " + averageHumidity +
                                                   " % average pressure " + averagePressure + " hpa");
    }
}
