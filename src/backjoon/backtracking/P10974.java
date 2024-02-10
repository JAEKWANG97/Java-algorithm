package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10974 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        Permutation(new boolean[N], 0, new int[N]);
        System.out.println(sb.toString());

    }

    static void Permutation(boolean[] visited, int count, int[] permutation) {
        if (count == N) {
            for (int i : permutation) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            permutation[count] = i + 1;
            visited[i] = true;
            Permutation(visited, count + 1, permutation);
            visited[i] = false;
        }
    }
}
