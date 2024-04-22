package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2295 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        N = input();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = input();
        }


    }

    private static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }
}
