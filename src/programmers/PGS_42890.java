package programmers;

import java.util.*;
import java.io.*;

class PGS_42890 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        boolean[] visited = new boolean[arr.length];
        Solution.combination(arr, visited, 0, arr.length, 3);
    }

    static class Solution {
        public int solution(String[][] relation) {
            int answer = 0;
            return answer;
        }

        private static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
            if (r == 0) {
                print(arr, visited, n);
                return;
            }
            for (int i = start; i < n; i++) {
                visited[i] = true;
                combination(arr, visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }

        private static void print(int[] arr, boolean[] visited, int n) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
        }
    }

}
