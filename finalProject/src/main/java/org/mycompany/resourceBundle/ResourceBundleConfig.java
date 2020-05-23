package org.mycompany.resourceBundle;


import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ResourceBundleConfig {

    private static String DEFAULT_LANG = "en";

    public ResourceBundleConfig(){

    }

    public ResourceBundle getResourceBundle(String lang, String baseName){
        if(lang==null) {
            lang = DEFAULT_LANG;
        }
        Locale locale= new Locale(lang);
        return ResourceBundle.getBundle(baseName, locale);
    }

}
