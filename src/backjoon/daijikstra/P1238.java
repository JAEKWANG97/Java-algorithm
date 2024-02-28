package backjoon.daijikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1238 {
    // 인접 리스트에 담을 Node class
    static class Node implements Comparable<Node> {
        int no;
        int value;

        public Node(int no, int value) {
            this.no = no;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) { // 인접리스트에 넣엇을 때 가장 최소가 아닌 최대
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 마을의 수
        int m = Integer.parseInt(st.nextToken()); // 간선의 수
        int x = Integer.parseInt(st.nextToken()); // 목표 노드 인덱스
        List<List<Node>> adjList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, value));
        }
    }

    static void dijkstra(int start, int n, int x) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
    }
}
