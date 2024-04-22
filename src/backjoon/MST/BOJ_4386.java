package backjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class UnionFind {
    private int[] root;
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (root[x] == x) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}

class BOJ_4386 {

    static class Node {
        int no;
        int x;
        int y;

        Node(int no, int x, int y) {
            this.no = no;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double weight;

        Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static int N;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Node> nodeList = new ArrayList<>();
    static List<Edge> edgeList = new ArrayList<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = (int) (Double.parseDouble(st.nextToken()) * 100);
            int y = (int) (Double.parseDouble(st.nextToken()) * 100);
            nodeList.add(new Node(i, x, y));
            // 1, 모든 노드 입력 받기
            // 2. 각 노드 당 거리 계산
            // 3. 간선의 가중치를 기준으로 오름차순 정렬
            // 4. 유니온 파인드 자료구조 초기화
            // 5. 간선 선택 및 사이클 검사
            // 6. 모든 노드가 연결도리 떄까지 반복
        }

        // 인접 리스트 구하기
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                edgeList.add(new Edge(i, j, calculateDistance(i, j)));
            }
        }

        Collections.sort(edgeList);

        UnionFind unionFind = new UnionFind(N);
        double result = 0;
        int count = 0;
        for (Edge edge : edgeList) {
            if (!unionFind.connected(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                result += edge.weight;
                count++;
            }
            if (count == N - 1) {
                break;
            }
        }

        System.out.printf("%.2f", result / 100);

    }

    private static double calculateDistance(int from, int to) {
        Node a = nodeList.get(from);
        Node b = nodeList.get(to);
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

}
