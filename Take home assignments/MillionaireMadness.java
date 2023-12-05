import java.util.*;

class MillionaireMadness {
    static class IntegerPair implements Comparable<IntegerPair> {
        public final int t;
        public final int u;
        public final int weight;

        public IntegerPair(int t, int u, int weight) {
            this.t = t;
            this.u = u;
            this.weight = weight;
        }

        public int compareTo(IntegerPair p) {
            return this.weight - p.weight;
        }
    }
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int row = kattio.getInt();
        int col = kattio.getInt();
        int[][] ladders = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ladders[i][j] = kattio.getInt();
            }
        }

        boolean[][] visited = new boolean[row][col]; 
        PriorityQueue<IntegerPair> queue = new PriorityQueue<>();
        int ladder = 0;
        queue.offer(new IntegerPair(0,0,0));

        while (!visited[row-1][col-1]) {
            IntegerPair temp = queue.poll();
            int r = temp.t;
            int c = temp.u;
            ladder = Math.max(temp.weight, ladder);
            int height = ladders[r][c];
            visited[r][c] = true;
     
            if (r > 0 && !visited[r - 1][c]) {
                System.out.println("go up");
                queue.offer(new IntegerPair(r - 1, c, ladders[r - 1][c] - height));
            } if (r < row - 1 && !visited[r + 1][c]) {
                System.out.println("go down");
                queue.offer(new IntegerPair(r + 1, c, ladders[r + 1][c] - height));
            } if (c > 0 && !visited[r][c - 1]) {
                System.out.println("go left");
                queue.offer(new IntegerPair(r, c - 1, ladders[r][c - 1] - height));
            } if (c < col - 1 && !visited[r][c + 1]) {
                System.out.println("go right");
                queue.offer(new IntegerPair(r, c + 1, ladders[r][c + 1] - height));
            }

        }

        kattio.println(ladder);
        kattio.close();
    }
}

