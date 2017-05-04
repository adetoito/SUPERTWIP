package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	    Scanner sc = new Scanner(System.in);
        Scanner fileReader = new Scanner(new File("src/com/company/rawDataU6.txt"));
        int index = 0;
        ArrayList<Student> students = new ArrayList();

        while (fileReader.hasNextLine()) {
            if (index != 0) {
                String line = fileReader.nextLine();
                String [] divisions = line.split("\t");
                int secretNumber = Integer.parseInt(divisions[0]);
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
            index++;
        }
    }
}
