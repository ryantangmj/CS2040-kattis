import java.util.*;

class Dominos {
    private static ArrayList <Integer> toposorted;
    private static ArrayList <ArrayList<Integer>> adjList;
    private static boolean[] sorted;
    private static boolean[] scc;  

    public static void toposort(int tile) {
        sorted[tile] = true;
        ArrayList<Integer> neighbours = adjList.get(tile);

        for (int neighbour : neighbours) {
          if (!sorted[neighbour]) {
            toposort(neighbour);
          }
        }

        toposorted.add(tile); 
    }

    public static void dfs(int tile) { 
        scc[tile] = true;
        ArrayList<Integer> neighbours = adjList.get(tile);

        for (int neighbour : neighbours) {
            if (!scc[neighbour]) {
                dfs(neighbour);
            }
        }
    }

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int testCases = kattio.getInt();
        int count;

        for (int testCase = 0; testCase < testCases; testCase++) {
            count = 0;
            int numTiles = kattio.getInt();
            int numLines = kattio.getInt();
            sorted = new boolean[numTiles];
            scc = new boolean[numTiles];
            toposorted = new ArrayList<>();
            adjList = new ArrayList <ArrayList<Integer>>();

            for (int i = 0; i < numTiles; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < numLines; j++) {
                adjList.get(kattio.getInt() - 1).add(kattio.getInt() - 1);
            }

            for (int tile = 0; tile < numTiles; tile++) {
                if (!sorted[tile]) {
                    toposort(tile);
                }
            }

            for (int tile = numTiles - 1; tile >= 0; tile--) {
                int t = toposorted.get(tile);
                
                if (!scc[t]) {
                    dfs(t);
                    count++;
                }
            }

            kattio.println(count);
        }

        kattio.close();
    }
}