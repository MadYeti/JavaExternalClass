package battle.helper;

import battle.droids.Droid;

import java.io.*;
import java.util.List;

/**
 * Created by MadYeti on 27.02.2020.
 */
public class RWdroidsToFile {

    public static void writeToFile(List<Droid> droids){
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("droids.csv"));
            for(Droid droid: droids){
                printWriter.write(droid.toString() + "\r\n");
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Droid[] readFromFile(){
        Droid[] droids = new Droid[4];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("droids.csv"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
