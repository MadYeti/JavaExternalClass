package version2.mvc;

import version2.doc.Doc;
import version2.doc.LoadingShip;
import version2.doc.UnloadingShip;

/**
 * Created by MadYeti on 26.03.2020.
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
