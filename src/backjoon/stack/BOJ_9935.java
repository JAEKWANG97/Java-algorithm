package backjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_9935 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        String target = br.readLine();
        LinkedList<Character> linkedList = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            linkedList.add(input.charAt(i));
            if (linkedList.size() >= target.length()) {
                boolean match = true;
                for (int j = 0; j < target.length(); j++) {
                    if (linkedList.get(linkedList.size() - target.length() + j) != target.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int j = 0; j < target.length(); j++) {
                        linkedList.remove(linkedList.size() - 1);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (Character ch : linkedList) {
            result.append(ch);
        }
        System.out.println(result.length() > 0 ? result.toString() : "FRULA");
    }
}
