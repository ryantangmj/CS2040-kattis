import java.io.*;
import java.util.*;

class Relayteam{
    public static void main(String[] args) throws IOException {
        TimeComparator timeComp = new TimeComparator();
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(inp.readLine());
        Person[] p = new Person[num];
        
        for (int i = 0; i < num; i++) {
            String details = inp.readLine();
            String[] eachDetail = details.split(" ");
            
            p[i] = new Person(eachDetail[0], Double.parseDouble(eachDetail[1]), 
                Double.parseDouble(eachDetail[2]));
        }
        
        List<Person> people = Arrays.asList(p);
        Collections.sort(people, timeComp);

        double currentTime = people.get(0).getTimeOne() + people.get(1).getTimeTwo() 
            + people.get(2).getTimeTwo() + people.get(3).getTimeTwo();
        int first = 0;
        
        for (int i = 1; i < num; i++) {
            double newTime = people.get(i).getTimeOne();
            
            for (int j = 0; j < 3; j++) {
                newTime += j != i ? people.get(j).getTimeTwo() : people.get(3).getTimeTwo();
            }
            
            if (newTime < currentTime) {
                currentTime = newTime;
                first = i;
            }
        }
        
        System.out.println(currentTime);
        System.out.println(people.get(first).getName());

        int count = 1;
        int i = 0;
        
        while (count != 4) {
            if (i != first) {
                System.out.println(people.get(i).getName());
                count++;
            }   
            i++;
        }
    }
}

class Person {
    private String name;
    private double timeOne;
    private double timeTwo;
    
    public Person (String name, double timeOne, double timeTwo) {
        this.name = name;
        this.timeOne = timeOne;
        this.timeTwo = timeTwo;
    }
    
    public String getName() {
        return name;
    }
    
    public double getTimeOne() {
        return timeOne;
    }
    
    public double getTimeTwo() {
        return timeTwo;
    }
}

class TimeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) { 
       if (p1.getTimeTwo() - p2.getTimeTwo() < 0) {
        return -1;
       } else if (p1.getTimeTwo() - p2.getTimeTwo() > 0) {
        return 1;
       } else {
        return 0;
       }
    }
}