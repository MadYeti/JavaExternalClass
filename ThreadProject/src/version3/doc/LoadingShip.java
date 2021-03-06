package version3.doc;

/**
 * Created by MadYeti on 29.03.2020.
 */
public class LoadingShip extends Ship{

    private Doc doc;
    private int shipNumber;

    public LoadingShip(int shipNumber) {
        this.shipNumber = shipNumber;
        containerAmountCapacity = (int) (Math.random() * 20 + 1);
    }

    public LoadingShip(Doc doc) {
        this.doc = doc;
        containerAmountCapacity = (int) (Math.random() * 20 + 1);
        new Thread(this, "Loading ship").start();
    }

    public int getShipNumber() {
        return shipNumber;
    }

    @Override
    public void run() {
        int place = -1;
        synchronized (this) {
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
        doc.putContainer(this.containerAmountCapacity, place);
        synchronized (Doc.getPORTS()) {
            doc.releasePlace(place);
        }
    }

    @Override
    public String toString() {
        return "LoadingShip{" +
                "containerAmountCapacity=" + containerAmountCapacity +
                ", shipNumber=" + shipNumber +
                '}';
    }
}
