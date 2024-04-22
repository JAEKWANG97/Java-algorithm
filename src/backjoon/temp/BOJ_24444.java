package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_24444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int START = Integer.parseInt(st.nextToken());

        List<PriorityQueue<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjList.add(new PriorityQueue<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        Queue<Integer> que = new ArrayDeque<>();
        que.add(START);
        int[] visited = new int[N + 1];
        visited[START] = 1;
        int cnt = 1;
        while (!que.isEmpty()) {
            int cur = que.poll();

            while (!adjList.get(cur).isEmpty()) {
                int next = adjList.get(cur).poll();
                if (visited[next] == 0) {
                    que.add(next);
                    visited[next] = ++cnt;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }

        System.out.println(sb);
    }
}
