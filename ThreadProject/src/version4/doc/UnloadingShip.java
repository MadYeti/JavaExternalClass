package version4.doc;


/**
 * Created by MadYeti on 31.03.2020.
 */
public class UnloadingShip extends Ship{

    private Doc doc;

    public UnloadingShip(Doc doc){
        this.doc = doc;
        containerAmountCapacity = (int)(Math.random() * 10 + 1);
        new Thread(this, "Unloading ship").start();
    }

    @Override
    public void run() {
        int place = -1;
        for (int i = 0; i < Doc.getPORTS().length; i++) {
            if (!Doc.getPORTS()[i]) {
                break;
            }
            if (i == Doc.getPORTS().length - 1) {
                i = -1;
            }
        }
        Doc.getReadLock().lock();
        try{
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
        }finally {
            Doc.getReadLock().unlock();
        }
        Doc.getWriteLock().lock();
        doc.getContainer(this.containerAmountCapacity, place);
        try{
            doc.releasePlace(place);
        }finally {
            Doc.getWriteLock().unlock();
        }
    }

}
