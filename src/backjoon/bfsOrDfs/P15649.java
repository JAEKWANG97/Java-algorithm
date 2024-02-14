package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15649 {

    static int n;
    static int m;

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        int count = 0;
        boolean[] visited = new boolean[n + 1];
        recursiveComb(count, arr, visited);

    }

    static void recursiveComb(int count, int[] arr, boolean[] visited) {
        sb = new StringBuilder();
        if (count == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                arr[count] = i;
                visited[i] = true;
                recursiveComb(count + 1, arr, visited);
                visited[i] = false;
            }
        }
    }


}
