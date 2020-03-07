package mvc;

import java.net.URL;
import java.util.*;

/**
 * Created by MadYeti on 03.03.2020.
 */
public class View {

    public View(){

    }

    public void printMap(Map<String, Map<URL, Integer>> map){
        for(Map.Entry<String, Map<URL, Integer>> parentEntry: map.entrySet()){
            String parentKey = parentEntry.getKey();
            Map<URL, Integer> childMap = parentEntry.getValue();
            System.out.print(parentKey + " : ");
            Set<Map.Entry<URL, Integer>> entries = childMap.entrySet();
            Comparator<Map.Entry<URL, Integer>> valueComparator = new Comparator<Map.Entry<URL,Integer>>() {

                @Override
                public int compare(Map.Entry<URL, Integer> o1, Map.Entry<URL, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }

            };
            List<Map.Entry<URL, Integer>> listOfEntries = new ArrayList<Map.Entry<URL, Integer>>(entries);
            Collections.sort(listOfEntries, valueComparator);
            for(Map.Entry<URL, Integer> element: listOfEntries){
                String[] words = element.toString().split("=");
                System.out.print(words[0] + " - " + words[1]);
                System.out.println("\r\n");
            }
        }
    }

}
