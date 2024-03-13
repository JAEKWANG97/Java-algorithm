package swexpert.solvingClub;

import java.io.*;
import java.util.*;

public class P3289 {

    static class Node {
        int data;
        Node parent; // 이 노드의 대표자를 가리키는 참조

        public Node(int data) {
            this.data = data;
            this.parent = this; // 초기에는 자기 자신이 대표자
        }

        // 대표자를 찾는 메소드 (경로 압축 적용)
        public Node find() {
            if (this.parent != this) {
                this.parent = this.parent.find(); // 경로 압축
            }
            return this.parent;
        }

        // 이 노드의 집합과 다른 노드의 집합을 합치는 메소드
        public void union(Node other) {
            Node thisRep = this.find();
            Node otherRep = other.find();

            if (thisRep != otherRep) {
                otherRep.parent = thisRep; // 다른 노드의 대표자를 이 노드의 대표자로 업데이트
            }
        }

        public boolean isSame(Node other) {
            return this.find() == other.find();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 노드의 개수
            int M = Integer.parseInt(st.nextToken()); // 연산의 개수

            Node[] nodes = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new Node(i);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int operation = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (operation == 0) { // Union 연산
                    nodes[a].union(nodes[b]);
                } else if (operation == 1) { // Find 연산 (같은 집합에 속해 있는지 확인)
                    sb.append(nodes[a].isSame(nodes[b]) ? "1" : "0");
                }
            }
            System.out.println(sb);
        }
    }
}
