package leetcode.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class ValidParentheses {

    private static List<Character> open = new ArrayList<>(Arrays.asList('(', '{', '['));
    private static List<Character> close = new ArrayList<>(Arrays.asList(')', '}', ']'));

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (open.contains(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty())
                    return false;
                if (close.indexOf(s.charAt(i)) != open.indexOf(stack.pop()))
                    return false;
            }
        }

        if (!stack.isEmpty())
            return false;

        return true;
    }
}
