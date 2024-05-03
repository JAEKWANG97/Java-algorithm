package swexpert.codeBattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if ((m & (1 << n) - 1) != (1 << n) - 1) {
                sb.append("#").append(t).append(" OFF").append("\n");

            } else {
                sb.append("#").append(t).append(" ON").append("\n");
            }

        }
        System.out.println(sb);
    }
}
