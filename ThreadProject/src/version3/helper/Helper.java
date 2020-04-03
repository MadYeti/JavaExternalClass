package version3.helper;

import version3.doc.LoadingShip;
import version3.doc.Ship;
import version3.doc.UnloadingShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadYeti on 30.03.2020.
 */
public class Helper {

    public static void main(String[] args) {

        List<Ship> list = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            list.add(new LoadingShip(i + 1));
            list.add(new UnloadingShip(i + 1));
        }

        list.stream()
                .filter(c -> c.getContainerAmountCapacity() > 10)
                .filter(s -> s instanceof LoadingShip)
                .map(k -> (LoadingShip)k)
                .map(LoadingShip::getShipNumber)
                .forEach(System.out::println);

        list.forEach(System.out::println);

    }

}
