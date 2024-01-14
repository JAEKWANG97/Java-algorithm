package backjoon.string.p4889;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = 1;

        while (true) {
            String s = br.readLine();
            Stack<Character> stack = new Stack<>();
            if (s == null || s.contains("-")) {
                break;
            }
            int count = 0;
            for (int i = 0; i < s.length(); i++) {

                char ch = s.charAt(i);
                if (ch == '{') {
                    stack.push(ch);
                } else {
                    if (stack.isEmpty()) {
                        count += 1;
                        stack.push('{');
                    } else {
                        stack.pop();
                    }

                }


            }
            count += stack.size() / 2;

            System.out.println(number + ". " + count);
            number += 1;
        }

    }


}
