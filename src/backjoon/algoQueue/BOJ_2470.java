package backjoon.algoQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int left = 0;
        int right = n - 1;
        StringBuilder sb = new StringBuilder();
        long minResult = Long.MAX_VALUE;
        while (left < right) {
            long curResult = arr[left] + arr[right];
            long min = Math.min(arr[left], arr[right]);
            long max = Math.max(arr[left], arr[right]);
            if (curResult == 0) {
                sb = new StringBuilder();
                sb.append(min).append(" ").append(max);
                break;
            } else if (Math.abs(curResult) < minResult) {
                minResult = Math.abs(curResult);
                sb = new StringBuilder();
                sb.append(min).append(" ").append(max);
            }

            if (curResult > 0) {
                right -= 1;
            } else {
                left += 1;
            }
        }
        System.out.println(sb);
    }
}
