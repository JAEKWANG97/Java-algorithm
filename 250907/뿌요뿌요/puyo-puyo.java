import java.util.*;

public class Main {
    static int answer = 0;
    static int n; 
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int value = dfs(i, j, grid[i][j]);
                    max = Math.max(max, value);
                    if (value >= 4) {
                        answer++;
                        
                    }
                }
            }
        }

        System.out.println(answer + " " + max);
    }

    private static int dfs(int x, int y, int value) {
        visited[x][y] = true;
        int size = 1; // 자기 자신 포함

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (visited[nx][ny]) continue;
            if (grid[nx][ny] != value) continue;

            size += dfs(nx, ny, value);
        }

        return size;
    }
}
