import java.io.*;
import java.util.*;

class Workstation {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int numResearchers = kattio.getInt();
        int maxTime = kattio.getInt();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        ArrayList<Researcher> researchers = new ArrayList<Researcher>();

        for (int i = 0; i < numResearchers; i++) {
            researchers.add(new Researcher(kattio.getInt(), kattio.getInt()));
        }

        Collections.sort(researchers);
        int counter = 0;
        
        for (Researcher researcher: researchers) {
            while (!pq.isEmpty()) {   
                if (researcher.getArrival() < pq.peek()) {
                    break;
                } else {  
                    if (researcher.getArrival() - pq.poll() <= maxTime) {    
                        counter++;             
                        break;
                    }  
                }
            }
            pq.add(researcher.getArrival() + researcher.getDuration());
        }

        kattio.println(counter);
        kattio.flush(); 
    }
}

class Researcher implements Comparable<Researcher> {
    private final int arrival;
    private final int duration;

    public Researcher(int arrival, int duration) {
        this.arrival = arrival;
        this.duration = duration;
    }

    public int getArrival() {
        return arrival;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Researcher other) {
        return this.arrival - other.getArrival();
    }
}