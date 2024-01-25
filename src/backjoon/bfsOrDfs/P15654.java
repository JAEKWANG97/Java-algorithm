package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P15654 {
    static int[] answer;
    static List<Integer> arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>();
        answer = new int[M];
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);

        visited = new boolean[N + 1];
        backtracking(1, 0, N, M, arr);
        System.out.println(sb);
    }

    public static void backtracking(int start, int depth, int N, int M, List<Integer> arr) {
        if (depth == M) {
            for (int num : answer) {
                sb.append(num).append(" ");
            }
            sb.append("\n");

            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[depth] = arr.get(i - 1);
                backtracking(i, depth + 1, N, M, arr);
                visited[i] = false;
            }
        }
    }
}
