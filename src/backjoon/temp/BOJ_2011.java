package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2011 {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
//        dfs(n, 0, new boolean[n.length() + 1]);
//        dfs(n);
        System.out.println(count);
    }

    private static void dfs(String n, int idx, boolean[] visited) {
        if (idx == n.length()) {
            count += 1;
            return;
        } else if (idx >= n.length()) {
            return;
        }

        if (visited[idx]) {
            return;
        }

        if (n.charAt(idx) == '1') {
            visited[idx] = true;
            dfs(n, idx + 1, visited);
            dfs(n, idx + 2, visited);
            visited[idx] = false;
        } else if (n.charAt(idx) == '2') {
            if (idx + 1 < n.length()) {
                if (n.charAt(idx + 1) <= '6') {
                    visited[idx] = true;
                    dfs(n, idx + 2, visited);
                    visited[idx] = false;
                }
                visited[idx] = true;
                dfs(n, idx + 1, visited);
                visited[idx] = false;
            }
        } else {
            visited[idx] = true;
            dfs(n, idx + 1, visited);
            visited[idx] = false;
        }
    }
}
