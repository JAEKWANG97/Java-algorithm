package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P24480 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, R;
    static Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    static int[] visitOrder; // 각 정점의 방문 순서를 저장할 배열

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visitOrder = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            map.put(i, new PriorityQueue<>(Comparator.reverseOrder()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());


            map.get(n).add(m);
            map.get(m).add(n);
        }

        dfs(new boolean[N + 1], map, R, 1);


        for (int i = 1; i <= N; i++) {
            sb.append(visitOrder[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(boolean[] visited, Map<Integer, PriorityQueue<Integer>> map, int start, int order) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        visitOrder[start] = order++;

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            PriorityQueue<Integer> neighbors = map.get(cur);
            if (neighbors != null) {
                while (!neighbors.isEmpty()) {
                    int next = neighbors.poll();
                    if (!visited[next]) {
                        visited[next] = true;
                        stack.push(cur);
                        stack.push(next);
                        visitOrder[next] = order++;
                        break;
                    }
                }
            }
        }
    }
}
