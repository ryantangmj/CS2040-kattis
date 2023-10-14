import java.io.*;

class Spelling {
    public static void main(String[] args) throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        int numLines = Integer.parseInt(inp.readLine());
        String[] lines = new String[numLines];
        String[] dictChar = new String[] {"2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55",
            "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999"};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numLines; i++) {
            lines[i] = inp.readLine();
        }
        
        for (int i = 0; i < numLines; i++) {
            System.out.printf("case #%d: ", i + 1);
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == ' ') {
                    if (sb.length() > 0 && '0' == sb.charAt(sb.length() - 1)) {
                        sb.append(" ");
                    }

                    sb.append("0");
                } else {
                    String current = dictChar[(int)lines[i].charAt(j) - (int)'a'];

                    if (sb.length() > 0 && current.charAt(0) == sb.charAt(sb.length() - 1)) {
                        sb.append(" ");
                    }
                    sb.append(current);
                }
            }
            System.out.println(sb);
            sb.delete(0, sb.length());
        }

        
    }
}