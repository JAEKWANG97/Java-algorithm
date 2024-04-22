package backjoon.divideAndConquer;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_2749 {
    static HashMap<Long, Long> map = new HashMap<>();
    static int MOD = 1000000;

    static {
        map.put(1L, 1L);
        map.put(2L, 1L);
        map.put(3L, 2L);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        System.out.println(Fibo(n));
    }

    private static long Fibo(long n) {
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            if (n % 2 == 1) {
                map.put(n, (Fibo(n / 2) * Fibo(n / 2) + Fibo(n / 2 + 1) * Fibo(n / 2 + 1)) % MOD);
            } else {
                map.put(n, (Fibo(n + 1) - Fibo(n - 1) + MOD) % MOD);
            }
            return map.get(n);
        }
    }
}
