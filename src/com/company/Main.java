package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner fileReader = new Scanner(new File("src/com/company/rawDataU6.txt"));
        int index = 0;
        ArrayList<Student> students = new ArrayList();
        ArrayList<Data> allLines = new ArrayList();

        while (fileReader.hasNextLine()) {
            if (index != 0) {
                String line = fileReader.nextLine();
                String [] divisions = line.split("\t");
                char firstChar = divisions[0].charAt(0);
                int charInt = (int)firstChar;
                if (charInt >= 48 && charInt <= 57) {
                    Data d = new Data (divisions);
                    allLines.add(d);
                    int secretNumber = allLines.get(allLines.size() - 1).retrieveSecretNumber();
                    if (students.size() > 0) {
                        for (int i = 0; i < students.size(); i++) {
                            if (secretNumber != students.get(i).returnSecretNum()) {
                                Student s = new Student(secretNumber);
                                students.add(s);
                                break;
                            }
                        }
                    } else {
                        Student s = new Student(secretNumber);
                        students.add(s);
                    }
                }
            }
            index++;
        }
    }

}