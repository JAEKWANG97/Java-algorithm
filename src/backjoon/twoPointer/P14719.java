package backjoon.twoPointer;

import java.io.*;
import java.util.*;

public class P14719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int H, W;
    static int[] arr;
    static int volume = 0;

    public static void main(String[] args) throws IOException {
        initVariables();
        calculateVolume();
        System.out.println(sb.toString());
    }

    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void calculateVolume() {

        int left = 0;
        int right = W - 1;
        int leftMax = arr[left];
        int rightMax = arr[right];

        //투 포인터가 서로 겹칠 때 까지 반복
        while (left < right) {
            leftMax = Math.max(arr[left], leftMax);
            rightMax = Math.max(arr[right], rightMax);

            // 더 높은 쪽을 향해 투 포인터 이동
            if (leftMax <= rightMax) {
                // 왼쪽 막대 최대 높이와의 차이를 더하고 왼쪽 포인터 이동
                volume += leftMax - arr[left];
                left += 1;
            } else {
                volume += rightMax - arr[right];
                right -= 1;
            }
        }
        sb.append(volume);
    }
}