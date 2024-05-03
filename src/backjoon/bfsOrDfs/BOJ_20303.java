package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20303 {

    static class Item {
        int count;
        int value;

        public Item(int count, int value) {
            this.count = count;
            this.value = value;
        }
    }

    static int N, M, K;
    static int[] candies;
    static List<List<Integer>> adjList;
    static List<Item> items = new ArrayList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        candies = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

    }

    private static void solve() {
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int[] kv;
            if (!visited[i]) {
                kv = bfs(i, visited);
                items.add(new Item(kv[0], kv[1]));
            }
        }
        for (Item item : items) {
            System.out.println(item.count + " " + item.value);
        }
        dp();
    }

    private static int[] bfs(int start, boolean[] visited) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(start);
        visited[start] = true;
        int count = 0;
        int value = 0;
        while (!que.isEmpty()) {
            int cur = que.poll();
            count += 1;
            value += candies[cur];
            for (Integer next : adjList.get(cur)) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                que.add(next);
            }
        }
        return new int[] { count, value };
    }

    private static void dp() {
        int[] dp = new int[N + 1];

        for (Item item : items) {
            if (item.count >= K) {
                continue;
            }

            for (int i = N; i >= item.count; i--) {
                dp[i] = Math.max(dp[i], dp[i - item.count] + item.value);
            }
        }
    }

}
