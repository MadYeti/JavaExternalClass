package battle.helper;

import battle.droids.Droid;
import battle.factory.DroidFactory;

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
        String line;
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("droids.csv"));
            while((line = bufferedReader.readLine()) != null){
                String[] elements = line.split(",");
                droids[i++] = DroidFactory.createDroidWithParameters(elements[0], Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return droids;
    }

    public static byte[] writeObjects(List<Droid> droids){
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            for(Droid droid: droids){
                objectOutputStream.writeObject(droid);
            }
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Droid[] readObjects(byte[] array){
        Droid[] droids = new Droid[4];
        int i = 0;
        Droid droid = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            while((droid = (Droid) objectInputStream.readObject()) != null){
                droids[i++] = droid;
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return droids;
    }



}
