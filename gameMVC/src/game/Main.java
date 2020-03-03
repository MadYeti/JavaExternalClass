package game;


/**
 * Created by MadYeti on 06.02.2020.
 */
public class Main {

    public static void main(String[] args) {

        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(view, model);
        controller.playGame();

    }

}
