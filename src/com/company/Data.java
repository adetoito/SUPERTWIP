package com.company;

import java.util.*;

public class Data {

    private ArrayList <String> divisions;

    public Data (String [] d) {
        for (String data : d) {
            if (!data.isEmpty()) {
                divisions.add(data);
            }
        }
    }

    public String retrieveData (int index) {
        return divisions.get(index);
    }

    public int retrieveSecretNumber () { return Integer.parseInt(divisions.get(0)); }

}
