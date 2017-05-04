package com.company;

public class Student {

    private String name;
    private int secNum;
    private double points;

    public Student (int sn) {
        secNum = sn;
    }

    public void setName (String n) { name = n; }

    public int returnSecretNum () { return secNum; }

    public void addPts (double pts) { points += pts; }

    public void subtractPts (double pts) { points -= pts; }

}
