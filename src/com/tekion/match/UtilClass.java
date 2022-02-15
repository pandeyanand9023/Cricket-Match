package com.tekion.match;
import java.util.*;

public class UtilClass {

    public static boolean validateInputs(String value, ArrayList<String> allowedValue){
        return allowedValue.contains(value);
    }
}
