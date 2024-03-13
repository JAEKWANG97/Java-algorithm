package swexpert.solvingClub;

import java.io.*;
import java.util.*;

public class Solution_1238_유재광 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int L; // 입력받는 데이터의 길이
    static int START; // 시작하는 노드의 번호
    static Map<Integer, List<Integer>> map;

    static int[] visited = new int[101];

    static int maxDepth = Integer.MIN_VALUE;
    static int maxNumber = 0;

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            init();
            bfs();

            System.out.println("#" + tc + " " + maxNumber);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(st.nextToken());
        Arrays.fill(visited, -1); // 방문하지 않은 상태를 -1로 초기화
        maxDepth = Integer.MIN_VALUE;
        maxNumber = 0;
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(to);
        }
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(START);
        visited[START] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (map.get(cur) != null) {
                for (int i : map.get(cur)) {
                    if (visited[i] == -1) {
                        queue.add(i);
                        visited[i] = visited[cur] + 1;
                    }
                }

            }
        }

        for (int i = 1; i < visited.length; i++) {
            if (visited[i] > maxDepth) {
                maxDepth = visited[i];
                maxNumber = i;
            } else if (visited[i] == maxDepth) {
                maxNumber = Math.max(maxNumber, i);
            }
        }
    }
}
