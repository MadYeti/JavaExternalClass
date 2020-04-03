package version2.doc;

import java.util.concurrent.Semaphore;

/**
 * Created by MadYeti on 26.03.2020.
 */
public class Doc {

    private int currentCapacity;
    private int totalCapacity;
    private static final boolean[] PORTS = new boolean[3];
    private static final Semaphore SEMAPHORE = new Semaphore(3, true);

    public Doc(){
        currentCapacity = 0;
        totalCapacity = 50;
    }

    synchronized public void getContainer(int amount, int place) {
        try {
            if (currentCapacity == 0) {
                System.out.println("Unloading ship is waiting");
                wait();
            }
            System.out.println("Unloading ship unloading " + amount + " containers on place #" + (place + 1));
            Thread.sleep(2000);
            if (currentCapacity >= amount) {
                currentCapacity = currentCapacity - amount;
            } else {
                currentCapacity = 0;
            }
            System.out.println("Doc currentCapacity: " + currentCapacity);
            System.out.println("Unloading ship leaving the doc");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void putContainer(int amount, int place) {
        try {
            if (currentCapacity + amount > totalCapacity) {
                System.out.println("Loading ship is waiting");
                wait();
            }
            System.out.println("Loading ship loading " + amount + " containers on place #" + (place + 1));
            Thread.sleep(2000);
            if (currentCapacity + amount < totalCapacity) {
                currentCapacity = currentCapacity + amount;
            }
            System.out.println("Doc currentCapacity: " + currentCapacity);
            System.out.println("Loading ship leaving the doc");
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void realeasePlace(int place){
        PORTS[place] = false;
        notifyAll();
    }

    public static Semaphore getSEMAPHORE() {
        return SEMAPHORE;
    }

    public static boolean[] getPORTS() {
        return PORTS;
    }
}
