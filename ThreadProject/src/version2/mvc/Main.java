package version2.mvc;


/**
 * Created by MadYeti on 26.03.2020.
 */
public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.startProgramm();

    }

}
