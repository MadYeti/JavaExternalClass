package org.factory;

import org.parsers.*;

/**
 * Created by MadYeti on 18.03.2020.
 */
public class ParserFactory {

    public static Parser createParser(String parserType){
        Parser parser = null;
        if(parserType.equals("DOM")){
            parser = new DOMParser();
        }else if (parserType.equals("SAX")){
            parser = new SaxParser();
        }else if (parserType.equals("StAX")){
            parser = new StAXParser();
        }
        return parser;
    }

}
