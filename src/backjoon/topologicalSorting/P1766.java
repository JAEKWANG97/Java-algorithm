package backjoon.topologicalSorting;

import java.io.*;
import java.util.*;

public class P1766 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;

    static int[] inNodeArr;
    static List<List<Integer>> outNodeList;

    // 위상 정렬인데 먼저 오는 순서가 없다면 오름차순
    public static void main(String[] args) throws IOException {
        initVariables();
        topologicalSort();
        System.out.println(sb.toString());
    }

    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inNodeArr = new int[N + 1];
        outNodeList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            outNodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int outNode = Integer.parseInt(st.nextToken());
            // 진출 노드 기준
            // 노드에서 다음 노드로 갈.
            outNodeList.get(node).add(outNode);
            // 진입 노드 기준
            // 노드에서 선행 되는 노드가 있는가?
            inNodeArr[outNode] += 1;
        }
    }

    private static void topologicalSort() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (inNodeArr[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");
            for (int n : outNodeList.get(cur)) {
                inNodeArr[n] -= 1;
                if (inNodeArr[n] == 0) {
                    queue.add(n);
                }
            }
        }

    }


}
