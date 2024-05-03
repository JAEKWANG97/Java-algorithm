package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17387 {
    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node [x=" + x + ", y=" + y + "]";
        }
    }

    private static class Edge {
        Node node1;
        Node node2;

        public Edge(Node node1, Node node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        // CCW 알고리즘을 사용하여 두 선분의 교차 여부를 판단하는 메소드
        public boolean isCross(Edge other) {

            int ccw1 = ccw(node1, node2, other.node1);
            int ccw2 = ccw(node1, node2, other.node2);
            int ccw3 = ccw(other.node1, other.node2, node1);
            int ccw4 = ccw(other.node1, other.node2, node2);

            // 두 선분이 서로 교차하는 경우
            if (ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
                // 두 선분이 한 직선 위에 있는 경우
                if (ccw1 == 0 && ccw2 == 0) {
                    if (Math.max(node1.x, node2.x) >= Math.min(other.node1.x, other.node2.x) &&
                            Math.max(other.node1.x, other.node2.x) >= Math.min(node1.x, node2.x) &&
                            Math.max(node1.y, node2.y) >= Math.min(other.node1.y, other.node2.y) &&
                            Math.max(other.node1.y, other.node2.y) >= Math.min(node1.y, node2.y)) {
                        return true;
                    }
                    return false;
                }
                return true;
            }
            return false;
        }

        // CCW 결과를 계산하는 함수
        public int ccw(Node p, Node q, Node r) {
            long crossProduct = (long) (q.x - p.x) * (r.y - p.y) - (long) (q.y - p.y) * (r.x - p.x);
            if (crossProduct > 0)
                return 1; // 반시계 방향
            if (crossProduct < 0)
                return -1; // 시계 방향
            return 0; // 평행
        }

        @Override
        public String toString() {
            return "Edge [node1=" + node1 + ", node2=" + node2 + "]";
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static Node[] nodes = new Node[4];
    private static Edge[] edges = new Edge[2];

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        nodes = new Node[4];
        edges = new Edge[2];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            nodes[i + 1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            edges[i] = new Edge(nodes[i], nodes[i + 1]);
        }
    }

    private static void solve() {
        Edge A = edges[0];
        Edge B = edges[1];

        if (A.isCross(B)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}
