package com.company;

import java.util.*;
import java.io.*;

public class Student implements Comparable {

    /** NOTES:
     * Student objects hold the information of each student who took the benchmark.
     * All "return" methods are self-explanatory.
     * addPts() adds points to its corresponding questions, based on parameters.
     * subtractPts() is similar to addPts(), except it subtracts instead of adding.
     * addAllNecessaryInformation() uses all of the Student object's information and overwrites its
     *      corresponding line with it.
     */

    public String name;
    public int secNum;

    public double Q1Points;
    public double Q2Points;

    public Student (int sn) throws IOException {
        secNum = sn;
        Scanner fileReader = new Scanner(new File("names.txt"));
        int index = 0;
        while (fileReader.hasNextLine()) {
            if (index != 0 && secNum == index) {
                break;
            }
            index++;
        }
    }

    public double returnQ1Grade () { return (double)Math.round((Q1Points * 100) / 9); }
    public double returnQ2Grade () { return (double)Math.round((Q2Points * 100) / 9); }
    public double returnAvgGrade () { return (returnQ1Grade() + returnQ2Grade()) / 2.00; }

    public void addPts (double pts, int qn) {
        if (qn == 1) {
            Q1Points += pts;
        } else {
            Q2Points += pts;
        }
    }

    public void subtractPts (double pts, int qn) {
        if (qn == 1) {
            Q1Points -= pts;
        } else {
            Q2Points -= pts;
        }
    }

    public void setName (String n) {
        name = n;
    }

    public int compareTo (Object o) {
        Student s = (Student)o;
        return this.name.compareTo(s.name);
    }

}
