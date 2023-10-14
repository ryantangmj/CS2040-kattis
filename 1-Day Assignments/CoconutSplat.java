import java.io.*;
import java.util.*;

class CoconutSplat {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String[] details = br.readLine().split(" ");
        int syllables = Integer.parseInt(details[0]);
        int num = Integer.parseInt(details[1]);

        ArrayList<Hand> hands = new ArrayList<Hand>();
        for (int i = 0; i < num; i++) {
            hands.add(new Hand(i + 1));
        }

        int currentIndex = 0;
        while (hands.size() > 1) {
            currentIndex = (currentIndex + syllables - 1) % hands.size();
            hands.set(currentIndex, hands.get(currentIndex).incrementCounter());
        
            if (hands.get(currentIndex).getCounter() == 1) {
                hands.add(currentIndex, new Hand(hands.get(currentIndex).getNum(), 1));
            } else if (hands.get(currentIndex).getCounter() == 2) {
                currentIndex++;
            } else if (hands.get(currentIndex).getCounter() == 3) {
                hands.remove(currentIndex);
            }
        }

        pw.println(hands.get(0).getNum());
        pw.flush();
    }
}

class Hand {
    private final int num;
    private final int counter;

    public Hand(int num) {
        this.num = num;
        this.counter = 0;
    }

    public Hand(int num, int counter) {
        this.num = num;
        this.counter = counter;
    }

    public Hand incrementCounter() {
        return new Hand(num, counter + 1);
    }

    public int getNum() {
        return num;
    }

    public int getCounter() {
        return counter;
    }
}