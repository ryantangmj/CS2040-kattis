class Islands {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int row = kattio.getInt();
        int col = kattio.getInt();
        char[][] islands = new char[row][col];
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            String island = kattio.getWord();
            for (int j = 0; j < col; j++) {
                islands[i][j] = island.charAt(j);
            }
        }

        kattio.println(countIslands(islands, visited));
        kattio.close();
    }

    public static int countIslands(char[][] islands, boolean[][] visited) {
        int count  = 0;

        for (int i = 0; i < islands.length; i++) {
            for (int j = 0; j < islands[0].length; j++) {
                if (islands[i][j] == 'L' && !visited[i][j]) {
                    dfs(i, j, islands, visited);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(int i, int j, char[][] islands, boolean[][] visited) {
        if (visited[i][j]) {
            return;
        } 
        
        visited[i][j] = true;

        int[] horizontal = {0, 0, -1, 1};
        int[] vertical = {1, -1, 0, 0};

        for (int a = 0; a < 4; a++) {
            int checkHorizontal = i + horizontal[a]; 
            int checkVertical = j + vertical[a];
            
            if (checkHorizontal < 0 || checkVertical < 0 || checkHorizontal >= islands.length || checkVertical >= islands[0].length) {
                continue; 
            }
            
            if (islands[checkHorizontal][checkVertical] == 'C') {
                dfs(checkHorizontal, checkVertical, islands, visited); 
            } 
            if (islands[checkHorizontal][checkVertical] == 'L') {
                dfs(checkHorizontal, checkVertical, islands, visited); 
            }
        }
    }
}