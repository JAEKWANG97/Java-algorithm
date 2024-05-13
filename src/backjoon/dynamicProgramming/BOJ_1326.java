package backjoon.dynamicProgramming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1326 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] stones = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stones[i] = sc.nextInt();
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        System.out.println(bfs(stones, N, a, b));
    }

    private static int bfs(int[] stones, int N, int a, int b) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[N + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);

        queue.offer(a);
        visited[a] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= N; i++) {
                int next = current + i * stones[current];
                if (next > N || next < 1 || visited[current] + 1 >= visited[next])
                    continue;
                queue.offer(next);
                visited[next] = visited[current] + 1;
            }
            for (int i = 1; i <= N; i++) {
                int next = current + i * stones[current];
                if (next > N || next < 1 || visited[current] + 1 >= visited[next])
                    continue;
                queue.offer(next);
                visited[next] = visited[current] + 1;
            }
        }

        return visited[b] == Integer.MAX_VALUE ? -1 : visited[b];
    }
}
