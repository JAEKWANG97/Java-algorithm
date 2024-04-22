package backjoon.bruteForceSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_27172 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, MAX;
    static int[] cards;

    static HashMap<Integer, Integer> map;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()), X[] = new int[N], INF = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            INF = Math.max(INF, X[i] = Integer.parseInt(st.nextToken()));
        }

        int[] P = new int[N + 1], pos = new int[INF + 1];
        for (int i = 0; i < N; i++) {
            pos[X[i]] = i + 1;
        }

        for (int mod : X) {
            for (int i = mod * 2; i <= INF; i += mod) {
                if (pos[i] != 0) {
                    P[pos[i]]--;
                    P[pos[mod]]++;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            sb.append(P[i]).append(" ");
        }
        System.out.print(sb);
    }
}
