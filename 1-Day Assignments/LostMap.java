import java.util.ArrayList;
import java.util.Collections;

class LostMap {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);

        int n = kattio.getInt();
        ArrayList<Edge> edgeList = new ArrayList<>();
        UFDS ufds = new UFDS(n + 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int weight = kattio.getInt();

                if (i != j) {
                    edgeList.add(new Edge(weight, i + 1, j + 1));
                } 
            }
        }

        Collections.sort(edgeList);
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            if (!ufds.isSameSet(edge.i, edge.j)) {
                ufds.unionSet(edge.i, edge.j);
                kattio.println(edge.i + " " + edge.j);
            }
        }

        kattio.close();
    }
}

class Edge implements Comparable<Edge> {
    public int weight;
    public int i;
    public int j;

    Edge(int weight, int i, int j) {
        this.weight = weight;
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class UFDS {                                              
    public int[] p;
    public int[] rank;
    public int[] setSize;
    public int numSets;

    public UFDS(int N) {
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;

        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[i] = 1;
        }
    }

    public int findSet(int i) { 
        if (p[i] == i) {
            return i;
        }
        else {
            p[i] = findSet(p[i]);
            return p[i]; 
        } 
    }

    public Boolean isSameSet(int i, int j) { 
        return findSet(i) == findSet(j); 
    }

    public void unionSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            numSets--;
            int x = findSet(i), y = findSet(j);
            
            if (rank[x] > rank[y]) {
                p[y] = x;
                setSize[x] = setSize[x] + setSize[y]; 
            }
            else { 
                p[x] = y;
                setSize[y] = setSize[x] + setSize[y]; 

                if (rank[x] == rank[y]) {
                    rank[y] = rank[y]+1; 
                }      
            } 
        } 
    }

    public int numDisjointSets() { 
        return numSets; 
    }

    public int sizeOfSet(int i) { 
        return setSize[findSet(i)]; 
    }
}