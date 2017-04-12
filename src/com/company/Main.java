package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	    Scanner sc = new Scanner(System.in);
        Scanner fileReader = new Scanner(new File("src/com/company/rawDataU6.txt"));
        int index = 0;
        while (fileReader.hasNextLine()) {
            if (index != 0) {
                String line = fileReader.nextLine();
                
            }
            index++;
        }
    }
}
