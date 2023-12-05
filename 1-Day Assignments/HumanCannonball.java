import java.util.*;

class HumanCannonball {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        Coordinate startCoord = new Coordinate(kattio.getDouble(), kattio.getDouble());
        Coordinate endCoord = new Coordinate(kattio.getDouble(), kattio.getDouble());
        int numCannons = kattio.getInt();

        Coordinate[] coordinates = new Coordinate[numCannons];
        for (int i = 0; i < numCannons; i++) {
            coordinates[i] = new Coordinate(kattio.getDouble(), kattio.getDouble());
        }

        double[][] weightMtx = new double[numCannons + 2][numCannons + 2];
        weightMtx[0][weightMtx.length - 1] = Math.hypot(startCoord.y - endCoord.y, startCoord.x - endCoord.x) / 5;
        weightMtx[weightMtx.length - 1][0] = Math.hypot(startCoord.y - endCoord.y, startCoord.x - endCoord.x) / 5;

        for (int i = 0; i < numCannons ; i++) {
            Coordinate firstCannon = coordinates[i];
            weightMtx[0][i + 1] = Math.hypot(startCoord.y - firstCannon.y, startCoord.x - firstCannon.x) / 5;
            weightMtx[i + 1][weightMtx.length - 1] = Math.abs(50 - Math.hypot(firstCannon.y - endCoord.y, firstCannon.x - endCoord.x)) / 5 + 2;

            for (int j = i + 1; j < numCannons; j++) {
                Coordinate secondCannon = coordinates[j];
                double dist = Math.hypot(firstCannon.y - secondCannon.y, firstCannon.x - secondCannon.x);
                double runTime = dist / 5;
                double launchTime = Math.abs(dist - 50) / 5 + 2;
                weightMtx[i + 1][j + 1] = Math.min(runTime, launchTime);
                weightMtx[j + 1][i + 1] = Math.min(runTime, launchTime);
            }
        }

        double[] time = new double[numCannons + 2];
        for (int i = 1; i < time.length; i++) {
            time[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Cannon> pq = new PriorityQueue<Cannon>();
        pq.add(new Cannon(0, time[0]));

        while (!pq.isEmpty()) {
            Cannon curr = pq.poll();

            if (curr.weight == time[curr.i]) {
                for (int i = 0; i < time.length; i++) {
                    if (i != curr.i && time[i] > time[curr.i] + weightMtx[curr.i][i]) {
                        time[i] = time[curr.i] + weightMtx[curr.i][i];
                        pq.add(new Cannon(i, time[i]));
                    }
                }
            }
        }

        kattio.println(time[weightMtx.length - 1]);
        kattio.close();
    }        
}

class Coordinate {
    double x, y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Cannon implements Comparable<Cannon> {
    public int i;
    public double weight;

    public Cannon(int i, double weight) {
        this.i = i;
        this.weight = weight;
    }

    public int compareTo(Cannon other) {
        return Double.compare(this.weight, other.weight);
    }
}