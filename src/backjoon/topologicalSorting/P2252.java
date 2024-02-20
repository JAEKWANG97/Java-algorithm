package backjoon.topologicalSorting;

import java.io.*;
import java.util.*;

public class P2252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] inCount;
    static List<Integer>[] outList;

    public static void main(String[] args) throws IOException {
        initVariables();
        bfs();
        System.out.println(sb.toString());
    }

    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inCount = new int[N + 1];
        outList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            outList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            inCount[value] = inCount[value] + 1;
            outList[key].add(value);
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inCount[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");
            for (int number : outList[cur]) {
                inCount[number] -= 1;
                if (inCount[number] == 0) {
                    queue.offer(number);
                }
            }
        }
    }
}
