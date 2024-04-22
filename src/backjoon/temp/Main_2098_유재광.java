package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2098_유재광 {
    static class Node {
        int no;
        int value;

        public Node(int no, int value) {
            this.no = no;
            this.value = value;
        }
    }

    static long minCost = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int item = Integer.parseInt(st.nextToken());
                map[i][j] = item;
            }
        }

        for (int i = 0; i < N; i++) {
            dfs(map, i, i, new boolean[N], 0, 0, N);
        }

        System.out.println(minCost);

    }

    static void dfs(int[][] map, int start, int idx, boolean[] visited, long cost, int count, int n) {
        if (cost > minCost) {
            return;
        }

        if (count == n && idx == start) { // 모든 도시를 방문하고 시작 도시로 돌아온 경우
            minCost = Math.min(cost, minCost);
            return;
        }

        for (int next = 0; next < n; next++) {
            if (!visited[next] && map[idx][next] > 0) { // 다음 도시를 아직 방문하지 않았고, 이동할 수 있는 경우
                visited[next] = true;
                dfs(map, start, next, visited, cost + map[idx][next], count + 1, n);
                visited[next] = false; // 백트래킹
            }
        }
    }

}

