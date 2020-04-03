package version3.mvc;

import version3.doc.Doc;
import version3.doc.LoadingShip;
import version3.doc.UnloadingShip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MadYeti on 29.03.2020.
 */
public class Model {

    private Doc doc;
    private LoadingShip loadingShip;
    private UnloadingShip unloadingShip;
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public Model(){
        doc = new Doc();
    }

    public LoadingShip createLoadingShip(){
        return new LoadingShip(doc);
    }

    public UnloadingShip createUnloadingShip(){
        return new UnloadingShip(doc);
    }

    public ExecutorService getExecutorService() {
        return executor;
    }
}
