import java.io.*;
import java.util.*;

class WeakVertices {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int num = kattio.getInt();
        Graph graph;

        while (num != -1) {
            int[][] mtx = new int[num][num];

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    mtx[i][j] = kattio.getInt();
                }
            }

            graph = new Graph(num, mtx);
            kattio.println(graph.weakVertice().trim());
            num = kattio.getInt();
        }

        kattio.close();
    }
}

class Graph {
    private int[][] adjMatrix;
    private int numVertices;

    public Graph(int numVertices, int[][] adjMatrix) {
        this.numVertices = numVertices;
        this.adjMatrix = adjMatrix;
    }

    public String weakVertice() {
        StringBuilder sb = new StringBuilder();
        boolean[] hasTriangle = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (adjMatrix[i][j] == 1 && oneCommonVertice(i, j)) {  
                        hasTriangle[i] = true;
                        hasTriangle[j] = true;
                        break;   
                    }
                }  
        }
        

        for (int i = 0; i < numVertices; i++) {
            if (!hasTriangle[i]) {
                sb.append(i + " ");
            }
        }

        return sb.toString();
    }

    private boolean oneCommonVertice(int u, int v) {
        for (int i = v + 1; i < numVertices; i++) {
            if (adjMatrix[u][i]  == 1 && adjMatrix[v][i] == 1) {
                return true;
            }
        }

        return false;
    }
}