package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15650 {
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visited = new boolean[N + 1];

        backtracking(1,0, N, M);
        System.out.println(sb);
    }

    public static void backtracking(int start , int depth, int N, int M) {

        if (depth == M) {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = start; i <= N; i++) {
            visited[i] = true;
            arr[depth] = i;
            backtracking(i ,depth + 1, N, M);
            visited[i] = false;
        }


    }

}
