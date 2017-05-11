package com.company;

import java.util.*;
import java.io.*;

public class Student {

    /** NOTES:
     * Student objects hold the information of each student who took the benchmark.
     * All "return" methods are self-explanatory.
     * addPts() adds points to its corresponding questions, based on parameters.
     * subtractPts() is similar to addPts(), except it subtracts instead of adding.
     * addAllNecessaryInformation() uses all of the Student object's information and overwrites its
     *      corresponding line with it.
     */

    //private String name;
    private int secNum;

    private double Q1Points;
    private double Q2Points;

    public Student (int sn) throws IOException {
        secNum = sn;
        Scanner fileReader = new Scanner(new File("names.txt"));
        int index = 0;
        while (fileReader.hasNextLine()) {
            if (index != 0) {
                //String line = fileReader.nextLine();
                if (secNum == index) {
                    //name = line.substring(0, line.indexOf("\t"));
                    break;
                }
            }
            index++;
        }
    }

    public int returnSecretNum () { return secNum; }

    public double returnQ1Grade () { return Q1Points / 9; }
    public double returnQ2Grade () { return Q2Points / 9; }
    public double returnAvgGrade () { return (returnQ1Grade() + returnQ2Grade()) / 2; }

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

}
