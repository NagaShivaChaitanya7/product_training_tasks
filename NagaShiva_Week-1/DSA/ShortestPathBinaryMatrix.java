import java.util.*;

public class ShortestPathBinaryMatrix {
    public static int shortestPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (grid[0][0] != 0 || grid[n - 1][m - 1] != 0) {
            return -1;
        }

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int distance = current[2];
            if (r == n - 1 && c == m - 1) {
                return distance;
            }
            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, distance + 1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(shortestPath(new int[][]{
            {0, 1}, {1, 0}
        })); 
        System.out.println(shortestPath(new int[][]{
            {0, 0, 0}, {1, 1, 0}, {1, 1, 0}
        })); 
        System.out.println(shortestPath(new int[][]{
            {1, 0}, {0, 0}
        })); 
        System.out.println(shortestPath(new int[][]{
            {0}
        })); 
        System.out.println(shortestPath(new int[][]{
            {0, 1}, {1, 1}
        })); 
    }
}
