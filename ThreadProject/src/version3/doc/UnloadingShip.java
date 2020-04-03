package version3.doc;

/**
 * Created by MadYeti on 29.03.2020.
 */
public class UnloadingShip extends Ship{

    private Doc doc;
    private int shipNumber;

    public UnloadingShip(int shipNumber){
        this.shipNumber = shipNumber;
        containerAmountCapacity = (int)(Math.random() * 10 + 1);
    }

    public UnloadingShip(Doc doc){
        this.doc = doc;
        containerAmountCapacity = (int)(Math.random() * 10 + 1);
        new Thread(this, "Unloading ship").start();
    }

    public int getShipNumber() {
        return shipNumber;
    }

    @Override
    public void run() {
        int place = -1;
        synchronized (this){
            for (int i = 0; i < Doc.getPORTS().length; i++) {
                if (!Doc.getPORTS()[i]) {
                    Doc.getPORTS()[i] = true;
                    place = i;
                    break;
                }
                if(i == Doc.getPORTS().length - 1){
                    i = -1;
                }
            }
        }
        doc.getContainer(this.containerAmountCapacity, place);
        synchronized (Doc.getPORTS()) {
            doc.releasePlace(place);
        }
    }

    @Override
    public String toString() {
        return "UnloadingShip{" +
                "containerAmountCapacity=" + containerAmountCapacity +
                ", shipNumber=" + shipNumber +
                '}';
    }
}
