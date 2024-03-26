package backjoon.bfsOrDfs;


import java.io.*;
import java.util.*;

public class BOJ_1167 {
    private static int maxNode;

    static class Node {

        int to;
        int value;

        public Node(int to, int value) {
            this.to = to;
            this.value = value;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static List<List<Node>> adjList;
    static boolean[] hasChild;

    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        search();
        System.out.println(maxValue);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int item = Integer.parseInt(st.nextToken());
                if (item == -1) {
                    break;
                }
                int to = item;
                int value = Integer.parseInt(st.nextToken());

                adjList.get(from).add(new Node(to, value));
                adjList.get(to).add(new Node(from, value));
            }
            
        }
    }

    private static void search() {
        boolean[] visited = new boolean[N + 1];
        dfs(1, 0, visited);
        Arrays.fill(visited, false);
        maxValue = Integer.MIN_VALUE;
        dfs(maxNode, 0, visited);
    }

    private static void dfs(int start, int value, boolean[] visited) {
        visited[start] = true;
        if (maxValue < value) {
            maxValue = value;
            maxNode = start;
        }

        for (Node node : adjList.get(start)) {
            if (!visited[node.to]) {
                dfs(node.to, value + node.value, visited);
            }
        }
    }

}
