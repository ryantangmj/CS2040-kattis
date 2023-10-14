import java.io.*;
import java.util.*;

class Conformity {
    public static void main(String[] args) throws IOException {
        Kattio kattio = new Kattio(System.in, System.out);
        int[] subjects = new int[5];
        HashMap<String, Integer> combinations = new HashMap<String, Integer>();
    
        int numCombi = kattio.getInt();
        for (int i = 0; i < numCombi; i++) {
            for (int j = 0; j < 5; j++) {
                subjects[j] = kattio.getInt();
            }

            Arrays.sort(subjects);
            String strSubject = Arrays.toString(subjects);

            if (combinations.containsKey(strSubject)) {
                combinations.replace(strSubject, combinations.get(strSubject) + 1);
            } else {
                combinations.put(strSubject, 1);  
            }
        }

        int maxNum = 0;
        int count = 0;
        
        for (Integer i: combinations.values()) {
            if (i > maxNum) {
                maxNum = i;
                count = i;
            } else if (i == maxNum) {
                count += i;
            }
        }

        kattio.println(count);
        kattio.close();
    }
}