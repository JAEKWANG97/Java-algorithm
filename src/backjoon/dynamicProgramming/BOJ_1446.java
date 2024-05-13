package backjoon.dynamicProgramming;

import java.util.*;
import java.io.*;

class BOJ_1446 {
    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, D;
    static int[] dp;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        int result = dfs(0); // 시작 지점에서 DFS 호출
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dp = new int[D + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i <= D; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (to <= D) {
                graph.get(from).add(new Node(to, weight));
            }
        }
    }

    private static int dfs(int cur) {
        if (cur == D) {
            return 0;
        }

        if (dp[cur] != -1) {
            return dp[cur];
        }

        dp[cur] = Integer.MAX_VALUE;

        for (Node next : graph.get(cur)) {
            dp[cur] = Math.min(dp[cur], dfs(next.to) + next.weight);
        }

        dp[cur] = Math.min(dp[cur], dfs(cur + 1) + 1);

        return dp[cur];
    }
}
