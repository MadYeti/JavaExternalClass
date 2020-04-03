package version4.mvc;

/**
 * Created by MadYeti on 31.03.2020.
 */
public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.startProgramm();

    }

}
