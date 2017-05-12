package com.company;

import java.util.*;

public class Data {

    /** NOTES:
     * Data objects hold the information of one line.
     * retrieveSecretNumber() is self-explanatory.
     * identifySyntaxErrors() finds all of the portions of the line that account for the number of syntax errors.
     * identifySyntaxPts() is a complementary method to the method described above; it calculates how many points are
     *      deducted because of syntax errors, taking into account which question it is assigned to.
     * identifyAdditionSections() finds all of the portions of the line that account for the addition of points.
     * calculateStudentPts() analyzes the data and calculates the correct point amount to the corresponding student and its
     *      corresponding questions.
     */

    private ArrayList <String> divisions = new ArrayList<>();
    private ArrayList syntaxErrors = new ArrayList();
    private ArrayList additionDivisions = new ArrayList();

    private double gainedQ1Pts;
    private double gainedQ2Pts;

    private double Q1SyntaxPts;
    private double Q2SyntaxPts;

    private int indexOfFirstSyntaxes;

    public Data (String [] d) {
        Q1SyntaxPts = 0; Q2SyntaxPts = 0;
        gainedQ1Pts = 0; gainedQ2Pts = 0;
        indexOfFirstSyntaxes = -1;
        for (String data : d) {
            if (!data.isEmpty()) {
                divisions.add(data);
            }
        }
        identifySyntaxErrors();
        identifyAdditionSections();
    }

    public int retrieveSecretNumber () { return Integer.parseInt(divisions.get(0)); }

    private void identifySyntaxErrors () {
        syntaxErrors.add(false);
        for (int i = 1; i < divisions.size(); i++) {
            String info = divisions.get(i);
            if (info.length() == 1) {
                syntaxErrors.add(true);
                if (indexOfFirstSyntaxes == -1) {
                    indexOfFirstSyntaxes = i;
                }
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
                if (i == indexOfFirstSyntaxes) {
                    Q1SyntaxPts = amtErrors * 0.25;
                } else {
                    Q2SyntaxPts = amtErrors * 0.25;
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
        //System.out.println("Now I identify places with added points");
        for (int i = 1; i < divisions.size(); i++) {
            if ((boolean)additionDivisions.get(i)) {
                String line = divisions.get(i);
                while (line.contains("+ .5") || line.contains("+ 1")) {
                    String editedLine;
                    //System.out.println(line);
                    //System.out.println(".5: " + line.indexOf("+ .5") + " | 1: " + line.indexOf("+ 1"));
                    int amtCut;
                    if (line.contains("+ .5")) {
                        editedLine = line.substring(line.indexOf("+ .5") + 2);
                        int nextHalf = editedLine.indexOf("+ .5");
                        int nextWhole = editedLine.indexOf("+ 1");
                        //System.out.println("NEW LINE: " + editedLine);
                        String value = editedLine.substring(0, editedLine.indexOf(" "));
                        //System.out.println("INDEX TIL NEXT HALF: " + nextHalf);
                        //System.out.println("INDEX TIL NEXT WHOLE: " + nextWhole);
                        if (!value.equals("w2") && !value.equals("w1,")) {
                            double addedPts = Double.parseDouble(value);
                            if (i < indexOfFirstSyntaxes) {
                                gainedQ1Pts += addedPts;
                            } else {
                                gainedQ2Pts += addedPts;
                            }
                        }
                        //amtCut = Math.abs((value.length() + 3) - line.length());
                        //amtCut = line.length() - (value.length() + 3);
                        if (nextHalf > nextWhole || nextHalf == -1) {
                            amtCut = editedLine.length() - nextWhole;
                        } else if (nextHalf == -1 && nextWhole == -1) {
                            break;
                        } else {
                            amtCut = editedLine.length() - nextHalf;
                        }
                    } else {
                        editedLine = line.substring(line.indexOf("+ 1") + 2);
                        int nextHalf = editedLine.indexOf("+ .5");
                        int nextWhole = editedLine.indexOf("+ 1");
                        //System.out.println("NEW LINE: " + editedLine);
                        String value = editedLine.substring(0, editedLine.indexOf(" "));
                        //System.out.println("INDEX TIL NEXT HALF: " + nextHalf);
                        //System.out.println("INDEX TIL NEXT WHOLE: " + nextWhole);
                        if (!value.equals("w2") && !value.equals("w1,")) {
                            double addedPts = Double.parseDouble(value);
                            if (i < indexOfFirstSyntaxes) {
                                gainedQ1Pts += addedPts;
                            } else {
                                gainedQ2Pts += addedPts;
                            }
                        }
                        //amtCut = Math.abs((value.length() + 2) - line.length());
                        //amtCut = line.length() - (value.length() + 2);
                        if (nextHalf > nextWhole || nextHalf == -1) {
                            amtCut = editedLine.length() - nextWhole;
                        } else if (nextHalf == -1 && nextWhole == -1) {
                            break;
                        } else {
                            amtCut = editedLine.length() - nextHalf;
                        }
                    }
                    line = line.substring(amtCut);
                    //System.out.println("LINE BEFORE CHANGE: " + line);
                    //System.out.println("\n");
                }
            }
        }
        //System.out.println("\nFINAL Q1 PTS for " + retrieveSecretNumber() + ": " + gainedQ1Pts);
        //System.out.println("FINAL Q2 PTS for " + + retrieveSecretNumber() + ": " + gainedQ2Pts);
    }

    public void calculateStudentPts (ArrayList<Student> students, int secNum) {
        for (int i = 0; i < students.size(); i++) {
            Student temp = students.get(i);
            int sn = temp.secNum;
            if (sn == secNum) {
                temp.addPts(gainedQ1Pts, 1); temp.addPts(gainedQ2Pts, 2);
                temp.subtractPts(Q1SyntaxPts, 1); temp.subtractPts(Q2SyntaxPts, 2);
            }
        }
    }

}