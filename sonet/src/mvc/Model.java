package mvc;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by MadYeti on 03.03.2020.
 */
public class Model {

    private Map<URL, Integer> urlMap = new HashMap<>();
    private Map<String, Map<URL, Integer>> countWordMap = new HashMap<String, Map<URL, Integer>>();
    private List<Integer> sonetAmount = new ArrayList<Integer>(20);
    private static Properties properties = new Properties();

    public Model(){
        try {
            InputStream input = new FileInputStream("properties.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText(String url){
        URL website = null;
        try {
            website = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection connection = null;
        try {
            connection = website.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder response = new StringBuilder();
        String inputLine;

        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public void initSonetArrayList(){
        while(sonetAmount.size() != 20){
            int sonetNumber =(int) (Math.random() * 154) + 1;
            if(!sonetAmount.contains(sonetNumber)){
                sonetAmount.add(sonetNumber);
            }
        }
    }

    public void getWordsAmount(String wordToFind){
        for(int i = 0; i < sonetAmount.size(); i++){
            String urlString = "http://shakespeare.mit.edu/Poetry/sonnet" + Model.getPropertieValue("" + sonetAmount.get(i)) + "html";
            String text = getText(urlString);
            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String[] words = text.split(" ");
            for (String word : words) {
                if(word.equals(wordToFind)) {
                    if (countWordMap.get(word) == null) {
                        urlMap.put(url, 1);
                    } else {
                        Integer freq = countWordMap.get(word).get(url);
                        if(freq == null){
                            urlMap.put(url, 1);
                        }else {
                            urlMap.put(url, freq + 1);
                        }
                    }
                    countWordMap.put(word, urlMap);
                }
            }
        }
    }

    public Map<String, Map<URL, Integer>> getCountWordMap() {
        return countWordMap;
    }

    public static String getPropertieValue(String key){
        return properties.getProperty(key);
    }

}
