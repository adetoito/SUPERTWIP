package com.company;

import java.util.*;

public class Data {

    /** NOTES:
     * Data objects hold the information of one line.
     * retrieveSecretNumber() is self-explanatory.
     * divideDataFurther() divides portions of a line into smaller portions.
     * identifySyntaxErrors() finds all of the portions of the line that account for the number of syntax errors.
     * addPtsToStudent() analyzes the data and adds the correct point amount to the corresponding student.
     */

    private ArrayList <String> divisions;
    private ArrayList syntaxErrors;

    public Data (String [] d) {
        for (String data : d) {
            if (!data.isEmpty()) {
                divisions.add(data);
            }
        }
        divideDataFurther();
        identifySyntaxErrors();
    }

    public int retrieveSecretNumber () { return Integer.parseInt(divisions.get(0)); }

    private void divideDataFurther () {
        for (int i = 0; i < divisions.size(); i++) {
            String d = divisions.get(i);
            if (d.contains(",")) {
                String [] furtherDivisions = d.split(",");
                for (int j = furtherDivisions.length - 1; j >= 0; j--) {
                    divisions.add(i, furtherDivisions[j]);
                }
                divisions.remove(i + furtherDivisions.length - 1);
            }
        }

    }

    private void identifySyntaxErrors () {
        syntaxErrors.add(false);
        for (int i = 1; i < divisions.size(); i++) {
            String info = divisions.get(i);
            if (info.length() == 1) {
                syntaxErrors.add(true);
            } else {
                syntaxErrors.add(false);
            }
        }
    }

    public void addPtsToStudent (ArrayList<Student> students, int secNum) {
        for (int i = 0; i < students.size(); i++) {
            Student temp = students.get(i);
            int sn = temp.returnSecretNum();
            if (sn == secNum) {

            }
        }
    }

}