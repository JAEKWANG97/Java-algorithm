package backjoon.string;

import java.util.Scanner;

public class P10808 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[26];

        String s = sc.nextLine();
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a'] += 1;
        }

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
