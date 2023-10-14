import java.io.*;
import java.util.*;

class Sortofsorting {
    public static void main(String[] args) throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(inp.readLine());
        ArrayList<String> consolidatedNames = new ArrayList<String>();
        
        while (num != 0) {
            ArrayList<Name> names = new ArrayList<Name>();

            for (int i = 0; i < num; i++) {
                names.add(new Name(inp.readLine()));
            }

            Collections.sort(names);

            for (Name name: names) {
                consolidatedNames.add(name.getName());
            }
            consolidatedNames.add("");

            num = Integer.parseInt(inp.readLine());
        } 

        consolidatedNames.remove(consolidatedNames.size() - 1);
        PrintWriter print = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        for (String name: consolidatedNames) {
            print.println(name);
        }

        print.flush();
    }
}


class Name implements Comparable<Name> {
    private String name;

    public Name(String name) {
        this.name = name;
    }
 
    public int compareTo(Name otherName) {
        if ((int) this.name.charAt(0) != (int) otherName.getName().charAt(0)) {
            return (int) this.name.charAt(0) - (int) otherName.getName().charAt(0);
        } else if((int) this.name.charAt(1) != (int) otherName.getName().charAt(1)) {
            return (int) this.name.charAt(1) - (int) otherName.getName().charAt(1);
        } else {
            return 0;
        }
    }

    public String getName() {
        return this.name;
    }
}