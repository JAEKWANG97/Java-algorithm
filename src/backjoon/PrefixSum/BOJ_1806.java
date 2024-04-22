package backjoon.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S;
    static long[] prefixSum;

    public static void main(String[] args) throws IOException {
        init();
        searchSize();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        prefixSum = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long item = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + item;
        }
    }

    private static void searchSize() {
        int start = 0, end = 1;
        int minSize = Integer.MAX_VALUE;

        while (end <= N && start <= end) {
            long sum = prefixSum[end] - (start == 0 ? 0 : prefixSum[start - 1]);

            if (sum >= S) {
                minSize = Math.min(minSize, end - start);
                start++;
            } else {
                end++;
            }
        }

        System.out.println(minSize == Integer.MAX_VALUE ? 0 : minSize + 1);
    }
}
