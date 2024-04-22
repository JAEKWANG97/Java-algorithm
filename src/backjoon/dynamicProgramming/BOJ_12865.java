package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] w;
    static int[] v;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        w = new int[N];
        v = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dp() {
        int[][] pack = new int[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            // 배낭 용량을 점점 늘려가며 순회
            for (int j = 0; j <= K; j++) {
                if (i == 0 || j == 0) {
                    pack[i][j] = 0;
                } else if (w[i - 1] <= j) {
                    // 형재 짐 무게가 배낭 용량 이내인 경우 최대가격 계산
                    //현재 짐 가격 + 이전 짐의 현재 짐 무게를 뺸 용량의 가격
                    pack[i][j] = Math.max(v[i - 1] + pack[i - 1][j - w[i - 1]], pack[i - 1][j]);

                } else {
                    // 용량을 넘어선 경우 이전 짐의 가격을 그대로 이관
                    pack[i][j] = pack[i - 1][j];
                }
            }
        }
        System.out.println(pack[N][K]);
    }
}
