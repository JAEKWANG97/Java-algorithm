package backjoon.daijikstra;

import java.io.*;
import java.util.*;

public class P10282 {
    static class Computer implements Comparable<Computer> {
        int id, time;

        public Computer(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            List<List<Computer>> adjList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adjList.get(b).add(new Computer(a, s));
            }

            dijkstra(adjList, N, C);
        }
    }

    private static void dijkstra(List<List<Computer>> adjList, int N, int start) {
        PriorityQueue<Computer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Computer(start, 0));
        int[] time = new int[N + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[start] = 0;
        int count = 0;
        int lastTime = 0;
        while (!priorityQueue.isEmpty()) {
            Computer cur = priorityQueue.poll();

            if (time[cur.id] > cur.time) {
                continue;
            }
            count++;
            lastTime = Math.max(lastTime, cur.time);


        }
    }
}
