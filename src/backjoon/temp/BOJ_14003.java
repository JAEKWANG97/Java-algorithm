package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14003 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        int[] arr = input();
        int[] result = findLIS(arr);
        System.out.println(result.length);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] input() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    public static int[] findLIS(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];

        int n = arr.length;
        int[] tails = new int[n];
        int size = 0;

        for (int x : arr) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size)
                ++size;
        }

        int[] actualLIS = new int[size];
        for (int i = 0, j = size - 1; i < n && j >= 0; i++) {
            if (arr[i] == tails[j]) {
                actualLIS[j] = arr[i];
                j--;
            }
        }

        return actualLIS;
    }
}
