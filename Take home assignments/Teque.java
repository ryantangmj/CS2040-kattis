import java.util.*;
import java.io.*;


class Teque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        TequeImpl teque = new TequeImpl();
        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" "); 

            if (!input[0].equals("get")) {
                if (input[0].equals("push_front")) {
                    teque.pushFront(Integer.parseInt(input[1]));
                } else if (input[0].equals("push_back")) {
                    teque.pushBack(Integer.parseInt(input[1]));
                } else {
                    teque.pushMiddle(Integer.parseInt(input[1]));
                }
            } else {
                pw.println(teque.get(Integer.parseInt(input[1])));
            }
        }

        pw.flush();
    }
}


class TequeImpl {
    public HashMap<Integer, Integer> front;
    public HashMap<Integer, Integer> back;
    public int startFront;
    public int endFront;
    public int startBack;
    public int endBack;

    public TequeImpl() {
        this.front = new HashMap<>();
        this.back = new HashMap<>();
        this.startFront = 0;
        this.endFront = 0;
        this.startBack = 0;
        this.endBack = 0;
    }

    public void pushFront(int num) {
        startFront--;
        front.put(startFront, num);
        balance();
    }

    public void pushBack(int num) {
        back.put(endBack, num);
        endBack++;
        balance();
    }

    public void pushMiddle(int num) {
        if (front.size() == back.size()) {
            startBack--;
            back.put(startBack, num);
        } else {
            front.put(endFront, back.get(startBack));
            endFront++;
            back.put(startBack, num);
        }
    }

    public int get(int index) {
        if (index < (endFront - startFront)) {
            return front.get(index + startFront);
        } else {
            index -= (endFront - startFront);
            return back.get(index + startBack);
        }
    }

    public void balance() {
        if (front.size() < back.size() - 1) {
            front.put(endFront, back.get(startBack));
            endFront++;
            back.remove(startBack);
            startBack++;
        } else if (front.size() > back.size()) {
            startBack--;
            endFront--;
            back.put(startBack, front.get(endFront));
            front.remove(endFront);
        }
    }
}