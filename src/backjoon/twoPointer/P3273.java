package backjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3273 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int count = 0;
    static int target;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int target = Integer.parseInt(br.readLine());
        int left = 0;
        int right = n - 1;
        int count = 0;
        Arrays.sort(arr);
        while (left < right) {
            if (arr[left] + arr[right] == target) {
                count++;
                right--;
                continue;
            }
            if (arr[left] + arr[right] > target) {
                right--;
                continue;
            }
            left++;
        }
        System.out.println(count);
    }

}
