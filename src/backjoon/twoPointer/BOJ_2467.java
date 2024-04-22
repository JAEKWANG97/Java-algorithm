package backjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2467 {
    private static int N;
    private static Long[] arr;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;


    public static void main(String[] args) {

    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new Long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
    }

    private static void solve() {
        Long sum = Long.MAX_VALUE;

        int left = 0;
        int right = N - 1;

        while (left <= right) {

            if (Math.abs(arr[left] + arr[right]) > 0) {

            }
        }
    }

}
// 산성 용액의 특성값은 1부터 1_000_000_000
// 알칼리성 용액의 특성값은 -1부터 -1_000_000_000

// 0에 가까운 값을 만드려고 함