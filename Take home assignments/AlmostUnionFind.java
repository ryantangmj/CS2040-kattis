import java.util.*;

class AlmostUnionFind {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        UFDS sets = new UFDS(kattio.getInt());

        int operations = kattio.getInt();

        for (int i = 0; i < operations; i++) {
            int operation = kattio.getInt();
            if (operation == 1) {
                sets.unionSet(kattio.getInt(), kattio.getInt());
            } else if (operation == 2) {
                sets.addSet(kattio.getInt(), kattio.getInt());
            } else {
                int q = kattio.getInt();
                kattio.println(sets.getSize(q) + " " + sets.getSum(q));
            }    
        }
        kattio.close();
    }
}

class UFDS {                                    
    public int[] p;
    public int[] parent;
    public int[] rank; 
    public long[] size;
    public long[] sum;

    public UFDS(int N) {
        p = new int[N + 1];
        parent = new int[N + 1];
        rank = new int[N + 1];
        size = new long[N + 1];
        sum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            this.p[i] = i;
            this.parent[i] = i;
            this.rank[i] = 0;
            this.size[i] = 1;
            this.sum[i] = i;
        }
    }

    public int findSet(int i) {
        int a = parent[i];
        int b = p[a];
        while (a != b) {
            p[a] = p[b];
            a = p[a];
            b = p[a];
        }
        return a; 
    }

    public Boolean isSameSet(int i, int j) { 
        return findSet(i) == findSet(j); 
    }

    public void addSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);                  
            parent[i] = y;

            size[x] -= 1;
            size[y] += 1;
            sum[x] -= i;
            sum[y] += i;
            
            if (rank[y] == 0) {
                rank[y] += 1; 
            } 
        }
    }

    public void unionSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            int x = findSet(i), y = findSet(j);

            if (rank[x] > rank[y]) {
                p[y] = x;
                size[x] += size[y];
                sum[x] += sum[y];
                size[y] = 0;
                sum[y] = 0;
            } else { 
                p[x] = y;
                size[y] += size[x];
                sum[y] += sum[x];
                size[x] = 0;
                sum[x] = 0;

                if (rank[x] == rank[y]) {
                    rank[y] += 1; 
                }                 
            } 
        } 
    }

    public long getSize(int i) {
        return size[findSet(i)];
    }

    public long getSum(int i) {
        return sum[findSet(i)];
    }
}
