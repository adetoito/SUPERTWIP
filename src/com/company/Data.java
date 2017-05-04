package com.company;

import java.util.*;

public class Data {

    private ArrayList<String> divisions;

    public Data (String [] d) {
        for (String data : d) {
            divisions.add(data);
        }
    }

    public String retrieveData (int index) {
        return divisions.get(index);
    }

}
