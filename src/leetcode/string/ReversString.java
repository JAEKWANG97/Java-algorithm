package leetcode.string;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class ReversString {
    public static void main(String[] args) {
        char[] s = new char[]{'d', 'a', 'c', 'e', 'c', 'a', 'r'};
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = ' ';
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        for (char c : s) {
            System.out.print(c);
        }
    }
}
