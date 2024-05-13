package swexpert.codeBattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_2948 {
    private static int T;
    private static int N;
    private static int M;
    private static int count;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static Set<String> setA;

    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            countCommonElements();
            System.out.println("#" + tc + " " + count);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        setA = new HashSet<>();
        count = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            setA.add(st.nextToken());
        }
    }

    private static void countCommonElements() throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (setA.contains(st.nextToken())) {
                count++;
            }
        }
    }
}