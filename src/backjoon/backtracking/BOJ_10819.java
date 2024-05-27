package backjoon.backtracking;

import java.util.Scanner;

public class BOJ_10819 {
    static int[] arr;
    static boolean[] visited;
    static int n;
    static int max = Integer.MIN_VALUE;
    static int[] permutation;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        permutation = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        dfs(0);
        System.out.println(max);
    }

    public static void dfs(int depth) {
        if (depth == n) {
            cal();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void cal() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(permutation[i] - permutation[i + 1]);
        }
        max = Math.max(max, sum);
    }
}
