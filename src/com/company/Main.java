package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner fileReader = new Scanner(new File("src/com/company/rawDataU6.txt"));
        Scanner namesReader = new Scanner(new File("src/com/company/names.txt"));
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
                    //int secretNumber = allLines.get(allLines.size() - 1).retrieveSecretNumber();
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
                    d.calculateStudentPts(students, secretNumber);
                    allLines.add(d);
                }
            }
            index++;
        }

        //addAllNecessaryInformation(students);
        index = 1;
        System.out.println("Name \t Secret Number \t Avg Grade \t Q1 Grade \t Q2 Grade");

        while (namesReader.hasNextLine()) {
            Student temp = students.get(index);
            String line = namesReader.nextLine();
            String name = line.substring(0, line.indexOf("\t"));
            String secNum = line.substring(line.indexOf("\t"));
            System.out.println(name + "\t" + secNum + "\t" + temp.returnAvgGrade() + "\t" + temp.returnQ1Grade() + "\t" + temp.returnQ2Grade());
        }
    }

    /*
    public static void addAllNecessaryInformation (ArrayList<Student> students) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("src/com/company/names.txt"));
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= students.size(); i++) {
            String line;
            Student temp = students.get(i);
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    sb.append(temp.)
                } else if (j == 1) {

                } else if (j == 2) {

                } else if (j == 3) {

                } else {

                }
            }
        }
    }
    */
}