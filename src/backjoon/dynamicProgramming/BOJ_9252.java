package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9252 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String str1;
    private static String str2;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        str1 = br.readLine();
        str2 = br.readLine();
    }

    private static void solution() {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = len1, j = len2;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        sb.reverse();
        System.out.println(dp[len1][len2]);
        System.out.println(sb.toString());
    }
}
