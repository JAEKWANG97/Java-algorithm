package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2644 {

    static int n;
    static int[][] graph;
    static boolean[] visited;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;
        }
        bfs(a, b);

    }

    private static void bfs(int a, int b) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        visited[a] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {

                int current = queue.poll();
                if (current == b) {
                    System.out.println(time);
                    return;
                }
                for (int i = 1; i <= n; i++) {
                    if (graph[current][i] == 1 && !visited[i]) {
                        queue.offer(i);
                        visited[i] = true;
                    }
                }
            }
            time++;
        }
        System.out.println(-1);
    }
}
