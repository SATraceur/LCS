package lcs;
/*
    getAllLCS method inspired by http://stackoverflow.com/questions/16848704/all-possible-lcslongest-common-subsequence-of-two-strings
    and modified to suit accordingly
*/
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LCS {

    private String x;
    private String y;
    private int[][] L;

    
    public LCS(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public int[][] calculateMatrix() {
        L = new int[x.length()+1][y.length()+1];              // Ititilize matrix size as length+1 to account for row of 0's
        for(int i = 0; i <= x.length(); i++) { L[i][0] = 0; } // Initilize column with 0's
        for(int j = 0; j <= y.length(); j++) { L[0][j] = 0; } // Initilize row with 0's
        
        for(int i = 1; i <= x.length(); i++) {                // Start past column of 0's
            for(int j = 1; j <= y.length(); j++) {            // Start past row of 0's
                if(x.charAt(i-1) == y.charAt(j-1)) {          // MATCH (diagonal move)
                    L[i][j] = L[i-1][j-1] + 1;
                } else {                                      // NOT MATCH (horizontal or vertical move)
                    L[i][j] = Math.max(L[i-1][j],L[i][j-1]);  // Set current position to max of horizontal and vertical positions
                }
            }
        }       
        return L;
    }

    public void printMatrix() {
        if (L != null) {
            for (int i = 0; i < L.length; i++) {
                for (int j = 0; j < L[i].length; j++) {
                    System.out.print(" " + L[i][j]);
                }
                System.out.println();
            }
        }
    }

    public int getLongestLength() { return L[x.length()][y.length()]; }

    public String getLCS() {
        StringBuilder LCS = new StringBuilder();
        int i = x.length();
        int j = y.length();
         
        while(i > 0 && j > 0) {
            if(x.charAt(i-1) == y.charAt(j-1)) {           // MATCH!
                LCS.insert(0,x.charAt(i-1));               // Add character to string appropriatly.
                i--; j--;                                  // Move diagonal.    
            } else {                                       // NOT A MATCH!
                if (L[i-1][j] == L[i][j-1]) { j--; }       // If horizontal and vertical positions are equal, move left.
                else if(L[i-1][j] < L[i][j-1]) { j--; }    // Else, move to maximum horizontal or vertical position.
                else { i--; }
            }
        }  
        return LCS.toString();
    }

    public List<String> getAllLCS(int length1, int length2) {
        if(length1 == 0 || length2 == 0) {                                 // If matrix edge is reached... 
            List<String> L1 = new ArrayList<>();
            L1.add("");
            return L1;                                                     // Return empty list
        }
        if(x.charAt(length1 - 1) == y.charAt(length2 - 1)) {               // MATCH!
            List<String> L1 = getAllLCS(length1 - 1, length2 - 1);         // Move diagonal and get all LCS's
            List<String> L2 = new ArrayList<>();
            for(String temp : L1) {                                        // Construct LCS strings.
                temp += x.charAt(length1 - 1);
                L2.add(temp);                                              // Add constructed strings to a new list.
            }
            return removeDuplicates(L2);                                   // Return new list with duplicates removed.
        } else {                                                           // NOT A MATCH!
            List<String> L1 = new ArrayList<>();
            List<String> L2 = new ArrayList<>(); 
            if(L[length1 - 1][length2] >= L[length1][length2 - 1]) {       // Move left 
                L1 = getAllLCS(length1 - 1, length2);                      // Get all LCS's
            }
            if(L[length1][length2 - 1] >= L[length1 - 1][length2]) {       // Move up
                L2 = getAllLCS(length1, length2 -1);                       // Get all LCS's
            }
            for(String temp : L1) { L2.add(temp); }                        // Combine LCS lists from horizontal and vertical moves.
            return removeDuplicates(L2);                                   // Return list with removed duplicates.
        }        
    }
    
    private List<String> removeDuplicates(List<String> L) {
        Set<String> s = new LinkedHashSet<>(L);
        return new ArrayList<>(s);
    }
 }
