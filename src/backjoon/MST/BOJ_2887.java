package backjoon.MST;


import java.io.*;
import java.util.*;


public class BOJ_2887 {

    public static class UnionFind {
        private int[] root;
        private int[] rank;

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == root[x]) {
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
                    rank[rootX]++;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }


    }

    private static class Node {
        int no;
        int x;
        int y;
        int z;

        public Node(int no, int x, int y, int z) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge {
        int from;
        int to;

        int value;

        public Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static Node[] nodeArr;
    static List<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        nodeArr = new Node[N];
        edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            nodeArr[i] = new Node(i, x, y, z);
        }

        Arrays.sort(nodeArr, (o1, o2) -> o1.x - o2.x);
        for (int i = 0; i < N - 1; i++) {
            edgeList.add(new Edge(nodeArr[i].no, nodeArr[i + 1].no, Math.abs(nodeArr[i].x - nodeArr[i + 1].x)));
        }

        Arrays.sort(nodeArr, (o1, o2) -> o1.y - o2.y);
        for (int i = 0; i < N - 1; i++) {
            edgeList.add(new Edge(nodeArr[i].no, nodeArr[i + 1].no, Math.abs(nodeArr[i].y - nodeArr[i + 1].y)));
        }

        Arrays.sort(nodeArr, (o1, o2) -> o1.z - o2.z);
        for (int i = 0; i < N - 1; i++) {
            edgeList.add(new Edge(nodeArr[i].no, nodeArr[i + 1].no, Math.abs(nodeArr[i].z - nodeArr[i + 1].z)));
        }

        Collections.sort(edgeList, (o1, o2) -> o1.value - o2.value);
    }

    private static void solve() {
        UnionFind unionFind = new UnionFind(N);
        int count = 0;
        int mstCost = 0;
        for (Edge edge : edgeList) {
            if (!unionFind.connected(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                mstCost += edge.value;
                count += 1;
            }

            if (count == N - 1) {
                break;
            }
        }
        if (N == 1) {
            mstCost = 0;
        }
        System.out.println(mstCost);
    }
}
