package backjoon.combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S;

    static int count;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            bitmaskingCombi(i);
        }
        System.out.println(count);

    }

    static void bitmaskingCombi(int r) {
        int allSets = 1 << N;
        for (int i = 1; i < allSets; i++) {
            if (Integer.bitCount(i) == r) { //i의 비트  i의 비트 중 1의 개수가 r과 같으면, 즉 r개의 원소를 포함하는 조합이면
                calCombination(i, N); // 조합 출력

            }
        }

    }

    private static void calCombination(int set, int n) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if ((set & (1 << i)) != 0) { // i번째 비트가 1이면, 해당 원소가 조합에 포함됨
                sum += numbers[i];
            }
        }
        if (sum == S) {
            count++;
        }
    }

}
