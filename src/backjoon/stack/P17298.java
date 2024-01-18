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

        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        while (st.hasMoreTokens()) {
            int item = Integer.parseInt(st.nextToken());
            stack.push(item);
        }

        
    }
}
