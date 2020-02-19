package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.startBattle();

    }

}
