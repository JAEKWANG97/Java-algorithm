package backjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P11003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long N, L;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        Deque<long[]> deque = new ArrayDeque<>();
        N = Long.parseLong(st.nextToken());
        L = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (long i = 0; i < N; i++) {
            long curNum = Long.parseLong(st.nextToken());

            // 새로운 값이 들어올 때, 이 값보다 큰 값을 가진 모든 요소를 deque에서 제거
            while (!deque.isEmpty() && deque.getLast()[0] >= curNum) {
                deque.removeLast();
            }

            // 현재 숫자와 그 인덱스를 deque에 추가
            deque.addLast(new long[]{curNum, i});

            // deque의 첫 번째 요소가 현재 구간 내에 있는지 확인하고, 아니라면 제거
            while (!deque.isEmpty() && deque.getFirst()[1] <= i - L) {
                deque.removeFirst();
            }

            // 구간 내 최소값을 결과 문자열에 추가
            sb.append(deque.getFirst()[0]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
