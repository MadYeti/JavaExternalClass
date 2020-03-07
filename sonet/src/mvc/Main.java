package mvc;

/**
 * Created by MadYeti on 03.03.2020.
 */
public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.countWordsViaURL();

    }

}
