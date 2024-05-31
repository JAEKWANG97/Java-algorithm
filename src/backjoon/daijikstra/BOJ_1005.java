package backjoon.daijikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, K, W;
    static int[] timeToBuild, inDegree, result;
    static List<List<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            init();
            topologicalSort();
            System.out.println(result[W]);
        }
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        timeToBuild = new int[N + 1];
        inDegree = new int[N + 1];
        result = new int[N + 1];
        adjList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            timeToBuild[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
            inDegree[to]++;
        }

        W = Integer.parseInt(br.readLine());
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            result[i] = timeToBuild[i];
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : adjList.get(current)) {
                result[next] = Math.max(result[next], result[current] + timeToBuild[next]);
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}