/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lcs;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Longest Common Subsequence.
 *
 * This program expects user input of a test level and 
 * then first sequence and then the second.
 * For example, java lcs.LCSTest (program output removed)
 * >1 GTTCCTAATA CGATAATTGAGA 
 * --- program output
 *
 * or
 *
 * java lcs.LCSTest (program output removed) 
 * >2 lullabybabies skullandbones 
 * --- program output
 *
 * @author lewi0146
 */
public class LCSTest {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();
        String[] input = line.split(" ");

        int testLevel = Integer.parseInt(input[0]);
        LCS lcs = new LCS(input[1], input[2]);

        if (testLevel > 0) {
            lcs.calculateMatrix();
            lcs.printMatrix();
            System.out.println("Length of LCS = " + lcs.getLongestLength());
        }

        if (testLevel > 1) {
            System.out.println("LCS: " + lcs.getLCS());
        }

        if (testLevel > 2) {
            List<String> seqsList = lcs.getAllLCS(input[1].length(), input[2].length());
            
            System.out.println("All sequences (" + seqsList.size() + ")");
            for (String s : seqsList) {
                System.out.println(s);
            }
        }

    }
}
