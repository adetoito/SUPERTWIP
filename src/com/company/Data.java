package com.company;

import java.util.*;

public class Data {

    /** NOTES:
     * Data objects hold the information of one line.
     * retrieveSecretNumber() is self-explanatory.
     * ------ MAY NOT BE USED ------ divideDataFurther() divides portions of a line into smaller portions.
     * identifySyntaxErrors() finds all of the portions of the line that account for the number of syntax errors.
     * identifySyntaxPts() is a complementary method to the method described above; it calculates how many points are
     *      deducted because of syntax errors.
     * identifyAdditionSections() finds all of the portions of the line that account for the addition of points.
     * calculateStudentPts() analyzes the data and calculates the correct point amount to the corresponding student.
     */

    private ArrayList <String> divisions = new ArrayList<>();
    private ArrayList syntaxErrors = new ArrayList();
    private ArrayList additionDivisions = new ArrayList();

    private double gainedPts;
    private double syntaxPts;

    public Data (String [] d) {
        syntaxPts = 0;
        for (String data : d) {
            if (!data.isEmpty()) {
                divisions.add(data);
            }
        }
        //divideDataFurther();
        identifySyntaxErrors();
        identifyAdditionSections();
    }

    public int retrieveSecretNumber () { return Integer.parseInt(divisions.get(0)); }

    /**
    private void divideDataFurther () {
        for (int i = 0; i < divisions.size(); i++) {
            String d = divisions.get(i);
            if (d.contains(", +")) {
                String [] furtherDivisions = d.split(", +");
                for (int j = furtherDivisions.length - 1; j >= 0; j--) {
                    divisions.add(i, furtherDivisions[j]);
                }
                divisions.remove(i + furtherDivisions.length - 1);
            }
        }

    }
    */

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
        identifySyntaxPts();
    }

    private void identifySyntaxPts () {
        for (int i = 1; i < divisions.size(); i++) {
            if ((boolean)syntaxErrors.get(i)) {
                int amtErrors = Integer.parseInt(divisions.get(i));
                for (int j = 0; j < amtErrors; j++) {
                    syntaxPts += 0.25;
                }
            }
        }
    }

    private void identifyAdditionSections () {
        additionDivisions.add(false);
        for (int i = 1; i < divisions.size(); i++) {
            String info = divisions.get(i);
            if (info.contains("+")) {
                additionDivisions.add(true);
            } else {
                additionDivisions.add(false);
            }
        }
        identifyAddedPts();
    }

    private void identifyAddedPts () {
        for (int i = 1; i < divisions.size(); i++) {
            if ((boolean)additionDivisions.get(i)) {
                String line = divisions.get(i);
                while (line.contains("+")) {
                    String value = line.substring(line.indexOf("+") + 2, line.substring(line.indexOf("+") + 2).indexOf(" "));
                    double addedPts = Double.parseDouble(value);
                    gainedPts += addedPts;
                    line = line.substring(value.length());
                }
            }
        }
    }

    public void calculateStudentPts (ArrayList<Student> students, int secNum) {
        for (int i = 0; i < students.size(); i++) {
            Student temp = students.get(i);
            int sn = temp.returnSecretNum();
            if (sn == secNum) {
                temp.addPts(gainedPts);
                temp.subtractPts(syntaxPts);
            }
        }
    }

}