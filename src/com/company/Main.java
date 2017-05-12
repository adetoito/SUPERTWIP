package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner fileReader = new Scanner(new File("rawDataU6.txt"));
        Scanner namesReader = new Scanner(new File("names.txt"));
        int index = 0;
        ArrayList<Student> students = new ArrayList();
        while (fileReader.hasNextLine()) {
            if (index != 0) {
                String line = fileReader.nextLine();
                String [] divisions = line.split("\t");
                String numString = divisions[0];
                try {
                    int secretNumber = Integer.parseInt(numString);
                    Data d = new Data (divisions);
                    if (students.size() > 0) {
                        for (int i = 0; i < students.size(); i++) {
                            if (secretNumber != students.get(i).secNum) {
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
                } catch (NumberFormatException e) {
                    // do nothing
                }
            }
            index++;
        }
        while (namesReader.hasNextLine()) {
            String line = namesReader.nextLine();
            String name = line.substring(0, line.indexOf(" "));
            String num = line.substring(line.indexOf(" ") + 1).replaceAll(" ", "");
            int secNum = Integer.parseInt(num);
            for (int i = 0; i < students.size(); i++) {
                Student temp = students.get(i);
                if (secNum == temp.secNum) {
                    temp.setName(name);
                }
            }
        }
        ArrayList<Student> compactStudents = new ArrayList();
        for (int i = 0; i < students.size() / 2; i++) {
            Student one = students.get(i);
            Student two = students.get(i + 1);
            Student newStudent = new Student(one.secNum);
            newStudent.Q1Points = calculateAverage(one.Q1Points, two.Q1Points);
            newStudent.Q2Points = calculateAverage(one.Q2Points, two.Q2Points);
            i++;
            compactStudents.add(newStudent);
        }
        /*
        int prevIndex = 0;
        for (int i = 0; i < students.size(); i++) {
            Student temp = students.get(i);
            if (compactStudents.size() == 0) {
                compactStudents.add(temp);
            } else {
                for (int j = 0; j < compactStudents.size(); j++) {
                    Student stolen = compactStudents.get(j);
                    if (stolen.secNum == temp.secNum) {
                        Student sAtPI = compactStudents.get(prevIndex);
                        sAtPI.Q1Points = calculateAverage(sAtPI.Q1Points, stolen.Q1Points);
                        sAtPI.Q2Points = calculateAverage(sAtPI.Q2Points, stolen.Q2Points);
                        compactStudents.add(sAtPI);
                        prevIndex = j;
                        break;
                    }
                }
            }
        }
        */
        Collections.sort(students);
        System.out.println("Name \t Secret Number \t Avg Grade \t Q1 Grade \t Q2 Grade");
        for (int i = 0; i < students.size(); i++) {
            Student temp = students.get(i);
            //String line = namesReader.nextLine();
            //String name = line.substring(0, line.indexOf(" "));
            //String secNum = line.substring(line.indexOf(" ") + 1);
            System.out.println(temp.name + "\t" + temp.secNum + "\t" + temp.returnAvgGrade() + "% \t" + temp.Q1Points + "\t" + temp.Q2Points);
            i++;
        }
    }

    public static double calculateAverage (double one, double two) {
        return (one + two) / 2;
    }
}