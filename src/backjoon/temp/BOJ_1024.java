package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1024 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        Long N = Long.parseLong(input[0]);
        Long L = Long.parseLong(input[1]);

        boolean found = false;
        for (Long i = L; i <= 100 && !found; i++) {
            Long temp = N - i * (i - 1) / 2;
            if (temp >= 0 && temp % i == 0) {
                Long start = temp / i;
                for (Long j = 0L; j < i; j++) {
                    System.out.print((start + j) + " ");
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("-1");
        }
    }
}
