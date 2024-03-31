package backjoon.binarySearch;

import java.io.*;
import java.util.*;

public class BOJ_3020 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, H; // N은 장애물의 총 개수, H는 동굴의 높이
    static int[] up, down; // 'up'은 석순, 'down'은 종유석의 높이 정보

    public static void main(String[] args) throws IOException {
        init(); // 입력을 받는 부분

        Arrays.sort(up);
        Arrays.sort(down);

        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= H; i++) {
            int total = 0;
            total += N / 2 - Arrays.binarySearch(up, i);
            total += N / 2 - Arrays.binarySearch(down, H - i + 1);
            if (total < min) {
                min = total;
                count = 1;
            } else if (total == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[N / 2];
        down = new int[N / 2];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                down[i / 2] = Integer.parseInt(br.readLine());
            } else {
                up[i / 2] = Integer.parseInt(br.readLine());
            }
        }
    }
}