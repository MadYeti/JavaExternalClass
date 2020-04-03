package version2.doc;

/**
 * Created by MadYeti on 26.03.2020.
 */
public class UnloadingShip extends Ship {

    private Doc doc;

    public UnloadingShip(Doc doc) {
        this.doc = doc;
        containerAmountCapacity = (int) (Math.random() * 10 + 1);
        new Thread(this, "Unloading ship").start();
    }

    @Override
    public void run() {
        try {
            Doc.getSEMAPHORE().acquire();
            int place = -1;
            synchronized (Doc.getPORTS()) {
                for (int i = 0; i < Doc.getPORTS().length; i++) {
                    if (!Doc.getPORTS()[i]) {
                        Doc.getPORTS()[i] = true;
                        place = i;
                        break;
                    }
                }
            }
            doc.getContainer(this.containerAmountCapacity, place);
            synchronized (Doc.getPORTS()) {
                doc.realeasePlace(place);
            }
            Doc.getSEMAPHORE().release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
