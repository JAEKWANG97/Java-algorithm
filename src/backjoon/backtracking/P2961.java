package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2961 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int minScore = Integer.MAX_VALUE;

    static int[][] flavors;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        flavors = new int[2][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            flavors[0][i] = Integer.parseInt(st.nextToken());
            flavors[1][i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(minScore);
    }


}
