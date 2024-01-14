package backjoon.string.p15649;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] arr = new int[M];
        boolean[] visited = new boolean[N + 1];
        backtracking(arr, visited, 0, N, M);
    }

    public static void backtracking(int[] arr, boolean[] visited, int depth, int N, int M) {
        if (depth == M) {
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                backtracking(arr, visited, depth + 1, N, M);
                visited[i] = false;
            }
        }
    }
}
