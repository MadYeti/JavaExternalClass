package org.mycompany.resourceBundle;


import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleConfig {

    private static String DEFAULT_LANG = "en";

    public ResourceBundleConfig(){

    }

    public static ResourceBundle getResourceBundle(String lang, String baseName){
        if(lang==null) {
            lang = DEFAULT_LANG;
        }
        Locale locale= new Locale(lang);
        return ResourceBundle.getBundle(baseName, locale);
    }

}
