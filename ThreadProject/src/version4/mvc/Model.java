package version4.mvc;

import version4.doc.Doc;
import version4.doc.LoadingShip;
import version4.doc.UnloadingShip;

/**
 * Created by MadYeti on 31.03.2020.
 */
public class Model {

    private Doc doc;
    private LoadingShip loadingShip;
    private UnloadingShip unloadingShip;

    public Model(){
        doc = new Doc();
    }

    public void createLoadingShip(){
        loadingShip = new LoadingShip(doc);
    }

    public void createUnloadingShip(){
        unloadingShip = new UnloadingShip(doc);
    }

}
