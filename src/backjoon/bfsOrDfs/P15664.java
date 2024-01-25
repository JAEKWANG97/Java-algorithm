package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P15664 {
    static List<Integer> answer;
    static List<Integer> arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);

        answer = new ArrayList<>();
        backtracking(0, 0, N, M);
        System.out.println(sb);
    }

    public static void backtracking(int start, int depth, int N, int M) {
        if (depth == M) {
            for (int num : answer) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int lastNum = -1; // 이전에 추가된 숫자를 추적하기 위한 변수
        for (int i = start; i < N; i++) {
            int currentNum = arr.get(i);
            if (currentNum != lastNum) {
                answer.add(currentNum);
                backtracking(i + 1, depth + 1, N, M);
                answer.remove(answer.size() - 1);
                lastNum = currentNum;
            }
        }
    }
}
