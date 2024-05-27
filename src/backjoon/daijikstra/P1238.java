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
    // 인접 리스트에 담을 Node 클래스
    static class Node implements Comparable<Node> {
        int no;
        int value;

        public Node(int no, int value) {
            this.no = no;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    static List<List<Node>> adjList;
    static List<List<Node>> reverseAdjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 마을의 수
        int m = Integer.parseInt(st.nextToken()); // 간선의 수
        int x = Integer.parseInt(st.nextToken()); // 목표 노드 인덱스
        adjList = new ArrayList<>();
        reverseAdjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
            reverseAdjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, value));
            reverseAdjList.get(to).add(new Node(from, value));
        }

        int[] toX = dijkstra(x, n, adjList);
        int[] fromX = dijkstra(x, n, reverseAdjList);

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, toX[i] + fromX[i]);
        }

        System.out.println(maxTime);
    }

    static int[] dijkstra(int start, int n, List<List<Node>> graph) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            if (currentNode.value > distance[currentNode.no])
                continue;

            for (Node nextNode : graph.get(currentNode.no)) {
                if (distance[nextNode.no] > distance[currentNode.no] + nextNode.value) {
                    distance[nextNode.no] = distance[currentNode.no] + nextNode.value;
                    priorityQueue.add(new Node(nextNode.no, distance[nextNode.no]));
                }
            }
        }

        return distance;
    }
}