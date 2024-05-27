package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int max;
    static int[] countArr;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }
        for (int i = 1; i <= N; i++) {
            if (max < countArr[i]) {
                max = countArr[i];
            }
        }
        for (int i = 1; i <= N; i++) {
            if (max == countArr[i]) {
                System.out.print(i + " ");
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
        }
        countArr = new int[N + 1];
        max = 0;
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        if (countArr[start] != 0) {
            return;
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < graph.get(current).size(); i++) {
                int next = graph.get(current).get(i);
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    countArr[start]++;
                }
            }
        }
    }
}
