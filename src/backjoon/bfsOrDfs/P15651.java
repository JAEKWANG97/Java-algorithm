package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15651 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dfs(n, m, 0, new ArrayList<>());
        System.out.println(sb.toString());
    }

    static void dfs(int n, int m, int depth, List<Integer> current) {
        if (depth == m) {
            for (int num : current) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            current.add(i);
            dfs(n, m, depth + 1, current);
            current.remove(current.size() - 1);  // 백트래킹: 마지막 요소 제거
        }
    }
}
