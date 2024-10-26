package backjoon.binarySearch;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1300 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, K;

    private static long answer;

    public static void main(String[] args) throws Exception {
        init();
        solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        answer = 0;
    }


    private static void solution() {
        long left = 0;
        long right = (long) N * N;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (count < K) {
                left = mid + 1;
            }
            if (count > K) {
                right = mid - 1;
                answer = mid;
            }
        }
    }
}
