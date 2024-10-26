package backjoon.temp;

import java.io.*;
import java.util.*;

public class BOJ_3079 {
    static class Node implements Comparable<Node> {
        int time;
        int totalTime;

        public Node(int time, int totalTime) {
            this.time = time;
            this.totalTime = time;
        }

        @Override
        public int compareTo(Node o) {
            return o.time + o.totalTime - this.time - this.totalTime;
        }

    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M;
    private static PriorityQueue<Node> T;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        T = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int time = Integer.parseInt(br.readLine());
            T.add(new Node(time, 0));
        }

        for (int i = 0; i < M; i++) {

            Node curNode = T.poll();
            int curTime = curNode.time;
            int curTotalTime = curNode.totalTime + curTime;
            System.out.println(curTotalTime);
            T.add(new Node(curTime, curTotalTime));

        }

    }
}
