package version4.doc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by MadYeti on 31.03.2020.
 */
public class Doc {

    private int currentCapacity;
    private int totalCapacity;
    private static final boolean[] PORTS = new boolean[3];
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private static Condition emptyCondition = writeLock.newCondition();
    private static Condition fullCondition = writeLock.newCondition();

    public Doc(){
        currentCapacity = 0;
        totalCapacity = 50;
    }

    public void getContainer(int amount, int place) {
        try {
            if (currentCapacity == 0) {
                System.out.println("Unloading ship is waiting");
                emptyCondition.await();
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

    public void putContainer(int amount, int place) {
        try {
            if (currentCapacity + amount > totalCapacity) {
                System.out.println("Loading ship is waiting");
                fullCondition.await();
            }
            System.out.println("Loading ship loading " + amount + " containers on place #" + (place + 1));
            Thread.sleep(2000);
            currentCapacity = currentCapacity + amount;
            System.out.println("Doc currentCapacity: " + currentCapacity);
            System.out.println("Loading ship leaving the doc");
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void releasePlace(int place){
        PORTS[place] = false;
        emptyCondition.signalAll();
        fullCondition.signalAll();
    }

    public static boolean[] getPORTS() {
        return PORTS;
    }

    public static Lock getReadLock() {
        return readLock;
    }

    public static Lock getWriteLock() {
        return writeLock;
    }
}
