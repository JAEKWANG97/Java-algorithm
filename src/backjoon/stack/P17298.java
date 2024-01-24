package backjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];

        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        int i = 0;
        while (st.hasMoreTokens()) {
            int item = Integer.parseInt(st.nextToken());
            arr[i] = (item);
            i += 1;
        }

        for (int j = 0; j < n; j++) {
            while (!(stack.empty()) && arr[stack.peek()] < arr[j]) {
                answer[stack.pop()] = arr[j];


            }


            stack.push(j);

        }

        while (!stack.empty()) {
            answer[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append(' ');
        }
        System.out.println(sb.toString().trim());


    }
}
